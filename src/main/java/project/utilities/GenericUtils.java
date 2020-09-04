package project.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Assert;

import com.github.javafaker.Faker;

public class GenericUtils {

		
	
	public static Faker faker(){
		return  new Faker(new Locale("en-IND"));
	}
	
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
			
		}catch (NumberFormatException ex) {
			return false;
		}
	}
	
	public static String randomString(int size){
		RandomStringGenerator randomStringGenerator =
		        new RandomStringGenerator.Builder()
		                .withinRange('0', 'z')
		                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
		                .build();
		return randomStringGenerator.generate(size).toString();
	}
	public static int getRandomNumberBetweenRange(int min,int max){
		Random random = new Random();
		int randomNum = random.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static String Add_days_to_given_date(String Date,int dayscount_to_add)
	{
		String IncreasedDate="";
		try{
			//String untildate="2011-10-08";//can take any date in current format    
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy" );   
			Calendar cal = Calendar.getInstance();    
			cal.setTime( dateFormat.parse(Date));    
			cal.add( Calendar.DATE, dayscount_to_add );    
			String convertedDate=dateFormat.format(cal.getTime());    
			System.out.println("Changed Date is => "+convertedDate);
			IncreasedDate=convertedDate;
		}catch(Exception e){
			
		}		
		return IncreasedDate;		
	}
	
	public static String get_Current_Date(String format)
	{		
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		String TodaysDate=dateFormat.format(date);
		//System.out.println("Today's Date:"+TodaysDate); //2016/11/16 		
		return TodaysDate;		
	}
	
	public static String convertDateToUIFormat(String sDate){
		String[] sDateSplit = sDate.split("-");
		String[] sDateArray = {sDateSplit[1],sDateSplit[2],sDateSplit[0]};
		String sDF = StringUtils.join(sDateArray, "/");
		return sDF;
	}
	
	public static String getNumberByMonth(String sMonth){
		Map<String,String> months = new HashMap<String,String>();
		months.put("JAN", "01");
		months.put("FEB", "02");
		months.put("MAR", "03");
		months.put("APR", "04");
		months.put("MAY", "05");
		months.put("JUN", "06");
		months.put("JUL", "07");
		months.put("AUG", "08");
		months.put("SEP", "09");
		months.put("NOV", "11");
		months.put("DEC", "12");
		return months.get(sMonth);	
	}
	
	public static String[] getAscendingOrder(String[] str,String name){
		String temp;
		String sr = "<><><><><><><><><><><><> Ascending Order :"+name+" <><><><><><><><><><><><>";
		printByCount(sr.length(),'-');
		System.out.println("\n"+sr);
		printByCount(sr.length(),'-');
		System.out.println("");
		
		System.out.println("Before Sort:"+Arrays.toString(str));
		for (int j = 0; j < str.length; j++){
			for (int i = j + 1; i < str.length; i++){
				if (str[i].compareTo(str[j]) < 0){
					temp = str[j];
					str[j] = str[i];
					str[i] = temp;
				}
			}
		}
		System.out.println(" After Sort:"+Arrays.toString(str));
		return str;			
	}
	
	public static String[] descendingOrder(String[] str,String name){
		String temp;
		String sr = "<><><><><><><><><><><><> Descending Order :"+name+" <><><><><><><><><><><><>";
		printByCount(sr.length(),'-');
		System.out.println("\n"+sr);
		printByCount(sr.length(),'-');
		System.out.println("");
		
		System.out.println("Before Sort:"+Arrays.toString(str));
		for (int j = 0; j < str.length; j++){
			for (int i = j + 1; i < str.length; i++){
				if (str[i].compareTo(str[j]) > 0){
					temp = str[j];
					str[j] = str[i];
					str[i] = temp;
				}
			}
		}
		System.out.println(" After Sort:"+Arrays.toString(str));
		return str;
	}
	
	public static void compareArrays(String[] str1,String[] str2){
		Assert.assertTrue("Arrays count Not Mached, length' are"+str1.length+","+str2.length,str1.length==str2.length);
		for(int i=0;i<str1.length;i++){
			String firstStr= str1[i].toString();
			String secondStr = str2[i].toString();
			if(firstStr.length()!=secondStr.length())
				Assert.fail(firstStr+" != "+secondStr);
			if(!firstStr.equals(secondStr))
				Assert.fail(firstStr+" != "+secondStr);
			//Assert.assertTrue("Value not matched, "+str1[i]+"!="+str2[i],str1[i]==str2[i]);
		}
		
	}
	
	public static void printByCount(int count,char c){
		for(int i=0;i<count;i++){
			System.out.print(String.valueOf(c));
		}
	}
	
}
