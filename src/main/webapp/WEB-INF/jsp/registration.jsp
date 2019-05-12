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

        <title>Ready4RUN - rejestracja</title>

        <link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/static/css/common.css" rel="stylesheet">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <style>
            .jumbotron{
                background-image: url("static/images/registration.png");
                color:white;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container mainPanel">
            <center><img src="static/images/logo.png" width="400" height="215"></center><br>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="jumbotron editProfileForm">
                        <form:form method="POST" modelAttribute="userForm" class="form-signin"  width="390">

                            <spring:bind path="username">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="input-group loginInput">
						                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <form:input path="username" type="text" class="form-control" maxlength="50" placeholder="Nazwa użytkownika" autofocus="true"></form:input>
					                </div>  
                                    <form:errors path="username"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="password">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="input-group loginInput">
						                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                        <form:input path="password" type="password" class="form-control" maxlength="50" placeholder="Hasło"></form:input>
					                </div>                                
                                    <form:errors path="password"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="passwordConfirm">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="input-group loginInput">
						                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                        <form:input path="passwordConfirm" type="password" class="form-control" maxlength="50" placeholder="Powtórz hasło"></form:input>
					                </div>
                                    <form:errors path="passwordConfirm"></form:errors>
                                    </div>
                            </spring:bind>
                            <div class="row">
                                <div class="col-md-6">
                                    <spring:bind path="name">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <div class="input-group loginInput">
						                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                <form:input path="name" type="text" class="form-control" maxlength="50" placeholder="Imię"></form:input>
					                        </div>  
                                            <form:errors path="name"></form:errors>
                                            </div>
                                    </spring:bind>
                                </div>
                                <div class="col-md-6">
                                    <spring:bind path="surname">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <div class="input-group loginInput">
						                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                <form:input path="surname" type="text" class="form-control" maxlength="50" placeholder="Nazwisko"></form:input>
					                    </div>
                                        <form:errors path="surname"></form:errors>
                                        </div>
                                    </spring:bind>
                                </div>    
                            </div>

                            <spring:bind path="dateOfBirth">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="example-date-input" class="col-2 col-form-label"><strong>Data urodzenia</strong></label>
                                    <form:input type="date" path="dateOfBirth" id="example-date-input" class="form-control" autofocus="true"></form:input>
                                    </div>
                            </spring:bind>

                            <label>Płeć</label>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="materialInline1" value="male" name="gender" checked="">
                                <label class="form-check-label" for="male">MĘŻCZYZNA</label>

                                <input type="radio" class="form-check-input" id="materialInline2" value="female" name="gender">
                                <label class="form-check-label" for="female">KOBIETA</label>
                            </div>

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
