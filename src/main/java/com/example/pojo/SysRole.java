package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author : SGQ
 * @date : 2022-06-15 17:00
 **/
@Data
public class SysRole implements GrantedAuthority {

    private Long id;
    private String roleName;
    private String roleDesc;

//get、set方法

    //标记该字段不做json处理
    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }

}
