<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "wapdb";
String userid = "wap-sys";
String password = "wap123";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
try{
String id=request.getParameter("id");
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql="select* from student order by id";
resultSet = statement.executeQuery(sql);
int i=0;
while(resultSet.next()){
String firstName=resultSet.getString("first_name");
String lastName=resultSet.getString("last_name");
String email=resultSet.getString("email");
String course=resultSet.getString("course");
String fulName=firstName +" "+ lastName + " " +course;
out.println(fulName );

}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>