package com.swe681.services;


	import java.sql.*;
	import java.security.SecureRandom;
	import javax.crypto.spec.PBEKeySpec;
	import javax.crypto.SecretKeyFactory;
	import java.math.BigInteger;
	import java.security.NoSuchAlgorithmException;
	import java.security.spec.InvalidKeySpecException;

	public class HashPassword {
		public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	    public static final int SALT_BYTE_SIZE = 24;
	    public static final int HASH_BYTE_SIZE = 24;
	    public static final int PBKDF2_ITERATIONS = 1000;
	    public static final int ITERATION_INDEX = 0;
	    public static final int SALT_INDEX = 1;
	    public static final int PBKDF2_INDEX = 2;
	    
	    String connectionString = "jdbc:mysql://swe645assignment4.cxfdxaz2eghd.us-east-1.rds.amazonaws.com:3306/swe645assignment4";
		String userName = "kdighe_swe645";
		String password = "kdighe_swe645";
		
		protected Connection createDbConnection( String connectionString,String  userName, String password) 
		{
			Connection con = null;
			System.out.println("---Connection String is -------"+connectionString);
			System.out.println("------Creating RDS MySQL connection-------");
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(connectionString, userName, password);
			} 
			catch (Exception e) 
			{
				System.out.println("------Error connecting-------");
				e.printStackTrace();
			}
			return con;
		}
	    
	    public static String createHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
	     {
	            return createHash(password.toCharArray());
	        }
	    
	    public static String createHash(char[] password)
	            throws NoSuchAlgorithmException, InvalidKeySpecException
	        {
	            // Generate a random salt
	            SecureRandom random = new SecureRandom();
	            byte[] salt = new byte[SALT_BYTE_SIZE];
	            random.nextBytes(salt);

	            // Hash the password
	            byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
	            // format iterations:salt:hash
	            return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +toHex(hash);
	              
	        }
	    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
	            throws NoSuchAlgorithmException, InvalidKeySpecException
	        {
	            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
	            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
	            return skf.generateSecret(spec).getEncoded();
	        }
	    private static byte[] fromHex(String hex)
	    {
	        byte[] binary = new byte[hex.length() / 2];
	        for(int i = 0; i < binary.length; i++)
	        {
	            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
	        }
	        return binary;
	    }
	    private static String toHex(byte[] array)
	    {
	        BigInteger bi = new BigInteger(1, array);
	        String hex = bi.toString(16);
	        int paddingLength = (array.length * 2) - hex.length();
	        if(paddingLength > 0)
	            return String.format("%0" + paddingLength + "d", 0) + hex;
	        else
	            return hex;
	    }
	    
	    public static boolean validatePassword(String password, String correctHash) throws NoSuchAlgorithmException, InvalidKeySpecException
	    {
	        return validatePassword(password.toCharArray(), correctHash);
	    }
	    
	    public static boolean validatePassword(char[] password, String correctHash) throws NoSuchAlgorithmException, InvalidKeySpecException
	    {
	            // Decode the hash into its parameters
	            String[] params = correctHash.split(":");
	            int iterations = Integer.parseInt(params[ITERATION_INDEX]);
	            byte[] salt = fromHex(params[SALT_INDEX]);
	            byte[] hash = fromHex(params[PBKDF2_INDEX]);
	            // Compute the hash of the provided password, using the same salt, 
	            // iteration count, and hash length
	            byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
	            // Compare the hashes in constant time. The password is correct if
	            // both hashes match.
	            return slowEquals(hash, testHash);
	     }
	    
	    private static boolean slowEquals(byte[] a, byte[] b)
	    {
	        int diff = a.length ^ b.length;
	        for(int i = 0; i < a.length && i < b.length; i++)
	            diff |= a[i] ^ b[i];
	        return diff == 0;
	    }
		
	    public static String hashSignUpPassword(String pwd){
			String hash1="";
			
			//String pwd = "abineby";
			//String sample1 ="abin123";
			//String sample2 = "abineby";
			try
			{
				 hash1 = createHash(pwd);
				 //hash2 = createHash(sample1);
				 //hash3 = createHash(sample2);
			}
			catch(Exception e){System.out.println(e);}
			
			//System.out.println("HASH OF PASSWORD = "+hash1);
//			try{
//				if(validatePassword(sample2, hash1)){
//					System.out.println("Valid");
//				}
//				else{
//					System.out.println("InValid");
//				}
//			}catch(Exception e){System.out.println(e);}
			
			//System.out.println("HASH OF PASSWORD = "+hash2);
			//if(hash2.equals(hash1)){
				//System.out.println("Match");
			  return hash1;
			
		}
		
		public boolean hashLoginPassword(String pwd,String uName)
		{
			Connection conn = null;
			String insertQuery = null;					
			boolean row  = false;
			System.out.println("-----------------In InsertStudentDetails function-------------------");
			 
			try
			{
				conn = createDbConnection(connectionString, userName, password);
				insertQuery = "SELECT pword FROM swe645assignment4.users WHERE username =?";
				PreparedStatement preparedStamenet = conn.prepareStatement(insertQuery);
				preparedStamenet.setString(1, uName);
				
				ResultSet rs = preparedStamenet.executeQuery();
				while(rs.next())
				{
					String hash1 = rs.getString("pword");
					
				if(validatePassword(pwd, hash1))
				{
					//System.out.println("Valid");
					row = true;
				}
				else{
					//System.out.println("InValid");
					row = false;
				}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			return row;
			
		}
	}


