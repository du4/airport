
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../static/include/begin-html.jsp" %>

<form class="form-horizontal" action="airport?action=Profile" method="POST">
    <fieldset>

        <!-- Form Name -->
        <legend align="center">Profile</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="login">Login</label>
            <div class="col-md-4">
                <input id="login" name="login"  type="text" value= "<c:out value="${user.login}"/>"  placeholder="login" class="form-control input-md" required="Login can't be empty.">
             </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="email">Email</label>
            <div class="col-md-4">
                <input id="email" name="email"  type="text" value="<c:out value="${user.email}"/>" placeholder="email" class="form-control input-md" required="Email can't be empty.">
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="pass">Password</label>
            <div class="col-md-4">
                <input id="pass" name="pass" type="password" value="<c:out value="${user.pass}"/>" placeholder="password" class="form-control input-md" required="Password can't be empty.">
            </div>
        </div>


        <!-- Select Basic -->
        <%--<div class="form-group">--%>
            <%--<label class="col-md-4 control-label" for="role_id">Select role_id</label>--%>
            <%--<div class="col-md-4">--%>
                <%--<select id="role_id" name="role_id"  class="form-control">--%>
                    <%--<c:forEach items="${roles}" var="role_id">--%>
                        <%--<option value=${role_id.id}>${role_id.role_id}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" class="btn btn-success">Save</button>
            </div>
        </div>

    </fieldset>
</form>


<%@ include file="../static/include/end-html.jsp" %>