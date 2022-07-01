package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.SysRoleMapper;
import com.example.mapper.SysUserMapper;
import com.example.pojo.SysRole;
import com.example.pojo.SysUser;
import com.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : SGQ
 * @date : 2022-06-15 17:11
 **/
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Integer INSERT_SUCCESS = 1;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void save(SysUser sysUser) {
        // 将密码加密入库
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserMapper.insert(sysUser);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public boolean addSysUser(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return sysUserMapper.insertSysUser(user) == INSERT_SUCCESS ;
    }

    /**
     * 认证业务
     *
     * @param username
     *            - 用户在浏览器输入的用户名
     * @return UserDetails - Spring Security的用户对象，返回 null表示认证失败！
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 用户信息和角色信息可以一步关联查询到位得到SysUser，我这里分开查询
         */
        // 1.查询用户
        SysUser sysUser = sysUserMapper.getByUsername(username);
        if (sysUser == null) {
            return null;
        }
        request.getSession().setAttribute("user",sysUser);

        System.out.println("我在做验证...");
        // 2.获取用户关联的所有角色
       List<SysRole> sysRoles = sysRoleMapper.listAllByUserId(sysUser.getId());
        /* sysUser.setRoles(sysRoles);
        System.out.println("====> sysUser=" + sysUser.toString());
        return sysUser;*/

        //给用户授权，ROLE_USER表示是USER权限而不是ROLE_USER权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(sysUser.getUsername(),sysUser.getPassword(),sysRoles);

    }
}
