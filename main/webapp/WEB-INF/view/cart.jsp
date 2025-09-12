<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ショッピングカート</title>
    <style>
        .action-form {
            display: inline;
        }
    </style>
</head>
<body>
    <h1>ショッピングカート</h1>
<c:if test="${not empty errorMessage}">
    <p style="color:red;">${errorMessage}</p>
</c:if>

    <c:if test="${empty cartList}">
        <p>カートは空です。</p>
    </c:if>

    <c:if test="${not empty cartList}">
        <table border="1">
            <tr>
                <th>商品名</th>
                <th>価格</th>
                <th>数量</th>
                <th>小計</th>
                <th>操作</th>
            </tr>
            <c:forEach var="item" items="${cartList}">
                <tr>
<td>
<a href="ShohinDetailServlet?shohin_id=${item.shohinId}">
${item.shohinMei}
 </a>
 </td>
 <td>${item.kakaku} 円</td>
                    
                    <!-- 数量変更フォーム -->
  <td>
    <form action="CartUpdateServlet" method="post" class="action-form">
        <input type="hidden" name="shohin_id" value="${item.shohinId}">
        <input type="number"
               name="quantity"
               value="${item.quantity}"
               min="1"
               style="width: 60px;">
        <button type="submit">変更</button>
    </form>
	</td>
                    <td>${item.kakaku * item.quantity} 円</td>

                    <!-- 削除フォーム -->
                    <td>
 <form action="CartDeleteServlet" method="post" class="action-form">
 <input type="hidden" name="shohin_id" value="${item.shohinId}">
 <button type="submit">削除</button>
 </form>
 </td>
 </tr>
 </c:forEach>
 </table>
 </c:if>

    <br>
    <div><a href="OrderServlet" class="register-button">商品を購入手続きへ</a></div>
    <a href="MenuServlet">メニューに戻る</a>
</body>
</html>
