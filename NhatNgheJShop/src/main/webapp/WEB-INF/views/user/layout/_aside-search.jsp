<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="panel panel-danger">
	<form action="/product/find-by-keywords" class="panel-body">
		<input value="${param.keywords }" name="keywords"
			placeholder="Keywords?" class="form-control" />
	</form>
</div>