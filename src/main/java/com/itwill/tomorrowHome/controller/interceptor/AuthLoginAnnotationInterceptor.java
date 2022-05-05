package com.itwill.tomorrowHome.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLoginAnnotationInterceptor extends HandlerInterceptorAdapter {
	public AuthLoginAnnotationInterceptor() {
		System.out.println("### AuthLoginAnnotationInterceptor()생성자");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

		if (loginCheck == null) {
			return true;
		}
		
		HttpSession session = request.getSession();
		String sM_id = (String) session.getAttribute("sM_id");

		if (sM_id == null) {
			response.sendRedirect("login_form");
			return false; 
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}