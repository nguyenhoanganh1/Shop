<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/account/change" method="post">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="glyphicon glyphicon-user"></i>
                <b>CHANGE PASSWORD</b>
            </h3>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label><i class="glyphicon glyphicon-user"></i> Username:</label>
                <input name="username" placeholder="Username?" class="form-control">
            </div>
            <div class="form-group">
                <label><i class="glyphicon glyphicon-lock"></i> Password:</label>
                <input name="password" placeholder="Password?" type="password" class="form-control">
            </div>
            <div class="form-group">
                <label><i class="glyphicon glyphicon-lock"></i> New Password:</label>
                <input name="newpwd" placeholder="New Password?" type="password" class="form-control">
            </div>
            <div class="form-group">
                <label><i class="glyphicon glyphicon-lock"></i> Confirm New Password:</label>
                <input name="confirm" placeholder="Confirm New Password?" type="password" class="form-control">
            </div>
        </div>
        <div class="panel-footer text-right">
            <button class="btn btn-default">
                <i class="glyphicon glyphicon-ok"></i> Change
            </button>
            <b class="pull-left text-danger"><i>${message}</i></b>
        </div>
    </div>    
</form>