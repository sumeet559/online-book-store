<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.training.topaz.Customer"%>
<%@include file="sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/homestylesheet.css">
<link rel="stylesheet" type="text/css" href="css/component.css">
<title>Insert title here</title>
<script type="text/javascript">
/* function changeSelect(){
	var val=document.getElementById("select1").value;
	if(val == "Category")
		{
		document.getElementById("select2").disabled=false;
		}
	else
		document.getElementById("select2").disabled=true;
}
 */
</script>
</head>
<body>

	<header><div id="headingfont">BOOK MAFIA</div>
	
	</header>
	<nav class="cl-effect-4">
		<a href="#"><%=customer.getFname() %></a>
		<a href="home.jsp">Home</a><a href="CartDisplayServlet">Cart</a> <a href="OrderHistoryServlet">Order History</a> <a href="LogOutServlet" id="logout">Log Out</a>
	
	</nav>

	<div id=content>
	
		<form action="SearchServlet?offset=0" method="post">
			<div id="search">

				<select id="select1" name="select1" onchange="changeSelect()">
					<option value="Title">Title</option>
					<option value="Author">Author</option>
					<option value="Publication">Publication</option>
					<!-- <option value="Category">Category</option> -->
				</select> 
				<!-- <select id="select2" name="selected2" disabled>
					<option value="Literature">Literature</option>
					<option value="Fiction">Fiction</option>
					<option value="Academics">Academics</option>
					<option value="Kids">Kids</option>
				</select> --> <input type=text name="text" placeholder="Hello <%=customer.getFname()%>!! Search here"> <input type="submit" value="SEARCH">

			</div>
		</form>
		
		
		
		<div class="sample">
			
			
			<div class="item">
				<div class="heading"><a href="ResultsServlet?category=Literature&offset=0">Literature</a></div>
				<a href="ResultsServlet?category=Literature&offset=0"><img alt="" src="images/1.jpg"></a>
				<div class="title"><a href="ItemServlet?title=Book on Title0">Harry Potter:Deathly Hallows</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title1">Harry Potter:Deathly Hallows</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title2">Harry Potter:Deathly Hallows</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title3">Harry Potter:Deathly Hallows</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title4">Harry Potter:Deathly Hallows</a></div><div class="border"></div>
				<div class="title"><a href="ResultsServlet?category=Literature&offset=0" id="see">See more</a></div>
			</div>
			
			<div class="item">
			<div class="heading"><a href="ResultsServlet?category=Fiction&offset=0">Fiction</a></div>
				<a href="ResultsServlet?category=Literature&offset=0"><img alt="" src="images/2.jpg"></a>
				<div class="title"><a href="ItemServlet?title=Book on Title5">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title6">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title7">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title8">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title9">Harry Potter</a></div><div class="border"></div>
			<div class="title"><a href="ResultsServlet?category=Fiction&offset=0" id="see">See more</a></div>
				
			</div>
			
			<div class="item">
			<div class="heading"><a href="ResultsServlet?category=Academics&offset=0">Academics</a></div>
			<a href="ResultsServlet?category=Literature&offset=0">	<img alt="" src="images/3.jpg"></a>
				<div class="title"><a href="ItemServlet?title=Book on Title10">Harry Potter:Deathly Hallows</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title11">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title12">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title13">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title14">Harry Potter</a></div><div class="border"></div>
			<div class="title"><a href="ResultsServlet?category=Academics&offset=0" id="see">See more</a></div>
			</div>
			
			<div class="item">
			<div class="heading"><a href="ResultsServlet?category=Kids&offset=0">Kids</a></div>
			<a href="ResultsServlet?category=Literature&offset=0">	<img alt="" src="images/4.png"></a>
				<div class="title"><a href="ItemServlet?title=Book on Title15">Harry Potter:Deathly</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title16">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title17">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title18">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ItemServlet?title=Book on Title19">Harry Potter</a></div><div class="border"></div>
				<div class="title"><a href="ResultsServlet?category=Kids&offset=0" id="see">See more</a></div>				
			</div>
		</div>
	</div>



	<footer><div>&#169;Copyrights SSS</div>
	</footer>
</body>
</html>