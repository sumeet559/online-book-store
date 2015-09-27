<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.training.topaz.Item"%>
<%@include file="sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/resultstylesheet.css">
<link rel="stylesheet" type="text/css" href="css/component.css">
<title>Insert title here</title>
</head>
<body>
<header><div id="headingfont">BOOK MAFIA</div></header>
<nav class="cl-effect-4">
		<a href="home.jsp">Home</a><a href="CartDisplayServlet">Cart</a> <a href="OrderHistoryServlet">Order History</a> <a href="LogOutServlet"	id="logout">Log Out</a>
	</nav>

<%!int offset=0; %>
<%
 List<Item>  listitems=(List<Item>) request.getAttribute("items");
%>

<table  cellspacing="40px"  >

<tbody>

<% for(int i=0;i<listitems.size();i++){ %>

<tr>


			<td>
			
			<table >
			<tr><td><a href="ItemServlet?title=<%=listitems.get(i).getTitle() %>"><img src="bookimages/<%=listitems.get(i).getItemNo()%>.jpg"></a></td>
			</tr>
			<tr><td class="title"><a href="ItemServlet?title=<%=listitems.get(i).getTitle() %>"><%=listitems.get(i).getTitle() %></a></td>
			</tr>
			<tr><td><%=listitems.get(i).getAuthor() %></td>
			</tr>
			<tr><td><%=listitems.get(i).getPublication() %></td>
			</tr>
			<tr><td class=cost>Rs <%=listitems.get(i).getCost() %></td>
			</tr>
			</table>
			
			</td>
			<% i++; 
			if (i >= listitems.size()){
					break;} 
			%>
			<td>
			
			<table >
			<tr><td><a  href="ItemServlet?title=<%=listitems.get(i).getTitle() %>"><img src="bookimages/<%=listitems.get(i).getItemNo()%>.jpg"></a></td>
			</tr>
			<tr><td  class="title"><a  href="ItemServlet?title=<%=listitems.get(i).getTitle() %>"><%=listitems.get(i).getTitle() %></a></td>
			</tr>
			<tr><td><%=listitems.get(i).getAuthor() %></td>
			</tr>
			<tr><td><%=listitems.get(i).getPublication() %></td>
			</tr>
			<tr><td class=cost>Rs <%=listitems.get(i).getCost() %></td>
			</tr>
			</table>
			
			</td>
			<% i++;
				if (i >= listitems.size()){
					break;}%>
			<td>
			
			<table>
			<tr><td><a  href="ItemServlet?title=<%=listitems.get(i).getTitle() %>"><img src="bookimages/<%=listitems.get(i).getItemNo()%>.jpg"></a></td>
			</tr>
			<tr><td  class="title"><a href="ItemServlet?title=<%=listitems.get(i).getTitle() %>"><%=listitems.get(i).getTitle() %></a></td>
			</tr>
			<tr><td><%=listitems.get(i).getAuthor() %></td>
			</tr>
			<tr><td><%=listitems.get(i).getPublication() %></td>
			</tr>
			<tr><td class=cost>Rs <%=listitems.get(i).getCost() %></td>
			</tr>
			</table>
		
			</td>
</tr>
<%} %>
<tr>
<td></td>
</tr>

</tbody>
</table>
<% offset=offset+30; %>
<a href="SearchServlet?offset=0&select1=<%=(String)request.getAttribute("select1") %>&text=<%=(String)request.getAttribute("text")%>">First page</a>
<a href="SearchServlet?offset=<%=offset%>&select1=<%=(String)request.getAttribute("select1") %>&text=<%=(String)request.getAttribute("text")%>">next page</a>
<a href="SearchServlet?offset=<%=offset%>&select1=<%=(String)request.getAttribute("select1") %>&text=<%=(String)request.getAttribute("text")%>">Last page</a>
<footer>Heu Footer
	</footer>
</body>
</html>