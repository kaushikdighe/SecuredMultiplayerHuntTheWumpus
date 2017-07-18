<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hunt The Wumpus</title>

<script type="text/javascript">
	
</script>

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
	setInterval("checkWinner()", 3000);
	//game function
	function checkWinner() {
		var msg="CHECK";
		$.get("ActionServlet",{msg:msg},function(responseText){
				if (responseText.length != 0) {
					var value = responseText.split(",");
					if (value.length == 2) {
						if (msg == "WINNER")
							alert(value[0]+" won the Game, "+value[0] +" beat "+value[1]);
						else
							alert(value[1]+" lost the game, "+value[1] +" beaten by "+value[0]);
					}
					location.href = "stat";
				}
				

			});
		}
	
	function info_message(msg) {
		if (msg == "DEAD" || msg == "WINNER") {
			$.get("ActionServlet",{msg:msg},function(responseText){
				if (responseText.length != 0) {
					var value = responseText.split(",");
					if (value.length == 2) {
						if (msg == "WINNER")
							alert(value[0]+" won the Game, "+value[0] +" beat "+value[1]);
						else
							alert(value[1]+" lost the game, "+value[1] +" beaten by "+value[0]);
					}
					location.href = "stat";
					
				}
				
			});
		}

	}
</script>

</head>

<body>

	<%
		String gameStatus = (String) session.getAttribute("gamestatus");
	%>



	<div class="sectionhead" align="center">
		<h2>Hunt the Wumpus</h2>
		<hr>
		<p>
			<b>Arrows remaining: <span id="arrowsLeft"
				style="color: blue; font-weight: 15px">5</span></b>
		</p>

	</div>
	<input type="hidden" id="gamestatus" value="<%=gameStatus%>"
		name="gamestatus" />
	<table align="center" cellspacing="15">
		<tr>
			<td>
				<div id="gameBoard" class="otherdiv">Hunt the Wumpus should
					load in this space. If it does not, it means that either Javascript
					is disabled on your browser, or the developer made a mistake.</div>
			</td>
			<td id="feedback">
				<!-- <div id="feedback"></div> -->
			</td>
		</tr>
		<tr>
			<td>
				<div id="movearrows" class="otherdiv">
					<form id="movementControls" onsubmit="return false;">
						<p>Move</p>
						<table>
							<tr>
								<td rowspan="3"><input type="image"
									src="images/ArrowLeft.png" width="44" height="44" id="left"
									title="left" value="&lt;-" onclick="move('left')" /></td>
								<td><input type="image" src="images/ArrowUp.png" width="44"
									height="44" id="up" title="up" value="^" onclick="move('up')" /></td>
								<td rowspan="3"><input type="image"
									src="images/ArrowRight.png" width="44" id="right" height="44"
									value="-&gt;" onclick="move('right')" /></td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td><input type="image" src="images/ArrowDown.png"
									width="44" height="44" id="down" value="v"
									onclick="move('down')" /></td>
							</tr>
						</table>
					</form>
				</div>
			</td>
			<td>
				<div id="shootarrows" class="otherdiv">
					<form id="arrowControls" onsubmit="return false;">
						<p>Shoot</p>
						<table>
							<tr>
								<td rowspan="3"><input type="image"
									src="images/ShootLeft.png" width="44" height="44" title="left"
									value="&lt;-" onclick="shoot('left')" /></td>
								<td><input type="image" src="images/ShootUp.png" width="44"
									height="44" title="up" value="^" onclick="shoot('up')" /></td>
								<td rowspan="3"><input type="image"
									src="images/ShootRight.png" width="44" height="44"
									title="right" value="-&gt;" onclick="shoot('right')" /></td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td><input type="image" src="images/ShootDown.png"
									width="44" height="44" title="down" value="v"
									onclick="shoot('down')" /></td>
							</tr>
						</table>
					</form>
				</div>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		var game = new Game;
		game.init();
	</script>


	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-19082249-1', 'osric.com');
		ga('send', 'pageview');
	</script>

</body>
</html>