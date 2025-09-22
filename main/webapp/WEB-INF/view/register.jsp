<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Oswald&display=swap');

    /* 背景画像アニメーション */
    @keyframes backgroundPan {
        0% { background-position: 0% 50%; }
        50% { background-position: 100% 50%; }
        100% { background-position: 0% 50%; }
    }

    body {
        font-family: 'Oswald', sans-serif, sans-serif;
        margin: 0;
        padding: 40px;
        color: white;

        /* 背景画像とアニメーション設定 */
        background: url('<%= request.getContextPath() %>/images/suityuu.jpg') no-repeat center center fixed;
        background-size: cover;
        animation: backgroundPan 30s ease-in-out infinite;

        position: relative;
        min-height: 100vh;
        overflow-x: hidden;
    }

    /* 背景の暗めオーバーレイ */
    body::before {
        content: "";
        position: fixed;
        top: 0; left: 0; right: 0; bottom: 0;
        background-color: rgba(0, 0, 0, 0.65);
        z-index: 0;
    }

    /* フォームはオーバーレイより前に表示 */
    form, h2, a.register-button {
        position: relative;
        z-index: 1;
    }

    h2 {
        text-align: center;
        font-weight: bold;
        margin-bottom: 30px;
        color: white;
    }

    form {
        max-width: 500px;
        margin: auto;
        background: rgba(0, 0, 0, 0.7); /* 半透明黒背景 */
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 15px rgba(255, 255, 255, 0.15);
    }

    label {
        display: block;
        margin-top: 15px;
        font-weight: bold;
        color: white;
    }

    input[type="text"],
    input[type="password"],
    input[type="email"],
    input[type="date"] {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        background-color: #111;
        color: white;
        transition: border-color 0.3s;
    }
    input[type="text"]:focus,
    input[type="password"]:focus,
    input[type="email"]:focus,
    input[type="date"]:focus {
        border-color: #ff4444;
        outline: none;
    }

    input[type="submit"] {
        display: block;
        margin: 25px auto 0 auto;
        width: 150px;
        padding: 12px;
        background-color: #ff0000;
        border: none;
        color: white;
        font-size: 16px;
        border-radius: 4px;
        cursor: pointer;
        font-weight: bold;
        transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #af0e0e;
    }

    a.register-button {
        display: block;
        text-align: center;
        margin-top: 30px;
        color: #FFFFFF;
        text-decoration: none;
        font-weight: bold;
        position: relative;
        z-index: 1;
        transition: text-decoration 0.3s;
    }

    a.register-button:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
 <h2>【会員登録】</h2>
<form action="RegisterServlet" method="post">
    <div>
        <label>氏名</label><br>
        <input type="text" name="shimei" size="40" placeholder="氏名を入力" required>
    </div>
    <div>
        <label>パスワード</label><br>
        <input type="text" name="password" size="40" placeholder="パスワードを入力" required>
    </div>
    <div>
        <label>郵便番号</label><br>
        <input type="text" name="yuubin_bangou" id="zipcode" size="40" placeholder="郵便番号を入力" required>
    </div>
    <div>
        <label>住所</label><br>
        <input type="text" name="address" size="40" id="address" placeholder="住所を入力" required>
    </div>
    <div>
        <label>電話番号</label><br>
        <input type="text" name="denwa_bangou" size="40" placeholder="電話番号を入力" required>
    </div>
    <div>
        <label>生年月日</label><br>
        <input type="date" name="seinengappi" size="40" placeholder="生年月日を入力" required>
    </div>
    <div>
        <label>メールアドレス</label><br>
        <input type="text" name="mail_address" size="40" placeholder="メールアドレスを入力" required>
    </div>
    <div>
        <label>支払方法</label><br>
        <input type="text" name="shiharai_houhou" size="40" placeholder="支払方法を入力" required>
    </div>
    <div>
        <input type="submit" value="登録する">
    </div>
</form>

<a href="LoginServlet" class="register-button">ログイン画面へ</a>

<script>
document.getElementById("zipcode").addEventListener("blur", function() {
    const zipcode = this.value.replace(/[^0-9]/g, ""); // 数字のみ抽出
    if (zipcode.length === 7) {
        fetch("https://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipcode)
            .then(response => response.json())
            .then(data => {
                if (data.results) {
                    const result = data.results[0];
                    const fullAddress = result.address1 + result.address2 + result.address3;
                    document.getElementById("address").value = fullAddress;
                } else {
                    alert("住所が見つかりませんでした。");
                }
            })
            .catch(error => {
                console.error("通信エラー:", error);
                alert("住所検索に失敗しました。");
            });
    } else {
        alert("郵便番号は7桁で入力してください。");
    }
});
</script>

</body>
</html>
