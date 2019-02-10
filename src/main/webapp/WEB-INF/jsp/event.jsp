<jsp:useBean id="eventDto" scope="request" type="ru.vigovskiy.strike_team.dto.event.EventDto"/>
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
<script type="text/javascript" src="resources/js/eventMembersDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<input type="hidden" id="eventId" name="eventId" value=${eventDto.id}>

<div class="d-flex justify-content-center">
    <div class="card" style="width: 30rem;">
        <div class="card-body">
            <h5 class="card-header">Событие</h5>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <h6 class="card-subtitle">Имя события</h6>
                    <p class="card-text">${eventDto.name}</p>
                </li>
                <li class="list-group-item">
                    <h6 class="card-subtitle">Описание события</h6>
                    <p class="card-text">${eventDto.description}</p>
                </li>
                <li class="list-group-item">
                    <h6 class="card-subtitle">День события</h6>
                    <p class="card-text">${eventDto.date}</p></li>
            </ul>
        </div>
    </div>
</div>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Участники</h3>
        <br/>
        <table class="table table-striped" id="membersDatatable">
            <thead>
            <tr>
                <th>ID участника</th>
                <th>ID user</th>
                <th>Решение</th>
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

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>