<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文完了画面</title>

</head>
<h1>注文が完了しました。</h1>
<body>
<div>購入した商品が出る。</div>
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