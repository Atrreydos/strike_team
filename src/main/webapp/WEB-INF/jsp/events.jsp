<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/eventDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="event.title"/></h3>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button class="btn btn-primary" onclick="add()">
                <span class="fa fa-plus"></span>
                Создать событие
            </button>
            <input type="hidden" id="roleAdmin" name="roleAdmin" value="true">
        </sec:authorize>
        <br/><br/>
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="upcoming-tab" data-toggle="tab" href="#upcoming" role="tab" aria-controls="upcoming" aria-selected="true">Предстоящие</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="in-voting-tab" data-toggle="tab" href="#in-voting" role="tab" aria-controls="in-voting" aria-selected="false">На голосовании</a>
            </li>
        </ul>

        <div class="tab-content" id="myTabContent">
            <div class="container tab-pane active w-100" id="upcoming" role="tabpanel" aria-labelledby="upcoming-tab">
                <h4 class="text-center">Приближающиеся события</h4>
                <table class="table table-striped" id="datatable_upcoming">
                    <thead>
                    <tr>
                        <th>Название события</th>
                        <th>Дата события</th>
                        <th>Статус события</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="container tab-pane" id="in-voting" role="tabpanel" aria-labelledby="in-voting-tab">
                <h4 class="text-center">События на голосовании</h4>
                <table class="table table-striped w-100" id="datatable_in_voting">
                    <thead>
                    <tr>
                        <th>Название события</th>
                        <th>Дата события</th>
                        <th>Статус события</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
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
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="status" name="status">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="common.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="common.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="description">Описание события</label>
                        <textarea class="form-control" rows="5" id="description" placeholder="описание события"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="date" class="col-form-label">Дата события</label>
                        <input class="form-control" id="date" name="date"
                               placeholder="Введите дату">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="saveEvent()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="event"/>
</jsp:include>
</html>