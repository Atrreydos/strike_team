<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a href="events" class="navbar-brand"><img src="resources/images/icon.ico"> <%--<spring:message code="app.title"/>--%>Менеджер событий</a>
        <form class="form-inline my-2">
            <a class="btn btn-info mr-1" href="users"><%--<spring:message code="user.title"/>--%>Пользователи</a>
            <a class="btn btn-primary" href="">
                <span class="fa fa-sign-in"></span>
            </a>
        </form>
    </div>
</nav>
