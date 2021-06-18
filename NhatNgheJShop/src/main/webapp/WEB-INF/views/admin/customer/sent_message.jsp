<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form action="/account/sent-sms" modelAttribute="form" method="post">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="glyphicon glyphicon-user"></i>
                <b>Sent Message</b>
            </h3>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label><i class="glyphicon glyphicon-user"></i> Phone Number(+84):</label>      
                <form:input path="number" name="number" class="form-control" value="+84" placeholder="Phone Number?"/>
            </div>
            <div class="form-group">
                <label><i class="glyphicon glyphicon-envelope"></i>Message:</label>
                <form:input path="message" name="message" class="form-control"  placeholder="Message?"/>             
            </div>
        </div>
        <div class="panel-footer text-right">
 		<button class="btn btn-default">
                <i class="glyphicon glyphicon-ok"></i> Sent
            </button> 
			<b class="pull-left text-danger"><i>${message}</i></b>
        </div>
    </div>    
</form:form>