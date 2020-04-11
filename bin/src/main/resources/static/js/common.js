
$(document).ready(function(){
	
	//메뉴 아코디언
	$("#category_btn").click(function(){
		var $target = $("#menu_accordion");
		if($target.css("right") == "-400px"){
			$("#overlay_t").show();
			$target.animate({right: "0px", opacity:1},800,function(){
				$("body").css({"overflow":"hidden"});
				$("#hello").animate({opacity:1},1000);
				$("#menu_category h1").animate({opacity:1},1000);
				for(var i = 0; i < $("#categoty_name li").length; i++){
					$("#menu_category ul li").eq(i).animate({opacity:1},i * 400,function(){
							
					});
				}
				
			});
		}
	});
	$(".close").click(function(){
		$("#overlay_t").hide();
		var $target = $("#menu_accordion");
		$target.animate({right: "-400px",opacity:0},200,function(){
			$("body").css("overflow","");
			$("#hello").css("opacity", 0);
			$("#menu_category h1").css("opacity", 0);
			$("#menu_category ul li").css("opacity", 0);
		});
	})
	
});