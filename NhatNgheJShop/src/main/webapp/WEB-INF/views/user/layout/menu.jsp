<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"	prefix="security"%>

<security:authentication property="principal" var="user" />
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button class="navbar-toggle" data-toggle="collapse"
				data-target="#hmenu">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Shop Online</a>
		</div>
		<div class="collapse navbar-collapse" id="hmenu">
			<ul class="nav navbar-nav">
				<li><a href="/home/index"><i
						class="glyphicon glyphicon-home"></i> <s:message
							code="layout.menu.home" /></a></li>
				<li><a href="/home/about"><i
						class="glyphicon glyphicon-info-sign"></i> <s:message
							code="layout.menu.about" /></a></li>
				<li><a href="/home/contact"><i
						class="glyphicon glyphicon-envelope"></i> <s:message
							code="layout.menu.contact" /></a></li>
				<li><a href="/home/feedback"><i
						class="glyphicon glyphicon-send"></i> <s:message
							code="layout.menu.faq" /></a></li>
				<li><a href="/home/faq"><i
						class="glyphicon glyphicon-question-sign"></i> <s:message
							code="layout.menu.feedback" /></a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><i class="glyphicon glyphicon-chevron-down"></i>
						<security:authorize access="!isAuthenticated()">
								<security:authentication property="principal" var="user" />
								<s:message code="layout.menu.account" /> 
						</security:authorize>
					
							<security:authorize access="isAuthenticated()">					
								${user.fullname }
							</security:authorize>
							<%-- <c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								${pageContext.request.userPrincipal.name}
							
						</c:when>
							<c:otherwise>
						 	${empty sessionScope.user ? '' : sessionScope.user.name }
						</c:otherwise>
						</c:choose>  --%>
						</a> 
						<ul class="dropdown-menu ">	
											
						<security:authorize access="!isAuthenticated()">
							<li><a href="/account/login">Đăng nhập</a></li>
							<li><a href="/account/forgot">Quên mật khẩu</a></li>
							<li><a href="/account/register">Đăng ký</a></li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">									
							<li>
								<form hidden="true" action="/account/logoff" method="post" name="logoutForm">
										<input type="submit" value="logout">
										<input type="hidden" name="${_csrf.parameterName}"
           										 value="${_csrf.token}" />
								</form>
								<a id="logoff" href="">Đăng xuất</a>
							</li>
							<li><a href="/account/change">Đổi mật khẩu</a></li>
							<li><a href="/account/edit">Cập nhật tài khoản</a></li>
							<li class="divider"></li>
							<li><a href="/order/list">Đơn đặt hàng</a></li>
							<li><a href="/order/items">Hàng đã mua</a></li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('admin','editor')">
							<li><a href="/admin/home/index">Trang Quản Trị</a></li>
						</security:authorize>
						</ul>
					
					<%-- <ul class="dropdown-menu">

						<c:choose>

							<c:when test="${empty sessionScope.user }">
								<li><a href="/account/login">Đăng nhập</a></li>
								<li><a href="/account/forgot">Quên mật khẩu</a></li>
								<li><a href="/account/register">Đăng ký</a></li>
							</c:when>

							<c:otherwise>
								<li><a href="/account/logoff">Đăng xuất</a></li>
								<li><a href="/account/change">Đổi mật khẩu</a></li>
								<li><a href="/account/edit">Cập nhật tài khoản</a></li>
								<li class="divider"></li>
								<li><a href="/order/list">Đơn đặt hàng</a></li>
								<li><a href="/order/items">Hàng đã mua</a></li>

								<c:if test="${sessionScope.user.admin }">
									<li><a href="/admin/home/index">Trang Quản Trị</a></li>
								</c:if>
							</c:otherwise>
						</c:choose>
					</ul> --%>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="/home/index?lang=en"><i
						class="glyphicon glyphicon-user"></i> English</a></li>
				<li><a href="/home/index?lang=vi"><i
						class="glyphicon glyphicon-log-in"></i> Tiếng Việt</a></li>
			</ul>
		</div>
	</div>
</nav>

<script type="text/javascript">
	$(function() {
		$("a[href*=lang]").click(function(e) {
			e.preventDefault();
			var href = $(this).attr("href");
			$.ajax({
				url : href,
				success : function() {
					location.reload();
				}
			})
		})
	});

	
</script>