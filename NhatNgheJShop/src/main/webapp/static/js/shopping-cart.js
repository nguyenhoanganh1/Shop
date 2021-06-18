$(function() {
	function show_shopping_cart(resp) {
		$(".cart-count").html(resp.count);
		$(".cart-amount").html(resp.amount);
	}
	
	// initializing
	$.ajax({
		url: `/cart/info`,
		success: function(resp) {
			show_shopping_cart(resp);
		}
	})

	$(".nn-cart-add").click(function() {
		var id = $(this).parents("[data-item]").attr("data-item");
		$.ajax({
			url: `/cart/add/${id}`,
			success: function(resp) {
				show_shopping_cart(resp);
			}
		})
		
		//Hiệu ứng giỏ hàng
		var img = $(this).parents(".nn-prod").find(".panel-body .nn-image-product");
	
		var options = {
			to: ".icon-shoppping-cart",
			className: "cart-effect"
		}
		
		img.effect("transfer", options, 700);
		var effect_css = `.cart-effect{
			border: 1px solid red;
			background-image: url("${img.attr("src")}");
			background-size: 100% 100%;
		}`;
		$("#style-shopping-cart").html(effect_css);
	});
	
	
	
	$(".nn-btn-remove").click(function() {
		/*var id = $(this).parents("[data-item]").attr("data-item");*/
		var item = JSON.parse($(this).parents("[data-item]").attr("data-item"));
		$.ajax
			({
				url: `/cart/remove/${item.id}`,
				success: function(resp) {
					show_shopping_cart(resp);
				}
			})
		$(this).parents("[data-item]").hide(500);
	});

	$(".nn-cart-changeQuantity").on("change", function() {
		/*var id = $(this).parents("[data-item]").attr("data-item");*/
		var quantity = $(this).val();

		var item = JSON.parse($(this).parents("[data-item]").attr("data-item"));
		$.ajax
			({
				url: `/cart/update/${item.id}/${quantity}`,
				success: function(resp) {
					show_shopping_cart(resp);
				}
			})
		var amt = Math.round(100 * quantity * item.price - (1 - item.disc)) / 100;
		$(this).parents("[data-item]").find(".nn-cart-amt").html("$" + amt);
	});

	$(".nn-cart-clear").click(function() {
		$.ajax
			({
				url: `/cart/clear`,
				success: function(resp) {
					show_shopping_cart(resp);
					$(".nn-cart-tbody").empty();
				}
			})
	});
})