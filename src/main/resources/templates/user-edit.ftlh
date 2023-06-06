<!DOCTYPE html>
<html>
<head>
    <title>TOURNAMENT HUB</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }

        .form-check {
            margin-bottom: 10px;
        }

        .save-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">MIREA SPORT FAMILY</h1>
    <hr>

    <h3 class="text-center">Редактирование пользователя: ${user.name}</h3>

    <form action="/admin/user/edit" method="post">
        <div class="form-group">
            <#list roles as role>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="${role}" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                    <label class="form-check-label" for="${role}">${role}</label>
                </div>
            </#list>
        </div>

        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">

        <div class="text-center save-button">
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
    </form>

    <div class="text-center">
        <a href="/" class="back-link">Главная страница</a>
    </div>
</div>
</body>
</html>
