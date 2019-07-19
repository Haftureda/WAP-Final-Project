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

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/HomepageControllerServlet")
public class HomepageControllerServlet extends HttpServlet {
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
		RequestDispatcher dispacher= request.getRequestDispatcher("/home.html");//send students to list.jsp page
		dispacher.forward(request, response);
	}

	
}
