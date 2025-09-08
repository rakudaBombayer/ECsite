<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン画面</title>
    
</head>
<body>
    <div class="container">
        <h1>フィギュアショップ</h1>
        <form action="LoginServlet" method="post">
            ユーザーID:<br>
            <input type="text" name="userId" required><br>
            パスワード:<br>
            <input type="password" name="pass" required><br>
            <input type="submit" value="ログイン">
            <!-- 会員登録ボタン -->
            <a href="MenuServlet" class="register-button">ログイン(仮)</a>
            <a href="RegisterServlet" class="register-button">会員登録(仮)へ</a>
        </form>
    </div>
</body>
</html>

<style>
        body {
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 40px;
        }

        form {
            background-color: white;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }

        input[type="text"], input[type="password"] {
            width: 250px;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"], .register-button {
            width: 100%;
            padding: 10px;
            margin-top: 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover,
        .register-button:hover {
            background-color: #45a049;
        }

        .register-button {
            margin-top: 10px;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }
    </style>
