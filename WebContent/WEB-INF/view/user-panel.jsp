<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Личный кабинет | Виртуальная железнодорожная касса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div style="text-align: right">
		<a class="link-cancel" href="logout">Выйти</a>
	</div>
	<div id="wrapper">
		<h3>С возвращением, ${sessionScope.user.name}!</h3>
		<form action="find-train" method="post">
			<table style="margin: auto; text-align: left">
				<tr>
					<td>Станция отправления:</td>
					<td><input name="departureStation" type="text" size="35"
						maxlength="70" required /></td>
				</tr>
				<tr>
					<td>Станция прибытия:</td>
					<td><input name="destinationStation" type="text" size="35"
						maxlength="70" required /></td>
				</tr>
				<tr>
					<td>Дата отправления:</td>
					<td><input name="departureDate" type="date" required /></td>
				</tr>
				<tr>
					<td>Час отправления:<br />(например, 19:35)</td>
					<td><input name="departureTime" type="time" required /></td>
				</tr>			
			</table>
			<input type="submit" class="button-accept" name="findTrain" value="Поиск" />
		</form>
	</div>
</body>
</html>