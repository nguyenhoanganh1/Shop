<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:forEach items="${cateProduct }" var="p">
	<div class="col-sm-4">
		<div class="panel panel-primary nn-prod">
			<div class="panel-heading text-center">${p.name } </div>
			<div class="panel-body text-center">
				<a href="/product/detail/${p.id}"> 
					<img class="nn-image-product" src="/static/images/items/${p.image }">
				</a>
			</div>
			<div class="panel-footer">
				<div class="row" data-item="${p.id }">
					<div class="col-md-4 text-left text-primary">$${p.unitPrice}</div>
					<div class="col-md-8 text-right">
						<div class="btn-group btn-group-sm">
							<jsp:include page="_actions.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${p.special }">
					<img alt="special" src="/static/images/special_icon.gif">
				</c:when>
				<c:when test="${p.latest }">
					<img alt="new" src="/static/images/new_icon.gif">
				</c:when>
				<c:when test="${p.discount > 0 }">
					<img alt="promo" src="/static/images/promo_icon.gif">
				</c:when>
			</c:choose>
		</div>
	</div>
</c:forEach>

<jsp:include page="_send-dialog.jsp" />