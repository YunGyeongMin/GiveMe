
$(document).ready(function(){
	
	//메뉴 아코디언
	$("#category_btn").click(function(){
		var $target = $("#menu_accordion");
		if($target.css("right") == "-400px"){
			$("#overlay_t").show();
			$target.animate({right: "0px", opacity:1},800,function(){
				$("body").css({"overflow":"hidden"});
				$("#hello").animate({opacity:1},1000);
			});
		}
	});
	$(".close").click(function(){
		$("#overlay_t").hide();
		var $target = $("#menu_accordion");
		$target.animate({right: "-400px",opacity:0},300,function(){
			$("body").css("overflow","");
			$("#hello").css("opacity", 0);
		});
	})
	
});