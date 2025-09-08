<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
</head>
<body>
<c:choose>
  <c:when test="${empty task}">
    <h2>【会員登録】</h2>
  </c:when>
  <c:otherwise>
    <h2>【登録変更】</h2>
  </c:otherwise>
</c:choose>
<tr><th>会員ＩＤ</th>
<td><input type="text" name="kaiin_id"
size="40"placeholder="会員ＩＤを入力"/></td></tr>
<tr><th>パスワード</th>
<td><input type="text" name="password"
size="40"placeholder="パスワードを入力"/></td></tr>
<tr><th>氏名</th>
<td><input type="text" name="shimei " 
size="40"placeholder="氏名を入力" /></td></tr>
<tr><th>郵便番号</th>
<td><input type="text" name="yuubin_bangou" 
size="40"placeholder="郵便番号を入力"/></td></tr>
<tr><th>住所</th>
<td><input type="text" name="address"
size="40"placeholder="住所を入力"/></td></tr>
<tr><th>電話番号</th>
<td><input type="text" name="denwa_bangou" 
size="40"placeholder="電話番号を入力" /></td></tr>
<tr><th>生年月日</th>
<td><input type="text" name="seinengappi"
size="40"placeholder="生年月日を入力"/></td></tr>
<tr><th>メールアドレス</th>
<td><input type="text" name="mail_address"
size="40" placeholder="メールアドレスを入力"/></td></tr>
<tr><th>支払方法</th>
<td><input type="text" name="shiharai_houhou" 
size="40" placeholder="支払方法を入力"/></td></tr>
<a href="LoginServlet" class="register-button">ログイン画面へ(仮)</a>
</body>
</html>