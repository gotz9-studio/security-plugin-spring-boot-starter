package com.gotz9.plugin.security.core.storage;

import com.gotz9.plugin.security.core.subject.SysMenu;
import com.gotz9.plugin.security.core.subject.SysPermission;
import com.gotz9.plugin.security.core.subject.SysRole;
import com.gotz9.plugin.security.core.subject.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface UserResourceRepository {

    SysUser selectUserByUsername(String username);

    Set<SysRole> loadRoleByUserId(long id);

    Set<SysPermission> loadPermissionByUserId(long id);

    Set<SysMenu> loadMenuByUserId(long id);

}
