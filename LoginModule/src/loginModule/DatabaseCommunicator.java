package loginModule;
//package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseCommunicator {
	
	private static String connectionUrl = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	
	
	
	
	
	
	
	
	/**
	 * This function creates one time connection
	 * to database and returns password for given login 
	 * (scrollable result of a query) or returns NULL
	 * @param username
	 * @return
	 */
	public static String getPasswd(String login) {
        
		String password = null;
	        
		
        try (Connection con = DriverManager.getConnection(connectionUrl,"z24", "ds4znf"); Statement stmt = con.createStatement();) 
        {
            
        	ResultSet rs = stmt.executeQuery("SELECT password FROM temp WHERE login = " + "'" + login + "'");
           
        	if(rs.next()){
        		password = rs.getString("password");
        	}
            
            
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
		
		return password;
		
    }
	
	
	public static Boolean register(String login, String password){
		
        
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
        		
        		return false;
        	}
        	else {
        	rs = stmt.executeQuery("INSERT INTO temp VALUES (DEFAULT, '" + login + "' , '" + password + "')");
        	rs = stmt.executeQuery("COMMIT");
        	
        	return true;
        	}
        	
            
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	//
//	/**
//	 * This function creates one time connection
//	 * to database and returns type ResultSet 
//	 * (scrollable result of a query) or returns NULL
//	 * @param query
//	 * @return
//	 */
//	public static ResultSet queryDatabase(String query) {
//        
//		String connectionUrl = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
//        ResultSet rs = null;
//        String password;
//        
//        try {
//			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//     
//        
//        try (Connection con = DriverManager.getConnection(connectionUrl,"z24", "ds4znf"); Statement stmt = con.createStatement();) 
//        {
//            
//            rs = stmt.executeQuery(query);
////            while (rs.next()) {
////                System.out.println(rs.getString("name"));
//            
//            con.close();
//           //System.out.println("Connection closed");
//           //return rs;
//        }
//        // Handle any errors that may have occurred.
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//		System.out.println("koniec");
//		
//		
//        return rs;
//		
//    }
//	
//	
//		/**
//		 * Calls queryDatabase with predefined query. 
//		 * @param username
//		 * @return returns password in String type.
//		 */
//	public static String getPasswd(String username) {
//		//ResultSet rs = queryDatabase("SELECT haslo FROM dane_czlonka WHERE login =" + username);
//		ResultSet rs = queryDatabase("SELECT password FROM temp_table WHERE login = " + "'" + username + "'");
//		String password;
//		
//		try {
//			rs.next();
//			password = rs.getString("password");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		return password;
//	}
//}
