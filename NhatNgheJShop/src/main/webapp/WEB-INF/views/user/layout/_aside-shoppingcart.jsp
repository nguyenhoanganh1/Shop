<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div class="panel panel-primary">
	<div class="panel-body">
		<div class="col-xs-5" style="font-size: 50px;">
			<b class="glyphicon glyphicon-shopping-cart icon-shoppping-cart"></b>
		</div>
		<ul class="col-xs-7">
			<li><b class="cart-count">0 </b>Items</li>
			<li><b class="cart-amount">0 </b>USD</li>
			<li><a href="/cart/view"> <s:message code="layout.menu.viewcart"/></a></li>
		</ul>
	</div>
</div>
<style id="style-shopping-cart">
	.cart-effect{
		border: 1px solid red;
		background-image: url("/static/images/items/1008.jpg");
		background-size: 100% 100%;
		
		}
</style>