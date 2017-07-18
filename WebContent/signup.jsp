<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sign Up-Hunt The Wumpus</title>
	
	<!-- ===========================
    STYLESHEETS
    =========================== --> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/animate.css">
    
    <!-- ===========================
    FONTS
    =========================== -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,900,600|Pacifico' rel='stylesheet' type='text/css'>
    <style type="text/css">
    .error
			{
				color: red
			}
    
    </style>
    <script type="text/javascript">
	
	function validationform()
	{
		//alert("in validation");
		document.getElementById("firstName").innerHTML = "";
		document.getElementById("lastName").innerHTML = "";
		document.getElementById("username").innerHTML = "";
		document.getElementById("createPassword").innerHTML = "";
		var firstName = document.getElementById("fname").value();
		var lastName = document.getElementById("lname").value();
		var userName = document.getElementById("username").value();
		var pWord = document.getElementById("createPassword").value();
		
				if(firstName.length<20)
					{
						document.getElementById("firstName").innerHTML = "Your first name should be less than 20 letters";
						return false;
					}
				else if(lastName.length<20)
				{
					document.getElementById("lastName").innerHTML = "Your last name should be less than 20 letters";
					return false;
				}
				else if(userName.length>5 && userName.length<20)
				{
					document.getElementById("username").innerHTML = "Your username should be between 5-20 letters";
					return false;
				}
				else if(pWord.length>8 && pWord.length<20)
				{
					document.getElementById("createPassword").innerHTML = "Your password should be between 8-20 letters";
					return false;
				}
				else
					{
						return true;
					}
			
	}
	</script>
</head>
<body>

<%
	String errormessage = (String) request.getAttribute("errormessage");
%>
	<div id="products" class="container">
		
		<!--SECTIONHEAD START-->
    	<div class="sectionhead text-center">
        <h2>Register</h2>
       	<hr>
    	</div><!--SECTIONHEAD END-->
    	<div class="row">
    		<div class="col-md-6 col-md-offset-3">
    		<div class ="error">
    		<%if(errormessage!=null) {%>
    		<p><%=errormessage %></p>
    		<%} %>
    		
    		
    		
    		</div>
    			<form name="signup" method="post" action="SignUp">
    				<div class="form-group">
						<label for="fname">First Name</label>
						<input type="text" class="form-control" id="fname" name="fname" autofocus/ required>
						<span id ="firstName" class = "error"></span>
					</div>
					<div class="form-group">
						<label for="lname">Last Name</label>
						<input type="text" class="form-control" id="lname" name="lname" / required>
						<span id ="lastName" class = "error"></span>
					</div>
    				<div class="form-group">
						<label for="username">User Name</label>
						<input type="text" placeholder="should contain more than 5 and less than 20 characters" class="form-control" id="username" name="username"/ required>
						<span id ="username" class = "error"></span>
					</div>
					<div class="form-group">
						<label for="password">Create Password</label>
						<input type="password" placeholder = "should contain more than 8 and less than 20 characters" class="form-control" id="createPassword" name="createPassword"/ required>
						<span id ="createPassword" class = "error"></span>
					</div>
					
					<input type="submit" class="btn btn-default" value="Register" name="task"/>
					<!-- <input type="submit" class="btn btn-default pull-right" value= "Reset" name="task"></input> -->
					<input type="reset" class="btn btn-default pull-right" value= "Reset" name="task"></input>
    			</form>
    		</div>
    	</div>
	</div>
</body>
</html>