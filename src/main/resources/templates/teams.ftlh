<!DOCTYPE html>
<html>
<head>
    <title>TOURNAMENT HUB</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }

        .team-section {
            margin-bottom: 30px;
        }

        .team-name {
            margin-top: 10px;
        }

        .member-list {
            margin-left: 20px;
        }

        .no-teams {
            margin-top: 20px;
            color: #dc3545;
            font-weight: bold;
        }

        .back-link {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">MIREA SPORT FAMILY</h1>
    <hr>

    <#list tournaments as tournament>
        <div>
            <h2 class="text-primary">${tournament.name}</h2>
            <p><strong>Место проведения:</strong> ${tournament.location}</p>
            <p><strong>Дата начала:</strong> ${tournament.startDate}</p>
            <p><strong>Дата окончания:</strong> ${tournament.endDate}</p>

            <div class="team-section">
                <h3 class="text-success">Команды:</h3>
                <#list tournament.teams as team>
                    <div>
                        <h4 class="team-name text-info">${team.name}</h4>
                        <p><strong>Участники:</strong></p>
                        <ul class="member-list">
                            <#list team.members as member>
                                <li>${member.name}</li>
                            </#list>
                        </ul>
                    </div>
                </#list>
                <#if tournament.teams?size == 0>
                    <p class="no-teams">Нет команд</p>
                </#if>
            </div>
            <hr>
        </div>
    <#else>
        <h2 class="text-danger">Нет турниров</h2>
    </#list>

    <div class="text-center">
        <a href="/" class="back-link">Главная страница</a>
    </div>
</div>
</body>
</html>
