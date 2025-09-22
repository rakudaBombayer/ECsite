$(".openbtn").click(function () {//ボタンがクリックされたら
	$(this).toggleClass('active');//ボタン自身に activeクラスを付与し
    $("#g-nav").toggleClass('panelactive');//ナビゲーションにpanelactiveクラスを付与
});

$("#g-nav a").click(function () {//ナビゲーションのリンクがクリックされたら
    $(".openbtn").removeClass('active');//ボタンの activeクラスを除去し
    $("#g-nav").removeClass('panelactive');//ナビゲーションのpanelactiveクラスも除去
});


//開くボタンを押した時には
$(".open-btn").click(function () {
    $("#search-wrap").addClass('panelactive');//#search-wrapへpanelactiveクラスを付与
	$('#search-text').focus();//テキスト入力のinputにフォーカス
});

//閉じるボタンを押した時には
$(".close-btn").click(function () {
    $("#search-wrap").removeClass('panelactive');//#search-wrapからpanelactiveクラスを除去
});