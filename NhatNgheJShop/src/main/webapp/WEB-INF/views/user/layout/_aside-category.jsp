<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Categories</h3>
	</div>
	<c:forEach items="${category }" var="cat">
		<div class="list-group">
			<a href="/product/find-by-category/${cat.id }"
				class="list-group-item">
				<c:choose>
					<c:when test="${pageContext.response.locale.language == 'vi'}"> 
						${cat.nameVN } 
					</c:when>
					<c:otherwise> ${cat.name }</c:otherwise> 
				</c:choose>				
				</a>
		</div>
	</c:forEach>

</div>

