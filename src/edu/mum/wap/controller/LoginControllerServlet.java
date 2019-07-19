package edu.mum.wap.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

import edu.mum.wap.DAO.AccountDOA;
import edu.mum.wap.DAO.StudentDAO;
import edu.mum.wap.model.Account;
import edu.mum.wap.model.Student;


@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountDOA accountdoa;
	@Resource(name = "jdbc/wapdb")
	private DataSource dataSource;// connection pool injection

	@Override
	public void init() throws ServletException {

		super.init();
		try {
			accountdoa = new AccountDOA(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		try {
			String myCommand=request.getParameter("command");
			if(myCommand==null) {
				myCommand="LIST";
			}
			switch(myCommand) {
			case "LIST":{
				/*
				 * String st = "Do you want to continue?"; JOptionPane.showMessageDialog(null,
				 * st);
				 */
				listStudents1(request, response);
				break;				
			  }
			
			case "ADD":{
				/*
				 * String st = "Add"; JOptionPane.showMessageDialog(null, st);
				 */
				getStudent(request, response);
				break;					
			}
			default:{				
			    listStudents(request, response);
				
			}
			
			}
		} 
		catch (Exception exc) {			
			exc.printStackTrace();
		}
	}

	private void getStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email=request.getParameter("email");
		String pw=request.getParameter("password");
		List<Account> students = AccountDOA.getStudents();
		
		Boolean tf=isAccountPresent(students,  email, pw);
		if(tf==true) {			
		listStudents( request, response);
			/*
			 * String st = "Log in successful!"; JOptionPane.showMessageDialog(null, st);
			 */
		}
		else {
			
			/*
			 * String st = "Either the email or password is wrong";
			 * JOptionPane.showMessageDialog(null, st);
			 */
			listStudents1(request, response);
		}
		
		
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Student> students = StudentDAO.getStudents();
		request.setAttribute("STUDENT_LIST", students);//add students to the request
		RequestDispatcher dispacher= request.getRequestDispatcher("/reg.jsp");//send students to list.jsp page
		dispacher.forward(request, response);
	}
	
	private void listStudents1(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//List<Account> students = AccountDOA.getStudents();
		//request.setAttribute("STUDENT_LIST", students);//add students to the request
		RequestDispatcher dispacher= request.getRequestDispatcher("/signin.jsp");//send students to list.jsp page
		dispacher.forward(request, response);
	}
	private boolean isAccountPresent(List<Account>students, String email, String pw) {
		for(Account temp:students) {
			if(email.equals(temp.getEmail())&& pw.equals(temp.getPassword())){
				return true;				
			}
		}
		return false;
	}

}
