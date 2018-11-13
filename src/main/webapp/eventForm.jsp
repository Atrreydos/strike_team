<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Event</title>
    <%--<link rel="stylesheet" href="css/style.css">--%>
</head>
<body>
<section>
    <h3><a href="index.html">Начальная страница</a></h3>
    <h2>${param.action == 'create' ? 'Create event' : 'Edit event'}</h2>
    <hr>
    <jsp:useBean id="event" type="ru.vigovskiy.strike_team.model.Event" scope="request"/>
    <form method="post" action="events">
        <input type="hidden" name="id" value="${event.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${event.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${event.description}" size=40 name="description"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
