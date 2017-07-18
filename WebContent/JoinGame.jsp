<%@page import="com.swe681.controller.CreateNewGame"%>
<%@page import="com.swe681.beans.CreateGameBean" %>
<%@page import="java.util.ArrayList" %>
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
	.button {
    display: block;
    width: 115px;
    height: 25px;
    background: #4E9CAF;
    padding: 10px;
    text-align: center;
    border-radius: 5px;
    color: white;
    font-weight: bold;
}

th, td { padding: 5px; }
table { border-collapse: separate; border-spacing: 5px; }

</style>
</head>
<body>
	<%
		ArrayList<String> gameList = (ArrayList<String>) session.getAttribute("gameList");
	%>
	<div id="products" class="container">
		<div class="sectionhead text-center">
			<h2>List of Games</h2>
			<p><b>Please click on any game to join</b></p>
        	<hr>
		</div>
	</div>
	<div class="row">
		<form method="post" action="JoinOldGame">
		<table align="center" cellspacing="15">
			<% for(int i = 0; i < gameList.size(); i+=1) { %>
					<%if( (i% 4)== 0) {%><tr><%} %>
		 		<td ><a class="btn btn-default" name="gameList" href="/HuntTheWumpusVersion2.1/JoinOldGame?id=<%=gameList.get(i)%>"><%= gameList.get(i) %></a></td>
		 		 <%if( ((i+1) % 4)== 0) {%></tr><%} %>
		 	<%} %>
		</table>
		
		 	<%-- <% for(int i = 1; i <= gameList.size(); i+=1) { %>
		 		<a class="btn btn-default" name="gameList" href="/HuntTheWumpus/CreateGameBean?id=<%=gameList.get(i-1)%>"><%= gameList.get(i-1) %></a>
		 		<%if( (i% 4)== 0) {%><br><%} %> 
		 	<%} %> --%>
		</form>
	</div>
</body>
</html>