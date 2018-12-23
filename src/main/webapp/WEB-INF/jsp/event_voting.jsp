<jsp:useBean id="eventVotingDto" scope="request" type="ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull"/>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="strike-team" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="card-deck">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Событие</h5>
            <h6 class="card-subtitle">Имя события</h6>
            <p class="card-text">${eventVotingDto.event.name}</p>
            <h6 class="card-subtitle">Описание события</h6>
            <p class="card-text">${eventVotingDto.event.description}</p>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Голосование</h5>
            <h6 class="card-subtitle">Описание голосования</h6>
            <p class="card-text">${eventVotingDto.description}</p>
        </div>
    </div>
</div>
<br>
    <div class="jumbotron pt-4">
        <div class="container">
            <h3 class="text-center">Возможные дни</h3>
            <table class="table table-striped" id="datatable">
                <thead>
                    <tr>
                        <th>ID дня голосования</th>
                        <th>ID голосования</th>
                        <th>День голосования</th>
                    </tr>
                </thead>
                <c:forEach items="${eventVotingDto.voteDays}" var="voteDay">
                    <jsp:useBean id="voteDay" type="ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto"/>
                    <tr>
                        <td>${voteDay.id}</td>
                        <td>${voteDay.eventVotingId}</td>
                        <td>${voteDay.day}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>