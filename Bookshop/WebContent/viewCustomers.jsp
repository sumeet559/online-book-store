<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/adminstylesheet.css">

<title>Insert title here</title>
<script>

	var xmlHttp;
	function createXMLHttpRequest() {
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} 
	}
	
	function listCustomers() {
		//alert('inside listCustomer');
		createXMLHttpRequest();
		var url = "viewCustomersServlet";
		xmlHttp.open("GET", url, true);
		xmlHttp.onreadystatechange = handlerViewCustomers;
		xmlHttp.send(null);
	}
	
	function handlerViewCustomers() {
		//alert('cp1');
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			//alert('cp2');
			var json = eval("(" + xmlHttp.responseText + ")");
			console.log(json);
			var theTable = document.createElement('table');
			theTable.setAttribute("class","table table-bordered");
			var parent = document.getElementById('table1');
			while(parent.hasChildNodes()){
				console.log("insde div");  
				parent.removeChild(parent.firstChild);
			} 
			var tr = document.createElement('tr');
			var td1 = document.createElement('td');
			var td2 = document.createElement('td');
			var td3 = document.createElement('td');
			var td4 = document.createElement('td');
			td1.setAttribute("class","sahi");
			td2.setAttribute("class","sahi");
			td3.setAttribute("class","sahi");
			td4.setAttribute("class","sahi"); 
			
			td1.appendChild(document.createTextNode("Username"));
			td2.appendChild(document.createTextNode("First Name"));
			td3.appendChild(document.createTextNode("Last Name"));
			td4.appendChild(document.createTextNode("E-mail"));
			tr.appendChild(td1);
			tr.appendChild(td2); 
			tr.appendChild(td3);
			tr.appendChild(td4);
			theTable.appendChild(tr);
			
			for ( var i = 0; i < json.length; i++) {
				var tr = document.createElement('tr');
				var td1 = document.createElement('td');
				var td2 = document.createElement('td');
				var td3 = document.createElement('td');
				var td4 = document.createElement('td');
				var td5 = document.createElement('td');
				var button=document.createElement('input');
				
				button.setAttribute("type","button");
				button.setAttribute("value","remove");
				button.setAttribute("onclick","removeCustomers()");		
				
				td1.setAttribute("id",json[i].username);
				td1.setAttribute("name",json[i].username);
				td1.setAttribute("value",json[i].username);
				
				td1.appendChild(document.createTextNode(json[i].username));
				td2.appendChild(document.createTextNode(json[i].fname));
				td3.appendChild(document.createTextNode(json[i].lname));
				td4.appendChild(document.createTextNode(json[i].email));
				td5.appendChild(button);
	
				tr.appendChild(td1);
				tr.appendChild(td2); 
				tr.appendChild(td3);
				tr.appendChild(td4);
				tr.appendChild(td5);
	
				theTable.appendChild(tr);
			}
		
			document.getElementById('table1').appendChild(theTable);
		}
	}
	
</script>
</head>
<body>
<header><div id="headingfont">BOOK MAFIA</div>
</header>
		<nav class="cl-effect-4">
		<a href="adminAddItems.jsp">Add Items</a> 
		<a href="adminRemoveItems.jsp">Update Stock</a>
		<a href="viewCustomers.jsp" >View Customers</a>
		<a href="PendingServlet" >Pending Customers</a>
		<a href="LogOutServlet" id="logout">Log Out</a>
	</nav>
		
		<div id=content>
		<input type="button" id=b onclick="listCustomers()" value="List">
		<div id="table1" ></div>
		</div>
		<footer></footer>
</body>
</html>