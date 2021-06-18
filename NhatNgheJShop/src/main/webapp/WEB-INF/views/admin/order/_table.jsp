<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<b>ORDER LIST</b>
		</h3>
	</div>
	<table class="table table-hover">
		<thead class="bg-success">
			<tr>
				<th>Id</th>
				<th>Customer</th>
				<th>Order Date</th>
				<th>Amount</th>
				<th>Shipping Address</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${items}">
				<tr>
					<td>${item.id}</td>
					<td>${item.customer.id}</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy" value="${item.orderDate}"/></td>
					<td>${item.amount}</td>
					<td>${item.address}</td>
					<td>${status[item.status]}</td>
					<td class="text-center">
						<div class="btn-group btn-group-xs">
							<a href="${url}/edit/${item.id}" class="btn btn-primary"> <i
								class="glyphicon glyphicon-edit"></i>
							</a> 
							<sec:authorize access="hasAuthority('admin')">
								<a href="${url}/delete/${item.id}" class="btn btn-danger"> <i
									class="glyphicon glyphicon-trash"></i>
								</a>
							</sec:authorize>
						</div>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panel-footer">
		<div class="btn-group btn-group-sm">
		Page ${pager.page + 1} of ${pager.count} pages 
		
			<a href="${url}/index?page=0" class="btn btn-warning"> 
				<i class="glyphicon glyphicon-hand-up"></i>
			</a> 
			<a href="${url}/index?page=${pager.page - 1}" class="btn btn-info">
				<i class="glyphicon glyphicon-hand-left"></i>
			</a> 
			
			<a class="btn btn-success">${pager.page + 1}/${pager.pageCount}</a>
			
			 <a href="${url}/index?page=${pager.page + 1}" class="btn btn-info">
				<i class="glyphicon glyphicon-hand-right"></i>
			</a> 
			
			<a href="${url}/index?page=${pager.pageCount - 1}" class="btn btn-warning"> 
				<i class="glyphicon glyphicon-hand-down"></i>
			</a>
			
		</div>
	</div>
	
</div>