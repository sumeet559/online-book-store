<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.training.topaz.Item"%>
<%@include file="sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/itemstylesheet.css">
<link rel="stylesheet" type="text/css" href="css/component.css">

<body>

	<%
		Item item = (Item) session.getAttribute("item");
	%>
	<header><div id="headingfont">BOOK MAFIA</div></header>
	<nav class="cl-effect-4">
	<a href="home.jsp">Home</a>
		<a href="CartDisplayServlet">Cart</a> 
		<a href="OrderHistoryServlet">Order History</a> <a href="LogOutServlet"
			id="logout">Log Out</a>
	</nav>
	<div id=content>
	
		<div id="image">
			<img src="bookimages/<%=item.getItemNo()%>.jpg" />
		</div>

		<div id="conblock">

			<div id=Title>
				<%=item.getTitle()%>
				
			</div>
			<div id=Author>
				Author:
				<%=item.getAuthor()%></div>
			<div id=Author>Language: English</div>
			<div id=Publication>
				Publication:
				<%=item.getPublication()%></div>
			<div id=Category>
				Category:<%=item.getCategory()%></div>
			<div id=Des>
				Description:<%=item.getDescription()%>
			</div>
			<div id=Cost>
				Rs
				<%=item.getCost()%></div>
				<form action="CartServlet" name="myForm" method=post>
			<div id=stock >
				Quantity: <select name=quantity id=quantity>
					<%
						int i = 1;
						while (i <= item.getStock()) {
					%>
					<option value=<%=i%>><%=i%></option>
					<%
						i++;
						}
					%>
				</select>
			</div>
			<input name=itemid type="text" value=<%=item.getItemNo()%> style="display:none">
			<div id="cart">
				<a href=# onclick="document.myForm.submit();" id="cartbutton"><img alt="cart" src="images/cart.png"></a>
			</div>
			
			</form>
			</div>
	</div>


	<footer> </footer>
</body>
</html>