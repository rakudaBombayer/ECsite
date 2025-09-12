<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品詳細画面</title>
    <style>
        .product-detail {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            font-family: sans-serif;
        }

        .product-image {
            max-width: 300px;
            height: auto;
        }

        .back-links {
            margin-top: 20px;
        }

        .shouhin_gazou {
            max-width: 300px;
            height: auto;
        }

        .register-button {
            display: inline-block;
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-right: 10px;
        }

        .register-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h1>商品詳細画面</h1>

    <div class="product-detail">
        <c:choose>
            <c:when test="${not empty shohin}">
                <h2>${shohin.shouhinMei}</h2>
                <img src="${shohin.shouhinGazou}" alt="商品画像" class="shouhin_gazou"><br><br>
                <strong>価格:</strong> <fmt:formatNumber value="${shohin.kakaku}" type="number" /> 円<br>
                <strong>在庫数:</strong> ${shohin.zaikoSuuryou} 個<br><br>
                <strong>商品説明:</strong><br>
                <p>${shohin.shouhinSetsumei}</p>
            </c:when>
            <c:otherwise>
                <p>商品情報が取得できませんでした。</p>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="back-links">
    <form action="CartServlet" method="post">
    <input type="hidden" name="shohin_id" value="${shohin.shohinId}">
    
    <label>数量:
        <input type="number" name="quantity" value="1" min="1" max="${shohin.zaikoSuuryou}">
    </label><br><br>

    <button type="submit" class="register-button">カートに追加</button>
</form>
        <a href="CartListServlet" class="register-button">ショッピングカート画面へ</a>
        <a href="MenuServlet" class="register-button">メニューへ</a>
    </div>

</body>
</html>
