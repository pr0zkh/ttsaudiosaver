package org.ttsaudiosaver.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.ttsaudiosaver.web.SessionAttributes;
import org.ttsaudiosaver.web.controller.UrlTemplate;
import org.ttsaudiosaver.web.model.User;

public class AuthenticationInterceptor implements HandlerInterceptor {
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		User user = (User)req.getSession().getAttribute(SessionAttributes.USER);
		if(!req.getRequestURI().startsWith(UrlTemplate.LOGIN) && user == null) {
			res.sendRedirect(UrlTemplate.LOGIN);
		} else if(req.getRequestURI().startsWith(UrlTemplate.LOGIN) && user != null) {
			res.sendRedirect(UrlTemplate.INDEX);
		}
		return true;
	}
}
