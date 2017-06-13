<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient View Details</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>

<a href="views/patientView.jsp">Back</a>

<div id = "patientdetails">
       		<div class="row">
        	<div class="container">
        	<h3> Patient Details - IPT </h3>
        		<table class="table table-hover">
					    
					    <tbody>
					      <tr>
					      	<td>EncounterNumber</td>
					        <td><c:out value="${discharges.encounterNumber}"/></td>
					      </tr>

					      <tr>
					      	<td>PatienName</td>
					        <td><c:out value="${discharges.patientName}"/></td>
					      </tr>
					      <tr>
					      	<td>MedicalRecordNumber</td>
					        <td><c:out value="${discharges.medicalRecordNumber}"/></td>
					      </tr>
					      <tr>
					      	<td>Discharge date</td>
					        <td><c:out value="${discharges.dischargeDate}"/></td>
					      </tr>
					      <tr>
					      	<td>AdmissionDate</td>
					        <td><c:out value="${discharges.admissionDate}"/></td>
					      </tr>
					      <tr>
					      	<td>Gender</td>
					        <td><c:out value="${discharges.gender}"/></td>
					      </tr>
					      <tr>
					      	<td>PatientPhoneNumber</td>
					        <td><c:out value="${discharges.patientPhoneNumber}"/></td>
					      </tr>
					      <tr>
					      	<td>PatientAddress</td>
					        <td><c:out value="${discharges.patientAddress}"/></td>
					      </tr>
					      
					    </tbody>
				</table>
        	</div>
        </div>
        <div class="row">
        	<div class="container">
        	<h3> Patient Diagnoses</h3>
        		<table class="table table-hover">
					    <tr>
					    
					    <td>Diagnosed For :</td>
					    <td><c:out value="${diagnosesDetail}"/></td>
					    
					    </tr>
				</table>
        	</div>
        </div>
        <div class="row">
        	<div class="container">
        	<h3> Patient Procedures</h3>
        		<table class="table table-hover">
					    <tr> 
					    <td> Procedure Performed :</td>
					     <td><c:out value="${proDetail}"/> </td>
					    
					    </tr>
				</table>
        	</div>
        </div>
        
        <div class="row">
        	<div class="container">
        	<h3> Medical Items Purchased</h3>
        		<table class="table table-hover">
					    <tr> 
					    <td> Charges Incurred :</td>
					     <td><c:out value="${medicalItem.charge}"/> </td>
					    
					    </tr>
					    
					    <tr>
					    <td> Medical Item Code :</td>
					     <td><c:out value="${medicalItem.medicalItem}"/> </td>
					    
					    </tr>
					    
					    <tr>
					    <td> Quantity Purchased :</td>
					     <td><c:out value="${medicalItem.quantity}"/> </td>
					    
					    </tr>
				</table>
        	</div>
        </div>
       </div>
        

</body>
</html>