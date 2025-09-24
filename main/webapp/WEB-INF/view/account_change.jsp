<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員情報変更画面</title>
    
</head>
<body>

<h2>会員情報変更</h2>
<div class="message">
    <c:if test="${not empty successMessage}">
        <p class="success">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p class="error">${errorMessage}</p>
    </c:if>
</div>
<form action="AccountChangeServlet" method="post">
    <label>氏名:</label>
    <input type="text" name="shimei" value="${account.shimei}" required>

    <label>パスワード:</label>
    <input type="password" name="password" value="${account.password}" required>

    <label>郵便番号:</label>
    <input type="text" name="yuubin_bangou" value="${account.yuubinBangou}">

    <label>住所:</label>
    <input type="text" name="address" value="${account.address}">

    <label>電話番号:</label>
    <input type="text" name="denwa_bangou" value="${account.denwaBangou}">

    <label>生年月日:</label>
    <input type="date" name="seinengappi" value="<fmt:formatDate value='${account.seinengappi}' pattern='yyyy-MM-dd'/>">

    <label>メールアドレス:</label>
    <input type="email" name="mail_address" value="${account.mailAddress}">

    <label>支払い方法:</label>
    <input type="text" name="shiharaiHouhou" value="${account.shiharaiHouhou}">
    
    <input type="submit" value="変更を保存">
</form>

<div class="back-link">
    <a href="MenuServlet">← メニューに戻る</a>
</div>

</body>
</html>


<style>
    body {
        font-family: sans-serif;
        /* 背景画像を設定。URLは好きな画像に変更してください */
        background: url('images/mori.jpg') no-repeat center center fixed;
        background-size: cover;     /* 画像を画面いっぱいに拡大 */
        background-position: center; /* 中央寄せ */
        background-repeat: no-repeat;
        padding: 40px;
        margin: 0;
        height: 100vh;
        color: white;
        /* 黒っぽいオーバーレイを重ねるために位置調整 */
        position: relative;
    }
    h2 {
        text-align: center;
        color: white;
        text-shadow: 1px 1px 4px rgba(0,0,0,0.7);
       text-shadow: 0 0 10px red, 0 0 20px crimson;
    }

    form {
        max-width: 500px;
        margin: auto;
        background: rgba(0, 0, 0, 0.7); /* 半透明の黒背景 */
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 0 20px rgba(255, 0, 0, 0.7);
    }

    label {
        display: block;
        margin-top: 15px;
        color: #f5f5f5;
        font-weight: bold;
        text-shadow: 0 0 3px rgba(0,0,0,0.8);
    }

    input[type="text"],
    input[type="password"],
    input[type="email"],
    input[type="date"],
    select {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: none;
        border-radius: 6px;
        outline: none;
        box-sizing: border-box;
        font-size: 1em;
        background-color: rgba(255, 255, 255, 0.9);
        color: #333;
        transition: box-shadow 0.3s ease;
    }

    input[type="text"]:focus,
    input[type="password"]:focus,
    input[type="email"]:focus,
    input[type="date"]:focus,
    select:focus {
        box-shadow: 0 0 8px 2px #ff0000;
        background-color: #fff;
    }

    input[type="submit"] {
        display: block;
        margin: 25px auto 0 auto; 
        width: 150px;             
        padding: 12px;
        background-color: #ff0000;
        border: none;
        color: white;
        font-size: 16px;
        border-radius: 8px;
        cursor: pointer;
        box-shadow: 0 0 10px #ff0000;
        transition: background-color 0.3s ease, box-shadow 0.3s ease;
    }

    input[type="submit"]:hover {
        background-color: #af0e0e;
        box-shadow: 0 0 15px #af0e0e;
    }

    .message {
        text-align: center;
        margin-top: 20px;
    }

    .success {
        font-size: 1.5em; 
        color: #90ee90; /* 緑っぽい成功色 */
        text-shadow: 0 0 5px rgba(144,238,144,0.8);
    }

    .error {
        font-size: 1.5em;
        color: #ff6666; /* 柔らかい赤 */
        text-shadow: 0 0 5px rgba(255,102,102,0.8);
    }

    .back-link {
        text-align: center;
        margin-top: 30px;
        text-shadow: 0 0 8px #ff0000;
    }

    .back-link a {
        color: #ffffff;
        font-weight: bold;
        text-decoration: none;
        text-shadow: 0 0 8px #ff0000;
        transition: text-decoration 0.3s ease;
    }

    .back-link a:hover {
        text-decoration: underline;
    }
</style>
