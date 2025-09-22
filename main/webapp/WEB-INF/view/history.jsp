<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>注文履歴</title>

  <style>
    /* ===== 背景画像と全体スタイル ===== */
    body {
      margin: 0;
      padding: 0;
      font-family: 'Oswald', sans-serif;
      color: white;
      background: url('images/finish.jpg') no-repeat center center fixed;
      background-size: cover;
      position: relative;
      min-height: 100vh;
    }

    /* 黒い半透明オーバーレイ */
    body::before {
      content: "";
      position: fixed;
      top: 0; left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.65);
      z-index: 0;
    }

    /* 全体を上に重ねる */
    body > * {
      position: relative;
      z-index: 1;
    }

    h1 {
      margin: 30px 0;
      text-align: center;
      font-size: 36px;
      color: #fff;
      text-shadow: 0 0 10px red, 0 0 20px crimson;
    }

    .order-list {
      width: 85%;
      margin: 0 auto 50px auto;
      display: flex;
      flex-direction: column;
      gap: 20px;
    }

    .order-card {
      display: flex;
      gap: 20px;
      background-color: rgba(0, 0, 0, 0.7);
      border-radius: 10px;
      padding: 15px 20px;
      box-shadow: 0 0 12px rgba(255, 0, 0, 0.4);
      transition: transform 0.3s ease;
    }

    .order-card:hover {
      transform: scale(1.02);
    }

    .order-image {
      width: 150px;
      height: 150px;
      border-radius: 8px;
      overflow: hidden;
      border: 1px solid #fff;
      background-color: #333;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .order-image img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      display: block;
    }

    .order-info {
      display: flex;
      flex-direction: column;
      justify-content: center;
      color: white;
    }

    .order-info div {
      margin-bottom: 8px;
      font-size: 16px;
    }

    .back-link {
      display: block;
      width: fit-content;
      margin: 0 auto 50px auto;
      padding: 12px 24px;
      background-color: red;
      color: white;
      text-decoration: none;
      border-radius: 6px;
      font-weight: bold;
      font-size: 16px;
      box-shadow: 0 0 10px red;
      transition: all 0.3s ease;
    }

    .back-link:hover {
      background-color: darkred;
      box-shadow: 0 0 20px crimson;
      transform: scale(1.05);
    }

    /* 中央揃えメッセージ */
    p {
      text-align: center;
      font-size: 18px;
    }
  </style>
</head>
<body>

  <h1>注文履歴</h1>

  <c:choose>
    <c:when test="${empty orderHistory}">
      <p>注文履歴がありません。</p>
    </c:when>
    <c:otherwise>
      <div class="order-list">
        <c:forEach var="item" items="${orderHistory}">
          <div class="order-card">
            <!-- 左：画像 -->
            <div class="order-image">
              <a href="ShohinDetailServlet?shohin_id=${item.shohinId}">
                <img src="${pageContext.request.contextPath}/images/${item.shohinGazou}" alt="${item.shohinMei}">
              </a>
            </div>

            <!-- 右：詳細 -->
            <div class="order-info">
              <div><strong>商品名：</strong><c:out value="${item.shohinMei}"/></div>
              <div><strong>価格：</strong><c:out value="${item.kakaku}"/>円</div>
              <div><strong>数量：</strong><c:out value="${item.quantity}"/></div>
              <div><strong>注文日時：</strong>
                <fmt:formatDate value="${item.orderTime}" pattern="yyyy年MM月dd日 HH:mm"/>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>

  <a href="MenuServlet" class="back-link">メニューへ戻る</a>

</body>
</html>
