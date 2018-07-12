<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Результати поиска | Виртуальная железнодорожная касса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div style="text-align: right">
		<a class="link-cancel" href="logout">Выйти</a>
	</div>
	<div id="trains-result">
	<c:choose>
	<c:when test="${empty routes}">
		<h3>Извините, по данным параметрам поездов не найдено</h3>
		</c:when>
		<c:otherwise>
		<h3>Поиск поездов на ${departureDate}</h3>
		<form action="create-invoice" method="post">
		<table style="margin: auto">
<tr class="odd">
<th>№ поезда</th>
<th>Откуда</th>
<th>Куда</th>
<th>Отправление</th>
<th>Прибытие</th>
<th>Люкс</th>
<th>Купе</th>
<th>Плацкарт</th>
</tr>
<c:forEach var="route" varStatus="loopStatus" items="${routes}">
<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
<td><c:out value="${route.id}"/></td>
<td><c:out value="${route.departureStation}"/></td>
<td><c:out value="${route.destinationStation}"/></td>
<td><c:out value="${route.departureTime}"/></td>
<td><c:out value="${route.destinationTime}"/></td>
<td><input type="submit" class="button-accept" name="suite${route.id}" value="Выбрать"/></td>
<td><input type="submit" class="button-accept" name="coupe${route.id}" value="Выбрать"/></td>
<td><input type="submit" class="button-accept" name="berth${route.id}" value="Выбрать"/></td>
</tr>
</c:forEach>
</table>
</form>
		</c:otherwise>
		</c:choose>
	</div>
</body>
</html>