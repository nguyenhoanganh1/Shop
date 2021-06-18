<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Online Shopping</title>
<tiles:insertAttribute name="head" />
</head>
<body class="container">
	 <header class="row">
		<h1 class="alert alert-success">
			<b class="glyphicon glyphicon-shopping-cart"></b> <span>Online
				Shopping</span>
		</h1>
	</header> 
	<nav class="row">
		<tiles:insertAttribute name="menu" />
	</nav>
	<main class="row">
		<article class="col-sm-9">
			<div class="row">
				<tiles:insertAttribute name="main" />
			</div>
		</article>
		<aside class="col-sm-3">
			<div class="row" style="margin-left: 2px;">
				<tiles:insertAttribute name="right" />
			</div>
		</aside>
	</main>
	<footer class="row text-center">
		<div class="panel panel-default">
			<div class="panel-body">Shop &copy; 2021. All rights
				reserved.</div>
		</div>
	</footer>
</body>
</html>
