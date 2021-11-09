package com.scsb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.scsb.config.Constants;
import com.scsb.model.Manager;

@Component
public class NavbarInterceptor implements HandlerInterceptor 
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
    {
    	// 取得該會員
    	Manager user = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
    	if (user == null)
    	{
    		response.sendRedirect("/scsb-springboot-0.0.3/login");
    		return false;
    	}

        return true;
    }
}