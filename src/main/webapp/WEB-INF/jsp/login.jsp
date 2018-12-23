<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-0">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message"><spring:message code="${param.message}"/></div>
        </c:if>
        <br/>
        <p>
            <a class="btn btn-lg btn-success" href="register"><spring:message code="app.register"/> &raquo;</a>
            <button type="submit" class="btn btn-lg btn-primary" onclick="login('user1_login', 'password')">
                <spring:message code="app.login"/> User
            </button>
            <button type="submit" class="btn btn-lg btn-primary" onclick="login('admin1_login', 'password')">
                <spring:message code="app.login"/> Admin
            </button>
        </p>
        <br/>
    </div>
</div>
<div class="container">
    <div class="lead">
        &nbsp;&nbsp;&nbsp;Это пробное веб-приложение для упрощения решения некоторых вопросов связанных с жизнью
        страйкбольной команды.<br/><br/>
        На данном этапе основной функционал (пока нихера не работает вообще) - админ создает события, открывает голосование по событию и добавляет в
        него на выбор в какие дни можно провести это событие. Пользователи голосуют по событию, выбирая какие дни
        подходят для них, а какие не подходят. По итогу голосований утверждается подходящий день и событие попадает в
        календарь событий.<br/>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript">

    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>

    function login(username, password) {
        setCredentials(username, password);
        $("#login_form").submit();
    }

    function setCredentials(login, password) {
        $('input[name="username"]').val(login);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>