package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import edu.mum.wap.DAO.StudentDAO;
import edu.mum.wap.model.Student;

/**
 * Servlet implementation class ProductController
 */
@WebServlet({ "/student", "" })
public class AjaxStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson mapper = new Gson();
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
			request.setAttribute("students", StudentDAO.getStudents());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("student-list.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonSting = request.getParameter("student");
		Student student = mapper.fromJson(jsonSting, Student.class);
	

		try {
			
			StudentDAO.addStudent(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();
        System.out.println(student.getFirstName()+" " + student.getLastName());
		out.print(mapper.toJson(student));
	}

}
