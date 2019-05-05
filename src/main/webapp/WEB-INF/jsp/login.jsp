<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width = device-width, initial-scale = 1">

        <title>Login</title>
        <link href="${contextPath}/static/css/bootstrap.css" rel="stylesheet">
        <link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/static/css/common.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

        <style type="text/css">
            .foo {
                background-image: url("static/images/login.png");
                color:white;
                padding: 5px;
                text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
                border-radius: 10px;
            }
        </style>

    </head>
    <body>
        <div class="container mainPanel">
            <center> <img src="static/images/logo.png" width="400" height="215"> </center><br>
            <center><h4>Witaj w serwisie społecznościowym poświęconym biegaczom.</h4></center>
            <center><h4>Dostęp do strony dopiero po zalogowaniu.</h4></center>

            <div class="row">
                <div class="col-md-4"> </div>
                <div class="col-md-4">
                    <div class="foo" width="390" height="303">
                        <form method="POST" action="${contextPath}/login" class="form-signin">
                            <div class="form-group ${error != null ? 'has-error' : ''}">
                                <span>${message}</span>
                                <input name="username" type="text" class="form-control" placeholder="Nazwa użytkownika" autofocus="true"/>
                                <input name="password" type="password" class="form-control" placeholder="Password"/>
                                <span><strong>${error}</strong></span>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj się</button> 
                            </div>
                    </div>
                </div>
            </div>
            <center><h4>Jeśli nie masz konta: <a href="${contextPath}/registration">ZAREJESTRUJ SIĘ.</a></h4></center>
        </div>

        <div class="navbar-fixed-bottom">
            <div class="panel-footer">
                Copyright: Praca magisterska 2019 (Kamil Zemczak).
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
