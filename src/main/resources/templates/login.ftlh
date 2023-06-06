<!DOCTYPE html>
<html>
<head>
    <title>Форма авторизации</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f2f2f2;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            margin-top: 100px;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-control {
            border-radius: 3px;
        }

        .btn-primary {
            width: 100%;
        }

        .register-link {
            display: block;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Авторизация</h1>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">Имя пользователя:</label>
            <input type="text" id="username" class="form-control" name="username" required>
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" class="form-control" name="password" required>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Вход" class="btn btn-primary btn-login">

        <a href="/registration" class="register-link">Зарегистрироваться</a>
    </form>
</div>
</body>
</html>



