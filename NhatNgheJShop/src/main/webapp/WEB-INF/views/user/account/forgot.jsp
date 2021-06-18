<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function onSubmit(token) {
		document.getElementById("loginForm").submit();
	}
</script>

<h1>${score }</h1>
<form id="loginForm" action="/account/forgot" method="post">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="glyphicon glyphicon-user"></i>
                <b>FORGOT PASSWORD</b>
            </h3>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label><i class="glyphicon glyphicon-user"></i> Username:</label>
                <input name="username" placeholder="Username?" class="form-control">
            </div>
            <div class="form-group">
                <label><i class="glyphicon glyphicon-envelope"></i> Email Address:</label>
                <input name="email" placeholder="Email Address?" class="form-control">
            </div>
        </div>
        <div class="panel-footer text-right">
        	<button class="g-recaptcha btn btn-primary" 
					data-sitekey="6LepSLYaAAAAAOpPC5fnUOoBaKaq7ruj6HzSa629"
					data-callback="onSubmit" 
					data-action="submit">
				<i class="glyphicon glyphicon-ok"></i> Retrieve Password
			</button>
			<!-- <button class="btn btn-default">
                <i class="glyphicon glyphicon-ok"></i> Retrieve Password
            </button> -->
			<b class="pull-left text-danger"><i>${message}</i></b>
        </div>
    </div>    
</form>