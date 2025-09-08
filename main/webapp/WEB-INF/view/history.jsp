<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴画面</title>
<h1>注文履歴画面</h1>
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
</head>
<body>
<div class="search-container">
    <div>注文履歴を表示</div>
    <div>購入した商品を表示</div>
    <div><a href="ShohinDetailServlet">購入した商品</a></div>
    <div><a href="ShohinDetailServlet">購入した商品</a></div>
    <div><a href="ShohinDetailServlet">購入した商品</a></div>
    <div><a href="MenuServlet">メニューへ</a></div>
</div>


</body>
</html>