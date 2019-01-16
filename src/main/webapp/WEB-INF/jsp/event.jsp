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
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="card-deck">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Событие</h5>
            <h6 class="card-subtitle">Имя события</h6>
            <p class="card-text">${eventDto.name}</p>
            <h6 class="card-subtitle">Описание события</h6>
            <p class="card-text">${eventDto.description}</p>
            <h6 class="card-subtitle">День события</h6>
            <p class="card-text">${eventDto.date}</p>
        </div>
    </div>
</div>
<br>


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