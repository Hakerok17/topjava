<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 08.02.2022
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Meals</title>
</head>
<body>
<ul>
    <li><a href="index.html">Home</a></li>
    <li><a href="users">Users</a></li>
</ul>
<hr>
<p><a href="meals?action=insert">Add User</a></p>
<table align="center" border="1" style="all-space-treatment: 1">
    <caption>Список приемов пищи</caption>

    <tr>
        <td>ID</td>
        <td>Дата</td>
        <td>Описание</td>
        <td>Калории</td>
    </tr>

    <c:forEach items="${meals}" var="mealTo">
            <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr style="color: ${mealTo.excess ? 'green' : 'red'}">
                <td><c:out value="${mealTo.id}"/></td>
                <td>
                    <javatime:parseLocalDateTime value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm"
                                                 var="parsedDateTime"/>
                    <javatime:format pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}"/>
                </td>
                <td><c:out value="${mealTo.description}"/>
                </td>
                <td><c:out value="${mealTo.calories}"/></td>
                <td><c:out value="${mealTo.excess}"/></td>
                <td><a href="meals?action=edit&mealId=<c:out value="${mealTo.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${mealTo.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
</table>
</body>
</html>