<!DOCTYPE html>
<html>
<head>
    <title>Спортивные Турниры</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .error-message {
            color: red;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="mt-4">MIREA SPORT FAMILY</h1>

    <#if errors??>
        <div class="alert alert-danger mt-4">
            <h4>Ошибки:</h4>
            <ul>
                <#list errors as error>
                    <li>${error.field}: ${error.defaultMessage}</li>
                </#list>
            </ul>
        </div>
    </#if>

    <h3 class="mt-4">Создать новый турнир</h3>
    <form action="/tournaments/create" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="tournamentName">Название турнира</label>
            <input type="text" class="form-control" id="tournamentName" name="name" placeholder="Введите название турнира">
            <small class="form-text text-danger">
                <#if errors??>
                    <#list errors as error>
                        <#if error.field == "name">
                            ${error.defaultMessage}
                        </#if>
                    </#list>
                </#if>
            </small>
        </div>

        <div class="form-group">
            <label for="tournamentLocation">Место проведения</label>
            <input type="text" class="form-control" id="tournamentLocation" name="location" placeholder="Введите место проведения">
            <small class="form-text text-danger">
                <#if errors??>
                    <#list errors as error>
                        <#if error.field == "location">
                            ${error.defaultMessage}
                        </#if>
                    </#list>
                </#if>
            </small>
        </div>

        <div class="form-group">
            <label for="tournamentStartDate">Дата начала</label>
            <input type="date" class="form-control" id="tournamentStartDate" name="startDate">
            <small class="form-text text-danger">
                <#if errors??>
                    <#list errors as error>
                        <#if error.field == "startDate">
                            ${error.defaultMessage}
                        </#if>
                    </#list>
                </#if>
            </small>
        </div>

        <div class="form-group">
            <label for="tournamentEndDate">Дата окончания</label>
            <input type="date" class="form-control" id="tournamentEndDate" name="endDate">
            <small class="form-text text-danger">
                <#if errors??>
                    <#list errors as error>
                        <#if error.field == "endDate">
                            ${error.defaultMessage}
                        </#if>
                    </#list>
                </#if>
            </small>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Добавить турнир</button>
    </form>

    <hr>
    <a href="/" class="text-center d-block mt-4">Главная страница</a>
</div>
</body>
</html>
