<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者画面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        form {
            margin-bottom: 40px;
            border: 1px solid #ccc;
            padding: 15px;
            width: 600px;
        }
        h2 {
            background-color: #f2f2f2;
            padding: 10px;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
        }
        input[type="submit"] {
            margin-top: 15px;
            padding: 8px 20px;
        }
    </style>
</head>
<body>

    <h1>管理者メニュー</h1>

    <!-- ① 商品追加 -->
    <form action="ShohinAdminServlet" method="post" enctype="multipart/form-data">
        <h2>① 商品を新規追加</h2>
        <input type="hidden" name="action" value="add">

        <label>商品名</label>
        <input type="text" name="shouhinMei" required>

        <label>商品説明</label>
        <input type="text" name="shouhinSetsumei" required>

        <label>価格</label>
        <input type="number" name="kakaku" required min="0" step="1">

        <label>在庫数量</label>
        <input type="number" name="zaikoSuuryou" required min="0" step="1">

        <label>商品画像（ファイル名を直接指定する簡易方式）</label>
        <input type="file" name="shouhinGazou" id="shouhinGazou" accept="image/*">

        <input type="submit" value="商品を追加">
    </form>

    <!-- ② 商品変更 -->
    <form action="ShohinAdminServlet" method="post">
        <h2>② 商品を変更</h2>
        <input type="hidden" name="action" value="edit">

        <label>商品ID（変更対象）</label>
        <input type="text" name="shohinId" required>

        <label>新しい商品名</label>
        <input type="text" name="shouhinMei">

        <label>新しい商品説明</label>
        <input type="text" name="shouhinSetsumei">

        <label>新しい価格</label>
        <input type="number" name="kakaku" required min="0" step="1">

        <label>新しい在庫数量</label>
        <input type="number" name="zaikoSuuryou" required min="0" step="1">

        <label>新しい商品画像</label>
        <input type="file" name="shouhinGazou" id="shouhinGazou" accept="image/*">

        <input type="submit" value="商品を変更">
    </form>

    <!-- ③ 商品削除 -->
    <form action="ShohinAdminServlet" method="post">
        <h2>③ 商品を削除</h2>
        <input type="hidden" name="action" value="delete">

        <label>削除する商品ID</label>
        <input type="text" name="shohinId" required>

        <input type="submit" value="商品を削除">
    </form>

    <div><a href="MenuServlet">メニューへ戻る</a></div>
</body>
</html>