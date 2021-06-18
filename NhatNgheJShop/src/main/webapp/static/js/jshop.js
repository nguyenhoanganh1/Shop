$(function() {	
		
	$("#loading").hide();	
	
	$(".btn-send").click(function() {	
		var id = $(".nn-share-open").parents("[data-item]").attr("data-item");
		console.log(id);	
		var data = {
			id: id,
			sender: $("#myModal #sender").val(),
			receiver: $("#myModal #receiver").val(),
			subject: $("#myModal #subject").val(),
			content: $("#myModal #content").val()
		};
		
		
		
		$("#loading").show();
		$.ajax({
			url: `/product/share/${id}`,
			method: 'post',
			data: data,
			success: function(resp) {
				$("#myModal").modal("hide");
				$("#loading").hide();				
			}
		})
	})
	
	
	
	/*Hiện thị mặt hàng yêu thích*/
	$(".nn-favo-add").click(function(){
	var id2 = $(this).parents("[data-item]").attr("data-item");	
	
		$.ajax({
			url: `/product/like/${id2}`,
			
			success: function(resp){
				$("#favos").html(resp);
			}
		})
	})
	
	$("#logoff").on("click",function(e){
		e.preventDefault();
		document.logoutForm.submit();
	})
	
})