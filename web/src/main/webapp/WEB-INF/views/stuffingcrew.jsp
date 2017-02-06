
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../static/include/begin-html.jsp" %>

<form class="form-horizontal" action="airport?action=StuffingCrew" method="POST">
    <fieldset>

        <!-- Form Name -->
        <legend align="center">Stuffing the crew</legend>
        <div class="form-group">
        <!-- Select Basic -->
        <div class="row">
            <div class="col-md-2">
                <label class="col-md-2 control-label">Pilots</label>
                <c:forEach items="${crewPilots}" var="crewPilot">
                    <select class="form-control" id="sel${crewPilot.id}">
                        <optgroup label="Pilots">
                            <c:forEach items="${pilots}" var="pilot">
                                <option value=${pilot.id}
                                        <c:if test="${pilot.id == crewPilot.id}">
                                                selected
                                        </c:if>
                                >${pilot.name}</option>
                            </c:forEach>
                            <option value=${crewPilot.id} selected>${crewPilot.name}</option>
                        </optgroup>
                    </select>
                </c:forEach>
            </div>
            <div class="col-md-2">
                <label class="col-md-2 control-label">Airhostesses</label>
                <c:forEach items="${crewAirhostsses}" var="crewAirhostss">
                    <select class="form-control" id="sel${crewAirhostss.id}">
                        <optgroup label="Airhostesses">
                            <c:forEach items="${airhostesses}" var="airhostess">
                                <option value=${airhostess.id}
                                        <c:if test="${airhostess.id == crewAirhostss.id}">
                                                selected
                                        </c:if>
                                >${airhostess.name}</option>
                            </c:forEach>
                        </optgroup>
                    </select>
                </c:forEach>
            </div>
        </div>
        </div>

        <%--<!-- Select Basic -->--%>
        <%--<div class="form-group">--%>
            <%--<label class="col-md-4 control-label" for="pilot1">Pilot1</label>--%>
            <%--<div class="col-md-4">--%>
                <%--<select id="pilot1" name="pilot1"  class="form-control" required="">--%>
                    <%--<option value="" selected disabled>pilot1</option>--%>
                    <%--<c:forEach items="${pilots}" var="pilot">--%>
                        <%--<option value=${pilot.id}>${pilot.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<!-- Select Basic -->--%>
        <%--<div class="form-group">--%>
            <%--<label class="col-md-4 control-label" for="pilot2">Pilot2</label>--%>
            <%--<div class="col-md-4">--%>
                <%--<select id="pilot2" name="pilot2"  class="form-control" required="">--%>
                    <%--<option value="" selected disabled>pilot2</option>--%>
                    <%--<c:forEach items="${pilots}" var="pilot">--%>
                        <%--<option value=${pilot.id}>${pilot.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<!-- Select Basic -->--%>
        <%--<div class="form-group">--%>
            <%--<label class="col-md-4 control-label" for="airhostess1">Airhostess1</label>--%>
            <%--<div class="col-md-4">--%>
                <%--<select id="airhostess1" name="airhostess1"   class="form-control" required="">--%>
                    <%--<option value="" selected disabled>airhostess1</option>--%>
                    <%--<c:forEach items="${airhostesses}" var="airhostess">--%>
                        <%--<option value=${airhostess.id}>${airhostess.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<!-- Select Basic -->--%>
        <%--<div class="form-group">--%>
            <%--<label class="col-md-4 control-label" for="airhostess2">Airhostess2</label>--%>
            <%--<div class="col-md-4">--%>
                <%--<select id="airhostess2" name="airhostess2"  class="form-control" required="">--%>
                    <%--<option value="" selected disabled>airhostess2</option>--%>
                    <%--<c:forEach items="${airhostesses}" var="airhostess">--%>
                        <%--<option value=${airhostess.id}>${airhostess.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton"  class="btn btn-success">Submit</button>
            </div>
        </div>

    </fieldset>
</form>

<%@ include file="../static/include/end-html.jsp" %>
