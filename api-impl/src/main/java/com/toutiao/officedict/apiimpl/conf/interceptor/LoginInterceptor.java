package com.toutiao.officedict.apiimpl.conf.interceptor;

import com.alibaba.fastjson.JSON;
import com.toutiao.officedict.apiimpl.authentication.IgnoreLogin;
import com.toutiao.officedict.apiimpl.authentication.RedisSession;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.common.util.crypto.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * zhangjinglei 2017/8/31 上午10:50
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisSession redisSession;

    @Autowired
    private LoginAndPermissionConfig config;
    @Autowired
    private LoginAndPermissionConfig loginAndPermissionConfig;

    private HashMap<String,String> codes=new HashMap<>();

    public LoginInterceptor( ){
//        List<SysPermissionInfo> sysPermissionInfos = sysPermissionCoreService.selectAll();
//        for(SysPermissionInfo info:sysPermissionInfos){
//            codes.put(info.getId().toString(),info.getName());
//        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 本请求忽略登录验证
         */
        IgnoreLogin methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreLogin.class);
        if(methodAnnotation!=null){
            return true;
        }
        /**
         * 验证cookie
         */

        SerializableData serializableData = validCookieValue(request, config.getCookiename());

        if(serializableData==null){
            response.setContentType("application/json;charset=utf-8");
            Cookie clearcookie = new Cookie(config.getCookiename(),null);
            clearcookie.setMaxAge(0);
            clearcookie.setPath("/");
            response.addCookie(clearcookie);

            PrintWriter writer = response.getWriter();
            writer.print(JSON.toJSONString(NashResult.Fail("no-login","没有登录")));
            writer.flush();
            return false;
        }
        request.setAttribute("_serializabledata_", serializableData);
        return true;
//        else {
//            /**
//             *
//             */
//            if(!redisSession.hasLogin(serializableData.getUserNo())){
//                response.setContentType("application/json;charset=utf-8");
//                PrintWriter writer = response.getWriter();
//                writer.print(JSON.toJSONString(NashResult.Fail("no-login","没有登录")));
//                writer.flush();
//                return false;
//            }
//            request.setAttribute("_serializabledata_", serializableData);
//
//        }
        //todo 暂时注释

//        Permission permissionAnnotation = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
//        if(permissionAnnotation==null){
//            throw new BaseException("no-permission","没有开放此功能，请联系管理员。");
//        }
//        else {
//            if(!permissionAnnotation.ignore()){
//                SerializableData serializabledata_ = (SerializableData) request.getAttribute("_serializabledata_");
//                if(serializabledata_==null){
//                    throw new BaseException("no-permission","请选择角色");
//                }
//                String tip="";
//                for(String annotationCode :permissionAnnotation.value()){
//                    if(serializabledata_.getPermissionCodes().contains(Integer.parseInt(annotationCode))){
//                        return true;
//                    }
//                    String permissionname = codes.get(annotationCode.trim());
//                    if(StringUtils.isNotBlank(permissionname)){
//                        tip += permissionname+"，";
//                    }
//                }
//                if(StringUtils.isNotBlank(tip)){
//                    tip="你缺少权限："+tip;
//                }
//                else {
//                    tip="你没有权限使用此功能";
//                }
//                throw new BaseException("no-permission",tip);
//            }
//            else {
//                return true;
//            }
//
//        }

//        return true;
    }

    private SerializableData validCookieValue(HttpServletRequest request,String cookieName){
        cookieName=cookieName.trim();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    try {
                        String cookievalue = AES.decrypt2(c.getValue(), "apollocrm1234567");
                        SerializableData serializableData = SerializableData.fromString(cookievalue);
                        if(!serializableData.checkVersion()) {
                            return null;
                        }

                        return serializableData;
                    }
                    catch (Exception e){
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
