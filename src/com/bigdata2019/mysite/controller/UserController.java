package com.bigdata2019.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bigdata2019.mysite.repository.UserDao;
import com.bigdata2019.mysite.vo.UserVo;
import com.bigdata2019.mysite.web.util.WebUtil;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("a");

		if (action.equals("joinform")) {
			WebUtil.forward(request, response, "WEB-INF/views/user/joinform.jsp");
		} else if (action.equals("join")) {

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);
			new UserDao().insert(vo);

			WebUtil.redirect(request, response, request.getContextPath() + "/user?a=joinsuccess");

		} else if (action.equals("joinsuccess")) {
			WebUtil.forward(request, response, "WEB-INF/views/user/joinsuccess.jsp");
		} else if (action.equals("loginform")) {
			WebUtil.forward(request, response, "WEB-INF/views/user/loginform.jsp");
		} else if (action.equals("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			UserVo vo = new UserDao().find(email, password);
			if (vo == null) {
				WebUtil.redirect(request, response, request.getContextPath() + "/user?a=loginform&result=fail");
				return;
			}

			// sign in
			// request contains sessionID
			HttpSession session = request.getSession();
			session.setAttribute("authUser", vo);

			// redirect to Main page
			WebUtil.redirect(request, response, request.getContextPath());
		} else if (action.equals("logout")) {
			HttpSession session = request.getSession();
			if (session == null) {
				WebUtil.redirect(request, response, request.getContextPath());
				return;
			}
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser==null) {
				WebUtil.redirect(request, response, request.getContextPath());
				return;
			}
			
			// sign out
			session.removeAttribute("authUser");
			session.invalidate();
			WebUtil.redirect(request, response, request.getContextPath());
		} else {
			WebUtil.redirect(request, response, request.getContextPath());

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
