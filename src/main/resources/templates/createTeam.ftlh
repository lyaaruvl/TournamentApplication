<!DOCTYPE html>
<html>
<head>
    <title>Создать команду</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }

        h1 {
            margin-bottom: 30px;
        }

        .error-message {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Создание команды</h1>
    <#if errors??>
        <div class="alert alert-danger">
            <h3>Ошибки:</h3>
            <ul>
                <#list errors as error>
                    <li>${error.field}: ${error.defaultMessage}</li>
                </#list>
            </ul>
        </div>
    </#if>
    <form action="/teams/create" method="post">
        <div class="form-group">
            <label for="name">Название команды:</label>
            <input type="text" name="name" id="name" class="form-control" required/>
            <#if errors??>
                <#list errors as error>
                    <#if error.field == "name">
                        <p class="error-message">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>
        </div>
        <div class="form-group">
            <label>Участники:</label>
            <br>
            <#list users as user>
                <div class="form-check">
                    <input type="checkbox" name="members" value="${user.id}" class="form-check-input"/>
                    <label class="form-check-label">${user.name}</label>
                </div>
            </#list>
            <#if errors??>
                <#list errors as error>
                    <#if error.field == "members">
                        <p class="error-message">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>
        </div>
        <div class="form-group">
            <label for="tournament">Турнир:</label>
            <select name="tournament" id="tournament" class="form-control" required>
                <#list tournaments as tournament>
                    <option value="${tournament.id}">${tournament.name}</option>
                </#list>
            </select>
            <#if errors??>
                <#list errors as error>
                    <#if error.field == "tournament">
                        <p class="error-message">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Создать команду</button>

    </form>

    <hr>
    <a href="/" class="text-center d-block mt-4">Главная страница</a>
</div>
</body>
</html>
