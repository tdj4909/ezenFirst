package com.a2a2lab.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginSessionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		관리자용
		if(request.getRequestURI().contains("Xdm")) {
			if (request.getSession().getAttribute("user") != null) {
				// by pass
				return true;
			} else {
				response.sendRedirect("/Xdm/loginXdm");
		        return false;
			}
		} else {
			//by pass
			if (request.getSession().getAttribute("user") != null) {
				// by pass
				return true;
			} else {
				response.sendRedirect("/TableOrder/accountLogin");
		        return false;
			}
		}
		
	}
}
