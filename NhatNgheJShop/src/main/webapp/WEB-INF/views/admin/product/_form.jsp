<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form action="${url}/index" modelAttribute="form" enctype="multipart/form-data">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <b>PRODUCT EDITION</b>
            <i class="text-danger pull-right">${message}</i>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group col-sm-6 text-center">
                        <label for="image_file">
                            <img src="/static/images/items/${form.image }" style="height: 200px; max-width: 95%">
                        </label>
                        <input id="image_file" name="image_file" type="file" class="form-control">
                        <form:hidden path="image"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label>Id:</label>
                        <form:input path="id" placeholder="Auto Number" readonly="true" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Name:</label>
                        <form:input path="name" placeholder="Name?" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Unit Price:</label>
                        <form:input path="unitPrice" placeholder="Unit Price?" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Discount:</label>
                        <form:input path="discount" placeholder="Discount?" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Quantity:</label>
                    <form:input path="quantity" placeholder="Quantity?" class="form-control"/>
                </div>
                <div class="form-group col-sm-6">
                    <label>Product Date:</label>
                    <form:input path="productDate" placeholder="Product Date?" class="form-control"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Category:</label>
                    <form:select path="category.id" class="form-control">
                        <form:options items="${categories}" itemValue="id" itemLabel="nameVN"/>
                    </form:select>
                </div>
                <div class="form-group col-sm-6">
                    <label>View Count:</label>
                    <form:input path="clickCount" placeholder="View Count?" class="form-control"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Latest:</label>
                    <div class="form-control">
                        <form:radiobutton path="latest" value="true" label="Yes" class="radio-inline"/>
                        <form:radiobutton path="latest" value="false" label="No" class="radio-inline"/>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label>Special:</label>
                    <div class="form-control">
                        <form:radiobutton path="special" value="true" label="Yes" class="radio-inline"/>
                        <form:radiobutton path="special" value="false" label="No" class="radio-inline"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-12">
                    <label>Description:</label>
                    <form:textarea path="description" rows="3" placeholder="Description?" class="form-control"></form:textarea>
                </div>
            </div>
        </div>
       <div class="panel-footer text-right">
			 <jsp:include page="../shared/_form-buttons.jsp"/>
		</div>
    </div>    
</form:form>