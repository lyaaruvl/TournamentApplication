<!DOCTYPE html>
<html>
<head>
    <title>Спортивные Турниры</title>
</head>
<body>
<h1>Спортивные Турниры</h1>

<#if user.email??>
    <hr>
    <h3>Создать новый турнир</h3>
    <form action="/tournaments" method="post">
        Название турнира: <input type="text" name="name"/><br><br>
        Место проведения: <input type="text" name="location"/><br><br>
        Дата начала: <input type="date" name="startDate"/><br><br>
        Дата окончания: <input type="date" name="endDate"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить турнир"/>
    </form>
</#if>
<a href="/">Главная страница</a>
</body>
</html>
