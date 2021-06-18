<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<form class="col-sm-8 col-sm-offset-2" action="/login" method="post">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<i class="glyphicon glyphicon-user"></i> <b>OTP Confirm</b>
			</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
					<label><i class="glyphicon glyphicon-user"></i> Username:</label> <input
					id=" username:" name=" username" value="${param.username}"
					placeholder="Username?" class="form-control">
			</div>	
			<div class="form-group">
				<label><i class="glyphicon glyphicon-ok"></i> OTP:</label> <input
					id="otp" name="otp" value="${param.otp}"
					placeholder="One time password OTP?" class="form-control">
			</div>	
		</div>
		<div class="panel-footer text-right">
			 <button class="btn btn-default">
                <i class="glyphicon glyphicon-ok"></i> Confirm
            </button> 
			<b class="pull-left text-danger"><i>${message}</i></b>
        </div>
	</div>
</form>




