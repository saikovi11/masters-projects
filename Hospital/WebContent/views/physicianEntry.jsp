<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/Diagnoses" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Physician Record Entry</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>

	<jsp:include page="menu.jsp"/>

<h3><center>Physician Record Entry</center></h3>

<form id="" name="physicianForm" action="${pageContext.request.contextPath}/PhysicianEntry">

<table>

<tr>

<td>Physician ID</td>

<td><input id="physicianID" name="physicianID" type="number" /> </td>

</tr>

<tr>

<td>National Provider ID</td>

<td> <input id="nationalProviderID" name="nationalProviderID" type="number" /> </td>

</tr>
<tr>

<td>Physician Name</td>

<td> <input id="physician" name="physician" type="text" /> </td>

</tr>

<tr>

<td>Email</td>

<td> <input id="email" name="email" type="email" /> </td>

</tr>

<tr>

<td>Gender</td>

<td>
<input type="radio" value="M" name="gender"/>Male
<input type="radio" value="F" name="gender"/>Female
</td>

</tr>

<tr>

<td>Primary State</td>

<td> <input id="state" name="state" type="text" /> </td>

</tr>

<tr>

<td>Primary City</td>

<td> <input id="city" name="city" type="text" /> </td>

</tr>

<tr>

<td>Primary Country</td>

<td> <input id="country" name="country" type="text" /> </td>

</tr>

<tr>

<td>
Hospital
</td>

<td>
<select id="hospital" name="hospital">


<c:forEach items="${hospitalList}" var="procedure">
<option value="${procedure.id}">${procedure.hospitalName} </option>
</c:forEach>

</select>
</td>

</tr>

<tr>


<th colspan="2">Specialties</th>


</tr>

<tr>

<td>Specialty Code</td>

<td> 

<select id="specialtyCode" name="specialtyCode">
<c:forEach items="${specialtiesList}" var="procedure">
<option value="${procedure.id}">${procedure.specialtyCode} </option>
</c:forEach>
</select>

 </td>

</tr>

<tr>

<td title="Only One Primary Specialty" >Is Primary Specialty</td>

<td> <input id="isPrimary" name="isPrimary" type="radio" value="Y">Yes
<input id="isPrimary" name="isPrimary" type="radio" value="N">No
</td>

</tr>

<tr> 
<td><input type="submit" value="Submit" ></td>

<td><input type="reset" value="Reset" ></td>

</tr>



</table>

</form>

</body>
</html>