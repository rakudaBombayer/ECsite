<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注文者情報確認</title>
   <style>
body {
	font-family: 'Oswald', sans-serif;
	background-color: #000000;
	padding: 40px;
}

h2 {
	text-align: center;
}

form {
	max-width: 500px;
	margin: auto;
	background: #000000;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin-top: 15px;
	color: white;
}

input[type="text"], input[type="password"], input[type="email"], input[type="date"],
	select {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="submit"] {
	margin-top: 25px;
	width: 30%;
	padding: 12px;
	background-color: #db433d;
	border: none;
	color: white;
	font-size: 16px;
	border-radius: 4px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #FF0000;
}

.message {
	text-align: center;
	margin-top: 20px;
}

.success {
	font-size: 1.5em; /* 1.5倍の大きさ */
	color: green; /* もし色も変えたい場合 */
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
	color: #555;
	text-decoration: none;
}

.back-link a:hover {
	text-decoration: underline;
}

h2 {
	color: white;
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
