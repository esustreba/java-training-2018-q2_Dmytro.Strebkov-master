<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Касса</title>
<title>Вход | Железнодорожная касса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div id="wrapper">
		<h1>
			Добро пожаловать в<br />Виртуальную железнодорожную кассу
		</h1>
		<h3><font color="red">
		   <c:if test="${not empty notActivated and notActivated eq 'true'}">
		      Учетная запись еще не активна
		   </c:if>
		   <c:if test="${not empty notExists and notExists eq 'true'}">
		      Учетная запись не найдена
		   </c:if>
		   </font>
		</h3>
		<form action="check-login" method="post">
			<table style="margin: auto">
				<tr>
					<td style="text-align: left">E-mail:</td>
					<td><input name="email" type="email" size="35" required /></td>
				</tr>
				<tr>
					<td style="text-align: left">Пароль:</td>
					<td><input name="password" type="password" size="35"
						maxlength="35" required /></td>
				</tr>
			</table>
			<table style="margin: auto">
				<tr>
					<td><input type="submit" class="button-accept"
					    name="userLogin"
						value="Пользователь" /></td>
					<td><input type="submit" class="button-accept"
						name="adminLogin"
						value="Администратор" /></td>
				</tr>
			</table>
		</form>
		<form action="registration" method="get">
			<input type="submit" class="button-register"
			name="register" value="Зарегистрироваться" />
		</form>
	</div>
</body>
</html>