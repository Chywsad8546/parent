package com.toutiao.officedict.apiimpl.controller.sys;

import com.toutiao.officedict.apiimpl.authentication.IgnoreLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WuShoulei
 * @Date 2018/11/19 20:00
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    @IgnoreLogin
    public String homeIndex() {
        return "/mvc/index.html";
    }

    @RequestMapping("/index")
    @IgnoreLogin
    public String homeIndex2() {
        return "/mvc/index.html";
    }
}
