package com.toutiao.officedict.apiimpl.controller.auth;


import com.toutiao.officedict.api.chance.response.SysUserResponse;
import com.toutiao.officedict.apiimpl.authentication.IgnoreLogin;
import com.toutiao.officedict.apiimpl.authentication.Permission;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.apiimpl.conf.interceptor.LoginAndPermissionConfig;
import com.toutiao.officedict.common.authentication.menu;
import com.toutiao.officedict.common.constant.resultcode.CommonResultCode;
import com.toutiao.officedict.common.exceptions.NashRequestException;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.common.staticonst.City;
import com.toutiao.officedict.common.util.NashBeanUtils;
import com.toutiao.officedict.common.util.crypto.AES;
import com.toutiao.officedict.common.util.officedict.CityMap;
import com.toutiao.officedict.dao.entity.admin.SysMenuEntity;
import com.toutiao.officedict.dao.entity.admin.SysRoleEntity;
import com.toutiao.officedict.dao.entity.admin.SysUserEntity;
import com.toutiao.officedict.service.repository.admin.SysMenuService;
import com.toutiao.officedict.service.repository.admin.SysRoleService;
import com.toutiao.officedict.service.repository.admin.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class Login {

    @Autowired
    private LoginAndPermissionConfig loginAndPermissionConfig;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = {"/userlogin"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @IgnoreLogin
    @Permission(ignore = true)
    public NashResult login(@RequestParam(value = "loginName", required = false) String loginName, @RequestParam(value = "password", required = false) String password, HttpServletResponse response) {

        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            throw new NashRequestException(CommonResultCode.COMM_ARG_ILLEGAL.getInfoCode(), "登录信息不能为空");
        }

        SysUserEntity sysUser = sysUserService.selectByLoginName(loginName.trim());
        if (sysUser == null) {
            return NashResult.Fail("提示", "请先开通此账号");
        }
        SysUserResponse sysUserResponse = checkLoginNamePassword(loginName, password);
        if (sysUserResponse.getPassword() == null) {
            return NashResult.Fail("提示", "用户名/密码,错误");
        }
        SerializableData d = new SerializableData();
        d.setUserId(sysUser.getId());
        d.setUserName(sysUser.getUserName());
        d.setVersion(SerializableData.GLOBAL_VERSION);
        d.setCityId(sysUser.getCityId());
        List<SysRoleEntity> sysRoleEntities = sysRoleService.loginRoleList(d.getUserId());
        SysRoleEntity currentRole = new SysRoleEntity();
        Integer currentRoleID = 0;
        for (SysRoleEntity item : sysRoleEntities) {
            currentRoleID = item.getId();
            currentRole = item;
            break;
        }
        d.setCurrentRole(currentRole);

        List<SysMenuEntity> sysMenuEntities = sysMenuService.selectByRoleID(currentRoleID);
        List<menu> menus = new ArrayList<>();
        for (SysMenuEntity item : sysMenuEntities) {
            menu m = new menu();
            NashBeanUtils.copyProperties(m, item);
            menus.add(m);
        }
        d.setMenus(menus);
        String cookievalue = AES.encrypt2(d.toJson(), "apollocrm1234567");
        Cookie cookie = new Cookie(loginAndPermissionConfig.getCookiename(), cookievalue);
        cookie.setPath("/");
        response.addCookie(cookie);
        return NashResult.build(0);
    }

    @RequestMapping(value = {"/logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @Permission(ignore = true)
    public NashResult logout(HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        Cookie clearcookie = new Cookie(loginAndPermissionConfig.getCookiename(), null);
        clearcookie.setMaxAge(0);
        clearcookie.setPath("/");
        response.addCookie(clearcookie);
        return NashResult.build(1);

    }

    @RequestMapping(value = {"/changeCity"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @Permission(ignore = true)
    public NashResult changeCity(@RequestParam(value = "cityId", required = false, defaultValue = "12") Integer cityId, HttpServletResponse response) throws Exception {
        SerializableData serializableData = User.getCurrent().getSerializableData();
        serializableData.setCityId(cityId);
        serializableData.setCityName(CityMap.getCityCNName(cityId));
        String cookievalue = AES.encrypt2(serializableData.toJson(), "apollocrm1234567");
        Cookie cookie = new Cookie(loginAndPermissionConfig.getCookiename(), cookievalue);
        cookie.setPath("/");
        response.addCookie(cookie);
        return NashResult.build(serializableData);

    }

    @RequestMapping(value = {"/getCityList"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @Permission(ignore = true)
    public NashResult getCityList() throws Exception {
         ArrayList<City> cityList = new ArrayList<>();
        cityList.add(new City(1,"石家庄","sjz"));
        cityList.add(new City(12,"北京","bj"));
        cityList.add(new City(13,"上海","sh"));
        cityList.add(new City(14,"天津","tj"));
        cityList.add(new City(15,"重庆","cq"));
        cityList.add(new City(16,"广州","gz"));
        cityList.add(new City(17,"深圳","sz"));
        cityList.add(new City(18,"珠海","zh"));
        cityList.add(new City(23,"惠州","huizhou"));
        cityList.add(new City(24,"东莞","dg"));
        cityList.add(new City(26,"杭州","hz"));
        cityList.add(new City(45,"成都","cd"));
        cityList.add(new City(55,"沈阳","sy"));
        cityList.add(new City(56,"大连","dl"));
        cityList.add(new City(65,"南京","nj"));
        cityList.add(new City(66,"无锡","wuxi"));
        cityList.add(new City(67,"苏州","suzhou"));
        cityList.add(new City(75,"福州","fz"));
        cityList.add(new City(84,"长春","changchun"));
        cityList.add(new City(103,"郑州","zz"));
        cityList.add(new City(113,"济南","jn"));
        cityList.add(new City(114,"青岛","qd"));
        cityList.add(new City(118,"烟台","yt"));
        cityList.add(new City(123,"合肥","hf"));
        cityList.add(new City(143,"海口","haikou"));
        cityList.add(new City(144,"三亚","sanya"));
        cityList.add(new City(176,"西安","xian"));
        cityList.add(new City(194,"武汉","wh"));
        cityList.add(new City(204,"长沙","cs"));
        cityList.add(new City(214,"南昌","nc"));
        return NashResult.build(cityList);
    }

    public SysUserResponse checkLoginNamePassword(String loginname, String password) {
        SysUserResponse sysUserResponse = new SysUserResponse();
        SysUserEntity sysUser = sysUserService.selectByLoginName(loginname.trim());
        if (password.equals(sysUser.getPassword())) {
            sysUserResponse.setLoginName(sysUser.getLoginName());
            sysUserResponse.setPassword(sysUser.getPassword());
        }
        return sysUserResponse;
    }


}
