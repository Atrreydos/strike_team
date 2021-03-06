<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <img src="resources/images/icon.ico">
        <sec:authorize access="isAuthenticated()">
            <form class="form-inline my-2">
                <a class="btn btn-info mr-1" href="events">События</a>
                <a class="btn btn-info mr-1" href="event-votings">Голосования по событиям</a>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
                </sec:authorize>
                <a class="btn btn-info mr-1" href="profile">Мой профиль</a>
                <a class="btn btn-primary" href="logout" title="Выйти">
                    <span class="fas fa-sign-out-alt"></span>
                </a>
            </form>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
                <input class="form-control mr-1" type="text" placeholder="Login" name="username">
                <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                <button class="btn btn-success" type="submit" title="Войти как">
                    <span class="fas fa-sign-in-alt"></span>
                </button>
            </form>
        </sec:authorize>
    </div>
</nav>