<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title"><s:message code="layout.aside.bestSeller"/></h3>
	</div>
	<div class="list-group">
		<a href="/product/find-by-special/0" class="list-group-item"> 
			<s:message code="layout.aside.latest"/> 
		</a> 
		
		<a href="/product/find-by-special/1" class="list-group-item">
			<s:message code="layout.aside.latest"/>
		</a> 
		
		<a href="/product/find-by-special/2" class="list-group-item"> 
			<s:message code="layout.aside.saleOff"/> 
		</a> 
		
		<a href="/product/find-by-special/3" class="list-group-item"> 
			<s:message code="layout.aside.mostView"/> 
		</a> 
		
		<a href="/product/find-by-special/4" class="list-group-item"> 
			<s:message code="layout.aside.special"/> 
		</a>
	</div>
</div>