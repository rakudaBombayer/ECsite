<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注文者情報確認</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Oswald&display=swap');

        body {
            font-family: 'Oswald', sans-serif;
            background: url('<%= request.getContextPath() %>/images/order.jpg') no-repeat center center fixed;
            background-size: cover;
            color: white;
            margin: 0;
            padding: 40px;
            position: relative;
            z-index: 0;
        }
       

        h2 {
            text-align: center;
            color: white;
            text-shadow: 0 0 10px red, 0 0 20px crimson;
        }

        form {
            max-width: 500px;
            margin: auto;
            background: rgba(34, 34, 34, 0.9);
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(255, 255, 255, 0.1);
        }

        label {
            display: block;
            margin-top: 15px;
            color: white;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #555;
            border-radius: 4px;
            background-color: #111;
            color: white;
        }

        input[type="submit"] {
            margin-top: 25px;
            width: 40%;
            padding: 12px;
            background-color: red;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: darkred;
        }

        .message {
            text-align: center;
            margin-top: 20px;
        }

        .success {
            font-size: 1.5em;
            color: green;
        }

        .error {
            font-size: 1.5em;
            color: red;
        }

        .back-link {
            text-align: center;
            margin-top: 30px;
           
        }

        .back-link a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            text-shadow: 0 0 8px #ff0000;
        }

        .back-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<h2>注文者情報確認</h2>

<div class="message">
    <c:if test="${not empty successMessage}">
        <p class="success">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p class="error">${errorMessage}</p>
    </c:if>
</div>

<form action="OrderServlet" method="post">
    <label>氏名:</label>
    <input type="text" name="shimei" value="${account.shimei}" required>

    <label>郵便番号:</label>
    <input type="text" name="yuubin_bangou" value="${account.yuubinBangou}">

    <label>住所:</label>
    <input type="text" name="address" value="${account.address}">

    <label>電話番号:</label>
    <input type="text" name="denwa_bangou" value="${account.denwaBangou}">

    <label>メールアドレス:</label>
    <input type="email" name="mail_address" value="${account.mailAddress}">

    <label>支払い方法:</label>
    <input type="text" name="shiharaiHouhou" value="${account.shiharaiHouhou}">

    <div style="display: flex; justify-content: center;">
        <input type="submit" value="次へ">
    </div>
</form>

<div class="back-link">
    <a href="CartListServlet">← カートに戻る</a>
</div>

</body>
</html>
