<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      var myData = [['Loại', 'Giá trị']];
      function drawChart() {
    	$.ajax({
    		url: "/admin/report/inventory-by-category",
    		success: function(resp){
    			resp.forEach(item => {
    				myData.push([item.group.nameVN, item.sum]);
    			}); 
    			
    			runDrawChart();
    		}
    	
    	});
    
    	function runDrawChart(){
    		var data = google.visualization.arrayToDataTable(myData);
	        var options = {
	          title: 'Hàng Tồn Kho Theo Loại',
	          is3D: true,
	        };

	        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
	        chart.draw(data, options);
    	}
    	
	    $(function(){
	    	$(window).resize(function(){
	    		runDrawChart();
	    	})
	    });
    	
      }
    </script>
</head>
<body>
	<div class="wrapper">
		<div class="chart" id="piechart_3d" ></div>
	</div>

</body>
</html>
<style>
.wrapper {
	position: relative;
	padding-bottom: 56.25%; /* 16:9 */
	height: 0;
}

.wrapper .chart {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}
</style>
