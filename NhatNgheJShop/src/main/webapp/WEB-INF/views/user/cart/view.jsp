<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<div class="panel panel-warning">
	<div class="panel-heading">
		<h3 class="panel-title">
			<b>YOUR SHOPPING CART</b>
		</h3>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Unit Price</th>
				<th>Discount</th>
				<th>Quantity</th>
				<th>Amount</th>
				<th></th>
			</tr>
		</thead>
		<tbody class="nn-cart-tbody">
			<c:forEach var="cart" items="${cart }" varStatus="st">
				<tr
					data-item='{"id": ${cart.id },"price": ${cart.price }, "disc": ${cart.disc }}'>
					<td>${st.index + 1 }</td>
					<td><a href="/product/detail/${cart.id }">${cart.name }</a></td>
					<td>$${cart.price}</td>
					<td>${cart.disc * 100 }%</td>
					<td><input class="nn-cart-changeQuantity" value="${cart.qty}"
						type="number" min="1" onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')"></td>
					<%-- <td>
						<div class="btn-group btn-group-xs">
							<button class="btn btn-warning">
								<i class="glyphicon glyphicon-minus"></i>
							</button>
							<button  class="btn btn-default" style="width: 40px;">${cart.qty }</button>
							<button class="btn btn-warning">
								<i class="glyphicon glyphicon-plus"></i>
							</button>
						</div>
					</td> --%>
					<td class="nn-cart-amt">$${cart.amount}</td>
					<td class="text-center">
						<button
							class="btn btn-xs btn-danger glyphicon glyphicon-trash nn-btn-remove"></button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panel-footer text-right">
		<a href="/" class="btn btn-sm btn-info">
			<i class="glyphicon glyphicon-hand-left"></i> Add More
		</a>
		<button class="btn btn-sm btn-warning nn-cart-clear">
			<i class="glyphicon glyphicon-trash"></i> Clear All
		</button>
		
		<security:authorize access="!isAuthenticated()">
			<a href="/order/checkout" class="btn btn-success btn-sm"> <i
				class="glyphicon glyphicon-ok"></i> Check out
			</a>
		</security:authorize>
		
		<%-- <c:if test="${empty sessionScope.user}">
			<a href="/order/checkout" class="btn btn-success btn-sm"> <i
				class="glyphicon glyphicon-ok"></i> Check out
			</a>
		</c:if> --%>

		<b class="pull-left">Total Amount: <i class="cart-amount">?</i></b>
	</div>
</div>

<security:authorize access="isAuthenticated()">
<form action="/order/purchase" method="post">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title">
					<b>ORDER INFORMATION</b>
				</h3>
			</div>
			<div class="panel panel-body">
				<input name="cart" type="hidden" value="???">
				<div class="row">
					<div class="form-group col-sm-12">
						<label>Shipping Address:</label> <input name="address" placeholder="Shipping Address?" class="form-control">
					</div>
					<div class="form-group col-sm-12">
						<label>Notes:</label>
						<textarea name="notes" placeholder="Write something here?" rows="3" class="form-control"></textarea>
					</div>
				</div>

			</div>

			<div class="panel-footer text-right">
				<button class="btn btn-danger">
					<i class="glyphicon glyphicon-gbp"></i> Purchase
				</button>
			</div>
		</div>
	</form>
</security:authorize>
<c:if test="${!empty sessionScope.user}">
	<form action="/order/purchase" method="post">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title">
					<b>ORDER INFORMATION</b>
				</h3>
			</div>
			<div class="panel panel-body">
				<input name="cart" type="hidden" value="???">
				<div class="row">
					<div class="form-group col-sm-12">
						<label>Shipping Address:</label> <input name="address" placeholder="Shipping Address?" class="form-control">
					</div>
					<div class="form-group col-sm-12">
						<label>Notes:</label>
						<textarea name="notes" placeholder="Write something here?" rows="3" class="form-control"></textarea>
					</div>
				</div>

			</div>

			<div class="panel-footer text-right">
				<button class="btn btn-danger">
					<i class="glyphicon glyphicon-gbp"></i> Purchase
				</button>
			</div>
		</div>
	</form>
</c:if>
