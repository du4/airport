
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../static/include/begin-html.jsp" %>

<form class="form-horizontal" action="airport?action=SIGNUP" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend align="center">Sign-up</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="login">Login</label>
            <div class="col-md-4">
                <input id="login" name="login"  type="text" placeholder="login" class="form-control input-md" required="Login can't be empty.">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="email">Email</label>
            <div class="col-md-4">
                <input id="email" name="email"  type="text" placeholder="email" class="form-control input-md" required="Email can't be empty.">
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="pass">Password</label>
            <div class="col-md-4">
                <input id="pass" name="pass"  type="password" placeholder="password" class="form-control input-md" required="Password can't be empty.">
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="passConfirm">Confirm Password</label>
            <div class="col-md-4">
                <input id="passConfirm" name="passConfirm"  type="password" placeholder="password" class="form-control input-md" required="Confirm the password.">
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" class="btn btn-success">Create User</button>
            </div>
        </div>

    </fieldset>
</form>


<%--<p>Cmd SIGN-UP: ${message}</p>--%>

<%@ include file="../static/include/end-html.jsp" %>


