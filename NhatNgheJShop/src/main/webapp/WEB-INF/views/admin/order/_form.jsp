<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

<form:form action="${url}/index" modelAttribute="form">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<b>ORDER EDITION</b> <i class="text-danger pull-right">${message}</i>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Order Id:</label>
					<form:input path="id" placeholder="Auto Number" readonly="true"
						class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>Order Date:</label>
					<form:input  name="orderDate" path="orderDate"	placeholder="Order Date?" class="form-control" 	/>				
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Customer:</label>
					<form:input path="customer.id" placeholder="Customer Id?"
						class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>Shipping Address:</label>
					<form:input path="address" placeholder="Shipping Address?"
						class="form-control" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Total Amount:</label>
					<form:input path="amount" placeholder="Total Amount?"
						class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>Order Status:</label>
					<form:select path="status" class="form-control">
						<form:options items="${status}" />
					</form:select>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label>Notes:</label>
					<form:textarea path="notes" rows="3" placeholder="Order Notes?"
						class="form-control"></form:textarea>
				</div>
			</div>
		</div>
		<div class="panel-footer text-right">
			<jsp:include page="../shared/_form-buttons.jsp" />
		</div>
	</div>
</form:form>
<jsp:include page="_order-details.jsp" />

