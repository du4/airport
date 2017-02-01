<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="menu" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Duch Airlines</title>
    <meta charset="utf-8">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }
    </style>


</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <div class="navbar-header">
                <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-send"></span>  Duch Airlines</a>
            </div>


        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <menu:li action="Index" glyphicon="glyphicon glyphicon-home" text="  Home"/>


                <c:if test="${user.role_id==1}">
                    <menu:li action="UserManagement" glyphicon="glyphicon glyphicon-list-alt" text="  User Management"/>
                    <menu:li action="NewFlight" glyphicon="glyphicon glyphicon-plane_id" text="  New flight"/>
                    <menu:li action="StuffingCrew" glyphicon="glyphicon glyphicon-bullhorn" text="  Add Flight Crew"/>
                </c:if>
                <c:if test="${user.role_id==2}">
                    <menu:li action="StuffingCrew" glyphicon="glyphicon glyphicon-bullhorn" text="  Add Flight Crew"/>
                </c:if>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${user==null}">
                    <menu:li action="Login" glyphicon="glyphicon glyphicon-log-in" text=" Login"/>
                    <menu:li action="SignUp" glyphicon="glyphicon glyphicon-user_id" text=" SignUp"/>
                </c:if>
                <c:if test="${user!=null}">
                    <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="${curUser}"><span class="glyphicon glyphicon-check"></span>  user_id=${user.login}</a></li>
                    <menu:li action="Logout" glyphicon="glyphicon glyphicon-log-out" text=" Logout"/>
                    <menu:li action="Profile" glyphicon="glyphicon glyphicon-user_id" text=" Profile"/>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="row content">
    <%--<div class="col-sm-1 sidenav"></div>--%>
    <div class="col-sm-12 text-left">