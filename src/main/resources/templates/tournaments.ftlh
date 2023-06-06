<!DOCTYPE html>
<html>
<head>
    <title>Турниры</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .tournament-container {
            margin-top: 50px;
        }

        .tournament-heading {
            font-size: 24px;
            font-weight: bold;
        }

        .tournament-details {
            margin-top: 10px;
        }

        .tournament-link {
            color: #007bff;
            text-decoration: none;
        }

        .tournament-link:hover {
            text-decoration: underline;
        }

        .no-tournaments {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Турниры MIREA SPORT FAMILY</h1>
    <hr>

    <div class="tournament-container">
        <#list tournaments as tournament>
            <div class="tournament-details">
                <h4 class="tournament-heading">${tournament.name}</h4>
                <p>Место проведения: ${tournament.location}</p>
                <p><a class="tournament-link" href="/tournaments/${tournament.id}">Подробнее...</a></p>
            </div>
        <#else>
            <h3 class="text-center no-tournaments">Турниров нет</h3>
        </#list>
    </div>

    <div class="text-center">
        <a href="/" class="back-link">Главная страница</a>
    </div>
</div>
</body>
</html>
