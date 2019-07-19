package edu.mum.wap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.mum.wap.model.Student;

public class StudentDAO {
	private static DataSource dataSource;

	public StudentDAO() {
		
	}

public StudentDAO(DataSource ds) {
		this.dataSource=ds;
	}
	public static List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		Connection myConn = null;
        Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();// dataSource==connection pool
			myStmt = myConn.createStatement();
			String sql = "select* from student order by last_name";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				Integer id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String course = myRs.getString("course");

				Student newStudent = new Student(id, firstName, lastName, email,course);
				students.add(newStudent);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);

		}
		return students;
	}
	
	public static Student getStudent(Integer theid) throws Exception {
		List<Student> students = new ArrayList<>();
		Student student=null;
		Connection myConn = null;
        Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();// dataSource==connection pool
			myStmt = myConn.createStatement();
			String sql = "select* from student order by last_name";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				Integer id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String course = myRs.getString("course");

				Student newStudent = new Student(id, firstName, lastName, email,course);
				students.add(newStudent);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);

		}
		for(Student s:students) {
			
			if(s.getId()==theid) {
	        student=s;
			break;
				
			}
		}
		return student;
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws Exception {
		if (myConn != null) {
			myConn.close();// the connection is back to the pool(available) for the next usage
		}
		if (myStmt != null) {
			myStmt.close();
		}
		if (myRs != null) {
			myRs.close();
		}

	}

	public static void addStudent(Student newStudent) throws Exception {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		try {
		myConn = dataSource.getConnection();
		String sql="insert into student"
		           + "(first_name, last_name, email, course)"
				   + "values(?,?,?,?)";
		myStmt=myConn.prepareStatement(sql);
		myStmt.setString(1, newStudent.getFirstName());
		myStmt.setString(2, newStudent.getLastName());
		myStmt.setString(3, newStudent.getEmail());
		myStmt.setString(4, newStudent.getCourse());
		myStmt.execute();// insert into the table

		}
		catch(Exception exc) {
			exc.getStackTrace();
		}
		finally {
		close(myConn,myStmt,null);	
		}
	}
}
