<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 12.02.2022
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <title>Add new meal</title>
</head>
<body>
<script>
    $(function () {
        $('input[name=dao]').datepicker();
    });
</script>

<form method="POST" action='meals' name="formAddMeal">
    Meal ID : <input type="text" readonly="readonly" name="mealId" value="
                     <c:out value="${mealTo.id}" />"/> <br/>
    Date Time : <input
        type="text" name="datetime"
        value="<fmt:formatDate pattern="MM/dd/yyyy" value="${mealTo.dateTime}" />"/> <br/>
    Description : <input
        type="text" name="description"
        value="<c:out value="${mealTo.description}" />"/> <br/>
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${mealTo.calories}" />"/> <br/>
</form>
</body>
</html>
