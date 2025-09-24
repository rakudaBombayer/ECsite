<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>購入内容確認画面</title>

    <style>
        @import url('https://fonts.googleapis.com/css2?family=Oswald&display=swap');

        body {
            font-family: 'Oswald', sans-serif;
            background: url('<%= request.getContextPath() %>/images/care.jpg') no-repeat center center fixed;
            background-size: cover;
            color: white;
            margin: 0;
            padding: 0;
        }

        h1 {
            margin: 30px 0;
            color: white;
            text-align: center;
            text-shadow: 0 0 10px red, 0 0 20px crimson;
        }

        table {
            width: 80%;
            margin: 0 auto 30px auto;
            border-collapse: collapse;
            background-color: rgba(34, 34, 34, 0.9); /* 背景半透明 */
            color: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(255, 255, 255, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #444;
        }

        th {
            background-color: #333;
        }

        img {
            width: 150px;
            height: 100px;
            object-fit: contain;
            border-radius: 6px;
        }

        .register-button,
        .back-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            margin: 10px;
        }

        .register-button {
            background-color: red;
            color: white;
        }

        .register-button:hover {
            background-color: darkred;
        }

        .back-button {
            background-color: gray;
            color: white;
        }

        .back-button:hover {
            background-color: #555;
        }

        .button-container {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-bottom: 50px;
        }
    </style>
</head>
<body>

    <h1>購入してよろしいですか？</h1>

    <c:if test="${empty purchasedList}">
        <p style="text-align:center;">購入履歴はありません。</p>
    </c:if>

    <c:if test="${not empty purchasedList}">
        <table>
            <tr>
                <th>商品画像</th>
                <th>商品名</th>
                <th>価格</th>
                <th>数量</th>
                <th>小計</th>
            </tr>
            <c:forEach var="item" items="${purchasedList}">
                <tr>
                    <td>
                        <img src="${pageContext.request.contextPath}/images/${item.shouhinGazou}" alt="${item.shohinMei}">
                    </td>
                    <td>${item.shohinMei}</td>
                    <td><fmt:formatNumber value="${item.kakaku}" type="number"/> 円</td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatNumber value="${item.kakaku * item.quantity}" type="number"/> 円</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <div class="button-container">
        <form action="OrderCompServlet" method="post" style="display: inline;">
            <input type="submit" value="確定" class="register-button">
        </form>
        <a href="OrderServlet" class="back-button">戻る</a>
    </div>

</body>
</html>
