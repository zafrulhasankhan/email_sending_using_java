<%-- 
    Document   : input
    Created on : May 11, 2020, 11:47:06 PM
    Author     : Zafrul Hasan Nasim
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<center>
<h1>Image Uploading</h1>
<table>
<form action="./ImageUpload" method="post">
<tr><td>Id</td><td><input type="text" name="id"/></td></tr>
<tr><td>Name</td><td><input type="text" name="name"/></td></tr>
<tr><td>Image</td><td><input type="file" name="image"/></td></tr>
<tr><td colspan="2"><input type="submit" value="upload"/></td></tr>
</form>
    <a href="ImageViewerController">see the all photo</a>
</table>
</center>
</body>
</html>