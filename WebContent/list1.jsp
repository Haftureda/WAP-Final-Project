<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>List of Students</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<%
//List<Student> students=(List<Student>) request.getAttribute("STUDENT_LIST");
%>
<body>
<div id="wrapper">
  <div id ="header">
  <h3> MUM REGISTRAR</h3>
  </div>
</div>
<div id="container">
  <div id="content">
 
     <table>
       <tr> 
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        
      </tr>
    <c:forEach var ="temps" items="${STUDENT_LIST }">
      <tr>
      <td>${temps.firstName} </td>
       <td>${temps.lastName} </td>
        <td>${temps.email} </td>
          
      </tr>
     </c:forEach>
     </table>
   <p>
 <a href="home.html">Back to home</a>
  </p>
  </div>
</div>
</body>
</html>