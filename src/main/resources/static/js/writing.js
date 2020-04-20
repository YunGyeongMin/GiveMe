var sel_files = []; //이미지담을 배열
var sel_file = [];

//서브이미지에서 특정 이미지 삭제
function deleteImg(index){
	if(index > 0) sel_files.splice(index,1);
	else if(index = 0) sel_file.splice(index,1);
	var img_class_index = ".img_index_"+index;
//	$(img_class_index).remove();
	$(img_class_index).attr("src", "/img/upload-icon.png");
	console.log(sel_files);
}


$(document).ready(function(){
	//이미지 등록
	   var sub_image = function(index, f){
		   var r = new FileReader();
		   r.onload = function(t){
				var html = `<img class="product_img img_index_`
				+ index
				+`" src="` + t.target.result  
				+`" style="width:100%"`
				+`data-file="` + t.target.name
				+`" data-index="`+ index
				+`" onclick="deleteImg(`+ index + `)`
				+`">`;
	
				$(".imgs_wrap").eq(index).empty();
				$(".imgs_wrap").eq(index).append(html);
		   }
		   r.readAsDataURL(f);
	   }
	   
	   //썸네일 이미지 업로드
		var input1 = document.createElement("INPUT");
		input1.setAttribute("type", "file");
		input1.onchange = function(e){
			var thumbnail = e.target.files[0];
			if(!thumbnail.type.match("image.*")){
				alert("이미지 확장자만 가능합니다!");
				return;
			}
			var r = new FileReader();
			r.onload = function(t){
				$(".product_img").eq(0).attr({
					"src":t.target.result,
					"data-key":0,
					"data-file":t.target.name,
					"onclick":"deleteImg(0)"
					});
				$(".product_img").eq(0).addClass("img_index_0");
			}
			r.readAsDataURL(thumbnail);
		};
		
		//다중 서브이미지 업로드
		var input2 = document.createElement("INPUT");
		input2.setAttribute("type", "file");
		input2.setAttribute("multiple", "multiple");
		input2.onchange = function(e){
			   if(input1.files.length == 0) {
				   alert("썸네일 이미지를 등록해주세요!");
				   return;
			   }
			   var subs = e.target.files;
			   for(var i = 0; i < subs.length; i++) {
				   if(i == 5) return;
				   if(!subs[i].type.match("image.*")){
					   alert("이미지 확장자만 가능합니다!");
					   return;
				   }
				   sub_image((i + 1), subs[i]);
			   }
		};
		
		//썸네일 이미지 등록
		$("#thumbnail_img").click(function(){
			input1.click();
		});
		
		//서브 이미지 등록
		$("#sub_img").click(function(){
			input2.click();
		});
	
	//상품등록
//		function itemRegister(){
			$(".register_btn").click(function(){
				//입력칸 예외처리
				var thumbnail_confirm = $(".imgs_wrap").eq(0).find(".product_img").prop("src");
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
				} else if(!$(".product_img").eq(0).hasClass("img_index_0") || thumbnail_confirm.substr(thumbnail_confirm.length-16 , 16) == "/upload-icon.png"){
					alert("썸네일 이미지를 등록해주세요.")
				}else{
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
							//ajax 이미지 등록
							if(input1.files.length > 0){
								console.log("썸네일 파일 확인!");
								var form = new FormData();
								form.append("file",input1.files[0]);
								$.ajax({
									type:"POST",
									url:"/T_imgUpload",
									enctype:"multipart/form-data",
									processData:false,
									contentType:false,
									cache:false,
									data: form
								}).done(function(d){
									if(d) console.log("썸네일 이미지 등록완료!")
									if(input1.files.length > 0){
										var form = new FormData();
										var len = input2.files.length;
										for(var i = 0; i < len; i++){
//											var name = "file" + i;
											form.append("file", input2.files[i]);
										}
										form.append("file_count", len);
										$.ajax({
											type:"POST",
											url:"/S_imgUpload",
											enctype:"multipart/form-data",
											processData:false,
											contentType:false,
											cache:false,
											data: form
										}).done(function(d){
											if(d) console.log("서브 이미지 등록완료!")
											else console.log("서브 이미지등록 오류! 관리자에게 문의하세요.")
										})
									} else {
										console.log("서브 이미지는 찾지 못하였습니다. 서브이미지를 등록하고싶은경우 다시 등록해주세요.")
									}	
										
								})
				
							}
							
						} else {
							alert("등록 오류발생했습니다. 관리자에게 문의 하세요!")
						}
					})
				}
				
				
				
			});
//		}
	
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
	   });
	   
	//무료나눔 체크박스 클릭시 가격쓰기 비활성화
	   $("#free_checkbox").click(function(){
		   if($("#free_checkbox").is(":checked") == true){
			   $("#writing_price").prop("disabled","disabled");
		   } else {
			   $("#writing_price").prop("disabled","");
		   }
	   });
	   
	   
	   
	//이미지 등록
//	   function imgRegister(){
//			//썸네일 이미지 업로드
//			var input = document.createElement("INPUT");
//			input.setAttribute("type", "file");
//			input.onchange = imgChange1
//				function(e){
//				console.log("e : " + e.target);
//				var files = e.target.files;
//				var filesArr = Array.prototype.slice.call(files);
//				console.log(files,filesArr);
//				
//				filesArr.forEach(function(f){
//					if(!f.type.match("image.*")){
//						alert("이미지 확장자만 가능합니다!")
//						files= "";
//						filesArr="";
//						return;
//					}
//					sel_file.push(f)
//					var r = new FileReader();
//					r.onload = function(t){
//					console.log("t :" + t.target);
//					$(".product_img").eq(0).attr({
//						"src":t.target.result,
//						"data-key":0,
//						"data-file":f.name,
//						"onclick":"deleteImg(0)"
//						});
//					$(".product_img").eq(0).addClass("img_index_0");
//					
//				}
//				r.readAsDataURL(e.target.files[0]);
//				})
//			}
//			return;
//			//다중 서브이미지 업로드
//			var input2 = document.createElement("INPUT");
//			input2.setAttribute("type", "file");
//			input2.setAttribute("multiple", "multiple");
//			input2.onchange = imgChange2
//				function(e){
//				var thumbnail_confirm = $(".imgs_wrap").eq(0).find(".product_img").prop("src");
//				if(thumbnail_confirm.substr(thumbnail_confirm.length-16 , 16) == "/upload-icon.png"){
//					alert("썸네일 이미지를 등록해주세요!");
//					return;
//				}
//				sel_files = [];
//				var files = e.target.files;
//				var filesArr = Array.prototype.slice.call(files);
//				console.log(files,filesArr);
//				if(filesArr.length > 5){
//					alert("서브이미지는 5개까지 입니다. 다시 등록 부탁드립니다.")
//					files= "";
//					filesArr="";
//					return;
//				}
//				console.log("e.target : " + e.target);
//				var index = 0;
//				filesArr.forEach(function(f){
//					if(!f.type.match("image.*")){
//						alert("이미지 확장자만 가능합니다!")
//						files= "";
//						filesArr="";
//						return;
//					}
//					
//					sel_files.push(f);
//					
//					var r = new FileReader();
//					r.onload = function(t){
////						console.log("t :" + t.target);
//						var html = `<img class="product_img img_index_`
//							+ index
//							+`" src="` + t.target.result  
//							+`" style="width:100%"`
//							+`data-file="` + f.name
//							+`" data-index="`+ index
//							+`" onclick="deleteImg(`+ index + `)`
//							+`">`;
//							
//						$(".imgs_wrap").eq(index + 1).empty();
//						$(".imgs_wrap").eq(index + 1).append(html);
//						index++;
//					}
//					r.readAsDataURL(f);
//				});
//				
//			}
			
//			//썸네일 이미지 등록
//			$("#thumbnail_img").click(function(){
//				input.click();
//			})
//			
//			//서브 이미지 등록
//			$("#sub_img").click(function(){
//				input2.click();
//			})
//	   }
	   
//		itemRegister() 	//상품 등록
//		imgRegister()	//이미지등록
	   
});



