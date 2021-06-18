<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
	function validateForm() {
		if (grecaptcha.getResponse()) {
			return true;
		} /* else {
				alert("Please prove that you're not robot !!!");
				return false;
			} */
	}
</script>
<form:form action="/account/register" onsubmit="return validateForm()"
	method="post" modelAttribute="form" enctype="multipart/form-data">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<i class="glyphicon glyphicon-user"></i> <b>REGISTRATION</b>
			</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-group col-sm-6">
					<label><i class="glyphicon glyphicon-user"></i> Username:</label>
					<form:input path="id" placeholder="Username?" class="form-control" required="required"/>
				</div>
				<div class="form-group col-sm-6">
					<label><i class="glyphicon glyphicon-text-size"></i>
						Fullname:</label>
					<form:input path="fullname" placeholder="Fullname?"
						class="form-control"  required="required"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label><i class="glyphicon glyphicon-lock"></i> Password:</label>
					<form:input path="password" placeholder="Password?" type="password"
						class="form-control" required="required"/>
				</div>
				<div class="form-group col-sm-6">
					<label><i class="glyphicon glyphicon-lock"></i> Confirm
						Password:</label> <input name="confirm" placeholder="Confirm Password?"
						type="password" class="form-control"  required="required">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label><i class="glyphicon glyphicon-picture"></i> Photo:</label> <input
						name="photo_file" placeholder="Photo?" type="file"
						class="form-control">
				</div>
				<div class="form-group col-sm-6">
					<label><i class="glyphicon glyphicon-envelope"></i> Email
						Address:</label>
					<form:input path="email" placeholder="Email Address?"
						class="form-control" />
				</div>
			</div>
			<div class="row ">
				<div class="col-sm-6 g-recaptcha "
					data-sitekey="6Ld99rUaAAAAAGLSL1T-MsG_MGh3e_WcxMJ6IiI5"></div>
			</div>
		</div>
	</div>
	<div class="panel-footer text-right">
		<button class="btn btn-default">
			<i class="glyphicon glyphicon-ok"></i> Sign Up
		</button>
		<b class="pull-left text-danger"><i>${message}${param.message}</i></b>
	</div>

</form:form>