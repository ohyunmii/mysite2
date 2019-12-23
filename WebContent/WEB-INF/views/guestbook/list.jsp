<%@page import="java.util.List"%>
<%@page import="com.bigdata2019.mysite.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	List<GuestbookVo> list = (List<GuestbookVo>) request.getAttribute("list");
%>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="/mysite2/guestbook?a=add" method="post">
					<input type="hidden" name="a" value="add">
					<table border=1 width=500>
						<tr>
							<td>Name</td>
							<td><input type="text" name="name"></td>
							<td>password</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="contents"
									cols=60 rows=5></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit"
								VALUE="Submit"></td>
						</tr>
					</table>
				</form>


				<%
					if (list != null) {
						for (GuestbookVo vo : list) {
				%>

						<table width=510 border=1>
							<tr>
								<td>[<%=vo.getNo()%>]
								</td>
								<td><%=vo.getName()%></td>
								<td><%=vo.getRegDate()%></td>
								<td><a
									href="<%=request.getContextPath() %>/guestbook?a=delete&no=<%=vo.getNo() %>">Delete</a></td>
							</tr>
							<tr>
								<td colspan=4><%=vo.getContents().replace("\n", "<br>")%>
								</td>
							</tr>
						</table> <br>

				<%
					}
				%>
				<%
					}
				%>

			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>