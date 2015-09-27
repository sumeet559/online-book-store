<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="css/adminstylesheet.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div id="headingfont">BOOK MAFIA</div>
	</header>
	<nav class="cl-effect-4">
		<a href="adminAddItems.jsp">Add Items</a> <a
			href="adminRemoveItems.jsp">Update Stock</a> <a
			href="viewCustomers.jsp">View Customers</a> <a href="PendingServlet">Pending
			Customers</a> <a href="LogOutServlet" id="logout">Log Out</a>
	</nav>
	<div id="content">
		<form action="addItemsServlet" method="get" id="additemsform">
			<table>
				<tr>
					<td>Title</td>
					<td>:</td>
					<td><input type="text" id="title" name="title"></td>

				</tr>
				<tr>
					<td>Author</td>
					<td>:</td>
					<td><input type="text" id="author" name="author"></td>

				</tr>
				<tr>
					<td>Publication</td>
					<td>:</td>
					<td><input type="text" id="publication" name="publication"></td>
				</tr>
				<tr>
					<td>Category</td>
					<td>:</td>
					<td><select id="category" name=category>
							<option value=Fiction>Fiction</option>
							<option value=Literature>Literature</option>
							<option value=Academics>Academics</option>
							<option value=Kids>Kids</option>
					</select></td>

				</tr>
				<tr>
					<td>Description</td>
					<td>:</td>
					<td><input type="text" id="desc" name="desc"></td>

				</tr>
				<tr>
					<td>Cost</td>
					<td>:</td>
					<td><input type="text" id="cost" name="cost"></td>

				</tr>
				<tr>
					<td>Stock</td>
					<td>:</td>
					<td><input type="text" id="stock" name="stock"></td>

				</tr>
				<tr>
					<td><input type="submit" value="Add"></td>
				</tr>
			</table>
			<!-- 	<h3 id="Message"></h3> -->
		</form>
	</div>
	<footer> </footer>
</body>
</html>