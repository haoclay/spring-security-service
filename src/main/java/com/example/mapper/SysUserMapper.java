package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.SysUser;
import org.apache.ibatis.annotations.Insert;

/**
 * @author : SGQ
 * @date : 2022-06-15 17:12
 **/
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getByUsername(String username);

    // 插入用户
    @Insert("insert into sys_user(id ,username, password, status ,password_non_expired ) value(null,#{username}, #{password}, 1 , 1)")
    int insertSysUser(SysUser sysUser);
}