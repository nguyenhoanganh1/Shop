<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="url" value="/admin/product" scope="request"/>

<ul class="nav nav-tabs">
  <li class="active"> <a data-toggle="tab" href="#tab1"><i class="glyphicon glyphicon-th-list"></i> Edit</a></li>
  <li> <a data-toggle="tab" href="#tab2"><i class="glyphicon glyphicon-edit"></i> List</a></li>
</ul>


<div class="tab-content">
    <div id="tab1" class="tab-pane fade in active">
        <jsp:include page="_form.jsp"/>
    </div>
    <div id="tab2" class="tab-pane fade">
        <jsp:include page="_table.jsp"/>
    </div>
</div>
