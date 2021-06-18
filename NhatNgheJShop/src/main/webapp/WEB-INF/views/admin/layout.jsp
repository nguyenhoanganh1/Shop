<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Shopping Center</title>
<tiles:insertAttribute name="head" />
</head>
<body class="container">
	<h1 class="text success">Trang quản lý</h1>
	<tiles:insertAttribute name="menu" />
	<tiles:insertAttribute name="main" />	
</body>
</html>