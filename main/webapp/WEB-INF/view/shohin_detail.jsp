<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品詳細画面</title>
    <style>
        /* ===== 背景設定 ===== */
        body {
            background-image: url('images/colony.jpg'); /* 好きな背景画像パスに変更 */
            background-size: cover;
            background-position: center center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            font-family: sans-serif;
            color: white;
            font-weight: bold;
            margin: 0;
            padding: 0;
            min-height: 100vh;
        }

        /* ===== 商品詳細コンテナ ===== */
        .product-detail {
            max-width: 1200px;
            margin: 40px auto;
            padding: 30px 40px;
            background-color: rgba(0, 0, 0, 0.7); /* 半透明の黒オーバーレイ */
            border-radius: 12px;
            box-shadow: 0 0 20px rgba(255, 0, 0, 0.5); /* 赤のふんわり影 */
        }

        /* ===== 商品コンテンツ ===== */
        .product-container {
            display: flex;
            align-items: flex-start;
            gap: 30px;
        }

        /* ===== 商品画像 ===== */
        .product-image {
            max-width: 400px;
            flex-shrink: 0;
        }

        .product-image img {
            max-width: 100%;
            height: auto;
            border: 1px solid #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(255, 0, 0, 0.6);
        }

        /* ===== 商品詳細右側 ===== */
        .product-right {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
        }

        .product-info h2 {
            font-size: 32px;
            margin-bottom: 20px;
            text-shadow: 0 0 8px #ff0000;
        }

        .product-info p {
            margin-bottom: 12px;
            line-height: 1.4;
        }

        /* ===== ボタン・フォーム周り ===== */
        .back-links {
            margin-top: 20px;
        }

        .back-links form {
            margin-bottom: 10px;
        }
h1{text-shadow: 0 0 10px red, 0 0 20px crimson;}
        label {
            color: white;
            font-weight: bold;
        }

        .register-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #ff0000;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            margin-right: 10px;
            font-weight: bold;
            box-shadow: 0 0 8px #ff0000;
            transition: background-color 0.3s ease, transform 0.3s ease;
            cursor: pointer;
            border: none;
            font-size: 16px;
        }

        .register-button:hover {
            background-color: #af0e0e;
            transform: scale(1.05);
        }
        .disabled-button {
		    background-color: #ccc;
		    color: #666;
		    cursor: not-allowed;
		}
        
    </style>
</head>
<body>

    <h1>商品詳細画面</h1>

    <div class="product-detail">
        <c:choose>
            <c:when test="${not empty shohin}">
                <div class="product-container">
                    <!-- 左：画像 -->
                    <div class="product-image">
                        <img src="images/${shohin.shouhinGazou}" alt="商品画像" class="shouhin_gazou">
                    </div>

                    <!-- 右：商品詳細 ＋ ボタン類 -->
                    <div class="product-right">
                        <div class="product-info">
                            <h2>${shohin.shouhinMei}</h2>
                            <p><strong>価格:</strong> <fmt:formatNumber value="${shohin.kakaku}" type="number" /> 円</p>
                            <p><strong>在庫数:</strong> ${shohin.zaikoSuuryou} 個</p>
                            <p><strong>商品説明:</strong><br>${shohin.shouhinSetsumei}</p>
                        </div>
						<!-- メッセージ -->
				        <c:if test="${not empty successMessage}">
				            <p style="color: #00ffff;">${successMessage}</p>
				        </c:if>
				        <c:if test="${not empty errorMessage}">
				            <p style="color: #ff6666;">${errorMessage}</p>
				        </c:if>
                        <!-- 商品情報の下に表示 -->
						
                        <div class="back-links">
                            <c:choose>
						    <c:when test="${shohin.zaikoSuuryou > 0}">

						        <form action="CartServlet" method="post">
						            <input type="hidden" name="shohin_id" value="${shohin.shohinId}">
						            <label>数量:
						                <input type="number" name="quantity" value="1" min="1" max="${shohin.zaikoSuuryou}">
						            </label><br><br>
						            <button type="submit" class="register-button">カートに追加</button>
						        </form>
						    </c:when>
						    <c:otherwise>
						        <p style="color: #ff6666;">商品がありません</p>
						        <button type="button" class="register-button disabled-button" disabled>カートに追加</button>
						    </c:otherwise>
						</c:choose>
                            <a href="CartListServlet" class="register-button">ショッピングカート画面へ</a>
                            <a href="MenuServlet" class="register-button">メニューへ</a>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>商品情報が取得できませんでした。</p>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
