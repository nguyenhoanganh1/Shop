<%@ page pageEncoding="utf-8"%>
<div class="row">
	<div class="col-sm-4 col-md-4 col-lg-2">
		<div class="panel panel-default text-center">
			<div class="panel-heading">Chưa xử lý</div>
			<div class="panel-body order0">1</div>
		</div>
	</div>
	<div class="col-sm-4 col-md-4 col-lg-2">
		<div class="panel panel-primary text-center">
			<div class="panel-heading">Đang liên lạc</div>
			<div class="panel-body order1">1</div>
		</div>
	</div>
	<div class="col-sm-4 col-md-4 col-lg-2">
		<div class="panel panel-danger text-center">
			<div class="panel-heading">Đã xác nhận</div>
			<div class="panel-body order2">1</div>
		</div>
	</div>
	<div class="col-sm-4 col-md-4 col-lg-2">
		<div class="panel panel-warning text-center">
			<div class="panel-heading">Đang chuyển hàng</div>
			<div class="panel-body order3">1</div>
		</div>
	</div>
	<div class="col-sm-4 col-md-4 col-lg-2">
		<div class="panel panel-info text-center">
			<div class="panel-heading">Đã hoàn thành</div>
			<div class="panel-body order4">1</div>
		</div>
	</div>
	<div class="col-sm-4 col-md-4 col-lg-2">
		<div class="panel panel-success text-center">
			<div class="panel-heading">Đã hủy</div>
			<div class="panel-body order-1">1</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-4">
		<div class="chart-wrapper">
			<div class="chart" id="inventory-by-category"></div>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="chart-wrapper">
			<div class="chart" id="revenue-by-category"></div>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="chart-wrapper">
			<div class="chart" id="revenue-by-customer"></div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-4">
		<div class="chart-wrapper">
			<div class="chart" id="revenue-by-year"></div>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="chart-wrapper">
			<div class="chart" id="revenue-by-quarter"></div>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="chart-wrapper">
			<div class="chart" id="revenue-by-month"></div>
		</div>
	</div>
</div>

<jsp:include page="_table.jsp"/>

<script src="https://www.gstatic.com/charts/loader.js"></script>
<script src="/static/js/report.js"></script>
<style>
.chart-wrapper {
	position: relative;
	padding-bottom: 56.25%;
	/* 16:9 */
	height: 0;
	margin-bottom: 20px;
	box-shadow: 0 0 2px gray;
}
.chart-wrapper:hover{
	box-shadow: 0 0 5px red;
}
.chart-wrapper .chart {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}
 .order-1, .order0, .order1, .order2, .order3, .order4{
 	color: red;
 	font-size: 20pt;
 	font-family: impact;
 }
 .col-lg-2 .panel-heading{
 	font-weight: bold;
 	font-variant: small-caps;
 }
</style>