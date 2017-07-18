<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hunt The Wumpus</title>
	
	<!-- ===========================
    STYLESHEETS
    =========================== --> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/style.css">
    <link rel="stylesheet" href="resources/responsive.css">
    <link rel="stylesheet" href="resources/animate.css">
    
    <!-- ===========================
    FONTS
    =========================== -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,900,600|Pacifico' rel='stylesheet' type='text/css'>
    <style type="text/css">
    .error
			{
				color: red !important;
			}
    
    .perror 
    {
    	font-size: 13px;
    	line-height: 1.7em;
    	color: #f51000;
	}
    </style>
</head>
<body>
<%
	String username = (String) session.getAttribute("username");
	String errormessage = (String) request.getAttribute("errormessage");
%>
	<div id="products" class="container">
		<div class="sectionhead text-center">
			<h2>Create New Game Information</h2>
       		<hr>
		</div>
		<div class="row">
    		<div class="col-md-6 col-md-offset-3">
    		<div class="error">
    		<%if(errormessage!=null) {%>
    		 <p class="error"><%=errormessage %></p>
    		 <%} %>
    		</div>
    			<form name="cgameinfo" method="post" action="CreateGameInfo">
    				<input type="hidden" id="username" value="<%=username %>" name="username" />
    				<div class="form-group">
						<label for="gname">Game Name</label>
						<input type="text" placeholder = "The game name should not contain more than 20 characters" class="form-control" id="gname" name="gname" autofocus required/>
					</div>
					<div class="form-group">
						<label for="gtime">Game Time Out</label>
						<input type="text" placeholder = "In Seconds(Between 30 seconds to 2 minutes)" class="form-control" id="gtime" name="gtime" />
					</div>
					<input type="submit" class="btn btn-default" value="Create Game" name="cgamebtn" />
    			</form>
    		</div>
    	</div>
	</div>
</body>
</html>