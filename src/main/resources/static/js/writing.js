$(document).ready(function(){
	
	itemRegister() 	//상품 등록
	imgRegister()	//이미지등록
	
	//상품등록
		function itemRegister(){
			$(".register_btn").click(function(){
				//입력칸 예외처리
				if($(".product_info_content").eq(0).find("input").val() == ""){
					alert("제목을 입력해주세요.")
				} else if($(".product_info_content").eq(1).find(".select_input option:selected").val() == "default"){
					alert("카테고리를 선택해주세요.")
				} else if($(".product_info_content").eq(2).find("input").val() == "" && $("#free_checkbox").is(":checked") == false){
					alert("가격입력 또는 무료나눔 체크해주세요.")
				} else if($(".product_info_content").eq(3).find(".yn_margin input[type=checkbox]").eq(0).is(":checked") == false &&
						$(".product_info_content").eq(3).find(".yn_margin input[type=checkbox]").eq(1).is(":checked") == false){
					alert("금액제시 여부를 선택해주세요.")
				} else if($(".product_info_content").eq(4).find("#writing_content").val() == ""){
					alert("상세설명을 입력해주세요.")
				} else{
					console.log("상품등록 리스트!")
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
					
					$.ajax({
							type: "POST",
							url: "/itemRegister",
							contentType : "application/json; charset=UTF-8",
							data : JSON.stringify(params)	 
					}).done(function(d){
						if(d > 0) {
							console.log("상품정보등록 완료!")
							
						} else {
							alert("등록 오류발생했습니다. 관리자에게 문의 하세요!")
						}
					})
				}
				
				
				
			});
		}
	
	//price 숫자만 입력 및 무료나눔 체크박스 비활성화
		$("#writing_price").on("keyup", function() {
			if($("#writing_price").val() != ""){
				$("#free_checkbox").prop("disabled","disabled");
			} else {
				$("#free_checkbox").prop("disabled","");
			}
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
	   
	//무료나눔 체크박스 클릭시 가격쓰기 비활성화
	   $("#free_checkbox").click(function(){
		   if($("#free_checkbox").is(":checked") == true){
			   $("#writing_price").prop("disabled","disabled");
		   } else {
			   $("#writing_price").prop("disabled","");
		   }
	   })
	
	//이미지 등록
	   function imgRegister(){
			//이미지 업로드 인풋창
			var input = document.createElement("INPUT");
			input.setAttribute("type", "file");
			input.onchange = function(e){
//				console.log("e : " + e.target);
				var r = new FileReader();
				r.onload = function(t){
//					console.log("t :" + t.target);
					$(".product_img").eq(0).attr("src",t.target.result);
				}
				r.readAsDataURL(e.target.files[0]);
			}
			//다중이미지 업로드 인풋창
			var input2 = document.createElement("INPUT");
			input2.setAttribute("type", "file");
			input2.setAttribute("multiple", "multiple");
			input2.onchange = function(e){
//				console.log("e.target : " + e.target);
				var r = new FileReader();
				r.onload = function(t){
					console.log("t.target :" + t.target);
//					console.log("t.target.result :" + t.target.result);
//					console.log("t.target.result :" + t.target.length);
					$(".product_img").eq(1).attr("src",t.target.result);
				}
				r.readAsDataURL(e.target.files[0]);
			}
			
			//썸네일 이미지 등록
			$("#thumbnail_img").click(function(){
				input.click();
			})
			
			//서브 이미지 등록
			$("#sub_img").click(function(){
				input2.click();
			})
			
			
			
	   }
	   
});