package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.SysRole;

import java.util.List;

/**
 * @author : SGQ
 * @date : 2022-06-15 17:13
 **/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> listAllByUserId(Long id);

}