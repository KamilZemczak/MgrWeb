<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="description" content="">
        <meta name="author" content="">

        <title>Registration</title>

        <link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/static/css/common.css" rel="stylesheet">

        <style>
            .jumbotron{
                background-image: url("static/images/test2.png");
                color:white;
                padding: 5px;
            }
            .asd{
                color: white;
                text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
            }
        </style>
    </head>
    <body>
        <div class="container mainPanel">
            <center> <img src="static/images/logo.png" width="400" height="215"> </center><br>
            <br>
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <div class="jumbotron text-center">
                        <form:form method="POST" modelAttribute="userForm" class="form-signin">

                            <spring:bind path="username">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="username" class="form-control" placeholder="Nazwa użytkownika"
                                                autofocus="true"></form:input>
                                    <form:errors path="username"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="password">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="password" class="form-control" placeholder="Hasło"></form:input>                                       
                                    <form:errors path="password"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="passwordConfirm">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="passwordConfirm" class="form-control"
                                                placeholder="Powtórz hasło"></form:input>
                                    <form:errors path="passwordConfirm"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="name">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="name" class="form-control" placeholder="Imię"
                                                autofocus="true"></form:input>
                                    <form:errors path="name"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="surname">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="surname" class="form-control" placeholder="Nazwisko"
                                                autofocus="true"></form:input>
                                    <form:errors path="surname"></form:errors>
                                    </div>
                            </spring:bind>

                             <spring:bind path="dateOfBirth">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="example-date-input" class="col-2 col-form-label"><asd><strong>DATA URODZENIA</strong></asd></label>
                                <form:input type="date" path="dateOfBirth" value="2011-08-19" id="example-date-input" class="form-control" autofocus="true"></form:input>
                            </div>
                                 </spring:bind>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Zarejstruj się</button>
                        </form:form>
                    </div>
                </div>
            </div>
            <center><h4><a href="${contextPath}/login">POWRÓT DO LOGOWANIA.</a></h4></center>
        </div>

        <div class="navbar-fixed-bottom">
            <div class="panel-footer">
                Copyright: Praca magisterska 2019 (Kamil Zemczak).
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/static/js/bootstrap.min.js"></script>          
    </body>
</html>
