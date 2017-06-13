<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Patient - IPT</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
	<jsp:include page="/PatientDetail"/>
	
</head>
<body>
		<jsp:include page="menu.jsp"/>

	<div class="container">
		
		<label for="ex3">Encounter Number</label>
		<div class="row">
			<div class="col6">
			<form action="${pageContext.request.contextPath}/PatientDetail">
				<div class="col-xs-4">
				  	<input class="form-control" id="ex3" name="term" type="text">
				</div>	
				 	<input type="submit" class="btn btn-primary" value="Search">
		    </form>
			</div>
        </div>
       
	</div>
	<script type="text/javascript" src = "js/bootstrap.min.js"></script>
	<script type="text/javascript" src = "js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src = "js/Display.js"></script>

</body>
</html>