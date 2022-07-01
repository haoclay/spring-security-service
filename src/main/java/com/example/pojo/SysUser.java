package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : SGQ
 * @date : 2022-06-15 16:59
 **/
@Data
public class SysUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Boolean status; //用户状态，1-开启-0禁用
    private Boolean passwordNonExpired; //密码是否失效，1-可用，0-失效
    /**
     * 用户关联的所有角色
     */
    private List<SysRole> roles = new ArrayList<>();

//get、set方法

    //标记该字段不做json处理
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return passwordNonExpired == null ? false : passwordNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return status == null ? false : status;
    }

}
