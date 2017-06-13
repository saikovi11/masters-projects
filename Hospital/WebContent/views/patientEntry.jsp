<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<jsp:include page="/Diagnoses" />



<title>Patient Record Entry</title>
</head>
<body>


	<jsp:include page="menu.jsp"/>
	
<h3><center>Patient Record Entry</center></h3>
<form name="patientEntry" action="${pageContext.request.contextPath}/PatientEntry" method="get">

<table>

<tr>
<td>
EncounterNumber
</td>

<td>

<input id="encounterNumber" name="encounterNumber" type="number" />
</td>


</tr>

<tr>
<td>
MedicalRecordNumber
</td>

<td>

<input id="medicalRecordNumber" name="medicalRecordNumber" type="number" />
</td>
</tr>

<tr>
<td>
Discharge Date
</td>

<td>

<input id="dischargeDate" name="dischargeDate" type="date" />
</td>
</tr>

<tr>
<td>
Admission Date
</td>

<td>
<input id="admissionDate" name="admissionDate" type="date" />
</td>
</tr>

<tr>
<td>
Gender
</td>

<td>

<input type="radio" value="M" name="gender"/>Male
<input type="radio" value="F" name="gender"/>Female

</td>
</tr>

<tr>
<td>
Patient Name 
</td>

<td>

<input id="patientName" name="patientName" type="text"/>
</td>
</tr>

<tr>
<td>
Patient Contact Number
</td>

<td>

<input id="contactNumber" name="contactNumber" type="text"/>
</td>
</tr>

<tr>
<td>
Patient Address
</td>

<td>

<textarea id="patientAddress" name="patientAddress">

</textarea>

</td>

</tr>

<tr>
<th colspan="2" >
Diagnosis
</th>

</tr>

<tr>

<td colspan="2">
<input id="seqDiag" name="seqDiag" type="hidden" value="1" />
</td>
</tr>

<tr>
<td>
Diagnosis Code
</td>

<td>


<select id="diagnosisCode" name="diagnosisCode" >

<c:forEach items="${diagnosesList}" var="procedure">
<option value="${procedure.id}">${procedure.diagnosesCode} </option>
</c:forEach>

</select>

</td>
</tr>

<tr>

<td>
Diagnosis Physician
</td>

<td>
<select id="diagPhysician" name="diagPhysician" >

<c:forEach items="${physiciansList}" var="procedure">
<option value="${procedure.id}">${procedure.physicianName} </option>
</c:forEach>

</select>
</td>
</tr>

<tr>

<th colspan="2">Procedures</th>

</tr>

<tr>


<td colspan="2" >
<input id="seqPro" name="seqPro" type="hidden" value="1" />
</td>


</tr>

<tr>
<td>
Procedure Code
</td>

<td>
<select id="procedureCode" name="procedureCode" >

<c:forEach items="${procedureList}" var="procedure">
<option value="${procedure.id}">${procedure.proceduresCode} </option>
</c:forEach>
</select>
</td>

</tr>
<tr>
<td>
Procedure Physician
</td>

<td>
<select id="proPhysician" name="proPhysician" >

<c:forEach items="${physiciansList}" var="procedure">
<option value="${procedure.id}">${procedure.physicianName} </option>
</c:forEach>

</select>
</td>

</tr>
<tr>


<th colspan="2">

Charges
</th>

</tr>

<tr>

<td>
Medical Item Code
</td>

<td>
<select id="medicalCode" name="medicalCode" >

<c:forEach items="${medicalItemList}" var="procedure">
<option value="${procedure.id}">${procedure.medicalItemCode} </option>
</c:forEach>
</select>
</td>

</tr>

<tr>

<td>
Quantity
</td>

<td>
<input id="quantity" name="quantity" type="number" />
</td>

</tr>

<tr>

<td>
Total Charge
</td>

<td>
<input id="totalCharge" name="totalCharge" type="number" />
</td>

</tr>

<tr>
<td> <input type="submit" value="Submit"> </td>

<td> <input type="reset" value="reset"> </td>
</tr>


</table>
</form>


</body>
</html>