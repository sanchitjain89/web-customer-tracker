<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
	<title>List Customers</title>
	
	<link type = "text/css"
		  rel = "stylesheet"
		  href = "${pageContext.request.contextPath}/resources/css/style.css">
	
</head>
<body>

	<div id = "wrapper">
		<div id = "header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id = "container">
		<div id = "content">
		
			<!-- Add new button: Add Customer -->
			
			<input type = "button" value = "Add Customer"
					onClick = "window.location.href = 'showFormForAdd'"
					class = "add-button"/>
			
			<!-- add out html content -->
			
			<table>
				<tr>
					<th>FirstName</th>
					<th>LastName</th>
					<th>EmailName</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var = "tempCustomer" items = "${customers}">
					<!-- construct update link with customer id -->
					<c:url var = "updateLink" value = "/customer/showFormUpdate">
						<c:param name="customerId" value = "${tempCustomer.id}"/>
					</c:url>
					
					<!-- construct delete link with customer id -->
					<c:url var = "deleteLink" value = "/customer/delete">
						<c:param name="customerId" value = "${tempCustomer.id}"/>
					</c:url>
				
					<tr>
						<td> ${tempCustomer.firstName}</td>	
						<td> ${tempCustomer.lastName}</td>	
						<td> ${tempCustomer.email}</td>	
						<!-- display the update link -->
						<td> 
							<a href = "${updateLink}"> Update</a>
							|
							<a href = "${deleteLink}"
							   onClick = "if (!(confirm('Are you sure you want to delete this customer?'))) return false"> Delete</a>
						</td>		
					</tr>
				</c:forEach>
			</table>			
		
		</div>
	</div>

</body>
</html>