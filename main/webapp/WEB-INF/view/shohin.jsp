<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索後画面</title>

</head>
<h1>商品検索結果画面</h1>
<body>
<div class="search-container">
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
</div>
<div></div><a href="CartServlet" class="register-button">ショッピングカート画面へ(仮)</a></div>
<div><a href="MenuServlet">メニュー</a></div>
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
</style>