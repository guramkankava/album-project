<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/indexjsp.css">
	<script type="text/javascript" src="js/jsForIndexJSP.js"></script>
</head>
<body>
	
	<h3>If you have account Please sign in or you can register below</h3>
	<form action="login" method="POST">
		<p>Email: <input type="text" name="email" id="sEmail"></p> 
		<p>Password: <input type="password" name="password" id="sPassword"></p> 
		<input type="submit" value="Log in" onclick="return validateLogin( 'sEmail', 'sPassword' )">
	</form>
	<br>
	<hr>
	<h3>Registration Here</h3>
	<form action="register" method="post">
		<p>first name: <input type="text" name="firstname" id="fName"></p> 
		<p>last name: <input type="text" name="lastname" id="lName"></p>
		<p>E mail: <input type="text" name="email" id="email"></p>
		<p>Password <input type="password" name="password" id=rPassword></p>
		<input type="submit" value="register" onclick="return validateRegistration( 'fName', 'lName', 'email', 'rPassword' )">
	</form>

</body>
</html>