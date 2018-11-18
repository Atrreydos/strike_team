<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>

    <form method="post" action="users">
        <b>Meals of&nbsp;</b>
        <select name="userId">
            <option value="1">User1</option>
            <option value="2">Admin1</option>
        </select>
        <button type="submit">Select</button>
    </form>

    <ul>
        <li><a href="users">Пользователи</a></li>
        <li><a href="events">События</a></li>
    </ul>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>