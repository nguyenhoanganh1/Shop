<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<sec:authentication property="principal" var="user" />
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<b>CUSTOMER LIST</b>
		</h3>
	</div>
	<table class="table table-hover">
		<thead class="bg-success">
			<tr>
				<th>Username</th>
				<!-- <th>Password</th> -->
				<th>Full Name</th>
				<th>Email Address</th>
				<!-- <th>Photo</th> -->
				<th>Status</th>
				<th>Role</th>
				<th>Authorization</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${items}">
				<tr>
					<td>${item.id}</td>
					<%-- <td>${item.password}</td> --%>
					<td>${item.fullname}</td>
					<td>${item.email}</td>
					<%-- <td>${item.photo}</td> --%>
					<td>${item.activated?'Active':'Inactive'}</td>
					<%-- <td>${item.admin?'Administrator':'User'}</td>		 --%>	
					
					<td>${item.roles}</td>	

					<td>${item.provider}</td>
					<td class="text-center">
						<div class="btn-group btn-group-xs">
							<sec:authorize access="hasAnyAuthority('admin','editor')">
								<a href="/admin/customer/edit/${item.id}" class="btn btn-primary"> <i
									class="glyphicon glyphicon-edit"></i>
								</a>
							</sec:authorize>
							<sec:authorize access="hasAuthority('admin')">
							 	
								 <a href="/admin/customer/delete/${item.id}" class="btn btn-danger"> <i
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
		<jsp:include page="../shared/_pager-buttons.jsp" />
	</div>
</div>