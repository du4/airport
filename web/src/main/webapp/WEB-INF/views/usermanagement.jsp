
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../static/include/begin-html.jsp" %>


<div class="container-fluid">

<div class="row">
    <b>
        <div class=col-md-1>ID</div>
        <div class=col-md-2>Login</div>
        <div class=col-md-2>Email</div>
        <div class=col-md-1>Role</div>
        <div class=col-md-2>Created Date</div>
        <div class=col-md-2>Modified Date</div>
    </b>
</div>
<br>
<c:forEach items="${users}" var="user">
    <div class="row">
        <form class="form-user-${user.id}" action="airport?action=UserManagement" method=POST>
            <div class=col-md-1>
                ${user.id}
            </div>
            <div class=col-md-2>
                <input id="textinput1" name="Login" type="text"
                       value="${user.login}" class="form-control input-md">
            </div>
            <div class=col-md-2>
                <input id="textinput3" name="Email" type="text"
                       value="${user.email}" class="form-control input-md">
            </div>

            <div class=col-md-1>
                <select id="role" name="userRole" class="form-control">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.id}" role=${role.id} ${role.id==user.role.id?"selected":""}>
                                ${role.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class=col-md-2>
                <input id="textinput4" name="CreatedDate" type="text"
                      value="${user.createdDate}" class="form-control input-md">
            </div>

            <div class=col-md-2>
                ${user.updatedDate}
            </div>


            <div class=col-md-1>
                <button id="singlebutton1" name="ID" value=${user.id} , class="btn btn-success">
                    Update
                </button>
            </div>

            <div class=col-md-1>
                <button id="singlebutton" name="ID" value=${user.id * -1} , class="btn btn-danger">
                        <%--onclick="document.getElementById('user_id_${user.id}').value=-document.getElementById('user_id_${user.id}').value;">--%>
                    Delete
                </button>
            </div>

        </form>
    </div>
    <br>
</c:forEach>

</div>

<%--<table width="100%" border="1" cellpadding="1" cellspacing="1">--%>
    <%--<tr>--%>
        <%--<th scope="col" bgcolor="#a9a9a9" align="center">LOGIN</th>--%>
        <%--<th scope="col" bgcolor="#a9a9a9" align="center">EMAIL</th>--%>
        <%--<th scope="col" bgcolor="#a9a9a9" align="center">ROLE</th>--%>
    <%--</tr>--%>
    <%--<c:forEach items="${users}" var="user_id">--%>
        <%--<tr>--%>
            <%--<td align="center"> ${user_id.login}</td>--%>
            <%--<td align="center">${user_id.email}</td>--%>
            <%--&lt;%&ndash;<td align="center">${roles.getByLogin(${user_id.role_id})}</td>&ndash;%&gt;--%>
            <%--<c:choose>--%>
                <%--<c:when test="${user_id.role_id==1}">--%>
                    <%--<td align="center">Administrator</td>--%>
                <%--</c:when>--%>
                <%--<c:when test="${user_id.role_id==2}">--%>
                    <%--<td align="center">Dispatcher</td>--%>
                <%--</c:when>--%>
                <%--<c:when test="${user_id.role_id==3}">--%>
                    <%--<td align="center">User</td>--%>
                <%--</c:when>--%>
            <%--</c:choose>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
<%--</table>--%>

<%@ include file="../static/include/end-html.jsp" %>
