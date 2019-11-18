/**
 * projectName: labelDatabase
 * fileName: MyFilter.java
 * packageName: com.fc.aden.shiro.service
 * date: 2019-09-23
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.shiro.service;

import com.alibaba.fastjson.JSONObject;
import com.fc.aden.common.security.EncryptionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.velocity.runtime.directive.Foreach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: MyFilter
 * @packageName: com.fc.aden.shiro.service
 * @description: 自定义shiro过滤器
 * @data: 2019-09-23
 **/
public class MyFilter extends AccessControlFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);


    /**
     * 判断是否拦截，false为拦截，true为允许
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object o) throws Exception {

        return false;
    }


    /**
     * 上面的方法返回false后(被拦截)，会进入这个方法；这个方法返回false表示处理完毕(不放行)；返回true表示需要继续处理(放行)
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse servletResponse) throws Exception {

        Subject subject = getSubject(request, servletResponse);
        String url = getPathWithinApplication(request);
//        log.info("当前用户正在访问的url为 " + url);
//        log.info("subject.isPermitted(url);" + subject.isPermitted(url));
        //可自行根据需要判断是否拦截，可以获得subject判断用户权限，也可以使用req获得请求头请求体信息
        //return true;

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String type = "";
        try {
            type = httpRequest.getHeader("type");
//            log.info("type："+type);

        } catch (Exception e) {
            type = null;
        }
        if (type != null && type.equals("app")) {
            String username = httpRequest.getHeader("username");
            String timeStamp = httpRequest.getHeader("timeStamp");
            String encryption = httpRequest.getHeader("encryption");
            boolean result = EncryptionUtils.verify(username, timeStamp, encryption);


            if (result){
                return true;
            }else {
                return false;
            }
        }

        return true;
    }
}
