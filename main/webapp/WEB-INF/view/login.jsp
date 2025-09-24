<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン画面</title>

    <style>
        /* ===== 背景画像アニメーション ===== */
        body {
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            color: white;
            height: 100vh;
            background: url('images/bg-gunpla-bike.jpg') repeat-x;
            background-size: cover;
            animation: scrollBackground 60s linear infinite;
            position: relative;
            overflow: hidden;
        }

        /* 暗めのフィルター（文字を見やすく） */
        body::before {
            content: "";
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.6); /* 半透明の黒 */
            z-index: 0;
        }

        @keyframes scrollBackground {
            0% { background-position: 0 0; }
            100% { background-position: -1920px 0; }
        }

        /* ===== コンテナ ===== */
        .container {
            position: relative;
            z-index: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        /* ===== タイトル ===== */
        h1 {
            font-size: 36px;
            margin-bottom: 40px;
            font-weight: bold;
            color: #ffffff;
            text-shadow: 0 0 10px red, 0 0 20px crimson;
            animation: fadeIn 1.2s ease-out;
        }

        /* ===== ログインフォーム ===== */
        form {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(255, 0, 0, 0.3);
            text-align: center;
            font-weight: bold;
            color: white;
            animation: fadeInUp 1.5s ease-out;
        }

        input[type="text"], input[type="password"] {
            width: 250px;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"], .register-button {
            width: 250px;
            padding: 10px;
            background-color: #ff0000;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            box-sizing: border-box;
            transition: transform 0.3s ease, background-color 0.3s ease;
        }

        input[type="submit"]:hover,
        .register-button:hover {
            background-color: #af0e0e;
            transform: scale(1.05);
        }

        .register-button {
            display: inline-block;
            margin-top: 15px;
            text-decoration: none;
            text-align: center;
            line-height: 1.5;
        }

        p {
            font-weight: bold;
            margin-top: 20px;
        }

        /* ===== アニメーション定義 ===== */
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>GUNPLA SHOP</h1>

        <form action="LoginServlet" method="post">
            ユーザーID:<br>
            <input type="text" name="userId" required><br>
            パスワード:<br>
            <input type="password" name="pass" required><br>
            
            <div style="display:flex; flex-direction: column; gap: 15px; margin-top: 15px; align-items: center;">
            <input type="submit" value="ログイン">
             <!-- <a href="RegisterServlet" class="register-button">会員登録</a> -->
             <button type="button" onclick="location.href='RegisterServlet'" class="register-button">
  会員登録
</button>
</div>
        </form>

        <!-- メッセージ -->
        <c:if test="${not empty successMessage}">
            <p style="color: #00ffff;">${successMessage}</p>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <p style="color: #ff6666;">${errorMessage}</p>
        </c:if>
    </div>
</body>
</html>
