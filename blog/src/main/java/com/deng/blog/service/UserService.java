package com.deng.blog.service;

import com.deng.blog.po.User;

/**
 * created by deng on 2020-11-18
 **/
public interface UserService {

    User checkUser(String username, String password);
}
