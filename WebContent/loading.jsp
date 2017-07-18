<%@page import="com.swe681.beans.SessionAttribute"%>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/style.css">
<link rel="stylesheet" href="resources/responsive.css">
<link rel="stylesheet" href="resources/animate.css">

<!-- ===========================
    FONTS
    =========================== -->
<link
	href='http://fonts.googleapis.com/css?family=Raleway:400,900,600|Pacifico'
	rel='stylesheet' type='text/css'>

<script type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="resources/wumpus.css">
<script type="text/javascript" src="resources/wumpus.js"></script>
<script type="text/javascript" src="resources/shortcut.js"></script>
<script type="text/javascript"
	src="http://gc.kis.v2.scr.kaspersky-labs.com/E840FA2C-0A93-454F-8C10-7E3F8FCC22F5/main.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="jquery/jquery.js"></script>
<script type="text/javascript" src="jquery/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="jquery/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="jquery/jquery-ui.theme.css">

<!-- <script type="text/javascript">
	 shortcut.add("Ctrl+Up",function() { move("up"); });
	shortcut.add("Ctrl+Right",function() { move("right"); });
	shortcut.add("Ctrl+Down",function() { move("down"); });
	shortcut.add("Ctrl+Left",function() { move("left"); });
	shortcut.add("Alt+Up",function() { shoot("up"); });
	shortcut.add("Alt+Right",function() { shoot("right"); });
	shortcut.add("Alt+Down",function() { shoot("down"); });
	shortcut.add("Alt+Left",function() { shoot("left"); }); 
</script> -->

<style type="text/css">
.divGameStatus {
	visibility: hidden;
}

.otherdiv {
	visibility: visible;
}

.sectionhead {
	padding: 0 150px;
}

.sectionhead h2 {
	padding-bottom: 10px;
}

.sectionhead p {
	color: #c0c0c0;
	font-size: 16px;
	line-height: 1.6em;
}

.sectionhead hr {
	width: 75px;
	border-bottom: 4px solid #eeeeee;
}
</style>

<script type="text/javascript">

/* window.onload = function disableGame(){
	
	var gamestatus = document.getElementById("gamestatus").value;
	//alert(gamestatus);
	//var wumpusboard = document.getElementById("wumpusboard").value;
	
	if(gamestatus == "created")
	{
		//alert('in if');
		document.getElementById("gameBoard").style.visibility = "hidden";
		document.getElementById("movearrows").style.visibility = "hidden";
		document.getElementById("shootarrows").style.visibility = "hidden";
		document.getElementById("show").style.visibility = "visible";
	}
} */
function navigateToGame(){
	msg="STARTGAME";
	$.get("CheckStatus",{msg:msg},function(responseText){
		if(responseText=="STARTGAME"){
			location.href = "game.jsp";
		}
	});
}
function checkIfJoined(){
	msg="USER2JOINED";
	$.get("CheckStatus",{msg:msg},function(responseText){
		//alert(responseText);
		if(responseText.length != 0)
		{
			var values=responseText.split(",")
			var user1,user2;
			if(values.length==1){
				var value=$("#creator").length;
				typeof value === "undefined"
				if(typeof value === "undefined" || value==0){
					user1=values[0];
					var newRow1 = $("<h2 id='creator'>Game Created User:"+user1+"</h2>");
		   			newRow1.insertBefore("#joinuser1");
		   		}
		   		setInterval(checkIfJoined(),6000);
			}
			else if(values.length==2){
				user1=values[0];
				user2=values[1];
				//var indx = 3;
				//var newRow = $('<tr><td>Joined User 2:'+responseText+'</td></tr>');
		   		//newRow.insertBefore($('#tbl tbody tr:nth('+indx+')'));
		   		var newRow1 = $("<h2 id='creator'>Game Created User:"+user1+"</h2>")
		   		newRow1.insertBefore("#joinuser1");
		   		var newRow2 = $("<h2 id='Joined'>Joined User:"+user2+"</h2>")
		   		newRow2.insertBefore("#joinuser2");
				navigateToGame();
			}
				
		}
		else
		{
			setInterval(checkIfJoined(),6000);
		}
	});
}

</script>

</head>

<body onload="checkIfJoined()">

	<div id="products" class="container">
		<div class="sectionhead text-center">
			<h2>Please wait while other user joins</h2>
			<hr>
		</div>
	</div>

	<input type="hidden" value="<%=session.getAttribute("game")%>">
	<form method="post" action="startGame">
		<table align="center" cellspacing="15" id="tbl">

			<tr>
				<td>
					<h2>
						Game Name:
						<%=session.getAttribute(SessionAttribute.GAME_NAME) %>
					</h2>
				</td>
			</tr>
			

			<tr>
				<td>
					<h3>
						<span id="joinuser1"></span>
					</h3>
				</td>
			</tr>
			<tr>
				<td>
				<h3>
				<span id="joinuser2"></span>
				</h3>
				</td>
			
			</tr>

		</table>

	</form>

</body>
</html>