<%@page import="com.training.topaz.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.training.topaz.Item"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/adminstylesheet.css">
<link rel="stylesheet" type="text/css" href="css/component.css">
<body>
<% List<Customer> listApprovals =(List<Customer>) request.getAttribute("approvals"); %>
	<header><div id="headingfont">BOOK MAFIA</div></header>
	<nav class="cl-effect-4">
		<a href="adminAddItems.jsp">Add Items</a> 
		<a href="adminRemoveItems.jsp">Update Stock</a>
		<a href="viewCustomers.jsp" >View Customers</a>
		<a href="PendingServlet" >Pending Customers</a>
		<a href="LogOutServlet" id="logout">Log Out</a>
	</nav>
	<div id=content>
		
	<%for(Customer customer:listApprovals){	%>
	<div id="detail">
		Username:<%=customer.getUsername() %>
		Name:<%=customer.getFname()%> <%=customer.getLname()%>
		Address:<%=customer.getAddress()%>
		Email:<%=customer.getEmail()%>
		Mobile:<%=customer.getMobile()%>
	</div>	
	<div><a href="ApprovalServlet?username=<%=customer.getUsername()%>&approve=true" class="button">Approve</a><a href="ApprovalServlet?username=<%=customer.getUsername()%>&approve=false" class="button">Reject</a></div>
	<br>
	<%} %>
	
	
	
	</div>


	<footer> </footer>
</body>
</html>