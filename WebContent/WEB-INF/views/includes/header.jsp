<%@page import="com.bigdata2019.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
%>

<div id="header">
	<h1>MySite</h1>
	<ul>
		<%
			if (authUser == null) {
		%>
		<li><a href="<%=request.getContextPath()%>/user?a=loginform">LogIn</a></li>
		<li></li>
		<li><a href="<%=request.getContextPath()%>/user?a=joinform">SignUp</a></li>
		<li></li>

		<%
			} else {
		%>
		<li><a href="<%=request.getContextPath()%>/user?a=updateform">AccountSettings</a></li>
		<li></li>
		<li><a href="<%=request.getContextPath()%>/user?a=logout">SignOut</a></li>
		<li></li>
		<li>Welcome, <%=authUser.getName()%>!</li>
		<%
			}
		%>
	</ul>
</div>