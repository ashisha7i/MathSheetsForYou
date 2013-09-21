package com.mathsheetsforyou;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import com.mathsheetsforyou.constants.Constant;

public class AuthFilter implements Filter {

	Logger LOGGER = Logger.getLogger("AuthFilter");
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession();
			
		UserService userService = UserServiceFactory.getUserService();
		String userName = "Guest";
		String userGreeting = "Welcome !!";
		
		String thisURL = request.getRequestURI();

		if (request.getUserPrincipal() != null) {
			userName = userService.getCurrentUser().getEmail();
			userGreeting = "Welcome " + userName;
		} else {
			userGreeting = "Please sign in " + userName;
		}
		
		System.out.println(userGreeting);

		request.getSession().setAttribute("USER_NAME", userName);
		request.getSession().setAttribute("USER_GREETING", userGreeting);
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
