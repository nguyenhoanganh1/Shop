<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
<form:form action="/admin/category/index" modelAttribute="form">
	<h1>${path}</h1>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<b>CATEGORY EDITION</b> <i class="text-danger pull-right">${message}</i>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label>Category Id:</label>
				<form:input path="id" name="id" placeholder="Auto Number" readonly="true"
					class="form-control" value="${param.id }"/>
			</div>
			<div class="form-group">
				<label>English Name:</label>
				<form:input path="name"  name="name" placeholder="English Name?"
					class="form-control" value="${param.name }" />
			</div>
			<div class="form-group">
				<label>Vietnamese Name:</label>
				<form:input path="nameVN"  name="nameVN" placeholder="Vietnamese Name?"
					class="form-control" value="${param.nameVN }"/>
			</div>
		</div>
		<div class="panel-footer text-right">
			<button formaction="/admin/category/create" ${empty form.id ? "" : "disabled"}
				class="btn btn-primary">
				<i class="glyphicon glyphicon-plus"></i> Create
			</button>
			<button formaction="/admin/category/update" ${empty form.id ? "disabled" : ""}
				class="btn btn-success">
				<i class="glyphicon glyphicon-saved"></i> Update
			</button>
			<sec:authorize access="hasAuthority('admin')">
				<a href="/admin/category/delete/${form.id}" ${empty form.id ? "disabled" : ""}
					class="btn btn-danger"> <i class="glyphicon glyphicon-trash"></i>
					Delete
				</a> 
			</sec:authorize>
			
			
			<a href="/admin/category/index" class="btn btn-info"> <i
				class="glyphicon glyphicon-retweet"></i> Reset
			</a>
		</div>
	</div>
</form:form>