function checkform() {
// Storing Field Values In Variables
	//alert("CP1");
 var username = document.getElementById("username").value;
 var password = document.getElementById("password").value;
 var fname = document.getElementById("fname").value;
 var lname = document.getElementById("lname").value;
 var email = document.getElementById("email").value;
 var mobile = document.getElementById("mobile").value;
 var address = document.getElementById("address").value;

 // Regular Expression For Email
 var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 // Regular expression for mobile number
 var mobileReg = /^[0-9]*$/; ///[2-9]{2}\d{8}/;
 // Conditions
 if (username != '' && email != '' && address != '' && fname!='' && mobile!='') {
	 if (email.match(emailReg)) {
		 if(mobile.length == 10 && mobile.match(mobileReg)){
			 alert("Successful Signup!");
			 return true;
		 }
		 else {
			 alert("Mobile Number should be numeric and 10 digits.");
			 return false;
		 }
	 }  
	 else {
		 alert("Invalid Email Address...!!!");
		 return false;
	 }
 } 
 else {
	 alert("All fields are required.....!");
	 return false;
 }
}

 function submitDetails() {	
	 alert('inside submitDetails');
	 if(checkform())
	 {
		 var varAction = 'RegisterServlet'; //'Registration?userId' + userId + '&function=' + funct;	
		 //alert('submitting varAction: ' + varAction);
		 document.forms["signupForm"].action = varAction;
		 //alert("action completed");
		 
		 document.forms["signupForm"].method = "POST";
		 //alert("method completed");
		 document.forms["signupForm"].submit();	
		 //alert("submit completed");
	 }

 }