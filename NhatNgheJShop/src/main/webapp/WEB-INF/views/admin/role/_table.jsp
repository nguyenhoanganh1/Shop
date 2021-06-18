<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<b>CATEGORY LIST</b>
		</h3>
	</div>
	<table class="table table-hover">
		<thead class="bg-success">
			<tr>
				<th>Id</th>
				<th>Role Name</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${items}">
				<tr>
					<td>${item.id}</td>
					<td>${item.role}</td>
					<td class="text-center">
						<div class="btn-group btn-group-xs">
							<sec:authorize access="hasAuthority('admin')">
								<a href="/admin/role/edit/${item.id}" class="btn btn-primary"> <i
									class="glyphicon glyphicon-edit"></i>
								</a>
								 <a href="/admin/role/delete/${item.id}" class="btn btn-danger"> <i
									class="glyphicon glyphicon-trash"></i>
								</a>
							</sec:authorize>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>