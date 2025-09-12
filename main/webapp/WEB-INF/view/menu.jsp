<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>

</head>
<body>
<h1>メニュー画面</h1>
<div class="search-container">
<a href="LoginServlet" class="register-button">ログアウト(仮)</a>
<a href="AccountChangeServlet" class="register-button">アカウント変更画面へ(仮)</a>
<a href="CartListServlet" class="register-button">ショッピングカート画面へ(仮)</a>
<a href="HistoryServlet" class="register-button">注文履歴画面へ(仮)</a>
<a href="AdminServlet" class="register-button">管理者用画面へ(仮)</a>
    <form action="SearchServlet" method="get" class="search-form">
        <!-- 検索キーワード -->
        <input type="text" name="keyword" placeholder="商品名を入力" required>

        <!-- カテゴリ選択 -->
        <label for="category">カテゴリ選択:</label>
        <select name="category" id="category">
            <option value="">すべて</option>
            <option value="figure">ショッピングカート</option>
            <option value="goods">注文履歴</option>
            <option value="game">会員情報</option>
            <option value="logout">ログアウト</option>
        </select>

        <!-- 検索ボタン -->
        <input type="submit" value="検索">
    </form>
    <div><a href="ShohinServlet">商品検索後の画面へ</a></div>
</div>
    <c:forEach var="item" items="${shohinList}">
    <div class="item-box">
        <a href="ShohinDetailServlet?shohin_id=${item.shohinId}">
            ${item.shouhinMei}${item.shohinId}
        </a>
        <p>${item.shouhinSetsumei}</p>
        <p>価格：${item.kakaku}円</p>
        <img src="images/${item.shouhinGazou}" alt="${item.shouhinMei}" width="150">
    </div>
	</c:forEach>

</body>
</html>

<style>
    /* フォーム全体を左上に配置 */
    .search-container {
        text-align: left;
        padding: 20px;
    }

    /* 検索フォームとカテゴリ選択を横並びにする */
    .search-form {
        display: flex;
        align-items: center;
        gap: 10px; /* 要素の間隔 */
    }

    input[type="text"] {
        width: 250px;
        padding: 5px;
    }

    select {
        padding: 5px;
    }

    input[type="submit"] {
        padding: 5px 15px;
    }
    .item-box {
    	border: 1px solid #ccc;         /* 枠線の色と太さ */
    	padding: 16px;                  /* 内側の余白 */
    	margin-bottom: 20px;            /* 下の余白 */
    	border-radius: 8px;             /* 角を丸く */
    	background-color: #f9f9f9;      /* 背景色（任意） */
    	box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* ほんのり影（任意） */
}
    
</style>