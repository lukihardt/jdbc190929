<%@page import="java.sql.DatabaseMetaData"%>
<%@page import="com.mysql.cj.jdbc.Driver"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>index.jsp</title>
</head>
<body>
	<!-- connect to database -->
	<%
		Driver driver = new Driver();
		String url = "jdbc:mysql://localhost:3306/books?serverTimezone=Asia/Shanghai";
		Properties info = new Properties();
		info.put( "user", "root");
		info.put( "password", "F2NRnjVsKe");
		Connection connection = driver.connect(url, info);
	%>

	<%=connection%><br>
	
	<%
		DatabaseMetaData dbmd = connection.getMetaData();
	%>
	dbmd.getURL()<%= dbmd.getURL() %><br>
	dbmd.getUserName()<%= dbmd.getUserName()%><br>
	dbmd.isReadOnly()<%= dbmd.isReadOnly() %><br>
	dbmd.getDatabaseProductName()<%= dbmd.getDatabaseProductName() %><br>
	dbmd.getDatabaseProductVersion()<%= dbmd.getDatabaseProductVersion() %><br>
	dbmd.getDriverName()<%= dbmd.getDriverName() %><br>
	dbmd.getDriverVersion()<%= dbmd.getDriverVersion() %><br>
	
</body>
</html>