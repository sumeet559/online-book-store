<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/adminstylesheet.css">
<link rel="stylesheet" href="css/tableCSS.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<title>Insert title here</title>
<script>
	var json1;

	function searchByStock() {
		if (confirm('Do you want to proceed')) {
			createXMLHttpRequest();
			var stock = document.getElementById("input").value;
			var url = "searchByStock?input=" + stock;
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = handlerSearchByStock;
			xmlHttp.send(null);
		}

	}

	function handlerSearchByStock() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

			var json = eval("(" + xmlHttp.responseText + ")");
			console.log(json);
			json1 = json;
			//	console.log(json1);
			var theTable = document.createElement('table');
			theTable.setAttribute("class", "table table-bordered");
			var parent = document.getElementById('table1');
			while (parent.hasChildNodes()) {
				console.log("insde div");
				//document.getElementById('table').replaceChild(theTable);  
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
			
			td1.appendChild(document.createTextNode("Title"));
			td2.appendChild(document.createTextNode("Cost (Rs)"));
			td3.appendChild(document.createTextNode("Current Stock"));
			td4.appendChild(document.createTextNode("Add to Stock"));
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
				var td6 = document.createElement('td');
				var input = document.createElement('input');
				var button = document.createElement('input');
				input.setAttribute("type", "text");
				input.setAttribute("name", i);
				input.setAttribute("id", i);
				button.setAttribute("type", "button");
				button.setAttribute("value", "Update Stock");
				button.setAttribute("onclick", "findByStock('" + json[i].title
						+ "'," + i + ");");
				td1.setAttribute("id", json[i].title);
				td1.setAttribute("name", json[i].title);
				td1.setAttribute("value", json[i].title);
				td6.setAttribute("id", "message");
				input.setAttribute("type", "text");
				td1.appendChild(document.createTextNode(json[i].title));
				td2.appendChild(document.createTextNode(json[i].cost));
				td3.appendChild(document.createTextNode(json[i].stock));
				td4.appendChild(input);
				td5.appendChild(button);
				//td6.appendChild("");
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				tr.appendChild(td5);
				tr.appendChild(td6);
				theTable.appendChild(tr);
			}

			document.getElementById('table1').appendChild(theTable);
		}
	}
	function findByStock(i, j) {
		createXMLHttpRequest();
		var stock = document.getElementById(j).value;
		//var title = document.getElementById(json1[i].title);
		//console.log(i);
		var url = "AddToStock?stockInput=" + stock + "&title=" + i;
		xmlHttp.open("GET", url, true);
		xmlHttp.onreadystatechange = handlerStockUpdate;
		xmlHttp.send(null);
		searchByStock();
	}
	function addStock(i, j) {
		createXMLHttpRequest();
		var category = document.getElementById("categorySelect").value;
		var pub = document.getElementById("pubSelect").value;
		var author = document.getElementById("authorSelect").value;
		var stock = document.getElementById(j).value;
		//var title = document.getElementById(json1[i].title);
		console.log(i);
		var url = "addStockServlet?categorySelect=" + category + "&pubSelect="
				+ pub + "&authorSelect=" + author + "&stockInput=" + stock
				+ "&abc=" + i;
		xmlHttp.open("GET", url, true);

		xmlHttp.onreadystatechange = handlerStockUpdate;
		xmlHttp.send(null);
		search();
	}


	function handlerStockUpdate() {

		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var json = eval("(" + xmlHttp.responseText + ")");
			if (json.message == "1") {
				//var mess = "Stock is updated";
				var msg = "";
				//setMessage(mess, "true");	 	    	  
			} else if (json.message == "0") {
				// var mess = "Stock update failed";
				//setMessage(mess, "false"); 
			}

		} else if (xmlHttp.readyState == 4 && xmlHttp.status != 200) {
			alert('Something went wrong...');
		}

	}
	function setMessage(message, isValid) {
		var messageArea = document.getElementById("message");
		var fontColor = "red";
		if (isValid == "true") {
			fontColor = "green";
		}
		messageArea.innerHTML = "<font color=" + fontColor + ">" + message
				+ " </font>";
	}

	var xmlHttp;
	function createXMLHttpRequest() {
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
	}

	function search() {
		if (confirm('Proceed?')) {
			createXMLHttpRequest();
			var category = document.getElementById("categorySelect").value;
			var username = document.getElementById("pubSelect").value;
			var author = document.getElementById("authorSelect").value;
			var url = "SearchItemServlet?categorySelect=" + category
					+ "&pubSelect=" + username + "&authorSelect=" + author;
			xmlHttp.open("GET", url, true);

			xmlHttp.onreadystatechange = handlerSearch;
			xmlHttp.send(null);
		}
	}

	function handlerSearch() {
		//
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

			var json = eval("(" + xmlHttp.responseText + ")");
			console.log(json);
			json1 = json;
			console.log(json1);
			var theTable = document.createElement('table');
			theTable.setAttribute("class", "table table-bordered");
			var parent = document.getElementById('table1');
			while (parent.hasChildNodes()) {
				console.log("insde div");
				//document.getElementById('table').replaceChild(theTable);  
				parent.removeChild(parent.firstChild);
			}

			for ( var i = 0; i < json.length; i++) {
				var tr = document.createElement('tr');
				var td1 = document.createElement('td');
				var td2 = document.createElement('td');
				var td3 = document.createElement('td');
				var td4 = document.createElement('td');
				var td5 = document.createElement('td');
				var td6 = document.createElement('td');
				var input = document.createElement('input');
				var button = document.createElement('input');
				input.setAttribute("type", "text");
				input.setAttribute("name", i);
				input.setAttribute("id", i);
				button.setAttribute("id","addToStock");
				button.setAttribute("type", "button");
				button.setAttribute("value", "Add to Stock");
				button.setAttribute("onclick", "addStock('" + json[i].title
						+ "'," + i + ");");
				td1.setAttribute("id", json[i].title);
				td1.setAttribute("name", json[i].title);
				td1.setAttribute("value", json[i].title);
				td6.setAttribute("id", "message");
				input.setAttribute("type", "text");
				td1.appendChild(document.createTextNode(json[i].title));
				td2.appendChild(document.createTextNode(json[i].cost));
				td3.appendChild(document.createTextNode(json[i].stock));
				td4.appendChild(input);
				td5.appendChild(button);
				//td6.appendChild("");
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				tr.appendChild(td5);
				tr.appendChild(td6);
				theTable.appendChild(tr);
			}

			document.getElementById('table1').appendChild(theTable);
		}
	}

	function categorySelectFunction() {
		createXMLHttpRequest();
		var username = document.getElementById("categorySelect").value;
		var url = "SearchItemServlet?categorySelect=" + username;
		xmlHttp.open("GET", url, true);
		//	console.log("line49");
		xmlHttp.onreadystatechange = handler;
		xmlHttp.send(null);
	}

	function pubSelectFunction() {
		// alert("inside pub select");
		createXMLHttpRequest();
		var category = document.getElementById("categorySelect").value;
		var username = document.getElementById("pubSelect").value;
		var url = "SearchItemServlet?categorySelect=" + category
				+ "&pubSelect=" + username;
		xmlHttp.open("GET", url, true);
		//	console.log("line58");
		xmlHttp.onreadystatechange = handlerPub;
		xmlHttp.send(null);
	}

	function handlerPub() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var json = eval("(" + xmlHttp.responseText + ")");

			for ( var i in json)
				addOptionPub(document.searchItems.authorSelect, json[i],
						json[i]);
		}
	}

	function addOptionPub(selectbox, text, value) {
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		selectbox.options.add(optn);
	}
	function handler() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var json = eval("(" + xmlHttp.responseText + ")");
			for ( var i in json)
				addOption(document.searchItems.pubSelect, json[i], json[i]);

		} else if (xmlHttp.readyState == 4 && xmlHttp.status != 200) {
			//alert('Something went wrong...');
		}
	}

	function addOption(selectbox, text, value) {
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		selectbox.options.add(optn);
	}

	/* $(document).ready(function(){
		$(table)
	}) */
</script>
</head>

<body>
	<header><div id="headingfont">BOOK MAFIA</div></header>
	<nav class="cl-effect-4">
		<a href="adminAddItems.jsp">Add Items</a> <a
			href="adminRemoveItems.jsp">Update Stock</a> <a
			href="viewCustomers.jsp">View Customers</a> <a href="PendingServlet">Pending
			Customers</a> <a href="LogOutServlet" id="logout">Log Out</a>
	</nav>
	<div id=contentOfUpdate>
		<form name="searchItems" style="width: 50%">
			<label>Search Books &nbsp&nbsp&nbsp&nbsp</label><select id="categorySelect"
				name="categorySelect" onblur="categorySelectFunction();">
				<option value="cat" disabled selected>Select Category</option>
				<option id="literature" value="Literature">Literature</option>
				<option id="fiction" value="Fiction">Fiction</option>
				<option id="academic" value="Academics">Academics</option>
				<option id="children" value="Kids">Kids</option>
			</select> <select id="pubSelect" name="pubSelect" onblur="pubSelectFunction()">
				<option value="pub" disabled selected>Select Publication</option>
			</select> <select id="authorSelect" name="authorSelect">
				<option value="" disabled selected>Select Author</option>
			</select><input type="button" onclick="search()" value="Search">
			<h6>
				Search by Stock <input type="text" id="input" name="input">
				<input type="button" onclick="searchByStock()" value="Search">
			</h6>

		</form>
		<!-- <div class="th" style="position: relative;left:10%;margin-right:10%;">Title</div><div class="th">Cost (Rs)</div><div class="th"> Stock</div><div class="th">Add to Stock</div> -->
		<div name="table1" id="table1"></div>
	</div>
	<footer></footer>
</body>
</html>