package com.security.controller;

import com.security.core.properties.SecurityProperties;
import com.security.support.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     *
     * 处理跳转
     * @author:
     * @date:       2019年1月28日 下午1:59:14
     * @version:	v1.0
     * @return String
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     *
     * 处理跳转
     * @author:
     * @date:       2019年1月28日 下午1:59:14
     * @version:	v1.0
     * @return String
     */
    @RequestMapping("/authentication/require")
    @ResponseBody
    public SimpleResponse require(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (null != savedRequest) {
            String redirectUrl = savedRequest.getRedirectUrl();
            System.out.println("引发跳转的url是：" + redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
//            if (StringUtils.endsWithIgnoreCase(redirectUrl, "error")) {
//                redirectStrategy.sendRedirect(request, response, redirectUrl);
//            }
        }

        return new SimpleResponse(HttpStatus.UNAUTHORIZED, "访问的服务需要身份认证，请引导用户到登录页");
    }

}