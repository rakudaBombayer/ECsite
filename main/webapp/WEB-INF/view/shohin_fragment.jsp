<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:forEach var="item" items="${shohinList}">
    <a href="ShohinDetailServlet?shohin_id=${item.shohinId}" class="item-link">
        <div class="item-box">
            <img src="images/${item.shouhinGazou}" alt="${item.shouhinMei}" width="150">
			<p class="js_typing_item">${item.shouhinMei}</p>
			<p class="js_typing_item">価格：${item.kakaku}円</p>
        </div>
    </a>
</c:forEach>
