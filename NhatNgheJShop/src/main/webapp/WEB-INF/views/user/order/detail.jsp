<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<b>ORDER INFORMATION</b>
		</h3>
	</div>
	<form:form class="panel-body" modelAttribute="order">
		<div class="row">
			<div class="form-group col-sm-4">
				<label>Order Id:</label>
				<form:input path="id" readonly="true" class="form-control" />
			</div>
			<div class="form-group col-sm-4">
				<label>Purchaser:</label>
				<form:input path="customer.id" readonly="true" class="form-control" />
			</div>
			<div class="form-group col-sm-4">
				<label>Order Date:</label>
				<form:input path="orderDate" readonly="true" class="form-control" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-4">
				<label>Total Amount:</label>
				<form:input path="amount" readonly="true" class="form-control" />
			</div>
			<div class="form-group col-sm-4">
				<label>Order Status:</label>
					<div class="form-control">
						<c:choose>
					    	<c:when test="${order.status == -1}"><label class="label label-primary">Canceled</label></c:when>
					    	<c:when test="${order.status == 0}"><label class="label label-info">New</label></c:when>
					    	<c:when test="${order.status == 1}"><label class="label label-success">Confirming</label></c:when>
					    	<c:when test="${order.status == 2}"><label class="label label-danger">Confirmed</label></c:when>
					    	<c:when test="${order.status == 3}"><label class="label label-success">Shipping</label></c:when>
					    	<c:when test="${order.status == 4}"><label class="label label-danger">Completed</label></c:when>
					 </c:choose>
				 </div>
			</div>
			<div class="form-group col-sm-4">
				<label>QR Code</label>
				<img alt="QR Code" src="${pageContext.request.contextPath }/order/qrcode/${order.id}" width="100px">
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-12">
				<label>Shipping Address:</label>
				<form:input path="address" readonly="true" class="form-control" />
			</div>
			<div class="form-group col-sm-12">
				<label>Notes:</label>
				<form:textarea path="notes" readonly="true" rows="3"
					class="form-control" />
			</div>
		</div>
	</form:form>
</div>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<b>ORDER DETAILS</b>
		</h3>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Unit Price</th>
				<th>Quantity</th>
				<th>Discount</th>
				<th>Amount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.orderDetails }" var="d">
				<tr>
					<td>${d.product.id }</td>
					<td>${d.product.name }</td>
					<td>${d.unitPrice }</td>
					<td>${d.discount }</td>
					<td>${d.quantity }</td>
					<td>${d.unitPrice * d.quantity - (1 * d.discount) }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:choose>
	<c:when test="${order.status == 0 }">
		<div class="panel panel-default">
			<div class="panel-heading">
				<a class="btn btn-default" href="/order/list">
					<span class="glyphicon glyphicon-list"></span> Your Order List</a>
				<a class="btn btn-default" href="/order/cancel/${order.id }">
					<span class="glyphicon glyphicon-trash"></span> Cancel Order</a>
			</div>
		</div>
	</c:when>
	<c:when test="${order.status == 1 }">
		<div class="panel panel-default">
			<div class="panel-heading">
				<a class="btn btn-default" href="/order/list">
						<span class="glyphicon glyphicon-list"></span> Your Order List</a>
					<a class="btn btn-default" href="/order/cancel/${order.id }">
						<span class="glyphicon glyphicon-trash"></span> Cancel Order</a>
			</div>
		</div>
	</c:when>
	<c:when test="${order.status == 2 }">
		<div class="panel panel-default">
			<div class="panel-heading">
				<a class="btn btn-default" href="/order/list">
					<span class="glyphicon glyphicon-list"></span> Your Order List</a>
				<a class="btn btn-default" href="/order/cancel/${order.id }">
					<span class="glyphicon glyphicon-trash"></span> Cancel Order</a>
			</div>
		</div>
	</c:when>
	<c:when test="${order.status == 3 }">
		<div class="panel panel-default">
			<div class="panel-heading">
				<a class="btn btn-default" href="/order/list">
					<span class="glyphicon glyphicon-list"></span> Your Order List</a>
				<a class="btn btn-default" href="/order/cancel/${order.id }">
					<span class="glyphicon glyphicon-trash"></span> Cancel Order</a>
			</div>
		</div>
	</c:when>

</c:choose>

