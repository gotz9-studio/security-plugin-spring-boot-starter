package com.gotz9.plugin.security.core.auth;


import com.gotz9.plugin.security.core.subject.SysMenu;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class SysUserDetails extends User {

    private final Set<SysMenu> menus;

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     *
     * @param menus user enable menu items in frontend
     */
    public SysUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Set<SysMenu> menus) {
        super(username, password, authorities);
        this.menus = Collections.unmodifiableSet(menus);
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link DaoAuthenticationProvider}.
     *
     * @param username              the username presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param password              the password that should be presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials have not
     *                              expired
     * @param accountNonLocked      set to <code>true</code> if the account is not locked
     * @param authorities           the authorities that should be granted to the caller if they
     *                              presented the correct username and password and the user is enabled. Not null.
     * @param menus                 user enable menu items in frontend
     * @throws IllegalArgumentException if a <code>null</code> value was passed either as
     *                                  a parameter or as an element in the <code>GrantedAuthority</code> collection
     */
    public SysUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Set<SysMenu> menus) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.menus = Collections.unmodifiableSet(menus);
    }

    public Collection<SysMenu> getMenus() {
        return menus;
    }

}
