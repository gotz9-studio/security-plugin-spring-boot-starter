package com.gotz9.plugin.security.core.subject;

public class SysMenu {

    private long id;

    private long parent;

    /**
     * 菜单项名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
