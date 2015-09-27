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
<link rel="stylesheet" href="css/cartstylesheet.css">
<link rel="stylesheet" type="text/css" href="css/component.css">
<body>
<%  List<Item> listItems=(List<Item>) request.getAttribute("listItems"); %>
<%  List<Cart> listCartItems=(List<Cart>) request.getAttribute("listCartItems"); %>
<header><div id="headingfont">BOOK MAFIA</div></header>
	<nav class="cl-effect-4">
	<a href="home.jsp">Home</a>
		<a href="CartDisplayServlet">Cart</a>
		 <a href="OrderHistoryServlet">Order History</a> 
		 <a href="LogOutServlet" id="logout">Log Out</a>
	</nav>
	<div id=content>
		<table  class="rw2d-table" >
		<thead>
		<tr>
		<th>Serial NO</th><th>Title</th><th>Price</th><th>Quantity</th><th>SubTotal</th>
		</tr>
		</thead>
		<%int j=0;double total=0; %>
		<%for(int i=0;i<listCartItems.size();i++){j++; %>
		<tr>
			<td><%=j%>
			</td>
			<td><a href="ItemServlet?title=<%=listItems.get(i).getTitle()%>"><img src="bookimages/<%=listItems.get(i).getItemNo()%>.jpg"/><%=listItems.get(i).getTitle()%></a>
			</td>
			<td><%=listItems.get(i).getCost() %>
			</td>
			<td><%double cost=listCartItems.get(i).getPrice(); %><%=listCartItems.get(i).getQuantity() %>
			</td>
			<td><%double subTotal=cost*listCartItems.get(i).getQuantity();%><%=subTotal %><%total=total+subTotal; %>
			</td>
			<td><a href="CartServlet?cartid=<%=listCartItems.get(i).getCartId()%>&remove=true">Remove</a>
			</td>
		</tr>
		<%} %>
		<tr>
		<td colspan="5" style="text-align:right">Total: Rs <%=total %></td></tr>
		</table>
		<%session.setAttribute("cart", listCartItems); %>
		<%session.setAttribute("total", total); %>
		<div id="order">
		<a href="OrderServlet"><img alt="" src="images/order.png"></a>
		</div>
	
	</div>


	<footer> </footer>
</body>
</html>