<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="/static/js/upload.js" type="text/javascript"></script>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<c:set var="url" value="/admin/customer" scope="request" />
<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
<form:form action="${url}/index" modelAttribute="form" enctype="multipart/form-data">
	<div class="panel panel-primary">
		<div class="panel-heading" >
			<b>CUSTOMER EDITION</b> <i class="text-danger pull-right">${message} <b style="color:yellow"> ${param.message}</b> </i>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-sm-6 text-center">				
					<label for="photo_file"> 
						<img src="/static/images/users/${form.photo }"
								style="height: 220px; max-width: 200px;">
					</label> 
					<input onchange="upload.change(this,'#photo_file')" 
							id="photo_file" name="photo_file" type="file" class="form-control">
					<form:hidden path="photo" />
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label>Username:</label>
						<form:input path="id" placeholder="Username?" class="form-control"
						 />
					</div>
					
					<sec:authorize access="hasAuthority('admin')">
						<div class="form-group">
							<label>Password:</label>
							<form:input path="password" placeholder="Password?"
								type="password" class="form-control" />
						</div>
						<div class="form-group">
							<label>Confirm Password:</label> <input name="confirm"
								type="password" value="${param.confirm}"
								placeholder="Confirm Password?" class="form-control" />
						</div>
					</sec:authorize>
					
					<div class="form-group">
						<label>Authorization: </label>
						<%-- <form:input path="provider" placeholder="Your choice ( LOCAL , FACEBOOK , GOOGLE )"
						class="form-control"/> --%>
						<form:select path="provider">
							<form:options items="${provider }"/>  
						</form:select>
							
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Full Name:</label>
					<form:input path="fullname" placeholder="Full Name?"
						class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>Email Address:</label>
					<form:input path="email" placeholder="Email Address?"
						class="form-control" />
				</div>			
			</div>
			
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Status:</label>
					<div class="form-control">
						<form:radiobutton path="activated" value="true" label="Active"
							class="radio-inline" />
						<form:radiobutton path="activated" value="false" label="Inactive"
							class="radio-inline" />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-6">
						<label><i class="glyphicon glyphicon-check"></i> Role:</label>				
						 <form:checkboxes items="${roles }" path="roles"/> 
					</div>
				</div>
				
				
			</div>
		</div>
		<div class="panel-footer text-right">
			<button formaction="${url}/create" ${empty form.id ? "" : "disabled"}
				class="btn btn-primary">
				<i class="glyphicon glyphicon-plus"></i> Create
			</button>
			<sec:authorize access="hasAuthority('admin')">
				<button formaction="${url}/update" ${empty form.id ? "disabled" : ""}
					class="btn btn-success">
					<i class="glyphicon glyphicon-saved"></i> Update
				</button>
			
				<a href="${url}/delete/${form.id}" ${empty form.id ? "disabled" : ""}
					class="btn btn-danger"> <i class="glyphicon glyphicon-trash"></i>
					Delete
				</a> 
			</sec:authorize>
			<a href="${url}/index" class="btn btn-info"> <i
				class="glyphicon glyphicon-retweet"></i> Reset
			</a>
		</div>
	</div>
</form:form>