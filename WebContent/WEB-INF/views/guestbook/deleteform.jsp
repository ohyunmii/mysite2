<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="/mysite2/guestbook?a=delete">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="<%= no %>">
					<label>Password</label>
					<input type="password" name="password">
					<input type="submit" value="Confirm">
				</form>
				<a href="<%=request.getContextPath()%>/guestbook?a=list">Go Back to Guestbook List</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>