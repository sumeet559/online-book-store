<%@page import="com.training.topaz.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.training.topaz.Item"%>
<%@include file="sessionCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/orderstylesheet.css">
<link rel="stylesheet" type="text/css" href="css/component.css">

<body>
	<%
		List<Order> listorder = (List<Order>) request.getAttribute("order");
	%>
	<header><div id="headingfont">BOOK MAFIA</div></header>
	<nav class="cl-effect-4">
		<a href="home.jsp">Home</a> <a href="CartDisplayServlet">Cart</a> <a
			href="OrderHistoryServlet">Order History</a> <a href="LogOutServlet"
			id="logout">Log Out</a>
	</nav>
	<div id=content>
	<div id="h">Order History</div>
		<%
			for (Order order : listorder) {
		%>
		<div class="orderItem">
			<div class=orderid>Order-ID: <%=order.getOrderid()%></div>
			<div class=date>Date: <%=order.getDate()%></div>
			
			<%
				List<Item> listitems = order.getListItems();
			%>
			<%
				for (Item item : listitems) {
			%>
			<div class=item><%=item.getTitle()%><div class=price><%=item.getCost()%></div></div>
			

			<%
				}
			%>
			<div class="br"></div>
			<div class=total>Total Rs <%=order.getTotal()%></div>
		</div>
		<%
			}
		%>

	</div>


	<footer> </footer>
</body>
</html>