<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title"><b>ORDER LINES</b></h3>
    </div>
    <table class="table table-hover">
        <thead class="bg-success">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Unit Price</th>
                <th>Discount</th>
                <th>Quantity</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${form.orderDetails}">
                <tr>
                    <td>${item.product.id}</td>
                    <td>${item.product.name}</td>
                    <td>${item.unitPrice}</td>
                    <td>${item.discount * 100}%</td>
                    <td>${item.quantity}</td>
                    <td> <fmt:formatNumber pattern="#,###.00" value="${item.unitPrice * item.quantity * (1 - item.discount)}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="panel-footer">
        <jsp:include page="../shared/_pager-buttons.jsp"/>
    </div>
</div>