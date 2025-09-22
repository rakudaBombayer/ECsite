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
            width: 550px;
            padding: 8px;
            margin-top: 4px;
        }
        input[type="submit"] {
            margin-top: 15px;
            padding: 8px 20px;
        }
        .message.success {
    	color: green;
    	font-weight: bold;
    	margin-bottom: 10px;
		}

		.message.error {
    	color: red;
    	font-weight: bold;
    	margin-bottom: 10px;
		}
        
    </style>
</head>
<body>

    <h1>管理者メニュー</h1>

    <!-- ① 商品追加 --><!-- 左側：商品登録フォーム -->
<div style="display: flex; gap: 40px; align-items: flex-start;">
    <div style="flex: 1;">
    <form action="ShohinAdminServlet" method="post" enctype="multipart/form-data">
        <h2>① 商品を新規追加</h2>
        <input type="hidden" name="action" value="add">

        <label>商品名</label>
        <input type="text" name="shouhinMei" required>
        
        <label>商品カテゴリ</label>
        <select name="shouhinCategory" required>
    		<option value="">-- 全て --</option>
				<option value="HG">HG [ハイグレード]</option>
				<option value="RG">RG [リアルグレード]</option>
				<option value="MG">MG [マスターグレード]</option>
		</select>

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
    </div>
    
    <!-- 右側：登録された商品情報 -->
    <div style="flex: 1;">
    	<c:if test="${not empty success}">
    		<div class="message success">${success}</div>
		</c:if>

		<c:if test="${not empty error}">
    		<div class="message error">${error}</div>
		</c:if>
    <c:if test="${not empty success}">
        <c:if test="${not empty registeredShohin}">
            <div class="registered-product">
                <h3>(登録・変更)された商品情報</h3>
                <p><strong>商品名：</strong> ${registeredShohin.shouhinMei}</p>
                <p><strong>商品カテゴリ：</strong> ${registeredShohin.categoryName}</p>
                <p><strong>商品説明：</strong> ${registeredShohin.shouhinSetsumei}</p>
                <p><strong>価格：</strong> ¥${registeredShohin.kakaku}</p>
                <p><strong>在庫数量：</strong> ${registeredShohin.zaikoSuuryou} 個</p>

                <c:if test="${not empty registeredShohin.shouhinGazou}">
                    <p><strong>商品画像：</strong><br>
                        <img src="images/${registeredShohin.shouhinGazou}" alt="商品画像" width="200">
                    </p>
                </c:if>
            </div>
        </c:if>
    </c:if>
</div>


</div>  
    
    

	
    <!-- ② 商品変更 -->
    <form action="ShohinAdminServlet" method="post" enctype="multipart/form-data" onsubmit="return confirm('本当にこの商品を変更してもいいですか？');">
        <h2>② 商品を変更</h2>
        <input type="hidden" name="action" value="edit">

        <label>変更したい商品名</label>
        <input type="text" name="oldShouhinMei" required>

        <label>新しい商品名</label>
        <input type="text" name="shouhinMei">
        
        <label>商品カテゴリ</label>
        <select name="shouhinCategory" required>
    		<option value="">-- 全て --</option>
				<option value="バイク１">バイク１</option>
				<option value="ガンダム">ガンダム</option>
				<option value="書籍">書籍</option>
		</select>

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
    <form action="ShohinAdminServlet" method="post" onsubmit="return confirm('本当にこの商品を削除しますか？');">
        <h2>③ 商品を削除</h2>
        <input type="hidden" name="action" value="delete">

        <label>削除する商品名</label>
        <input type="text" name="oldShouhinMei" required>

        <input type="submit" value="商品を削除">
    </form>

    <div><a href="MenuServlet">メニューへ戻る</a></div>
</body>
</html>