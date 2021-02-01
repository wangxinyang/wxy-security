package com.security.filter;

import com.security.entity.SformDictEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
public class DefencesAttackFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String requestMethod = req.getRequestURI();//获取请求信息
        requestMethod = requestMethod.substring(0, requestMethod.lastIndexOf("/"));
        requestMethod = requestMethod.substring(requestMethod.lastIndexOf("/") + 1, requestMethod.length());
//        SformDictEntity redisRequestMethod = (SformDictEntity) RedisUtils.get("dictRedisRequestMethod");//获取redis中不进行过滤的请求
        SformDictEntity redisRequestMethod = null;
        try {
            if(redisRequestMethod == null || redisRequestMethod.getDictValue().indexOf(requestMethod) < 0 ){//对需要进行过滤的数据进行过滤
                if(redisRequestMethod != null){
                    String[] keys = req.getRequestURI().substring(0, req.getRequestURI().lastIndexOf("/")).split("/");
                    if(keys == null || keys.length == 0){
                        chain.doFilter(req, response);
                    }else{
                        Boolean bool = false;
                        for (int i = keys.length - 1; i >= 0; i--) {
                            if(StringUtils.isNotEmpty(keys[i])){
                                if(redisRequestMethod.getDictValue().indexOf(keys[i]) >= 0 ){//对需要进行过滤的数据进行过滤
                                    bool = true;
                                    break;
                                }
                            }
                        }
                        if(bool){
                            chain.doFilter(req, response);
                        }else{
                            chain.doFilter(new DefencesServletRequest(req), response);
                        }
                    }
                }else{
                    chain.doFilter(new DefencesServletRequest(req), response);
                }
            }else{
                chain.doFilter(req, response);
            }
        } catch (Exception e) {
            chain.doFilter(req, response);
        }

    }

    public void destroy() {

    }
}
