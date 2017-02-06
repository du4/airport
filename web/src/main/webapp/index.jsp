
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="WEB-INF/static/include/begin-html.jsp" %>


<div class="page-header">
    <h3 align="center">Flight time-table</h3>
</div>

<div class="container-fluid">

    <div class="panel panel-warning">
        <div class="panel-heading">Find panel</div>
        <div class="panel-body">
            <div class="row">
                <form class="form-find" action="airport?action=index" method=POST>
                    <!-- Select Basic -->
                    <div class="col-md-2">
                        <select id="from" name="from"  class="form-control">
                            <option value="" selected disabled>Departure</option>
                            <c:forEach items="${airports}" var="airport">
                                <option value=${airport.acronim}>${airport.acronim}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- Select Basic -->
                    <div class="col-md-2">
                        <select id="to" name="to" class="form-control">
                            <option value="" selected disabled>Destination</option>
                            <c:forEach items="${airports}" var="airport">
                                <option value=${airport.acronim}>${airport.acronim}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class=col-md-2>
                        <input name="departureTime" size="16" type="text" value="" placeholder="Departure time" class="form_datetime">
                    </div>

                    <div class=col-md-2>
                        <input name="arrivalTime" size="16" type="text" value="" placeholder="Arrival time" class="form_datetime">
                    </div>
                    <!-- Button -->
                    <div class="col-md-2">
                        <button id="singlebutton" class="btn btn-success">Find</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <table class="table table-hover">
        <thead>
        <tr>
            <th>Code</th>
            <th>Company</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Plane</th>
            <th>From</th>
            <th>To</th>

            <c:if test="${user.getRole().getId() < 3}">
                <th>id</th>
                <th>Crew</th>
                <th>User</th>
                <th>CreatedDate</th>
            </c:if>

        </tr>
        </thead>
        <tbody>
        <form action="airport?action=StuffingCrew" method=GET>
            <c:forEach items="${flightStr}" var="f">
                <tr>
                    <td>
                        <label class=</label>
                                ${f.flightCode}
                    </td>
                    <td>${f.company}</td>
                    <td>${f.departure_time}</td>
                    <td>${f.arrival_time}</td>
                    <td>${f.plane}</td>
                    <td>${f.from}</td>
                    <td>${f.to}</td>
                    <c:if test="${user.getRole().getId() < 3}">
                        <td>${f.id}</td>
                        <td>
                            <c:forEach items="${f.crew}" var="e">
                                ${e.name }
                            </c:forEach>
                            <button type="submit" name="flight_id"  value=${f.id}  , class="btn btn-primary btn-xs">...</button>
                        </td>
                        <td>${f.user}</td>
                        <td>${f.created_date}</td>
                    </c:if>

                </tr>
            </c:forEach>
            <input type="hidden" name="action" value="StuffingCrew" />
        </form>
        </tbody>
    </table>

    <div class="row">
        <div class="col-md-2">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end = "${pageCount}">
                    <li
                            <c:if test="${i == activePageIndex}">
                                class="active"
                            </c:if>

                    ><a href="airport?action=index&activePageIndex=${i}&pageSize=${pageSize}">${i}</a></li>
                </c:forEach>
            </ul>
        </div>
        <%--<div class="col-md-1">--%>
            <%--<div class="form-group">--%>
                <%--<label for="selPage">Page size:</label>--%>
                <%--<select id="selPage" name="selPage" class="form-control">--%>
                    <%--<option value="1">1</option>--%>
                    <%--<option value="2" selected>2</option>--%>
                    <%--<option value="3">3</option>--%>
                    <%--<option value="4">4</option>--%>
                    <%--<option value="5">5</option>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>

</div>

<script type="text/javascript" src="WEB-INF/static/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<script type="text/javascript">
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 2,
        pickTime: false
    });

</script>

<%@ include file="WEB-INF/static/include/end-html.jsp" %>