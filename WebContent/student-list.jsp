<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" type="text/css" rel="stylesheet">

<title>Add New Student</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
</head>
<body>
<label for="searchStudent">Search</label> <input type="text" id="searchStudent"
									  placeholder="search" />
<input id="btn_search" type="submit" value="Submit" />
	<table id="tbl_students">
		<thead>
			<tr>
			    <th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Course</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="student" items="${students}" >
				<tr>
				     <td><c:out value="${student.id}" /></td>
					<td><c:out value="${student.firstName}" /></td>
					<td><c:out value="${student.lastName}" /></td>
					<td><c:out value="${student.email}" /></td>
					<td><c:out value="${student.course}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<fieldset>
		<div>
		<label for="id">ID</label> <input type="text" id="id"
				 placeholder="id" />
		  </div>
		  
		  <div>
		
			<label for="first_name">First Name</label> <input type="text" id="first_name"
				 placeholder="first name" />
		</div>
		<div>
			<label for="name">Last Name</label> <input type="text" id="last_name"
				placeholder="Last name" />
		</div>
		<div>
			<label for="email">Email</label> <input type="text" id="email"
				placeholder="email" />
		</div>
		<div>
			<label for="course">Course</label> <input type="text" id="course"
				placeholder="course" />
		</div>
		

		<div>
			<input id="btn_add" type="submit" value="Submit" />
		</div>
	</fieldset>
</body>
</html>