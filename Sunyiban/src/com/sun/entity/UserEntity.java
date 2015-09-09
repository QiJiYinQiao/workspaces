package com.sun.entity;

import com.sun.base.BaseEntity;


/**
 * @File: UserEntity.java
 * @Author: Sunyiban
 * @Version: v 1.0
 * @Date: 2015-09-01 10:51am
 * @Description: 用户类
 */
public class UserEntity extends BaseEntity {
    private String id;

    private String username;

    private String password;

    private String realname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }
}