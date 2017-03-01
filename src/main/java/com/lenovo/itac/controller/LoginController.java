package com.lenovo.itac.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lenovo.itac.entity.LoginUserInfo;
import com.lenovo.itac.http.response.ResponseEntity;
import com.lenovo.itac.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login")
	public ResponseEntity doLogin(LoginUserInfo info, HttpServletRequest request) {
		ResponseEntity response = new ResponseEntity();
		String loginName = info.getLoginName();
		String password = info.getPassword();
		
		boolean result = loginService.login(loginName, password);
		if (result) {
			HttpSession session = request.getSession();
			if (session.getAttribute("user") == null) {
				session.setAttribute("user", info);
			}
		} else {
			response.setCode("404");
		}
		return response;
	}
	
	@RequestMapping(value="/logout")
	public ResponseEntity doLogout(HttpServletRequest request) {
		ResponseEntity response = new ResponseEntity();
		
		LoginUserInfo info = (LoginUserInfo)request.getSession().getAttribute("user");
		if (info == null) {
			response.setCode("404");
		} else {
			boolean result = loginService.logout(info.getLoginName());
			if (!result) {
				response.setCode("400");
			} else {
				request.getSession().removeAttribute("user");
			}
		}
		
		return response;
	}
}
