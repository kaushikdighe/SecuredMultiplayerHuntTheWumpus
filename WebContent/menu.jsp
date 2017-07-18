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
    
    <script type="text/javascript">
    
    /* document.getElementById("creategame").onclick = function (){
        location.href = "game.jsp";
    }; */
	</script>
</head>
<body>
<div align="right">
<form method="post" action="Logout">
<input type="submit" class="btn btn-default" name ="logoff" value="Log Out" /><br/>
</form>

</div>
<%
	String username = (String)session.getAttribute("username");
%>
	<div id="products" class="container">
		
		<div class="sectionhead text-center">
        <h2>Menu</h2>
        <hr>
        <h2>Welcome, <%=username%></h2>
        
    	</div><!--SECTIONHEAD END-->
    	
    	<div class="row">	
    		<form name="login" method="post" action="CreateNewGame">
    		 <div align="center">
    			<input type="hidden" id="username" name="username" value="<%=username%>"/> 			
    			<input type="submit" class="btn btn-default" name ="newgamebtn" value="Create New Game" /><br/>
    			<input type="submit" class="btn btn-default" name ="newgamebtn" value="Join Games" /><br/>
    			<input type="submit" class="btn btn-default" name ="newgamebtn" value="Statistics" /><br/>
    			</div>
    		</form>
    	</div>
	</div>
</body>
</html>