<!DOCTYPE html>
<html>
<head>
    <title>Панель администратора</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>

        .container {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .admin-panel {
            margin-top: 50px;
        }

        .admin-panel h1 {
            font-size: 36px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 30px;
        }

        .admin-panel table {
            width: 100%;
            margin-bottom: 20px;
        }

        .admin-panel table th {
            background-color: #f8f9fa;
            font-weight: bold;
            text-align: left;
            padding: 10px;
        }

        .admin-panel table td {
            padding: 10px;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="admin-panel">
        <h1>Панель администратора</h1>

        <h2>Список пользователей</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Email</th>
                <th>Номер телефона</th>
                <th>Активность</th>
                <th>Роль</th>
                <th>Редактирование</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <#list users as user>
            <tr>
                <th>${user.email}</th>
                <th>${user.phoneNumber}</th>
                <th><#if user.active>true<#else>false</#if></th>
                <th><#list user.roles as role>${role}</#list></th>
                <th><a href="/admin/user/edit/${user.id}">Редактировние</a></th>
            </tr>
            <#else>
                <h3>Пользователей нет</h3>
            </#list>
            </tbody>
        </table>
        <a href="/" class="text-center d-flex justify-content-center">Главная страница</a>
    </div>
</div>
</body>
</html>
