<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="strike-team" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <%--@elvariable id="userDto" type="ru.vigovskiy.strike_team.dto.user.UserDto"--%>
        <div class="row">
            <div class="col-5 offset-3">
                <h3>${userDto.name} <spring:message code="${register ? 'app.register' : 'app.profile'}"/></h3>
                <form:form class="form-group" modelAttribute="userDto" method="post" action="${register ? 'register' : 'profile'}"
                           charset="utf-8" accept-charset="UTF-8">

                    <strike-team:inputField labelCode="user.name" name="name"/>
                    <strike-team:inputField labelCode="user.login" name="login"/>
                    <strike-team:inputField labelCode="user.password" name="password" inputType="password"/>

                    <div class="text-right">
                        <a class="btn btn-secondary" onclick="window.history.back()">
                            <span class="fa fa-close"></span>
                            <spring:message code="common.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <span class="fa fa-check"></span>
                            <spring:message code="common.save"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>