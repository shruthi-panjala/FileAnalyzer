package com.epam.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewFileServlet
 */
public class ExtraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExtraServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		response.setContentType("text/html");
		String name = request.getParameter("uploadfile");
		File file = new File(name);
		PrintWriter out = response.getWriter();
		out.println(name);
		UploadFile obj = new UploadFile();
		
		// -------------getting properties-------------------
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastmod = sdf.format(file.lastModified());
		Path path = Paths.get(name);
		BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
		FileTime cdate = attr.creationTime();
		FileTime atime = attr.lastAccessTime();
		String[] dc = cdate.toString().split("T");
		String[] access = atime.toString().split("T");
		long length = file.length();
		
//------------------------finding no.of words,paragraphs,lines...........................
		int wcount = 0;
		int pcount = 0;
		int lcount = 0, ecount = 0 ,cap=0,stop=0,atpara1=1 ,atpara2=1;
		String msg = "";
		String[] exe = file.getName().split("\\.");
		for (int i = 0; i < exe.length; i++) {
			if (exe[i].compareTo("txt") == 0 || 
					exe[i].compareTo("text") == 0) {
				// out.println("it is a text file");
				BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
				String line;

				while ((line = br.readLine()) != null) {
					lcount++;
					String[] words = line.split(" ");
					wcount += words.length;
					StringTokenizer st=new StringTokenizer(line," ");
					if(st.countTokens()==0){
						pcount++;
					}

				}
				//------------finding full stop errors----------------
				BufferedReader br1 = new BufferedReader(new FileReader(file.getAbsoluteFile()));
				String line1;
				while ((line1 = br1.readLine()) != null) {
					ecount++;
					StringTokenizer st=new StringTokenizer(line1," ");
					if(st.countTokens()==0){
						atpara1++;
					}
					String[] error = line1.split("\\. ");
					for (int j = 0; j < error.length-1; j++) {
						//System.out.println(j+1);
						char ch = error[j+1].charAt(0);

						int key = ch;
						if (!(key >=65 && key <= 90)) {
							msg += "<br>Error in paragraph "+atpara1+" at line " + ecount + " missed Capitalization after (.)";
							
						}
					}
					String[] error1 = line1.split("\\. ");
					for (int j = 0; j < error.length-1; j++) {
						//System.out.println(j+1);
						char ch = error[j+1].charAt(0);

						int key = ch;
						if (!(key >=65 && key <= 90)) {
							msg += "<br>Error in paragraph "+atpara1+" at line " + ecount + " missed Capitalization after (.)";
							
						}
					}
				}
				//---------------finding capital letter violations--------------------
				BufferedReader br2 = new BufferedReader(new FileReader(file.getAbsoluteFile()));
				String line2;
				while ((line2 = br2.readLine()) != null) {
					stop++;
					StringTokenizer st=new StringTokenizer(line2," ");
					if(st.countTokens()==0){
						atpara2++;
					}
					if(stop==1){
						int key3=line2.charAt(0);
						if(!(key3>=65&&key3<=90));
						msg+="<br>Uppercase error at first line at 'starting'";
						
					}
					
					String[] error2=line2.split(" ");
					//System.out.println(error2.length);
					for(int k=1;k<error2.length;k++){
						//System.out.println(error2[k]);
						int key2=error2[k].charAt(0);
						if(key2>64&&key2<91){
							cap++;
							int fullstop=error2[k-1].charAt(error2[k-1].length()-1);
							boolean condition=fullstop!=44 &&!(error2[k-1].compareTo("-")==0)&&!(error2[k-1].compareToIgnoreCase("the")==0)
									&&!(error2[k-1].compareToIgnoreCase("an")==0)&& !(error2[k-1].compareToIgnoreCase("a")==0);
							if(condition){
								msg+="<br> Error in paragraph "+atpara2+ " at line "+stop+" missing '.' at word '"+error2[k-1]+"' ";
								//System.out.println(error2[k-1]);
							}
						}
					}
					}
				
				//---------if ending
				obj.setMessage(msg);

			}
			else if(exe[i].compareTo("doc") == 0 || exe[i].compareTo("docx") == 0){
				obj.setMessage("plz Upload a readable text file to know more information..");
				//HWPFDocument doc=new HWPFDocument(file.getAbsoluteFile());
				//WordExtractor we=new WordExtractor();
			}
			else {
				obj.setMessage(file.getName() + " is not a Readable File");
			}

		}

		/*obj.setFileName(file.getName());
		obj.setDateCreated(dc[0]);
		obj.setDateMod(lastmod);
		obj.setLastAccess(access[0]);
		obj.setSize(length);
		obj.setNwords(wcount);
		obj.setNlines(lcount);
		obj.setNparas(pcount);
		obj.setCapitalwords(cap++);*/

		request.setAttribute("myfile", obj);
		 // JDBC driver name and database URL
	       final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	       final String DB_URL="jdbc:mysql://localhost/TEST";

	      //  Database credentials
 final String USER = "root";
	      final String PASS = "password";
	      try{
	          // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	          Statement stmt = conn.createStatement();
	          String sql;
	          sql = "insert into files values("+file.getName()+","+length+","+lcount+","+pcount+","+wcount+")";
	          
	          ResultSet rs = stmt.executeQuery(sql);
	      // Set response content type
	      response.setContentType("text/html");
		RequestDispatcher dispatch = request.getRequestDispatcher("final.jsp");
		dispatch.forward(request, response);
	      }
	      catch(SQLException se){
	          //Handle errors for JDBC
	          se.printStackTrace();
	       }catch(Exception e){
	          //Handle errors for Class.forName
	          e.printStackTrace();
	       }finally{
	          //finally block used to close resources
	          /*try{
	             if(stmt!=null)
	                stmt.close();
	          }catch(SQLException se2){
	          }// nothing we can do
	          try{
	             if(conn!=null)
	             conn.close();
	          }catch(SQLException se){
	             se.printStackTrace();
	          }//end finally try
*/	       } //end try
	}

}
