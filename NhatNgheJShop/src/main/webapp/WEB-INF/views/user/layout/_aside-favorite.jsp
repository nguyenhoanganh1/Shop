<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:if test="${!empty favos}">
	<div class="panel panel-danger nn-favo">
	    <div class="panel-heading">
	        <h3 class="panel-title">
	        	<i class="glyphicon glyphicon-heart"></i>
	        	Favorite Items ${favos.size() }
	        </h3>
	    </div>
	    <div class="panel-body">
	    	<c:forEach items="${favos}" var="p">
		    	<div class="col-xs-4 text-center">
		    		<a href="/product/detail/${p.id}">
			            <img src="/static/images/items/${p.image}">
			        </a>
		        </div>
	        </c:forEach>
	    </div>
	</div>
</c:if>

<style>
.nn-favo .col-xs-4{
	padding: 0px;
}
.nn-favo a{
	display: block;
	margin: 5px; 
	padding: 5px; 
	box-shadow: 0 0 3px gray; 
	border-radius: 5px;
}
.nn-favo a:hover{
	box-shadow: 0 0 5px red; 
}
.nn-favo img{
	height: 50px; 
	max-width: 100%;
}
</style>