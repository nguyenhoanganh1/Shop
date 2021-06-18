<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!--Slide show-->
<div class="mi-slider" id="mi-slider">
	<c:forEach items="${list }" var="c">
		<ul>
			<c:forEach items="${c.products}" var="p">
				<li><a href="/product/detail/${p.id }"> 
				<img alt="" src="/static/images/items/${p.image }" />				
				</a></li>
			</c:forEach>
		</ul>
	</c:forEach>
	<nav>
		<c:forEach var="d" items="${list }">
			<a href="#">${d.name }</a>
		</c:forEach>
	</nav>
</div>
<style>
	.mi-slider{
		height: 330px;
	}
	.mi-slider ul li img {
		height: 200px;
	}
</style>
<script>
	$(function() {
		showCatSlider('.mi-slider', 5000);
	});
</script>