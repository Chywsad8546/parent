package com.toutiao.officedict.apiimpl.controller;

import com.toutiao.officedict.apiimpl.authentication.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WuShoulei on 2017/12/4
 */
@RestController
public class BaseController {

    /**
     * 获取当前登录用户信息
     * @return
     */
    @ModelAttribute("user")
    public User getCurrentUser(){
        User user = User.getCurrent();
        return user;
    }
}
