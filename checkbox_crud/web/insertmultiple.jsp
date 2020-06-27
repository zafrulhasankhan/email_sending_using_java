<%-- 
    Document   : insertmultiple
    Created on : May 29, 2020, 6:23:32 PM
    Author     : Zafrul Hasan Nasim
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
String languages="";
String lang[]=request.getParameterValues("lang");
for(int i=0;i<lang.length;i++){
    languages+=lang[i]+",";
}

        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtuts", "root", "");
        Statement st=con.createStatement();
        int i=st.executeUpdate("insert into sports_details values('"+languages+"')");
        out.println("Data is successfully inserted into database.");
        }
        catch(Exception e){
        System.out.println(e);
        }
        %>
    </body>
</html>
