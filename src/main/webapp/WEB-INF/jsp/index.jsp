<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="Sat, 01 Dec 2001, 00:00:00 GMT">

        <title>Ready4RUN</title>

        <link href="static/css/bootstrap.min.css" rel="stylesheet">
        <link href="static/css/style.css" rel="stylesheet">
        <style>
            .jumbotron{
                background-color:#262626;
                color:white;
                padding-top: 5px;
                padding-bottom: 20px;
            }
            .carousel {
                width:626px;
                height:417px;
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

    <center>
        <div class="container">
            <h2><strong>Pokaz zdjęć użytkowników</strong></h2>
            <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="2000">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="item active">
                        <img src="static/images/carousel1.jpg">
                    </div>

                    <div class="item">
                        <img src="static/images/carousel2.jpg">
                    </div>

                    <div class="item">
                        <img src="static/images/carousel3.jpg">
                    </div>
                    <div class="item">
                        <img src="static/images/carousel4.jpg">
                    </div>
                </div>

                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Poprzedni</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Następny</span>
                </a>
            </div>
        </div>
    </center>


    <div class="navbar-fixed-bottom">
        <div class="panel-footer">
            Copyright: Praca magisterska 2019 (Kamil Zemczak).
        </div>
    </div>

    <script src="static/js/jquery-1.11.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>

</body>
</html>
