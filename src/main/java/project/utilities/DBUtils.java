package project.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;

public class DBUtils extends PageObject{
	
	static Connection conn = null;
	
	EnvironmentVariables environmentVariables;
	
	 public void closeConnection(){
	    	System.out.println("Closing DB Connection .....");
	    	try {
	    		if(conn != null)
	    			conn.close();
	    		System.out.println("DB Connection closed successfully.");
	    		}
	    	catch(SQLException e)  { 
	    			e.getMessage();
	    			}
	    	finally{
	        	   conn = null;
	           }
	    }
	 public Connection createDBConnection(String connectionUrl,String userName,String password) throws SQLException{
	    	System.out.println("Establising DB Connection....");
	        try {
	        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            conn = DriverManager.getConnection(connectionUrl,userName,password);
	               
	            String msg = (conn!=null) ? "Connected to the Database.. " : "Database connection failed ";
	            System.out.println(msg+" with connection string : "+connectionUrl);
	        }catch (SQLException | ClassNotFoundException e) {
	        	closeConnection();
	            throw new SQLException("Unble to open connection , Reason=> "+e.getMessage());
	        }
	        String s = "";
	        char[] chars = s.toCharArray();
	        int i =chars.length;
	        return conn;
	 }
	
	public Connection createDBConnection() throws SQLException{
		try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	String conString = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ConnectionString");		
			String user = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("dbusername");
			String pass = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("dbpassword");
            conn = DriverManager.getConnection(conString,user,pass);
               
            String msg = (conn!=null) ? "Connected to the Database.. " : "Database connection failed ";
            System.out.println(msg+" with connection string : "+conString);
        }catch (SQLException | ClassNotFoundException e) {
        	closeConnection();
            throw new SQLException("Unble to open connection , Reason=> "+e.getMessage());
        }
        return conn;
	}
	
	 public String executeSQLQuery(String sqlQuery,int columnIndex) throws SQLException{
	    String queryResult = null;
	    try {    		
	    	conn = createDBConnection();
	        Statement statement = conn.createStatement();                     
	        ResultSet result = statement.executeQuery(sqlQuery); 
	        System.out.println("Executed SQL Query : "+ sqlQuery);
	        while(result.next()){
	             queryResult = result.getString(columnIndex).toString();
	        }
	        System.out.println("DB Result: "+queryResult);
	        closeConnection();   
	    }
	   	catch (SQLException | IllegalArgumentException | SecurityException e) {
	         e.printStackTrace();
	         closeConnection();
	         throw new SQLException(e.getMessage());
	    }	
	    return queryResult; 
	 }
	 
	 public String executeSQLQuery_GetValue_By_ColumnName(String sqlQuery,String columnName) throws SQLException{
		    String queryResult = null;
		    try { 
		    	conn = createDBConnection();
		        Statement statement = conn.createStatement();                     
		        ResultSet result = statement.executeQuery(sqlQuery); 
		        System.out.println("Executed SQL Query : "+ sqlQuery);
		        ResultSetMetaData metaData = result.getMetaData();		        
		        int columnsNumber = metaData.getColumnCount();
		        while(result.next()){
		        	for (int i = 1; i <= columnsNumber; i++){
		        		String _columnName = metaData.getColumnName(i); 
		        		if(_columnName.equals(columnName)){
		        			queryResult=result.getString(i);
		        			System.out.println("DB Result: "+_columnName+"-"+queryResult);
		        			break;
		        		}
		        	}
		        }
		        closeConnection();   
		    }
		   	catch (SQLException | IllegalArgumentException | SecurityException e) {
		         e.printStackTrace();
		         closeConnection();
		         throw new SQLException(e.getMessage());
		    }	
		    return queryResult; 
	 }
	 
	 public List<String> executeSQLQuery_Get_Entire_ColumnData(String sqlQuery,String columnName) throws SQLException{
		 	List<String> list = new ArrayList<String>();
		    try {  
		    	conn = this.createDBConnection();
		        Statement statement = conn.createStatement();                     
		        ResultSet rs = statement.executeQuery(sqlQuery); 
		        System.out.println("Executed SQL Query : "+ sqlQuery);
		        ResultSetMetaData metaData = rs.getMetaData();		        
		        int columns = metaData.getColumnCount();		        
		        while (rs.next()) {
		            for (int i = 1; i <= columns; ++i) {
		            	if(metaData.getColumnName(i).equals(columnName)){
		            		list.add(rs.getObject(i).toString());break;
		            	}
		            }	
		        }
		        closeConnection();   
		    }
		   	catch (SQLException | IllegalArgumentException | SecurityException e) {
		         e.printStackTrace();
		         closeConnection();
		         throw new SQLException(e.getMessage());
		    }	
		    return list; 
	 }
	 
	 public Map<String, List<String>> executeSQLQuery_GetMap(String sqlQuery) throws SQLException{
		 	Map<String, List<String>> map = null;
		    try {  
		    	conn = this.createDBConnection();
		        Statement statement = conn.createStatement();                     
		        ResultSet rs = statement.executeQuery(sqlQuery); 
		        System.out.println("Executed SQL Query : "+ sqlQuery);
		        ResultSetMetaData metaData = rs.getMetaData();		        
		        int columns = metaData.getColumnCount();
		        map = new HashMap<>(columns);
		        for (int i = 1; i <= columns; ++i) 
		        	map.put(metaData.getColumnName(i), new ArrayList<>());
		        while (rs.next()) {
		            for (int i = 1; i <= columns; ++i) {
		            	map.get(metaData.getColumnName(i)).add(String.valueOf(rs.getObject(i)));
		            }
		        }
		        closeConnection();   
		    }
		   	catch (SQLException | IllegalArgumentException | SecurityException e) {
		         e.printStackTrace();
		         closeConnection();		        	 
		         throw new SQLException(e.getMessage());
		    }	
		    return map; 
	 }

}
