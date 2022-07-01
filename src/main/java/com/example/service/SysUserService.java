package com.example.service;

import com.example.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author : SGQ
 * @date : 2022-06-15 17:11
 **/
public interface SysUserService extends UserDetailsService {

    void save(SysUser user);

    List<SysUser> findAll();

    boolean addSysUser(SysUser user);

}
