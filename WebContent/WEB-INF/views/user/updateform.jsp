<%@page import="com.bigdata2019.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
	UserVo vo = (UserVo)request.getAttribute("userVo");
%>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post"
					action="/mysite2/user?a=join">
					<input type="hidden" name="a" value="join/" /> <label
						class="block-label" for="name">Name</label> <input id="name"
						name="name" type="text" value="<%=vo.getName() %>"> <label
						class="block-label" for="email">Email</label>
					<h4>
						<%=vo.getEmail() %></h4>

					<label class="block-label">Password</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>Gender</legend>

						<%if(vo.getGender().equals("female")){ %>
						<label>Female</label> <input type="radio" name="gender"
							value="female" checked="checked"> <label>Male</label> <input
							type="radio" name="gender" value="male">

						<% } else{ %>
						<label>Female</label> <input type="radio" name="gender"
							value="female" > <label>Male</label> <input
							type="radio" name="gender" value=" male"checked="checked">
						<% 	} %>
					</fieldset>

					<br> <input type="submit" value="Update">

				</form>
			</div>
		</div>


		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>