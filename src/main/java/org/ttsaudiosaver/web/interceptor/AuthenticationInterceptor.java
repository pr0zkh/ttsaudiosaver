package org.ttsaudiosaver.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.ttsaudiosaver.web.SessionAttributes;
import org.ttsaudiosaver.web.controller.UrlTemplate;
import org.ttsaudiosaver.web.model.User;

public class AuthenticationInterceptor implements HandlerInterceptor {
	
	private List<String> unauthorizedAccessAllowedURLs;
	
	public AuthenticationInterceptor() {
		unauthorizedAccessAllowedURLs = new ArrayList<String>();
		
		unauthorizedAccessAllowedURLs.add(UrlTemplate.SIGN_UP);
		unauthorizedAccessAllowedURLs.add(UrlTemplate.LOGIN);
		unauthorizedAccessAllowedURLs.add(UrlTemplate.FORGOT_PASSWORD);
	}
	
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
		if(req.getMethod().equalsIgnoreCase(RequestMethod.GET.name())) {
			User user = (User)req.getSession().getAttribute(SessionAttributes.USER);
			String requestURI = req.getRequestURI();
			if(user == null) {
				for(String s : unauthorizedAccessAllowedURLs) {
					if(requestURI.startsWith(s)) {
						return true;
					} 
				}
				res.sendRedirect(UrlTemplate.LOGIN);
				return false;
			} else {
				for(String s : unauthorizedAccessAllowedURLs) {
					if(requestURI.startsWith(s)) {
						res.sendRedirect(UrlTemplate.INDEX);
						return false;
					} 
				}
				return true;
			}
		}
		return true;
	}
}
