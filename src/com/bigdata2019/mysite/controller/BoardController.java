package com.bigdata2019.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bigdata2019.mysite.repository.BoardDao;
import com.bigdata2019.mysite.repository.GuestbookDao;
import com.bigdata2019.mysite.vo.BoardVo;
import com.bigdata2019.mysite.vo.UserVo;
import com.bigdata2019.mysite.web.util.WebUtil;

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("a");

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if ("list".equals(action)) {

//			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");

			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.findAll();

			request.setAttribute("list", list);

			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");

		} else if ("writeform".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");
			// If the user is not signed-in, user will not proceed to the write form.
			if (session == null) {
				WebUtil.redirect(request, response, request.getContextPath() + "/board?a=list");
				return;
			}
			if (authUser == null) {
				WebUtil.redirect(request, response, request.getContextPath() + "/board?a=list");
				return;
			}
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");

		} else if ("search".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");

			String keyword = request.getParameter("keyword");

			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.search(keyword);

			request.setAttribute("list", list);
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");

		} else if ("write".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");

			String title = request.getParameter("title");
			String contents = request.getParameter("content");

			BoardVo boardVo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContents(contents);
			new BoardDao().insert(boardVo, authUser);

			WebUtil.redirect(request, response, request.getContextPath() + "/board?a=list");

		} else if ("modifyform".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");

			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");

		} else if ("view".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");
			// board vo

			// WebUtil.redirect(request, response, request.getContextPath() +
			// "/user?a=view&entryno=no");
			// WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");

		} else if ("modify".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");

		} else if ("delete".equals(action)) {
			
			request.setCharacterEncoding("UTF-8");
			
			// show delete icons ONLY if entry author == user    ---- needs to be done in list.jsp
			Long no = Long.parseLong(request.getParameter("no"));
			new BoardDao().delete(no);
			
			WebUtil.redirect(request, response, request.getContextPath() + "/board?a=list");

		} else {
			WebUtil.redirect(request, response, "WEB-INF/views/board?a=list");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
