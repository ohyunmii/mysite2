<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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
					action="<%=request.getContextPath()%>/user?a=join">
					<input type="hidden" name="a" value="join" /> 
					
					<label class="block-label" for="name">Name</label> 
					<input id="name" name="name" type="text" value=""> 
					<label class="block-label" for="email">Email</label> 
					<input id="email" name="email" type="text" value=""> 
					<input type="button" value="ID Duplicate Check"> 
					<label class="block-label">Password</label> 
					<input name="password" type="password" value="">

					<fieldset>
						<legend>Gender</legend>
						<label>Female</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>Male</label> <input type="radio"
							name="gender" value="male">
					</fieldset>
					
					<br>

					<fieldset>
						<legend>Terms and Conditions</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>I have read and agree to the mysite Terms and Conditions.</label>
					</fieldset>

					<input type="submit" value="Sign Up">

				</form>
			</div>
		</div>


		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>