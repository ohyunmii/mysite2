package com.bigdata2019.mysite.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2019.mysite.repository.GuestbookDao;
import com.bigdata2019.mysite.vo.GuestbookVo;
import com.bigdata2019.mysite.web.util.WebUtil;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("a");

		if ("list".equals(action)) {
			
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.findAll();
			
			request.setAttribute("list", list);
			
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
			
		} else if("deleteform".equals(action)) {
			
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
			
		} else if("add".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String pwd = request.getParameter("password");
			String contents = request.getParameter("contents");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(pwd);
			vo.setContents(contents);
			
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);
			
			WebUtil.redirect(request, response, request.getContextPath()+"/guestbook?a=list");
			
			
		}  else if("delete".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");
			
			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");
			
			new GuestbookDao().delete(no, password);
			
			WebUtil.redirect(request, response, request.getContextPath()+"/guestbook?a=list");	
			
		} else {
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
//			GuestbookDao dao = new GuestbookDao();
//			List<GuestbookVo> list = dao.findAll();
//			
//			request.setAttribute("list", list);
//			
			WebUtil.redirect(request, response, "/WEB-INF/views/guestbook?a=list");
		}



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
