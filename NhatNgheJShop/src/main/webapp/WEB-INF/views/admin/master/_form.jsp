<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<form:form action="/admin/master/update" modelAttribute="form" enctype="multipart/form-data">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="glyphicon glyphicon-user"></i>
                <b>Permistion</b>
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-4 text-center">
                    <label for="photo_file"> <img
						src="/static/images/users/${form.photo }"
						style="height: 220px; max-width: 200px;">
					</label> 
					
					<input onchange="upload.change(this,'#photo_file')"
						id="photo_file" name="photo_file" type="file" class="form-control">
					<form:hidden path="photo" />
                </div>
                <div class="col-sm-8">
                    <div class="form-group">
                        <label><i class="glyphicon glyphicon-user"></i> Username:</label>
                        <form:input path="id" placeholder="Username?" class="form-control" readonly="true"/>
                    </div>
                    <div class="form-group">
                        <label><i class="glyphicon glyphicon-text-size"></i> Fullname:</label>
                        <form:input path="fullname" placeholder="Fullname?" class="form-control" readonly="true"/>
                    </div>
                    <div class="form-group">
                        <label><i class="glyphicon glyphicon-envelope"></i> Email Address:</label>
                        <form:input path="email" placeholder="Email Address?" class="form-control" readonly="true"/>
                    </div>
                    <div class="form-group">
                        <label><i class="glyphicon glyphicon-check"></i> Role:</label>
                        <form:checkboxes items="${roles}"  path="roles" /> 
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-footer text-right">
            <form:hidden path="password"/>
            <form:hidden path="activated"/>
            <button class="btn btn-default">
                <i class="glyphicon glyphicon-ok"></i> Update Permistion
            </button>
            <b class="pull-left text-danger"><i>${message}</i></b>
        </div>
    </div>    
</form:form>