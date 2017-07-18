package com.swe681.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.swe681.beans.SessionAttribute;
import com.swe681.beans.UserStats;

public class DatabaseServicesCreateGame {


	String connectionString = "jdbc:mysql://swe645assignment4.cxfdxaz2eghd.us-east-1.rds.amazonaws.com:3306/swe645assignment4";
	String userName = "kdighe_swe645";
	String password = "kdighe_swe645";




	public void InsertGame(String gameName, int gameTime, String gameStatus, String userId)
	{


		Connection conn = null;
		String insertQuery = "";
		int row  = 0;
		System.out.println("-----------------In InsertGameDetails function-------------------");
		PreparedStatement preparedStamenet = null;
		//gameStatus = "created";
		//System.out.println(firstName + lastName + uName +pword);

		try
		{
			conn = DatabaseService.createDbConnection();

			preparedStamenet = conn.prepareStatement("INSERT into swe645assignment4.game(gameName,gameTime,gameStatus,userId) values(?,?,?,?)");
			preparedStamenet.setString(1, gameName);
			preparedStamenet.setInt(2, gameTime);
			preparedStamenet.setString(3, gameStatus);
			preparedStamenet.setString(4, userId);

			preparedStamenet.executeUpdate();

			System.out.println("------query------"+ preparedStamenet.toString());
		}
		catch(Exception e)
		{
			System.out.println("----------Error in InsertListing-------");
			e.printStackTrace();
		}




	}

	public ArrayList<String> getGameInfo()
	{
		Connection conn = null;
		//String insertQuery = "";
		int row  = 0;
		System.out.println("-----------------In Check User Exist function-------------------");
		PreparedStatement preparedStamenet = null;
		String query = null;
		String status = "created";
		ArrayList<String> hm = new ArrayList<String>();

		try
		{
			conn = DatabaseService.createDbConnection();
			query = "SELECT gameName FROM swe645assignment4.game WHERE gameStatus=?";
			preparedStamenet = conn.prepareStatement(query);
			preparedStamenet.setString(1, status);
			ResultSet rs = preparedStamenet.executeQuery();
			System.out.println("Query is-----"+preparedStamenet);

			while(rs.next())
			{
				String gameName = rs.getString("gameName");
				hm.add(gameName);
			}

			System.out.println("---Row value is---"+row);
		}
		catch(Exception e)
		{
			System.out.println("----------Error in InsertListing-------");
			e.printStackTrace();
		}

		return hm;

	}

	public int gamesWon(String username)
	{
		Connection conn = null;
		int countWon = 0;
		System.out.println("-----------------In Games Won function-------------------");
		PreparedStatement preparedStamenet = null;
		String query = null;
		String gameStatus= "Finished";
		try
		{
			conn = DatabaseService.createDbConnection();
			query = "Select Count(playerWon) as 'Games Won' from swe645assignment4.game where userId = ? and gameStatus = ?";
			preparedStamenet = conn.prepareStatement(query);
			preparedStamenet.setString(1, username);
			preparedStamenet.setString(2, gameStatus);
			ResultSet rs = preparedStamenet.executeQuery();

			while(rs.next())
			{
				countWon = rs.getInt("Games Won");
			}

			System.out.println("---Count of Player Won -----"+countWon);

		}
		catch(Exception e)
		{
			System.out.println("----------Error in gamesWon-------");
			e.printStackTrace();
		}

		return countWon;
	}

	public int gamesLost(String username)
	{
		Connection conn = null;
		int countLost = 0;
		System.out.println("-----------------In Games Lost function-------------------");
		PreparedStatement preparedStamenet = null;
		String query = null;
		String gameStatus= "Finished";
		try
		{
			conn = DatabaseService.createDbConnection();
			query = "Select Count(playerLost) as 'Games Lost' from swe645assignment4.game where userId = ? and gameStatus = ?";
			preparedStamenet = conn.prepareStatement(query);
			preparedStamenet.setString(1, username);
			preparedStamenet.setString(2, gameStatus);
			ResultSet rs = preparedStamenet.executeQuery();

			while(rs.next())
			{
				countLost = rs.getInt("Games Lost");
			}

			System.out.println("---Count of Player Won -----"+countLost);

		}
		catch(Exception e)
		{
			System.out.println("----------Error in gamesWon-------");
			e.printStackTrace();
		}

		return countLost;
	}

	public List<UserStats> leaderBoard()
	{
		List<UserStats> list = new ArrayList<UserStats>();
		Connection conn = null;
		HashMap <String,Integer> lb = new HashMap<String,Integer>();
		UserStats user=new UserStats();
		String[][] leaderboard = new String[10][10];
		System.out.println("-----------------In Leader Board function-------------------");
		PreparedStatement preparedStamenet = null;
		String query = null;
		String gameStatus= "Finished";
		int index=0;
		String currentName="";
		try
		{
			conn = DatabaseService.createDbConnection();
			query = "SELECT u.username,IFNULL(win.win,0) as win,IFNULL(lost.lost,0)  as lost FROM users u left outer join (SELECT playerWon as player,count(*) as win FROM game WHERE gameStatus like 'Finished' GROUP BY playerWon) win on u.username=win.player left outer join (SELECT playerLost as player,count(*) as  lost FROM game WHERE gameStatus like 'Finished' Group BY playerLost) lost on u.username=lost.player ORDER BY win DESC, lost ASC";
			preparedStamenet = conn.prepareStatement(query);
			//preparedStamenet.setString(1, gameStatus);
			//preparedStamenet.setString(2, gameStatus);
			ResultSet rs = preparedStamenet.executeQuery();

			while(rs.next())
			{
				//lb.put(rs.getString("playerWon"),rs.getInt("Wins"));
				String playerName = rs.getString("username");
				String wins = rs.getString("win");
				String loss = rs.getString("lost");

				index++;
				UserStats u=new UserStats();
				u.setIndex(""+index);
				u.setName(playerName);
				u.setGameWon(wins);
				u.setGameLost(loss);
				u.setTotal(""+(Integer.parseInt(wins)+Integer.parseInt(loss)));
				if(playerName!=null&&currentName.equals(playerName)){
					user=u;
				}
				else{
					list.add(u);
				}
			}
			list.add(1, user);
			preparedStamenet.close();
			System.out.println("---Leader Board-----");

		}
		catch(Exception e)
		{
			System.out.println("----------Error in gamesWon-------");
			e.printStackTrace();
		}


		return list;
	}
	public static String checkGameStatus(String game){
		String status="";
		Connection conn=null;
		PreparedStatement preparedStamenet=null;
		try {
			conn = DatabaseService.createDbConnection();
			String query = "SELECT gameStatus FROM game WHERE gameName= ? ";

			preparedStamenet = conn.prepareStatement(query);
			preparedStamenet.setString(1, game);
			ResultSet rs = preparedStamenet.executeQuery();

			while(rs.next())
			{
				status=rs.getString("gameStatus");
			}
			preparedStamenet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public static boolean changeGameStatus(String game,String status){
		boolean isChanged=false;
		Connection conn=null;
		PreparedStatement preparedStamenet=null;
		try {
			conn = DatabaseService.createDbConnection();
			String query = "UPDATE game SET gameStatus=? WHERE gameName=?";

			preparedStamenet = conn.prepareStatement(query);
			preparedStamenet.setString(1, status);
			preparedStamenet.setString(2, game);
			int updateCount = preparedStamenet.executeUpdate();

			if(updateCount==1){
				isChanged=true;
			}
			preparedStamenet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isChanged;
	}

	public static boolean addUserToGame(String joinedGame,String username) {
		boolean status=false;
		String stat="";
		Connection conn=null;
		int updateCount=0;
		PreparedStatement preparedStamenet=null;
		System.out.println(joinedGame);
		try {
			conn = DatabaseService.createDbConnection();
			stat=checkGameStatus(joinedGame);

			if(stat.equals(SessionAttribute.GAME_CREATED)){
				String query="UPDATE game SET gameStatus=? , player2=? WHERE gameName=?";
				preparedStamenet=conn.prepareStatement(query);
				preparedStamenet.setString(1, SessionAttribute.GAME_CREATED);
				preparedStamenet.setString(2, username);
				preparedStamenet.setString(3, joinedGame);
				updateCount=preparedStamenet.executeUpdate();
				System.out.println(preparedStamenet+"...."+updateCount);
				if(updateCount==1){
					status=true;
				}
				preparedStamenet.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public static String isUserJoinedGame(String gameName) 
	{
		// TODO Auto-generated method stub
		String status="";
		String status1 = "";
		Connection conn=null;
		PreparedStatement preparedStamenet=null;

		try 
		{
			conn = DatabaseService.createDbConnection();
			String query = "SELECT userId,player2 FROM game WHERE gameName= ? ";

			preparedStamenet = conn.prepareStatement(query);
			preparedStamenet.setString(1, gameName);
			ResultSet rs = preparedStamenet.executeQuery();

			while(rs.next())
			{
				status1 = rs.getString("userId");
				status=rs.getString("player2");
			}
			preparedStamenet.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status!=null&&status.length()!=0)
			return status1 + ","+ status;
		else 
			return status1;
	}

	public int ifGameAlreadyExists(String gameName)
	{
		Connection conn = null;
		int gameExists = 0;
		System.out.println("-----------------In Game Already Exists function-------------------");
		PreparedStatement preparedStamenet = null;
		String query = null;

		try
		{
			conn = DatabaseService.createDbConnection();
			query = "Select gameId from swe645assignment4.game where gameName = ?";
			preparedStamenet = conn.prepareStatement(query);
			preparedStamenet.setString(1, gameName);
			//preparedStamenet.setString(2, gameStatus);
			ResultSet rs = preparedStamenet.executeQuery();

			while(rs.next())
			{
				gameExists++;
			}

			System.out.println("---If game Exists -----"+gameExists);

		}
		catch(Exception e)
		{
			System.out.println("----------Error in gameAlreadyExists-------");
			e.printStackTrace();
		}

		return gameExists;


	}

	public static String changeUserStatus(String gameName, String username, String msg) {
		// TODO Auto-generated method stub
		String status="";
		String user2="";
		String stat="";
		Connection conn=null;
		int updateCount=0;
		String winner="";
		String looser="";
		PreparedStatement preparedStamenet=null;
		System.out.println(gameName);
		try {
			conn = DatabaseService.createDbConnection();
			stat=checkGameStatus(gameName);

			if(stat.equals(SessionAttribute.GAME_PLAYING)){
				user2=getUser(gameName,username);
				if(msg.equals("WINNER")){
					winner=username;
					looser=user2;
				}
				else if(msg.equals("DEAD")){
					winner=user2;
					looser=username;
				}
				String query="UPDATE game SET gameStatus=? , playerWon=?,playerLost=? WHERE gameName=?";
				preparedStamenet=conn.prepareStatement(query);
				preparedStamenet.setString(1, SessionAttribute.GAME_FINISHED);
				preparedStamenet.setString(2, winner);
				preparedStamenet.setString(3, looser);
				preparedStamenet.setString(4, gameName);
				updateCount=preparedStamenet.executeUpdate();
				System.out.println(preparedStamenet+"...."+updateCount);
				if(updateCount==1){
					status=winner+","+looser;
				}
				preparedStamenet.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public static String getUser(String gameName,String username){
		String user="";
		PreparedStatement preparedStamenet=null;
		Connection conn=null;
		try{
			conn = DatabaseService.createDbConnection();
			String query="SELECT userId,player2 FROM game WHERE gameName=?";
			preparedStamenet=conn.prepareStatement(query);
			preparedStamenet.setString(1, gameName);
			ResultSet rs=preparedStamenet.executeQuery();
			while(rs.next())
			{
				String creator=rs.getString("userId");
				String other=rs.getString("player2");
				if(creator!=null&&other!=null){
					if(creator.equals(username))
						user=other;
					else
						user=creator;


				}
			}
			preparedStamenet.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public static String getWinnerLooser(String gameName){
		String winloss="";
		PreparedStatement preparedStamenet=null;
		String winner="";
		String looser="";
		Connection conn=null;
		try{
			conn = DatabaseService.createDbConnection();
			String query="SELECT playerWon,PlayerLost FROM game WHERE gameName=?";
			preparedStamenet=conn.prepareStatement(query);
			preparedStamenet.setString(1, gameName);
			ResultSet rs=preparedStamenet.executeQuery();
			while(rs.next())
			{
				winner=rs.getString("playerWon");
				looser=rs.getString("PlayerLost");
				if(winner!=null&&looser!=null)
					winloss=winner+","+looser;
			}
			preparedStamenet.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return winloss;
	}

	public static String checkWinner(String gameName) {
		// TODO Auto-generated method stub
		String status="";
		String winner="";
		String looser="";
		System.out.println(gameName);
		String winloss=getWinnerLooser(gameName);
		if(!winloss.equals("")){
			String[] res=winloss.split(",");
			if(res.length==2){
				winner=res[0];
				looser=res[1];
				status=winner+","+looser;
			}
		}
		return status;
	}


}
