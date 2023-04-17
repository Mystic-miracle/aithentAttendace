package attendanceServlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Upload extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			ServletFileUpload sf=new ServletFileUpload(new DiskFileItemFactory());
			HttpSession session=request.getSession();
			
//			Part filePart = request.getPart("file");
//		    String fileName = filePart.getSubmittedFileName();
//			//String file="";
//
//			
//		    File directoryPath = new File("D:\\DataAttendance");
//		 
//		        // Creating the temporary file
//		        File tempFile = File.createTempFile(fileName,".xlsx",directoryPath);
//				File excelFile = File.createTempFile(fileName, ".xlsx");
//				filePart.write(excelFile);
			
				
				List<FileItem> multifiles = sf.parseRequest(request);
				//String file="";
				String filename;
				for(FileItem item : multifiles)

				{	
					filename=item.getName();
					
					item.write(new File("D:\\Users\\khuha\\Desktop\\data\\"+filename));
					session.setAttribute("filename", filename);

				}		

		}


				catch (Exception ex) {			

					System.out.println(ex);

				}
				finally {
					response.sendRedirect(request.getContextPath()+"/sheetnum.jsp");
					
//					 RequestDispatcher rd = request.getRequestDispatcher("DataCollect");
//				        
//				        rd.include(request, response); 
				}

			

	}
	
}	
