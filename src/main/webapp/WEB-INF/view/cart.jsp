<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート画面</title>

</head>
<body>
<h1>カート画面へ</h1>
<div>商品</div>
<div class="search-container">
    
    <div><a href="OrderServlet" class="register-button">商品を購入手続きへ</a></div>
    <div><a href="MenuServlet" class="register-button">メニューへ</a></div>
</div>

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