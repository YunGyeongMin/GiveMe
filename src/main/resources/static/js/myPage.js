$(document).ready(function(){
	
	/*탭메뉴*/
	$(".tabs li").click(function(){
		var tab_id = $(this).attr('data-tab');
		console.log(tab_id);
		switch (tab_id) {
		case "tab_1":
			break;
		case "tab_2":
			$(".tabs li").eq(0).addClass("pick_clear2");
			break;
		case "tab_3":
			$(".tabs li").eq(1).addClass("pick_clear2");
			$(".tabs li").eq(0).addClass("pick_clear2");
			break;
		default:
			alert("오류입니다. 관리자에게 문의하세요!");
			break;
		}
		$(".tabs li").removeClass("pick");
		$(".tab-content").removeClass("pick");
		$(this).addClass("pick")
		$("#"+tab_id).addClass("pick")
	})
	
	/*별점주기 팝업창*/
	$("#myPage_trust_btn").click(function(){
		$("#overlay_t").show();
		$("#popup_div").css("display","block");
		$("body").css("overflow","hidden");
		$("#popup_div").css({
			"top":(($(window).height() - $("#popup_div").outerHeight()) / 2 + $(window).scrollTop()) +"px",
			"left": (($(window).width()-$("#popup_div").outerWidth())/2+$(window).scrollLeft())+"px"
		});
	});
	
	$("#close_btn").click(function(){
		$("#overlay_t").hide();
		$("#popup_div").css("display","none");
		$("body").css("overflow","auto");
	})
	
	$(".star span").click(function(){
		console.log($(this));
		console.log($(this).parent().children("span"));
		console.log($(this).addClass("on").prevAll("span"));
		$(this).parent().children("span").removeClass("on");
		$(this).addClass("on").prevAll("span").addClass("on");
		return false;
	})
	
	/*회원탈퇴 팝업창*/
	$(".user_remove").click(function(){
		$("#overlay_t").show();
		$("#popup_div2").css("display","block");
		$("body").css("overflow","hidden");
		$("#popup_div2").css({
			"top":(($(window).height() - $("#popup_div").outerHeight()) / 2 + $(window).scrollTop()) +"px",
			"left": (($(window).width()-$("#popup_div").outerWidth())/2+$(window).scrollLeft())+"px"
		});
	});
	
	$("#close_btn2").click(function(){
		$("#overlay_t").hide();
		$("#popup_div2").css("display","none");
		$("body").css("overflow","auto");
	})
	
	$("#remove_btn").click(function(){
		alert("회원탈퇴가 완료 되었습니다.");
	})
	
	
	
});