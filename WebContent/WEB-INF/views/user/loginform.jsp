<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	String result = request.getParameter("result");
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
				<form id="login-form" name="loginform" method="post"
					action="<%=request.getContextPath()%>/user">
					<input type="hidden" name="a" value="login" /> <label
						class="block-label" for="email">Email</label> <input id="email"
						name="email" type="text" value=""> <label
						class="block-label">Password</label> <input name="password"
						type="password" value="">

					<%
						if ("fail".equals(result)) {
					%>
					<p>Failed to sign in.</p>
					<%
						}
					%>
					<input type="submit" value="Sign in">
				</form>
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>