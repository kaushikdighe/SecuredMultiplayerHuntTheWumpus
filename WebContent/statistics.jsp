<%@page import="java.util.List"%>
<%@page import="com.swe681.beans.UserStats"%>
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
	</script>
	<style type="text/css">		
	table 		
	{		
    	border-collapse: collapse;		
    	width: 100%;		
	}		
	th, td 		
	{		
		font-family: 'Raleway', sans-serif;		
		font-size:20px;		
    	text-align: left;		
    	padding: 8px;		
	}			
	tr:nth-child(even){background-color: #f2f2f2}		
	th 		
	{		
		font-family: 'Raleway', sans-serif;		
    	background-color: #f53f2c;		
    	color: white;		
	}		
</style>
</head>
<body>

	
	<div id="products" class="container">
		
		<div class="sectionhead text-center">
        	<h2>Statistics</h2>
       	 	<hr>
        </div>
        
        <div class="leaderBoard">
        	<form name="leaderB" action="">
        	<table border="1">

	        	<tr>
	        		<th>Rank</th>
	        		<th>Player Name</th>
	        		<th>Games Played</th>
	        		<th>Games Won</th>
	        		<th>Games Lost</th>
	        	</tr>
 <%
	List<UserStats> list=(List<UserStats>) request.getAttribute("list");
    for(UserStats u:list){
    String rank = (String) u.getIndex();
    String username = (String) u.getName();
	String countWon = (String) u.getGameWon();
	String countLost = (String) u.getGameLost();
	String totalCount = (String) u.getTotal();
	if(username!=null){
%>
	       		<tr>
	       			<td><%=rank%></td>
	 				<td><%=username%></td>
	 				<td><%=totalCount%></td>
	 				<td><%=countWon%></td>
	 				<td><%=countLost%></td>
	 			</tr>
  <%
	}
  }
  %>
        	</table>
        	</form>
       </div>
    </div>
    <div>
    <form action="menu.jsp" method="get" class="btn-btn-default">
    	<input type="submit" class="btn btn-default" value="Back to Menu"></input><br>
    </form>
    
    </div>
    
</body>
</html>