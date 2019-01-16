<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/eventVotingsDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Голосования по событиям</h3>
        <br/>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button class="btn btn-primary" onclick="add()">
                <span class="fa fa-plus"></span>
                Создать голосование
            </button>
            <input type="hidden" id="roleAdmin" name="roleAdmin" value="true">
        </sec:authorize>
        <br/><br/>
        <table class="table table-striped" id="datatable">
            <thead>
                <tr>
                    <th>Название события</th>
                    <th>Описание события</th>
                    <th>Описание голосования</th>
                    <th></th>
                    <th></th>
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
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="event_id" name="event.id">
                    <div class="form-group">
                        <label for="event_name" class="col-form-label">Название события</label>
                        <input type="text" class="form-control" id="event_name" name="event.name"
                               placeholder="<spring:message code="common.name"/>">
                    </div>
                    <div class="form-group">
                        <label for="event_description" class="col-form-label">Описание события</label>
                        <input type="text" class="form-control" id="event_description" name="event.description"
                               placeholder="<spring:message code="event.description"/>">
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-form-label">Описание голосования по событию</label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="event.description"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="saveEventVoting()">
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
    <jsp:param name="page" value="eventVoting"/>
</jsp:include>
</html>