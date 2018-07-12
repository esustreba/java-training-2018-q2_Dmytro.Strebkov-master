<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Панель администратора | Виртуальная железнодорожная касса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<sql:query dataSource="jdbc/railway_system" var="activated">
SELECT COUNT(*) FROM users WHERE isActivated = '1';
</sql:query>
Активные пользователи: 
<br>
<sql:query dataSource="jdbc/railway_system" var="nonactivated">
SELECT COUNT(*) FROM users WHERE isActivated = '0';
out.println("nonactivated");
</sql:query>
<div style="text-align: right"><a class="link-cancel" href="logout">Выйти</a></div>
<div id="admin-panel">
<h3>С возвращением!</h3>
<c:choose>
<c:when test="${not empty unconfirmedUsers}">
<form action="admin-panel" method="post">
<table style="margin: auto">
<tr class="odd">
<th>№</th>
<th>Email</th>
<th>Фамилия</th>
<th>Имя</th>
<th>Телефон</th>
<th>Сделать действие</th>
</tr>
<c:forEach var="unconfUser" varStatus="loopStatus" items="${unconfirmedUsers}">
<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
<td><c:out value="${unconfUser.id}"/></td>
<td><c:out value="${unconfUser.email}"/></td>
<td><c:out value="${unconfUser.surname}"/></td>
<td><c:out value="${unconfUser.name}"/></td>
<td><c:out value="${unconfUser.phone}"/></td>
<td>
<select name="select${unconfUser.id}">
<option value="ignore">Игнорировать</option>
<option value="activate">Активировать</option>
  <option value="delete">Удалить</option>
</select>
</td>
</tr>
</c:forEach>
</table>
<input type="submit" class="button-accept" name="updateUsers" value="Подтвердить" />
</form>
</c:when>
<c:otherwise>
<c:out value="Неактиввированных пользователей не найдено"></c:out>
</c:otherwise>
</c:choose>
</div>
</body>
</html>