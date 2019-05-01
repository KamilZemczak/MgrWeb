<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="description" content="">
        <meta name="author" content="">

        <title>Stock Exchange</title>

        <link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/static/css/common.css" rel="stylesheet">

        <style>
            .jumbotron{
                background-color:#9dd29c;
                color:white;
                padding: 5px;
                padding-top: 5px;
                padding-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <div class="navbar navbar-inverse">
            <a href="/" class="navbar-brand">Stocks</a>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">User panel <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="user-money">Edit wallet</a></li>
                            <li><a href="user-account">Edit account</a></li>
                        </ul></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <form id="logoutForm" method="POST" action="${contextPath}/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </c:if>

                    <li class="navbar-text">Logged in as ${user.name} ${user.surname}</li>
                    <li><a href="user-account"><span class="glyphicon glyphicon-cog" style="font-size:20px;"></span></a></li>
                    <li><a onclick="document.forms['logoutForm'].submit()" href="#"><span class="glyphicon glyphicon-off" style="font-size:20px;"></span></a></li>
                </ul>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <div class="jumbotron text-center">

                        <h2><strong>EDIT ACCOUNT</strong>:</h2>
                        <h5>If you change your login or password, you will need to log in again.</h5>
                        <h5>(with new login or password)</h5>
                        <form:form method="POST" modelAttribute="userEdit" class="form-signin">

                            <spring:bind path="username">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                                autofocus="true" value="${user.username}"></form:input>
                                    <form:errors path="username"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="name">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="name" class="form-control" placeholder="Name"
                                                autofocus="true" value="${user.name}" readonly="true"></form:input>
                                    <form:errors path="name"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="surname">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="surname" class="form-control" placeholder="Surname"
                                                autofocus="true" value="${user.surname}"></form:input>
                                    <form:errors path="surname"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="money">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="money" class="form-control" placeholder="Budget (PLN)"
                                                autofocus="true" value="${user.money}"></form:input>
                                    <form:errors path="money"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="password">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="password" class="form-control" placeholder="New password"></form:input>
                                    <form:errors path="password"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="passwordConfirm">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="passwordConfirm" class="form-control"
                                                placeholder="Repeat password"></form:input>
                                    <form:errors path="passwordConfirm"></form:errors> 
                                    </div>
                            </spring:bind>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Save changes</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>

        <div class="navbar-fixed-bottom">
            <div class="panel-footer">
                Copyright: Project FP 2018 (Kamil Zemczak).
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/static/js/bootstrap.min.js"></script>
    </body>
</html>
