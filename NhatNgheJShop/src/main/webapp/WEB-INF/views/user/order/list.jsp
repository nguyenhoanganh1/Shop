<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="panel panel-warning">
	<div class="panel-heading">
		<h3 class="panel-title">
			<b>YOUR PURCHASED ORDERS</b>
		</h3>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>STT</th>
				<th>Id</th>
				<th>Order Date</th>
				<th>Amount</th>
				<th>Shipping Address</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o" varStatus="st">
				<tr>
					<td>${st.index + 1 }</td>
					<td>${o.id }</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy" value="${o.orderDate }"/></td>			
					<td>${o.amount }</td>
					<td>${o.address }</td>
					<td>${status[o.status]}</td>
					<td class="text-center">
						<a href="/order/detail/${o.id}"	class="btn btn-xs btn-warning glyphicon glyphicon-search"></a> 
						<c:if test="${o.status == 0 }">
							<a href="/order/cancel/${o.id }" class="btn btn-xs btn-danger glyphicon glyphicon-trash"></a>
						</c:if>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panel-footer text-right">
		<b>Total Amount: ?</b>
	</div>
</div>