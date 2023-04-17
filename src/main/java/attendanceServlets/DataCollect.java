package attendanceServlets;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataCollect extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	

		try {			
			//getting session -------------
			HttpSession session=request.getSession();
			String filesname=(String)session.getAttribute("filename");			
			//Excel file processing-----------------------------
			FileInputStream fis= new FileInputStream("D:\\Users\\khuha\\Desktop\\data\\"+filesname);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			int sheetnum=Integer.parseInt(request.getParameter("sheetnumber"));
			XSSFSheet sheet=wb.getSheetAt(sheetnum-1);				
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			//connection to mysql
			String url="jdbc:mysql://localhost:3306/aithent";
			String uname="root";
			String pass="mysql";			

			Connection con=DriverManager.getConnection(url,uname,pass);
			Statement st=con.createStatement();
			String dbUquery="use aithent;";
			st.execute(dbUquery);

			String tbquery="create table if not exists EmployeeDetails( Name varchar(100),PK_emp int  NOT NULL AUTO_INCREMENT, primary key(PK_emp));";
			System.out.println(tbquery);
			st.execute(tbquery);
			String tbDetQuery="create table if not exists EmployeeAttendance( FK_Emp int ,PK_attendance int NOT NULL AUTO_INCREMENT, date Date, day_type varchar(50), primary key(PK_attendance),foreign key(FK_emp) references employeedetails(PK_emp));";
			st.execute(tbDetQuery);
			//-------------------------------------------check code begins{----------------------------------
			String checkQuery="alter table EmployeeAttendance ADD Unique Index(fk_emp, Date);";
			st.execute(checkQuery);
			
			
			//---------------------------------------------check code ends }----------------------------------

			// database configured------------------	

			Iterator<Row> rows=sheet.iterator();
			int rownum=0;
			while (rows.hasNext()){

				Row currentrow=rows.next();
				if (currentrow.getRowNum()!=0){
					int numEmployees = 0;

					Cell namecell=currentrow.getCell(0);
					String name= namecell.getStringCellValue();
					String result="select count(*) from employeedetails where name=\'"+name+"\';";
					System.out.println(result);
					ResultSet rs = st.executeQuery(result);
					System.out.println(rs);
					if(rs.next()){
						numEmployees += rs.getInt(1);
						}
					
					if (numEmployees==0){
						String insertEmpData="Insert into employeedetails (Name) values(\""+name+"\");";
						System.out.println(insertEmpData);
								
						st.execute(insertEmpData);

					}
					ResultSet FK_Emp=st.executeQuery("select PK_emp from EmployeeDetails where Name=\""+name+"\";");
					int fk_emp = 0;
					if(FK_Emp.next()){
						fk_emp=FK_Emp.getInt(1);
						}
					
					Iterator <Cell> cellItr=currentrow.cellIterator();
					int temp=1;
					while (cellItr.hasNext() && temp<sheet.getRow(0).getPhysicalNumberOfCells()) {
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
						
						
						//-------------------------------------------check code begins{----------------------------------
						String verifyUnique="select count(*) from employeeattendance where fk_emp="+fk_emp+" and date=\""+date+"\";";
						ResultSet rsUnique=st.executeQuery(verifyUnique);
						int unique_check = 0;
						if(rsUnique.next()){
							unique_check=rsUnique.getInt(1);
							}
						if (unique_check>0){
							String delQuery="Delete from employeeAttendance where fk_emp="+fk_emp+" and date=\""+date+"\";";
							st.execute(delQuery);
							System.out.println("deleted entry corresponding to employee"+fk_emp+" on date "+date);
							
						}
						
						
						
						//---------------------------------------------check code ends }----------------------------------
						st.execute("insert into employeeAttendance (FK_emp,date,day_type) values (\""+fk_emp+"\",\""+date+"\",\""+daytype+"\")");
						temp++;
					}











				}
				rownum++;
			}





			wb.close();
			fis.close();
			con.close();


			//request.setAttribute("mysqlCol", mysqlCol);
			//RequestDispatcher rd=request.getRequestDispatcher("result.jsp");  
			//rd.include(request, response);  

		}
		catch(Exception e1) {

			System.out.println(e1);
		}
		
		finally {
			response.sendRedirect(request.getContextPath() +"/results.jsp");
		}

	}
	

}