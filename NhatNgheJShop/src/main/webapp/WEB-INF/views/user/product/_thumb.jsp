<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="panel panel-default">
	<div class="panel-body">
		<c:forEach var="p" items="${prods }">
			<div class="col-sm-6">
				<a href="/product/detail/ ${p.id }"> <img
					src="/static/images/items/${p.image }"> ${p.name }
				</a>
			</div>
		</c:forEach>
	</div>
</div>