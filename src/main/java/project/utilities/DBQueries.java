package project.utilities;



public class DBQueries {
		
	public static String getUserName(String userName){				
		String SQuery = "SELECT UserName,FirstName,LastName FROM Retrieval_Management.pmt.users WHERE UserName='"+userName+"'";
		return SQuery;
	}
	
	public static String getNewlyAddedProject(String projectId){
		String SQuery = "SELECT * FROM  Retrieval_Management.dbo.Projects WHERE ProjectID='"+projectId+"'";
		return SQuery;
	}
	
	public static String getRandomMasterAccountID(){
		return "SELECT TOP 100 concat(requestor_id,' - ',requestor_name) AS MasterAccountName FROM stage_oracle.dbo.Requestors WHERE requestor_id in (select SUPERACCOUNT from stage_oracle.dbo.superToAccount )order by 1 asc";		
	}
	
	public static String getProectNames(){
		return "SELECT * FROM Retrieval_Management.pmt.applications";
	}
	
	public static String getCustomers(String clientID){
		return "SELECT * FROM Retrieval_Management.dbo.customers WHERE ClientID='"+clientID+"'";
	}
	
	public static String getUserNameByID(String userID){
		return "SELECT LastName+','+FirstName AS FullName FROM Retrieval_Management.pmt.users WHERE UserID='"+userID+"'";
	}
	
	public static String getUserIDByUserName(String userName){
		return "SELECT * FROM Retrieval_Management.pmt.users WHERE UserName='"+userName+"'";
	}
	
	public static String getApplicationUserMappingBYUserID(String userID){
		return "SELECT * FROM Retrieval_Management.pmt.applicationusermapping WHERE UserID='"+userID+"'";
	}
	
	public static String getClients(){
		return "SELECT concat(clientid,' - ',name) AS ClientName FROM Retrieval_Management.dbo.clients";
	}
	
	public static String getCustomer(String customerID){
		return "SELECT * FROM Retrieval_Management.dbo.Customers WHERE CustomerID='"+customerID+"'";
	}
	
	public static String getCustomerHistory (String customerID,String action){
		return "SELECT * FROM Retrieval_Management.pmt.customerhistory WHERE CustomerID ='"+customerID+"'  AND ActionName='"+action+"'";
	}

	public static String getExistingCustomerName(String clientID) {		
		return "SELECT TOP 1 * FROM Retrieval_Management.dbo.customers WHERE ClientID='"+clientID+"' ORDER BY CreationDT ASC" ;
	}
	
	public static String getRandomClientName(){
		return "SELECT concat(ClientID,' - ',NAME) AS ClientName FROM Retrieval_Management.dbo.Clients WHERE ClientID = (SELECT TOP 1 ClientID FROM Retrieval_Management.dbo.Customers GROUP BY ClientID HAVING COUNT(Name)>1)";
	}
	
	public static String formQueryBy(String table,String allConditions){
		String query = 	"SELECT * FROM Retrieval_Management."+table+" WHERE ";
		String[] conditions = allConditions.split(",");
		for(int i=0;i<conditions.length;i++){
			String whereCondition = "";
			String specCondition = "";
			if(conditions.length>1 && i!=0)
				query+=" AND ";
			// Split Column and Value from each condition
			String[] c = conditions[i].split(":");
			if(c[1].contains("$")){
				// IF condition have specific condition like order by 
				String[] spec = (c[1].toString()).split("\\$");
				specCondition = " "+spec[1];
				String serenityVal = SeleniumUtils.getValueByName(spec[0]);
				whereCondition = serenityVal==null?spec[0]:serenityVal;
			}
			else
				whereCondition = SeleniumUtils.getValueByName(c[1])==null?c[1]:SeleniumUtils.getValueByName(c[1]);
			
			query+=c[0]+"='"+whereCondition+"'"+specCondition;
		}
		return query;
	}
}
