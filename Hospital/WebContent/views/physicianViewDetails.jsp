<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Physician View Details</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>

<a href="views/physicianView.jsp">Back</a>

 <div id = "physiciandetails">
       		<div class="row">
        	<div class="container">
        	<h3> Selected Physician Details </h3>
        		<table class="table table-hover">
					    
					    <tbody>
					      <tr>
					      	<td>PhysicianID</td>
					        <td><c:out value="${physicians.physicianId}"/></td>
					      </tr>

					      <tr>
					      	<td>NationalProviderID</td>
					        <td><c:out value="${physicians.nationalProviderId}"/></td>
					      </tr>
					      <tr>
					      	<td>PhysicianName</td>
					        <td><c:out value="${physicians.physicianName}"/></td>
					      </tr>
					      <tr>
					      	<td>Email</td>
					        <td><c:out value="${physicians.email}"/></td>
					      </tr>
					      <tr>
					      	<td>Gender</td>
					        <td><c:out value="${physicians.gender}"/></td>
					      </tr>
					      <tr>
					      	<td>Primary State</td>
					        <td><c:out value="${physicians.primaryState}"/></td>
					      </tr>
					      <tr>
					      	<td>PrimaryCity</td>
					        <td><c:out value="${physicians.primaryCity}"/></td>
					      </tr>
					      <tr>
					      	<td>PrimaryCountry</td>
					        <td><c:out value="${physicians.primaryCountry}"/></td>
					      </tr>
					      <tr>
					      	<td>Hospitals Affiliation</td>
					        <td>
					        	<c:out value="${hospital}"/>
					        </td>
					      </tr>
					      <tr>
					      	<td>Specialties Affiliation</td>
					        <td>
					        	<c:out value="${special}"/>
					        </td>
					      </tr>
					      <tr>
					      	<td>No of Discharges Diagnosed</td>
					        <td><c:out value="${diagCount}"/></td>
					      </tr>
					      <tr>
					      	<td>No of Procedures Performed</td>
					        <td><c:out value="${proCount}"/></td>
					      </tr>
					      

					    </tbody>
				</table>
        	</div>
        </div>
        </div>
   

</body>
</html>