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

import edu.mum.wap.DAO.AccountDOA;
import edu.mum.wap.model.Account;


@WebServlet("/SignupControllerServlet")
public class SignupControllerServlet extends HttpServlet {
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
				signup(request, response);
				break;				
			  }
			
			case "ADD":{
				
				checkStudent(request, response);			
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

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String pw=request.getParameter("password");
		Account newStudent=new Account(firstName,lastName,email,pw);
		AccountDOA.addStudent(newStudent);
		listStudents(request, response);
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Account> students = AccountDOA.getStudents();
		request.setAttribute("STUDENT_LIST", students);//add students to the request
		RequestDispatcher dispacher= request.getRequestDispatcher("/reg2.jsp");//send students to list.jsp page
		dispacher.forward(request, response);
	}
	private void signup(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Account> students = AccountDOA.getStudents();
		request.setAttribute("STUDENT_LIST", students);//add students to the request
		RequestDispatcher dispacher= request.getRequestDispatcher("/add-form.jsp");//send students to list.jsp page
		dispacher.forward(request, response);
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		RequestDispatcher dispacher= request.getRequestDispatcher("/signin.jsp");//send students to list.jsp page
		dispacher.forward(request, response);
	}
	
	private boolean isAccountPresent(List<Account>students, String email, String pw) {
		for(Account temp:students) {
			if(email.equals(temp.getEmail())){
				return true;				
			}
		}
		return false;
	}
	
private void checkStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email=request.getParameter("email");
		String pw=request.getParameter("password");
		List<Account> students = AccountDOA.getStudents();
		
		Boolean tf=isAccountPresent(students,  email, pw);
		if(tf==true) {			
	    System.out.println("User already exists");
	    signin(request, response);
			
		}
		else {		
			
			 addStudent(request, response);
		}
		
		
		
	}

}
