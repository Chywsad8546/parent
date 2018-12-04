package com.toutiao.officedict.apiimpl.controller.auth;

import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.common.util.officedict.CityMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginUserRestful {

    @RequestMapping(value = "/getCurrentUser")
    @ResponseBody
    public NashResult getCurrentUser() {
        Map<String, Object> res = new HashMap<>();
        res.put("user_name", User.getCurrent().getUserName());
        res.put("role_code", User.getCurrent().getSerializableData().getCurrentRole().getCode());
        res.put("role_id", User.getCurrent().getSerializableData().getCurrentRole().getId());
        res.put("role_name", User.getCurrent().getSerializableData().getCurrentRole().getName());
        res.put("city_id", User.getCurrent().getSerializableData().getCityId());
        res.put("city_name", CityMap.getCityCNName(User.getCurrent().getSerializableData().getCityId()));
        res.put("menus", User.getCurrent().getSerializableData().getMenus());
        return NashResult.build(res);
    }
}
