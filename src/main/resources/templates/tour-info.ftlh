<!DOCTYPE html>
<html>
<head>
    <title>Tournament Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .tournament-details {
            padding: 50px;
            background-color: #f8f9fa;
        }

        .team-container {
            margin-top: 30px;
            border: 1px solid #ccc;
            padding: 20px;
            background-color: #fff;
        }

        .tournament-title {
            font-size: 18px;
            font-weight: bold;
            margin-top: 0;
            margin-bottom: 20px;
        }

        .member-list {
            margin-top: 10px;
        }

        .member-list-item {
            padding: 5px 0;
            border-bottom: 1px solid #ccc;
        }

    </style>
</head>
<body>
<div class="container tournament-details">
    <h1 class="text-center">Детали турнира</h1>
    <hr>

    <div class="row">
        <div class="col-lg-6 mx-auto">
            <h2 class="tournament-title text-center">${tournament.name}</h2>
            <p><b>Место проведения:</b> ${tournament.location}</p>
            <p><b>Дата начала:</b> ${tournament.startDate}</p>
            <p><b>Дата завершения:</b> ${tournament.endDate}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6 mx-auto">
            <h3 class="text-center">Команды:</h3>
            <div class="team-container">
                <#list tournament.teams as team>
                    <div class="team">
                        <h4 class="team-name">${team.name}</h4>
                        <ul class="member-list">
                            <#list team.members as member>
                                <li class="member-list-item">${member.name}</li>
                            </#list>
                        </ul>
                    </div>
                </#list>
            </div>
        </div>
    </div>
    <a href="/" class="text-center d-block mt-4">Главная страница</a>
</div>
</body>
</html>
