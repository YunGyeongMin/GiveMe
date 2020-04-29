$(document).ready(function(){
	
	$("#comment_btn").click(function(){
		console.log($("#comment").val());
		
		var param = {comment : $("#comment").val()}
		
		$.ajax({
			type:"POST",
			url:"/comment",
			contentType : "application/json; charset=UTF-8",
			data : JSON.stringify(param)
		}).done(function(d){
			if(d > 0) {
				console.log("댓글등록완료!");
				location.reload();
//				$("#comment_div").load(window.location.href + $("#comment_div"));
			};
		})
		
		
	});
	
});