package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Telling tomcat to create connection pool
	@Resource(name="jdbc/wapdb")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//   Set up the printwriter
				PrintWriter out = response.getWriter();
				response.setContentType("text/plain");
				
				//  Get a connection to the database
				Connection myConn = null;
		        Statement myStmt = null;
				ResultSet myRs = null;
				
				try {
					
					myConn = dataSource.getConnection();// dataSource==connection pool
					
					//   Create a SQL statements
					//String sql = "select * from student";
					String sql="select* from student order by id";
					myStmt = myConn.createStatement();
					
					//   Execute SQL query
					myRs = myStmt.executeQuery(sql);
					
					//   Process the result set
					
					while (myRs.next()) {
						String firstName = myRs.getString("first_name");
						String lastName = myRs.getString("last_name");						
						String course = myRs.getString("course");
						String fulName=firstName +" "+ lastName + " " +course;
						out.println(fulName );
						
						if(course=="WAP") {
							String fulName1=firstName +" "+ lastName + " " +course;
							out.println(fulName1 );
						}
						if(course=="WAA") {
							String fulName2=firstName +" "+ lastName + " " +course;
							out.println(fulName2 );
						}
						if(course=="Algorithms") {
							String fulName3=firstName +" "+ lastName + " " +course;
							out.println(fulName3 );
						}
						
						if(course=="EA") {
							String fulName4=firstName +" "+ lastName + " " +course;
							out.println(fulName4 );
						}
						
						if(course=="MWA") {
							String fulName5=firstName +" "+ lastName + " " +course;
							out.println(fulName5 );
						}
					}
				}
				catch (Exception exc) {
					exc.printStackTrace();
				}

	}

}
