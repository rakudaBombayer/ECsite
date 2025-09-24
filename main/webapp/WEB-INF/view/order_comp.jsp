<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文完了画面</title>

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">

<style>
    body {
        font-family: 'Oswald', sans-serif;
        background: url('<%= request.getContextPath() %>/images/order_comp.jpg') no-repeat center center fixed;
        background-size: cover;
        margin: 0;
        padding: 40px;
        position: relative;
        color: white;
        min-height: 100vh;
    }

    body::before {
        content: "";
        position: fixed;
        top: 0; left: 0; right: 0; bottom: 0;
        background: rgba(0, 0, 0, 0.6);
        z-index: -1;
    }

    /* 🎯 より黄色くした MISSION COMPLETE */
    h1 {
        text-align: center;
        color: #FFFF33; /* 明るい黄色 */
        font-weight: 900;
        font-size: 3.5em;
        margin-bottom: 40px;
        text-transform: uppercase;
        text-shadow:
            0 0 5px #FFFF33,
            0 0 15px #FFFF66,
            0 0 25px #FFD700,
            0 0 35px #FFC107;
    }

    table {
        border-collapse: collapse;
        width: 90%;
        margin: auto;
        background-color: rgba(34, 34, 34, 0.9);
        color: white;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 0 15px rgba(255, 215, 0, 0.7);
    }

    th, td {
        padding: 12px;
        border: 1px solid #555;
        text-align: center;
    }

    th {
        background-color: #111;
        font-weight: bold;
    }

    td img {
        width: 100px;
        margin-right: 10px;
        vertical-align: middle;
    }

    .total-row td {
        background-color: #111;
        font-weight: bold;
        font-size: 1.2em;
    }

    .center-box {
        display: flex;
        justify-content: center;
        margin-top: 30px;
    }

    /* 🟠 よりオレンジ寄りにしたボタン */
    .menu-link {
        background-color: #FF8C00; /* ダークオレンジ */
        color: white;
        text-decoration: none;
        padding: 12px 24px;
        border-radius: 6px;
        font-size: 18px;
        font-weight: bold;
        border: none;
        cursor: pointer;
        box-shadow: 0 0 10px #FF8C00;
        transition: background-color 0.3s, box-shadow 0.3s;
    }

    .menu-link:hover {
        background-color: #FFA500; /* オレンジ */
        box-shadow: 0 0 20px #FFA500;
    }

    p {
        text-align: center;
        font-size: 20px;
        margin-top: 20px;
        color: #fff;
        text-shadow: 0 0 5px rgba(0,0,0,0.8);
    }
</style>
</head>
<body>

    <h1>MISSION COMPLETE</h1>

    <div class="center-box">
        <c:if test="${empty cartList}">
            <p>購入商品がありません。</p>
        </c:if>
    </div>

    <c:if test="${not empty cartList}">
        <table>
            <tr>
                <th>商品</th>
                <th>価格</th>
                <th>数量</th>
                <th>小計</th>
            </tr>
            <c:forEach var="item" items="${cartList}">
                <tr>
                    <td style="text-align: left;">
                        <img src="images/${item.shouhinGazou}" alt="商品画像">
                        ${item.shohinMei}
                    </td>
                    <td><fmt:formatNumber value="${item.kakaku}" type="number"/>円</td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatNumber value="${item.kakaku * item.quantity}" type="number"/>円</td>
                </tr>
            </c:forEach>
            <tr class="total-row">
                <td colspan="3" style="text-align: right;">合計金額：</td>
                <td><fmt:formatNumber value="${totalPrice}" type="number"/>円</td>
            </tr>
        </table>
    </c:if>

    <div class="center-box">
        <a href="MenuServlet" class="menu-link">メニュー画面へ</a>
    </div>

</body>
</html>
