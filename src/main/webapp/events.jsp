<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Event list</title>
    <%--<link rel="stylesheet" href="css/style.css">--%>
</head>
<body>
<section>
    <h3><a href="index.html">Начальная страница</a></h3>
    <h2>Events</h2>
    <%--<form method="post" action="events?action=filter">--%>
        <%--<dl>--%>
            <%--<dt>From Date:</dt>--%>
            <%--<dd><input type="date" name="startDate" value="${param.startDate}"></dd>--%>
        <%--</dl>--%>
        <%--<dl>--%>
            <%--<dt>To Date:</dt>--%>
            <%--<dd><input type="date" name="endDate" value="${param.endDate}"></dd>--%>
        <%--</dl>--%>
        <%--<dl>--%>
            <%--<dt>From Time:</dt>--%>
            <%--<dd><input type="time" name="startTime" value="${param.startTime}"></dd>--%>
        <%--</dl>--%>
        <%--<dl>--%>
            <%--<dt>To Time:</dt>--%>
            <%--<dd><input type="time" name="endTime" value="${param.endTime}"></dd>--%>
        <%--</dl>--%>
        <%--<button type="submit">Filter</button>--%>
    <%--</form>--%>
    <hr/>
    <a href="events?action=create">Add Event</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Описание</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${events}" var="event">
            <jsp:useBean id="event" scope="page" type="ru.vigovskiy.strike_team.model.Event"/>
            <tr>
                <td>${event.name}</td>
                <td>${event.description}</td>
                <td><a href="events?action=update&id=${event.id}">Update</a></td>
                <td><a href="events?action=delete&id=${event.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>