<%@page import="com.training.topaz.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.training.topaz.Item"%>
<%@ page import="com.training.topaz.Cart"%>
<%@include file="sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/orderstylesheet.css">
<link rel="stylesheet" type="text/css" href="css/component.css">
<body>
<header><div id="headingfont">BOOK MAFIA</div></header>
	<nav class="cl-effect-4">
	<a href="home.jsp">Home</a>
		<a href="CartDisplayServlet">Cart</a>
		 <a href="OrderHistoryServlet">Order History</a> 
		 <a href="LogOutServlet" id="logout">Log Out</a>
	</nav>
	<div id=content>
	
	<br>
	<% String flag=(String)request.getAttribute("flag"); %>
	<%if(flag.equalsIgnoreCase("success")){ %>
		<div class=result>Dear <%=customer.getFname()%> <%=customer.getLname() %> Your order has been placed Successfully.</div>
	<%}else{ %>
			<div class=result>Sorry <%=customer.getFname() %> Your order cannot be placed right now.Sorry for inconvenience.You may try again later</div>
	 <%} %>
	</div>


	<footer> </footer>
</body>
</html>