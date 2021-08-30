package com.gotz9.plugin.security.core.auth;

import com.gotz9.plugin.security.core.storage.ResourceMapper;
import com.gotz9.plugin.security.core.subject.SysMenu;
import com.gotz9.plugin.security.core.subject.SysPermission;
import com.gotz9.plugin.security.core.subject.SysRole;
import com.gotz9.plugin.security.core.subject.SysUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseUserDetailsService implements UserDetailsService {

    private final ResourceMapper resourceMapper;

    public DatabaseUserDetailsService(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = resourceMapper.selectUserByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException(username);

        // 加载权限
        Set<SysRole> roles = resourceMapper.loadRoleByUserId(user.getId());
        Set<SysPermission> permissions = resourceMapper.loadPermissionByUserId(user.getId());

        Stream<SimpleGrantedAuthority> roleStream = roles.stream().map(sysRole -> new SimpleGrantedAuthority("ROLE_" + sysRole.getAuthority()));
        Stream<SimpleGrantedAuthority> permissionStream = permissions.stream().map(sysPermission -> new SimpleGrantedAuthority(sysPermission.getAuthority()));

        Set<SimpleGrantedAuthority> authorities = Stream.concat(roleStream, permissionStream).collect(Collectors.toSet());

        // 加载菜单
        Set<SysMenu> menus = resourceMapper.loadMenuByUserId(user.getId());

        return new SysUserDetails(username, user.getPassword(), authorities, menus);
    }

}
