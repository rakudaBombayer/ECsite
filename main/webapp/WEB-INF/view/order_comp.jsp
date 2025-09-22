<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文完了画面</title>

</head>
<h1>注文が完了しました。</h1>
<body>


<c:if test="${not empty cartList}">
    <table>
        <tr>
            <th>商品名</th>
            <th>価格</th>
            <th>数量</th>
            <th>小計</th>
        </tr>
        <c:forEach var="item" items="${cartList}">
            <tr>
                <td>${item.shohinMei}</td>
                <td>${item.kakaku} 円</td>
                <td>${item.quantity}</td>
                <td>${item.kakaku * item.quantity} 円</td>
            </tr>
        </c:forEach>
    </table>

    <h3>合計金額: ${totalPrice} 円</h3>
</c:if>
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