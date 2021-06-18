<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="btn-group btn-group-xs">
    <a href="${url}/edit/${item.id}" class="btn btn-primary">
        <i class="glyphicon glyphicon-edit"></i>
    </a>
    <sec:authorize access="hasAuthority('ADMIN')">
	    <a href="${url}/delete/${item.id}" class="btn btn-danger">
	        <i class="glyphicon glyphicon-trash"></i>
	    </a>
    </sec:authorize>
</div>