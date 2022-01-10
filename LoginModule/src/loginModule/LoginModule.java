package loginModule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class LoginModule{
	
	public static String message = null;
	private static String connectionUrl = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";

    /**
     * Compares provided parameters with expected database value
     * @param username
     * @param password
     * @return true if given parameters equal expected values.
     */
    public static Boolean authenticate(String login, String password)
    {
    	message = null; 
        String expected_password = null;
        
        try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try (Connection con = DriverManager.getConnection(connectionUrl,"z24", "ds4znf"); Statement stmt = con.createStatement();) 
        {
            
        	ResultSet rs = stmt.executeQuery("SELECT password FROM temp WHERE login = " + "'" + login + "'");
           
        	if(rs.next()){
        		expected_password = rs.getString("password");
        	}  
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	
        if (password.equals(expected_password)) {
        	
        	message = "Authentication succesful.";
        	return true;
        }
        else {
        	message = "Wrong login or password.";
        	return false;
        }
      
    }
    
    
    
    
    
    
    
    
    @SuppressWarnings("deprecation")
	public static Boolean authenticate_2(String login, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	 
    	
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	Connection conn = null;
    	
    	String expected_password = null;
    	try {
    	        conn = DriverManager.getConnection(connectionUrl,"z24", "ds4znf");
    	    } catch (java.sql.SQLException e1) {
    	        e1.printStackTrace();
    	    }
    	    try {

    	        Statement stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery("SELECT password FROM temp WHERE login = " + "'" + login + "'");
    	        
    	        
    	        if (rs.next()){
    	        		expected_password = rs.getString("password");
    	        	}
    	        
    	       
    	    } catch (java.sql.SQLException e) {
    	        e.printStackTrace();
    	    }
    	    	
    	    if (password.equals(expected_password)) {
            	
            	message = "Authentication succesful.";
            	return true;
            }
            else {
            	message = "Wrong login or password.";
            	return false;
            }
          
    	}
    
    
    
    /**
     * Creates singular database connection, checks 
     * if given login exists, then executes INSERT query.
     *	
     * @param login
     * @param password
     * @return true if succesfuly inserted data 
     */
  public static Boolean register(String login, String password) {
	  message = null;
	  try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   
      
      try (Connection con = DriverManager.getConnection(connectionUrl,"z24", "ds4znf"); Statement stmt = con.createStatement();) 
      {
      	
      	ResultSet rs;
      	
      	
      	rs = stmt.executeQuery("SELECT login FROM temp WHERE login = " + "'" + login + "'");
      	
      	if (rs.next()) {
      		message = "Login already taken.";
      		return false;
      		
      	}
      	else {
      	rs = stmt.executeQuery("INSERT INTO temp VALUES (DEFAULT, '" + login + "' , '" + password + "')");
      	rs = stmt.executeQuery("COMMIT");
      	message = "Succesfuly registered";
      	return true;
      	}
      	
          
      }
      // Handle any errors that may have occurred.
      catch (SQLException e) {
          e.printStackTrace();
      }
		return false;
	}
	
  
  	/**
  	 * Creates one time database connection. 
  	 * Retrieves data searching by login parameter.
  	 * @param login
  	 * @return resultList - first index is PrimaryKey
  	 */
  public static String[] getUserData(String login) {
	  	
	  	message = null;
	  	
		String[] resultList = new String[10];
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
    
    try (Connection con = DriverManager.getConnection(connectionUrl,"z24", "ds4znf"); Statement stmt = con.createStatement();) 
    {
    	
    	ResultSet rs;
    	rs = stmt.executeQuery("SELECT * FROM temp WHERE login = " + "'" + login + "'");
    	ResultSetMetaData rsmd = rs.getMetaData();  
    	
    	if (rs.next()) {
	
    		for (int i = 1 ; i <= rsmd.getColumnCount() ; i++) {
    			
    			resultList[i-1] = rs.getString(i);
    		
    		}  		
    	}
    	else {
    		message = "No such user in database.";
    		
    	}
    }          
    // Handle any errors that may have occurred.
    catch (SQLException e) {
        e.printStackTrace();
    }
    
    return resultList;
	}
	
	  
	
	  
}	