$(document).ready(function(){
	
	itemRegister() // 상품 등록
	
	
	function itemRegister(){
		$(".register_btn").click(function(){
			//입력칸 예외처리
			if($(".product_info_content").eq(0).find("input").val() == ""){
				alert("제목을 입력해주세요.")
			}
			if($(".product_info_content").eq(1).find(".select_input option:selected").val() == "default"){
				alert("카테고리를 선택해주세요.")
			}
			if($(".product_info_content").eq(2).find("input").val() == "" && $("#free_checkbox").is(":checked") == false){
				alert("가격입력 또는 무료나눔 체크해주세요.")
			}
			if($(".product_info_content").eq(3).find(".yn_margin input[type=checkbox]").eq(0).is(":checked") == false &&
					$(".product_info_content").eq(3).find(".yn_margin input[type=checkbox]").eq(1).is(":checked") == false){
				alert("금액제시 여부를 선택해주세요.")
			}
			if($(".product_info_content").eq(4).find("#writing_content").val() == ""){
				alert("상세설명을 입력해주세요.")
			}
			
			
			//가격 value 담기
			var price = "";
			if($("#free_checkbox").is(":checked") == true){
				price = "무료나눔";
			} else {
				price = $("#writing_price").val();
			}
			//금액제시 value 담기
			var offerYn = "";
			for(var i = 0; i < $(".yn_margin input[type=checkbox]").length; i++){
			    if($(".yn_margin input[type=checkbox]").eq(0).is(":checked") == true){
			      offerYn = "Y" //금액제시 가능
			    } else if($(".yn_margin input[type=checkbox]").eq(1).is(":checked") == true){
			      offerYn = "N"// 금액제시 불가
			    }
			}
			var params = {
				title : $(".title_input").val(),
				category: $(".select_input option:selected").val(),
				price: price,
				content : $("#writing_content").val(),
				offerYn : offerYn
			};
			console.log(params);
			
			
//			$.ajax({
//					type: "POST",
//					url: "/itemRegister",
//					contentType : "application/json; charset=UTF-8",
//					data : JSON.stringify(params)	 
//			}).done(function(){
//				
//			})
			
		});
	}
	
	//price 숫자만 입력
		$("#writing_price").on("keyup", function() {
			if(/\D/.test(this.value)) {
		            this.value = this.value.replace(/\D/g, "");
		            alert("숫자만 입력가능합니다.");
		        }
	    });
	//금액제시 체크박스
		$(".yn_margin input[type=checkbox]").click(function(){
			if($(this).prop("checked")){
			$(".yn_margin input[type=checkbox]").prop("checked",false);
			$(this).prop("checked", true);
		}
	   })
	   
	//무료나눔 체크박스
	   $("#free_checkbox").click(function(){
		   if($("#free_checkbox").is(":checked") == true){
			   $("#writing_price").prop("disabled","disabled");
		   } else {
			   $("#writing_price").prop("disabled","");
		   }
	   })
	   
	   
});