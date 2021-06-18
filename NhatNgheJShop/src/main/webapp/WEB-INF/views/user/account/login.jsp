<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form class="col-sm-8 col-sm-offset-2" action="/account/login"
	method="post">
	<div class="row">
		<h2 style="text-align: center">Login with Social Media or
			Manually</h2>
			<b class="text-danger"><i>${message}${param.message}</i></b>
		<div class="vl">
			<span class="vl-innertext">or</span>
		</div>

		<div class="col form-group">
			<a href="/oauth2/authorization/facebook" class="fb btn nut"> <i
				class="fa fa-facebook fa-fw"></i> Login with Facebook
			</a> <a href="/oauth2/authorization/google" class="google btn nut"><i
				class="fa fa-google fa-fw"> </i> Login with Google+ </a>
		</div>

		<div class="col form-group">
			<div class="hide-md-lg">
				<p>Or sign in manually:</p>
			</div>
			<input value="${form.username}" id="username" name="username"
				value="${param.username }" placeholder="Username?"
				class="form-control" required="required"> <input
				value="${form.password}" id="password" name="password"
				placeholder="Password?" type="password" class="form-control">


			<label class="checkbox-inline" for="remember-me"> <i
				class="glyphicon glyphicon-save"></i> Remember? <input
				name="remember-me" id="remember-me" type="checkbox">
			</label> <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input type="submit" value="Login">
		</div>

	</div>
	<div class="bottom-container">
		<div class="row">
			
			<div class="col">
				<a href="#" style="color: white" class="btn">Sign up</a>
			</div>
			<div class="col">
				<a href="#" style="color: white" class="btn">Forgot password?</a>
			</div>
		</div>
	</div>
</form>
<%-- 
<form class="col-sm-8 col-sm-offset-2" action="/account/login"
	method="post">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<i class="glyphicon glyphicon-user"></i> <b>LOGIN</b>
			</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label><i class="glyphicon glyphicon-user"></i> Username:</label> <input
					value="${form.username}" id="username" name="username"
					value="${param.username }" placeholder="Username?"
					class="form-control">
			</div>
			<div class="form-group">
				<label><i class="glyphicon glyphicon-lock"></i> Password:</label> <input
					value="${form.password}" id="password" name="password"
					placeholder="Password?" type="password" class="form-control">
			</div>
			<div class="row">
				<div class="col-sm-6 form-group text-left">
					<input name="remember-me " id="remember-me" type="checkbox">
					<label class="checkbox-inline" for="remember-me"> <i
						class="glyphicon glyphicon-save"></i> Remember?
					</label>
				</div>

				<div class="col-sm-6 text-right">
					<button class="nut nut-default text-right">
						<i class="glyphicon glyphicon-ok"></i> Login
					</button>
				</div>
			</div>
		</div>
		<input hidden="true" name="${_csrf.parameterName}"
			value="${_csrf.token}">
		<div class="panel-footer text-right">
			<span class="psw"><a href="/account/forgot">Forgot your
					password?</a></span> <b class="pull-left text-danger"><i>${message}${param.message}</i></b>
		</div>
	</div>
</form>
<div class="col-sm-8 col-sm-offset-2 text-center">

	<h3>OR</h3>
	<div class="col-sm-12">
		<a href="/oauth2/authorization/facebook"><i
			class="fab fa-facebook"></i> Đăng Nhập Facebook </a>

	</div>
	<div class="col-sm-12">
		<a href="/oauth2/authorization/google"><i class="fab fa-google"></i>
			Đăng Nhập Google</a>
	</div>
</div>

 --%>

