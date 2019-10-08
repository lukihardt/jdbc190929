<%@page import="com.mysql.cj.jdbc.Driver"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- connect to database -->
	<%
		Driver driver = new Driver();
		String url = "jdbc:mysql://localhost:3306/books";
		Properties info = new Properties();
		info.put( "user", "root");
		info.put( "password", "F2NRnjVsKe");
		Connection connection = driver.connect(url, info);
	%>

	<%=connection%>
</body>
</html>