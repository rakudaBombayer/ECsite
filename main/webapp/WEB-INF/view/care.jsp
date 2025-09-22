<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入内容確認画面</title>

<style>
body {
	font-family: 'Oswald', sans-serif;
}

h1 {
	margin-bottom: 20px;
	color: white;
	text-align: center;
}

table {
	border-collapse: separate;
	border-spacing: 0;
	width: 80%;
	margin: 0 auto 20px auto;
	border: 2px solid black; /* テーブル外枠を黒く */
	border-radius: 12px;
	overflow: hidden;
}

th, td {
	border: 1px solid black; /* セルの罫線も黒く */
	padding: 10px;
	text-align: center;
}

th, td {
	border: 1px solid #999;
	padding: 8px;
	text-align: center;
}

.form-section {
	margin-top: 30px;
}

.form-group {
	margin-bottom: 10px;
}

input[type="text"], textarea {
	width: 300px;
	padding: 5px;
}

textarea {
	height: 100px;
}

.register-button {
	padding: 10px 20px;
	background-color: #2b90d9;
	color: white;
	border: none;
	cursor: pointer;
}

.register-button:hover {
	background-color: #1c6ca8;
}

body {
	background-color: black;
	color: white;
}

.register-button {
	background-color: red;
	color: white;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 5px;
}

/* 戻るボタン専用のスタイル */
.back-button {
	background-color: gray;
	color: white;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 5px;
}
/* ボタンを中央揃えで横並びにする */
.button-container {
	display: flex;
	justify-content: center;
	gap: 20px;
	margin-top: 30px;
}
</style>
</head>

<body>

	<h1>購入してよろしいですか？</h1>

	<c:if test="${empty cartList}">
		<p>カートには商品がありません。</p>
	</c:if>

	<c:if test="${not empty cartList}">

		<table>
			<tr style="background-color: #fff; color: #000;">
				<th>商品</th>
				<th>価格</th>
				<th>数量</th>
				<th>小計</th>
			</tr>
			<c:forEach var="item" items="${cartList}">
				<tr style="background-color: #fff; color: #000;">
					<td style="text-align: left;"><img
						src="images/CB300R-HONDA.jpg" alt="商品画像"
						style="width: 100px; margin-right: 10px; vertical-align: middle;">
						${item.shohinMei}</td>
					<td>${item.kakaku}円</td>
					<td>${item.quantity}</td>
					<td>${item.kakaku * item.quantity}円</td>
				</tr>
			</c:forEach>

			<tr style="background-color: #000; color: #fff;">
				<td colspan="3" style="text-align: right; font-weight: bold;">合計金額：</td>
				<td style="font-weight: bold;">${totalPrice}円</td>
			</tr>
		</table>




		<div class="button-container">
			<form action="OrderCompServlet" method="post">
				<input type="submit" value="確定" class="register-button">
			</form>

			<a href="OrderServlet" class="back-button">戻る</a>
			<style>
				.back-button {
				text-decoration: none;
				}
			</style>
		</div>
	</c:if>
	<br>

</body>
</html>