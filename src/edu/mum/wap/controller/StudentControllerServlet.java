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

import edu.mum.wap.DAO.StudentDAO;
import edu.mum.wap.model.Student;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDAO studentdoa;
	@Resource(name = "jdbc/wapdb")
	private DataSource dataSource;// connection pool injection

	@Override
	public void init() throws ServletException {

		super.init();
		try {
			studentdoa = new StudentDAO(dataSource);
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
				listStudents(request, response);
				break;				
			  }
			
			case "ADD":{
				addStudent(request, response);
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
		String course=request.getParameter("course");
		Student newStudent=new Student(firstName,lastName,email, course);
		StudentDAO.addStudent(newStudent);
		listStudents(request, response);
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Student> students = StudentDAO.getStudents();
		request.setAttribute("STUDENT_LIST", students);//add students to the request
		RequestDispatcher dispacher= request.getRequestDispatcher("/list.jsp");//send students to list.jsp page
		dispacher.forward(request, response);
	}

}
