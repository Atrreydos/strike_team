<jsp:useBean id="eventVotingDto" scope="request" type="ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull"/>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="strike-team" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/voteDayDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<input type="hidden" id="eventVotingId" name="eventVotingId" value=${eventVotingDto.id}>
<input type="hidden" id="enabledCount" name="enabledCount" value=${enabledCount}>

<br>

<div class="d-flex justify-content-center">
    <div class="card-deck" style="width: 80rem;">
        <div class="card">
            <div class="card-body">
                <h5 class="card-header">Событие</h5>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <h6 class="card-subtitle">Имя события</h6>
                        <p class="card-text">${eventVotingDto.event.name}</p>
                    </li>
                    <li class="list-group-item">
                        <h6 class="card-subtitle">Описание события</h6>
                        <p class="card-text">${eventVotingDto.event.description}</p>
                    </li>
                    <li class="list-group-item">
                        <h6 class="card-subtitle">День события</h6>
                        <p class="card-text">${eventVotingDto.event.date}</p>
                    </li>
                </ul>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <h5 class="card-header">Голосование</h5>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <h6 class="card-subtitle">Описание голосования</h6>
                        <p class="card-text">${eventVotingDto.description}</p>
                    </li>
                    <li class="list-group-item">
                        <h6 class="card-subtitle">Статус голосования</h6>
                        <p class="card-text">${eventVotingDto.status}</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<br>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Возможные дни</h3>
        <br/>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button class="btn btn-primary" onclick="addWithInput()">
                <span class="fa fa-plus"></span>
                <spring:message code="common.add"/>
            </button>
            <button class="btn btn-primary" onclick="">
                <span class="fas fa-vote-yea"></span>
                Подсчитать
            </button>
            <button class="btn btn-primary" onclick="selectVoteDay()">
            <span class="fas fa-vote-yea"></span>
            Выбрать
            </button>
            <input type="hidden" id="roleAdmin" name="roleAdmin" value="true">
        </sec:authorize>
        <br/><br/>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>День голосования</th>
                <th>Мое решение</th>
                <th>ЗА</th>
                <th>ПРОТИВ</th>
                <th>Очистить</th>
                <th>Прогресс</th>
                <th>Удалить день</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <div class="form-group">
                        <label for="day" class="col-form-label">День</label>
                        <input class="form-control" id="day" name="day" placeholder="Введите дату">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="saveVoteDay()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="selectDay">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="selectDayModalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <table class="table table-striped" id="selectDayDatatable">
                    <thead>
                    <tr>
                        <th>День голосования</th>
                        <th>Голоса</th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="voteDay"/>
</jsp:include>
</html>