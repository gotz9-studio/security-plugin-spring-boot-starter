package com.gotz9.plugin.security.core.subject;

public class SysRole {

    private long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色验证相关字段
     */
    private String authority;

    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
