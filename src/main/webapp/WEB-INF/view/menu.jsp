<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>

</head>
<body>
<h1>メニュー画面</h1>
<div class="search-container">
<a href="LoginServlet" class="register-button">ログイン画面へ(仮)</a>
<a href="CartServlet" class="register-button">ショッピングカート画面へ(仮)</a>
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
<div><a href="ShohinDetailServlet">商品1のフィギュア</a></div>
<div><a href="ShohinDetailServlet">商品2のフィギュア</a></div>
<div><a href="ShohinDetailServlet">商品3のフィギュア</a></div>
<div><a href="ShohinDetailServlet">商品4のフィギュア</a></div>
<div><a href="ShohinDetailServlet">商品5のフィギュア</a></div>
<div><a href="ShohinDetailServlet">商品6のフィギュア</a></div>

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