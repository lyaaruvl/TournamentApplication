<!DOCTYPE html>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .navbar {
            background-color: #f8f9fa;
            padding: 20px;
        }

        .navbar-brand {
            font-size: 24px;
            font-weight: bold;
        }

        .text-primary {
            text-color: #074CED;
        }

        .nav-link {
            font-size: 18px;
        }

        .container {
            margin-top: 50px;
            text-align: center;
        }

        .jumbotron {
            padding: 50px;
        }

        .jumbotron h1 {
            font-size: 36px;
            font-weight: bold;
        }

        .jumbotron p {
            font-size: 18px;
            margin-bottom: 20px;
        }
        .btn-primary {
            width: 100%;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="/" class="text-primary">MIREA SPORT FAMILY</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <#if user.email??>
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" class="btn btn-primary" value="Выйти"/>
                <#else>
                    <a href="/login" class="btn btn-primary">Войти</a>
                </#if>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <#if user.email??>
            <h1>Профиль пользователя: <i>${user.name}</i></h1>
            <p>Добро пожаловать на наш сайт!</p>
            <#if user.isAdmin()>
                <a href="/admin" class="btn btn-primary btn-block">Панель администратора</a>
                <br>
                <a href="/tournaments/create" class="btn btn-primary btn-block">Создать турнир</a>
                <br>
                <a href="/teams/export" class="btn btn-primary btn-block">Выгрузить список команд</a>
                <br>
            <#else>
                <a href="/teams/create" class="btn btn-primary btn-block">Создать команду</a>
                <br>
            </#if>
        </#if>
            <a href="/tournaments" class="btn btn-primary btn-block">Посмотреть турниры</a>
            <br>
            <a href="/teams" class="btn btn-primary btn-block">Посмотреть команды</a>
            <br>
            <hr>
            <h4>Зарегистрированные турниры</h4>
            <#list tournaments as tournament>
            <div>
                <p><b>${tournament.name}</b> | <a href="/tournaments/${tournament.id}">Подробнее...</a></p>
            </div>
            <#else>
                <h3>Турниров нет</h3>
            </#list>
    </div>
</div>
</body>
</html>
