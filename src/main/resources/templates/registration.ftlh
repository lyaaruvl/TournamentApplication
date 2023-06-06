<!DOCTYPE html>
<html>
<head>
    <title>Форма регистрации</title>
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

        .btn-register {
            width: 100%;
        }

        .error-message {
            color: #dc3545;
            font-size: 10px;
            margin-top: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Регистрация</h1>
    <form action="/registration" method="post">
        <div class="form-group">
            <label for="name">Имя пользователя:</label>
            <input type="text" id="name" class="form-control" name="name" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" class="form-control" name="email" required>
        </div>

        <div class="form-group">
            <label for="phoneNumber">Номер телефона:</label>
            <input type="text" id="phoneNumber" class="form-control" name="phoneNumber" required>
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" class="form-control" name="password" required>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <input type="submit" value="Зарегистрироваться" class="btn btn-primary btn-register">

        <div class="error-message">
            <#if errorMessage??>
                ${errorMessage}
            </#if>
        </div>
    </form>
</div>
</body>
</html>
