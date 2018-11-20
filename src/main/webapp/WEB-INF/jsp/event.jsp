<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <jsp:useBean id="event" scope="request" type="ru.vigovskiy.strike_team.model.Event"/>
    <h2>Событие</h2>
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
    <h3>Имя события : ${event.name}</h3><br>
    <h3>Описание события : ${event.description}</h3><br>
    <h3>Дни события:</h3><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Дата</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${event.eventDays}" var="eventDay">
            <jsp:useBean id="eventDay" scope="page" type="ru.vigovskiy.strike_team.model.EventDay"/>
            <tr>
                <td>${eventDay.day}</td>
                <%--<td><fmt:formatDate value="${eventDay.day}" pattern="dd-MM-yyyy"/></td>--%>
                <td><a href="events?action=update&id=${eventDay.id}">Update</a></td>
                <td><a href="events?action=delete&id=${eventDay.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="event-days?action=create">Add EventDay</a>
    <hr/>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>