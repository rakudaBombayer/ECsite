<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/vegas/2.4.4/vegas.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.10.0/css/lightbox.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">


</head>
<body>
<div id="slider-area" class="bgextend bgRLextendTrigger">
    <div class="bgappearTrigger">
        <div id="slider">
        	<div class="slider-text js_typing_top">GUNPLA</div>
        	<div class="slider-text-under js_typing_top">EC SHOP</div>
        </div>
    </div>  
</div>
<div class="header-icons">
	<div class="openbtn"><span></span><span></span><span></span></div>
	<div class="cart"><a href="CartListServlet">
	  <i class="fas fa-shopping-cart"></i>
	  <span class="cart-count">${cartNum}</span>
	</div>
</div>


<nav id="g-nav">
<div id="g-nav-list">
<ul>
<li><a href="LogoutServlet">Logout</a></li>	
<li><a href="AccountChangeServlet">Account</a></li>	
<li><a href="HistoryServlet">Purchase History</a></li>
<c:if test="${loginUser.admin}">	
	<li><a href="AdminServlet">Admin</a></li>
</c:if>
</ul>
</div>
</nav>




<div class="search-container">


<!-- <a href="LogoutServlet" class="register-button">ログアウト(仮)</a>
<a href="AccountChangeServlet" class="register-button">アカウント変更画面へ(仮)</a>
<a href="CartListServlet" class="register-button">ショッピングカート画面へ(仮)</a>
<a href="HistoryServlet" class="register-button">注文履歴画面へ(仮)</a>
 -->

    <form action="SearchServlet" method="get" class="search-form">
			<!-- 商品名 -->
			<input type="text" name="keyword" placeholder="商品名を入力">

			<!-- カテゴリー -->
			<label for="category">カテゴリ選択:</label> <select name="category"
				id="category">
				<option value="">-- 全て --</option>
				<option value="HG">HG [ハイグレード]</option>
				<option value="RG">RG [リアルグレード]</option>
				<option value="MG">MG [マスターグレード]</option>
			</select> 
			<input type="submit" value="検索">
	</form>
    
    
</div>

<!--  商品画像を3つずつ表示-->
<h2 class="js_typing_item redmoji">ITEMS</h2>
<div class="item-container">
    <c:forEach var="item" items="${shohinList}">
    <a href="ShohinDetailServlet?shohin_id=${item.shohinId}" class="item-link">
    <div class="item-box">
        
  		<img src="images/${item.shouhinGazou}" alt="${item.shouhinMei}" width="150">
		
        <p class="js_typing_item">${item.shouhinMei}</p>
        <p class="js_typing_item">価格：${item.kakaku}円</p>
        
    </div>
    </a>
	</c:forEach>
</div>


<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/vegas/2.4.4/vegas.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/shuffle-text@0.3.0/build/shuffle-text.min.js"></script>
<!-- <script src="js/script.js"></script> -->
<script src="js/menu.js"></script>

</body>
</html>

<style>
	body {
	  background-color: #000000; /* 完全な黒 */
	  color: #ffffff;            /* テキストを白にして読みやすく */
	  font-family: 'Oswald', sans-serif;
	  
	}
	
	.header-icons {
	  height: 60px;
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	  padding: 0 20px;
	 /*  position: fixed; */
	  top: 0;
	  left: 0;
	  right: 0;
	  z-index: 1000;
	  background-color: #000;
	}

	.openbtn {
  position: fixed;
 
  right: 70px; /* カートの左側に */
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 5px;
  cursor: pointer;
  z-index: 10000;
}

	
	.openbtn span {
	  display: flex;
	  width: 25px;
	  height: 3px;
	  margin: 6px 0;
	  background-color: white;
	}
	
	.cart {
	  position: fixed;         /* ← これで画面に固定！ */
	  top: 20px;               /* 上からの距離 */
	  right: 20px;             /* 右からの距離 */
	  font-size: 24px;
	  color: white;
	  z-index: 9999;           /* 他の要素より前面に表示 */
	}

	.cart-count {
	  position: absolute;
	  top: -8px;
	  right: -10px;
	  background-color: red;
	  color: white;
	  font-size: 16px;
	  font-weight: bold;
	  padding: 2px 6px;
	  border-radius: 50%;
	}
	
	
    /* フォーム全体を左上に配置 */
    .search-container {
        text-align: left;
        padding: 20px;
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
    .item-box {
    	border: 1px solid #ccc;         /* 枠線の色と太さ */
    	box-shadow: 0 0 10px red, 0 0 20px crimson;
    	background-color: #222;
    	padding: 16px;                  /* 内側の余白 */
    	margin-bottom: 20px;            /* 下の余白 */
    	border-radius: 8px;             /* 角を丸く */
    	/* background-color: #f9f9f9;      /* 背景色（任意） */ */
    	box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* ほんのり影（任意） */
    	opacity: 0;
		transform: translateY(30px);
		transition: opacity 0.6s ease, transform 0.6s ease;
	}
	.item-box img {
	  width: 100%;
	  height: auto;
	  object-fit: cover;
	  border-radius: 8px;
	}
	.item-box p {
  		font-weight: bold;
	}

	.item-box.visible {
	  opacity: 1;
	  transform: translateY(0);
	}
	.item-box:hover {
  		transform: translateY(0) rotate(-5deg);
	}
	.redmoji{
		text-shadow: 0 0 10px red, 0 0 20px crimson;
	}
.item-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr); /* 4列に分割 */
  gap: 20px; /* アイテム間の余白 */
  padding: 20px;
}


#slider {
  height: 100vh;
  width: 100%;
  position: relative;
  z-index: 1;
  overflow: hidden;
}

.slider-text {
  position: absolute;
  top: 50%;
  left: 15%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 3rem;
  font-weight: bold;
  /* text-shadow: 2px 2px 5px rgba(0,0,0,0.7); */
  text-shadow: 0 0 10px red, 0 0 20px crimson;
  z-index: 2;
  pointer-events: none;
}
.slider-text-under {
  position: absolute;
  top: 60%;
  left: 20%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 3rem;
  font-weight: bold;
  text-shadow: 0 0 10px red, 0 0 20px crimson;
  z-index: 2;
  pointer-events: none;
}
.item-link {
  text-decoration: none;
  color: inherit;
  display: block;
}

.search-form {
  background-color: #1a1a1a; /* ダークグレー背景 */
  padding: 20px;
  border-radius: 12px;
  display: flex;
  gap: 15px;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  box-shadow: 0 4px 8px rgba(0,0,0,0.3); /* ほんのり影 */
}

.search-form input[type="text"],
.search-form select,
.search-form input[type="submit"] {
  padding: 10px 15px;
  border: none;
  border-radius: 8px;
  background-color: #333;
  color: #fff;
  font-size: 1rem;
  transition: background-color 0.3s ease;
}

.search-form input[type="text"]::placeholder {
  color: #aaa;
}

.search-form input[type="submit"] {
  background-color: #444;
  cursor: pointer;
}

.search-form input[type="submit"]:hover {
  background-color: #ff0000;
}

.cart i {
  color: white !important;
}


</style>

<script>


document.addEventListener("DOMContentLoaded", function () {
  // スライダー画像の切り替え
  const slider = document.getElementById("slider");
  const images = [
	"images/gyakusyuugundam.jpg",
    "images/SeedDestinygundam.jpg",
    "images/UCgundam.jpg",
    "images/Wgundam.jpg",
    "images/OOgundam.jpg",
    "images/Ggundam.jpg"
  ];
  let currentIndex = 0;
  const img = document.createElement("img");
  img.src = images[currentIndex];
  img.alt = "スライダー画像";
  img.style.width = "100%";
  img.style.height = "auto";
  slider.appendChild(img);

  setInterval(() => {
    currentIndex = (currentIndex + 1) % images.length;
    img.src = images[currentIndex];
  }, 2000);

  // ファーストビューの文字は即アニメーション
  const topElements = document.querySelectorAll('.js_typing_top');
  topElements.forEach(el => {
    const shuffle = new ShuffleText(el);
    shuffle.duration = 3000;
    shuffle.start();
  });

  // 商品一覧の文字はスクロールしてから1秒後にアニメーション
  const itemTextElements = document.querySelectorAll('.js_typing_item');
  const textObserver = new IntersectionObserver((entries, obs) => {
	  let textCount = 0;
	  
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        setTimeout(() => {
          const shuffle = new ShuffleText(entry.target);
          shuffle.duration = 3000;
          shuffle.start();
        }, 100 + textCount);

        textCount += 500;
        obs.unobserve(entry.target);
      }
    });
  }, {
    threshold: 0.4
  });
  itemTextElements.forEach(el => textObserver.observe(el));

  // 商品ボックスのフェードイン
  const itemBoxes = document.querySelectorAll('.item-box');
	let shownCount = 0; // 表示済みの数をカウント
	
	const boxObserver = new IntersectionObserver((entries, obs) => {
	  entries.forEach(entry => {
	    if (entry.isIntersecting) {
	      // 遅延時間を表示順にずらす（例：100msずつ）
	      setTimeout(() => {
	        entry.target.classList.add('visible');
	      }, shownCount * 100);
	
	      shownCount++; // 次の要素の遅延時間を増やす
	      obs.unobserve(entry.target);
	    }
	  });
	}, {
	  threshold: 0.2
	});
  itemBoxes.forEach(el => boxObserver.observe(el));
});


</script>


