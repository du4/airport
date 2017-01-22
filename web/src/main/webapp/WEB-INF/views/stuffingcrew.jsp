
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../static/include/begin-html.jsp" %>

<form class="form-horizontal" action="airport?command=StuffingCrew" method="POST">
    <fieldset>

        <!-- Form Name -->
        <legend align="center">Stuffing the crew_id</legend>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="pilot1">Pilot1</label>
            <div class="col-md-4">
                <select id="pilot1" name="pilot1"  class="form-control" required="">
                    <option value="" selected disabled>pilot1</option>
                    <c:forEach items="${pilots}" var="pilot">
                        <option value=${pilot.id}>${pilot.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="pilot2_id">Pilot2</label>
            <div class="col-md-4">
                <select id="pilot2_id" name="pilot2_id"  class="form-control" required="">
                    <option value="" selected disabled>pilot2_id</option>
                    <c:forEach items="${pilots}" var="pilot">
                        <option value=${pilot.id}>${pilot.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="airhostess1_id">Airhostess1</label>
            <div class="col-md-4">
                <select id="airhostess1_id" name="airhostess1_id"   class="form-control" required="">
                    <option value="" selected disabled>airhostess1_id</option>
                    <c:forEach items="${airhostesses}" var="airhostess">
                        <option value=${airhostess.id}>${airhostess.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="airhostess2_id">Airhostess2</label>
            <div class="col-md-4">
                <select id="airhostess2_id" name="airhostess2_id"  class="form-control" required="">
                    <option value="" selected disabled>airhostess2_id</option>
                    <c:forEach items="${airhostesses}" var="airhostess">
                        <option value=${airhostess.id}>${airhostess.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton"  class="btn btn-success">Create Crew</button>
            </div>
        </div>

    </fieldset>
</form>

<%@ include file="../static/include/end-html.jsp" %>
