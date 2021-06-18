<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<button formaction="${url}/create" ${empty form.id ? "" : "disabled"} class="btn btn-primary">
    <i class="glyphicon glyphicon-plus"></i> Create
</button>
<button formaction="${url}/update" ${empty form.id ? "disabled" : ""} class="btn btn-success">
    <i class="glyphicon glyphicon-saved"></i> Update
</button>
<sec:authorize access="hasAuthority('ADMIN')">
	<a href="${url}/delete/${form.id}" ${empty form.id ? "disabled" : ""} class="btn btn-danger">
	    <i class="glyphicon glyphicon-trash"></i> Delete
	</a>
</sec:authorize>
<a href="${url}/index" class="btn btn-info">
    <i class="glyphicon glyphicon-retweet"></i> Reset
</a>