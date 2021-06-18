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
				<th>English Name</th>
				<th>Vietnamese Name</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${items}">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.nameVN}</td>
					<td class="text-center">
						<div class="btn-group btn-group-xs">
							<sec:authorize access="hasAnyAuthority('editor', 'admin')">
								<a href="${url}/edit/${item.id}" class="btn btn-primary"> <i
									class="glyphicon glyphicon-edit"></i>
								</a>
							</sec:authorize> 
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
			<a href="${url}/index/0" class="btn btn-warning"> <i
				class="glyphicon glyphicon-hand-up"></i>
			</a> <a href="${url}/index/${pager.pageNo - 1}" class="btn btn-info">
				<i class="glyphicon glyphicon-hand-left"></i>
			</a> <a class="btn btn-success">${pager.pageNo + 1}/${pager.pageCount}
			</a> <a href="${url}/index/${pager.pageNo + 1}" class="btn btn-info">
				<i class="glyphicon glyphicon-hand-right"></i>
			</a> <a href="${url}/index/${pager.pageCount - 1}"
				class="btn btn-warning"> <i
				class="glyphicon glyphicon-hand-down"></i>
			</a>
		</div>
	</div>
</div>