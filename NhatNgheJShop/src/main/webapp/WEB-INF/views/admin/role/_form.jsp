<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form action="/admin/role/index" modelAttribute="form">
	<h1>${path}</h1>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<b>Role EDITION</b> <i class="text-danger pull-right">${message}</i>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label>Role Id:</label>
				<form:input path="id" name="id" placeholder="Auto Number" readonly="true"
					class="form-control" value="${param.id }"/>
			</div>
			<div class="form-group">
				<label>Role Name:</label>
				<form:input path="role"  name="role" placeholder="Role Name?"
					class="form-control" value="${param.role }" />
			</div>
		</div>
		<div class="panel-footer text-right">
			<button formaction="/admin/role/create" ${empty form.id ? "" : "disabled"}
				class="btn btn-primary">
				<i class="glyphicon glyphicon-plus"></i> Create
			</button>
			<button formaction="/admin/role/update" ${empty form.id ? "disabled" : ""}
				class="btn btn-success">
				<i class="glyphicon glyphicon-saved"></i> Update
			</button>
			<sec:authorize access="hasAuthority('admin')">
				<a href="/admin/role/delete/${form.id}" ${empty form.id ? "disabled" : ""}
					class="btn btn-danger"> <i class="glyphicon glyphicon-trash"></i>
					Delete
				</a> 
			</sec:authorize>
			
			<a href="/admin/role/index" class="btn btn-info"> <i
				class="glyphicon glyphicon-retweet"></i> Reset
			</a>
		</div>
	</div>
</form:form>