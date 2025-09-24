<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ショッピングカート</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Oswald&display=swap');

        body {
            font-family: 'Oswald', sans-serif;
            background: url('<%= request.getContextPath() %>/images/cart.jpg') no-repeat center center fixed;
            background-size: cover;
            color: white;
            margin: 0;
            padding: 0;
            position: relative;
            z-index: 0;
        }

        h1 {
            margin: 30px 0;
            color: white;
            text-align: center;
            text-shadow: 0 0 10px red, 0 0 20px crimson;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: rgba(34, 34, 34, 0.9);
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

        .action-form {
            display: inline;
        }

        input[type="number"] {
            width: 60px;
            padding: 5px;
            background-color: #111;
            color: white;
            border: 1px solid #555;
            border-radius: 4px;
        }

        button {
            background-color: red;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: darkred;
        }

        .action-button {
            display: block;
            width: fit-content;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: red;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            transition: background-color 0.3s;
        }

        .action-button:hover {
            background-color: darkred;
        }

        .register-button {
            display: block;
            width: fit-content;
            margin: 30px auto 10px auto;
            padding: 10px 20px;
            background-color: red;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            transition: background-color 0.3s;
        }

        .register-button:hover {
            background-color: darkred;
        }

        /* 戻るリンク用スタイル */
        .back-link {
            text-align: center;
            margin-top: 30px;
        }

        .back-link a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            text-shadow: 0 0 8px #ff0000;
            cursor: pointer;
            font-size: 1.1em;
        }

        .back-link a:hover {
            text-decoration: underline;
        }

        a {
            color: red;
            display: block;
            text-align: center;
            margin-bottom: 40px;
        }

        p {
            text-align: center;
            color: white;
        }

        .product-image {
            width: 150px;
            height: 100px;
            object-fit: contain;
            border-radius: 6px;
            display: inline-block;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <h1>ショッピングカート</h1>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

    <c:if test="${empty cartList}">
        <p>カートは空です。</p>
    </c:if>

    <c:if test="${not empty cartList}">
        <table>
            <tr>
                <th>画像</th>
                <th>商品名</th>
                <th>価格</th>
                <th>数量</th>
                <th>小計</th>
                <th>操作</th>
            </tr>
            <c:forEach var="item" items="${cartList}">
                <tr>
                    <!-- 商品画像（リンク付き） -->
                    <td>
                        <a href="ShohinDetailServlet?shohin_id=${item.shohinId}">
                            <img src="${pageContext.request.contextPath}/images/${item.shouhinGazou}" 
                                 alt="${item.shohinMei}" class="product-image">
                        </a>
                    </td>

                    <!-- 商品名 -->
                    <td>${item.shohinMei}</td>

                    <!-- 価格 -->
                    <td>${item.kakaku} 円</td>

                    <!-- 数量 -->
                    <td>
                        <form action="CartUpdateServlet" method="post" class="action-form">
                            <input type="hidden" name="shohin_id" value="${item.shohinId}">
                            <input type="number" name="quantity" value="${item.quantity}" min="1">
                            <button type="submit">変更</button>
                        </form>
                    </td>

                    <!-- 小計 -->
                    <td>${item.kakaku * item.quantity} 円</td>

                    <!-- 削除 -->
                    <td>
                        <form action="CartDeleteServlet" method="post" class="action-form">
                            <input type="hidden" name="shohin_id" value="${item.shohinId}">
                            <button type="submit">削除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="OrderServlet" class="action-button">商品を購入手続きへ</a>

    <div class="back-link">
        <a href="MenuServlet">メニューに戻る</a>
    </div>

</body>
</html>
