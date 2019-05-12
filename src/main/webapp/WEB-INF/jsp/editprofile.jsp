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

        <title>Ready4RUN</title>

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

        <div class="navbar navbar-inverse">
            <a href="/" class="navbar-brand">Ready4RUN</a>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Panel użytkownika<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="user-account">Edytuj profil</a></li>
                        </ul></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <form id="logoutForm" method="POST" action="${contextPath}/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </c:if>

                    <li class="navbar-text">Zalogowany jako ${user.name} ${user.surname}</li>
                    <li><a href="user-account"><span class="glyphicon glyphicon-cog" style="font-size:20px;"></span></a></li>
                    <li><a onclick="document.forms['logoutForm'].submit()" href="#"><span class="glyphicon glyphicon-off" style="font-size:20px;"></span></a></li>
                </ul>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="jumbotron editProfileForm">
                        <div class="text-center">
                            <h2><strong>EDYCJA PROFILU</strong>:</h2>
                        </div>
                        <form:form method="POST" modelAttribute="userEdit" class="form-signin">
                            <spring:bind path="username">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label>Nazwa użytkownika</label>
                                    <div class="input-group">
						                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <form:input path="username" type="text" class="form-control" maxlength="50" placeholder="Nazwa użytkownika" autofocus="true" value="${user.username}"></form:input>
					                </div> 
                                    <form:errors path="username"></form:errors>
                                </div>
                            </spring:bind>

                            <div class="row">
                                <div class="col-md-6">
                                    <spring:bind path="name">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <label>Imię</label>
                                            <div class="input-group">
						                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                <form:input path="name" type="text" class="form-control" maxlength="50" placeholder="Imię" value="${user.name}"></form:input>
					                        </div>  
                                            <form:errors path="name"></form:errors>
                                            </div>
                                    </spring:bind>
                                </div>
                                <div class="col-md-6">
                                    <spring:bind path="surname">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <label>Nazwisko</label>
                                            <div class="input-group">
						                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                <form:input path="surname" type="text" class="form-control" maxlength="50" placeholder="Nazwisko" value="${user.surname}"></form:input>
                                            </div>
                                        <form:errors path="surname"></form:errors>
                                        </div>
                                    </spring:bind>
                                </div>    
                            </div>

                            <spring:bind path="dateOfBirth">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label>Data urodzenia</label>
                                    <form:input type="date" path="dateOfBirth" id="example-date-input" class="form-control" autofocus="true" value="${user.editDate}"></form:input>
                                    </div>
                            </spring:bind>
                            
                            <label>Płeć</label>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="materialInline1" value="male" name="gender" checked="">
                                <label class="form-check-label" for="male">MĘŻCZYZNA</label>

                                <input type="radio" class="form-check-input" id="materialInline2" value="female" name="gender">
                                <label class="form-check-label" for="female">KOBIETA</label>
                            </div>

                            <spring:bind path="weight">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label>Waga</label>
                                    <form:input type="number" path="weight" class="form-control" placeholder="Waga" value="${user.weight}"></form:input>
                                    <form:errors path="weight"></form:errors>
                                </div>
                            </spring:bind>
                            
                            <spring:bind path="height">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label>Wzrost</label>
                                    <form:input type="number" path="height" class="form-control" placeholder="Wzrost" value="${user.height}"></form:input>
                                    <form:errors path="height"></form:errors>
                                </div>
                            </spring:bind>
                            
                            <label>Ulubiona dyscyplina</label>
                            <select class="form-control" name="favourite">
                                <option selected="" disabled="true">${user.favourite}</option>
                                 <option value="None" disabled="true">---------------------------------------------------</option>
                                <option value="Pływanie">Bieganie</option>
                                <option value="Pływanie">Pływanie</option>
                                <option value="Jazda na rowerze">Jazda na rowerze</option>
                                <option value="Trening siłowy">Trening siłowy</option>
                                <option value="Chodzenie">Chodzenie</option>
                            </select>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Zapisz zmiany</button>
                        </form:form>
                    </div>
                </div>
            </div>
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
