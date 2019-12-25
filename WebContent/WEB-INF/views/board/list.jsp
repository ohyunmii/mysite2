<%@page import="com.bigdata2019.mysite.vo.UserVo"%>
<%@page import="com.bigdata2019.mysite.repository.UserDao"%>
<%@page import="com.bigdata2019.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	List<BoardVo> list = (List<BoardVo>) request.getAttribute("list");


	//new UserDao().find(vo.getUserNo()).getName()
%>


<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
				<div id="content">
			<div id="board">
				<form id="search_form" action="<%=request.getContextPath()%>/board?a=search" method="post">
					<input type="text" id="keyword" name="keyword" value="">
					<input type="submit" value="Search">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>No</th>
						<th>Title</th>
						<th>Author</th>
						<th>Hit</th>
						<th>Date</th>
						<th>&nbsp;</th>
					</tr>
									
					<%
						if(list!=null){
							for(BoardVo vo:list){
					%>
					<tr>
						<td><%=vo.getNo() %></td>
						<td><a href="<%=request.getContextPath() %>/board?a=view&no=<%=vo.getNo()%>"><%=vo.getTitle() %></a></td>
						<td><%=vo.getUserNo() %></td>
						<td><%=vo.getHit() %></td>
						<td><%=vo.getRegDate() %></td>
						<td><a href="<%=request.getContextPath() %>/board?a=list&no=<%=vo.getNo()%>" class="del">Delete</a></td>
					</tr>
					<%
							}
					
					 %>
					 <%
						}
					 %>
				</table>
				<div class="bottom">
						<a href="<%=request.getContextPath() %>/board?a=writeform"
						id="new-book">Write</a>
				</div>				
			</div>
		</div>


		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>