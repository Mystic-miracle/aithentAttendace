package attendanceServlets;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.xmlbeans.impl.regex.ParseException;

public class AppUtils {
	public static String date_maker(String strdate) throws ParseException {
		String sdate=strdate.toLowerCase();
		String year="2022";
		String dateFinal;
		boolean isSingleDigit=Pattern.matches("[0-9]-[A-Z]*[a-z]+",sdate);
		boolean isDoubleDigit=Pattern.matches("[0-9][0-9]-[A-Z]*[a-z]+",sdate);
		ArrayList<String> monthsList=new ArrayList<String>();
		monthsList.add("buffer"); //so that months corresponding to actual month number and not index
		monthsList.add("jan");
		monthsList.add("feb");
		monthsList.add("mar");
		monthsList.add("apr");
		monthsList.add("may");
		monthsList.add("jun");
		monthsList.add("jul");
		monthsList.add("aug");
		monthsList.add("sep");
		monthsList.add("oct");
		monthsList.add("nov");
		monthsList.add("dec");
		String date;
		int month=0;
		if (isSingleDigit==true) {
			date="0"+sdate.substring(0,1);
			
			month+=monthsList.indexOf(sdate.substring(2,5).toLowerCase());
			if (month<10){
				dateFinal=year+"-"+"0"+month+"-"+date;
				
				
			}
			else{
				dateFinal=year+"-"+month+"-"+date;
			   
			}
		}
		else {
			date=sdate.substring(0,2);
			month+=monthsList.indexOf(sdate.substring(3,6).toLowerCase());
			if (month<10){
				dateFinal=year+"-"+"0"+month+"-"+date;
				
				
			}
			else{
				dateFinal=year+"-"+month+"-"+date;
			   
			}

		}
		

		
		
		
		
		
		return dateFinal;
		


		
		
		
		
		
		
	}
	public static int loadtoMySQL(Row currentrow, Statement st, int temp, Sheet sheet,int fk_emp) throws SQLException{
	
	String daytype;
	Cell cell=currentrow.getCell(temp, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	String cellVal=cell.getStringCellValue();
	if (cellVal.equalsIgnoreCase("F")) {
		daytype="Full Day";							
	}
	else if (cellVal.equalsIgnoreCase("L")) {
		daytype="Leave";						
	}
	else if (cellVal.equalsIgnoreCase("H")){
		daytype="Half Day";
	}
	else if (cell.getStringCellValue().equals("")){
		daytype="Blank";
	}
	else {
		daytype="Invalid";
	}

	Cell dateCell=sheet.getRow(0).getCell(cell.getColumnIndex());
	String datecellVal=dateCell.getStringCellValue();
	System.out.println(datecellVal);
	String date=AppUtils.date_maker(datecellVal);
	st.execute("insert into employeeAttendance (FK_emp,date,day_type) values (\""+fk_emp+"\",\""+date+"\",\""+daytype+"\")");
	temp++;
	return temp;
	} 

}
