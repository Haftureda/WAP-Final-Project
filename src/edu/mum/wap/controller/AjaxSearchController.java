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
@WebServlet({"/search"  })
public class AjaxSearchController extends HttpServlet {
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
	
	 
			 

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer jsonInt = Integer.parseInt(request.getParameter("searchStudent"));
		System.out.println( jsonInt);

	    //    Product product = mapper.fromJson(request.getParameter("product"), Product.class);
	        Student student=new Student();
	        try {
				student=  StudentDAO.getStudent(jsonInt);
				System.out.println(student.getFirstName()+" " + student.getLastName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       // dao.addProduct(product);

	        PrintWriter out = response.getWriter();

	        out.print(mapper.toJson(student));
}
}
