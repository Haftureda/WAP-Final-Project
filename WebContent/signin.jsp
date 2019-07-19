
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="css/style.css" type="text/css" rel="stylesheet">
<link href="css/add-form-style.css" type="text/css" rel="stylesheet">
 <script src="js/validform.js" type ="text/javascript"></script>
</head>
<body>
<div id="wrapper">
  <div id ="header">
  <h3> MUM REGISTRAR</h3>
  </div>
</div>
<div id="container">
<h3>Sign In Form</h3>
<form name="RegForm" action="LoginControllerServlet" method="GET" onsubmit="return validform()">
<input type="hidden" name="command" value="ADD">
<table>
   <tbody>
      
       <tr>
      <td> <label>Email</label> </td>
      <td><input type ="text" name ="email"></td>
      </tr>
       <tr>
      <td> <label>Password</label> </td>
      <td><input type ="password" name ="password"></td>
      </tr>
       
       
             
        <tr>
      <td> <label></label> </td>
      <td><input type ="submit" name ="submit" value="Sign in" class="save"></td>
      </tr>      
   </tbody>   
</table>  
  <div class="margin-top20 text-center"> Don't have an account? <a href="signup.jsp">Create an account</a></div>
</form>
</div>
</body>
</html>