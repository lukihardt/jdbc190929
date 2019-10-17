<%@page import="sopo.cn.utils191007.MyDBUtils"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="sopo.cn.model191008.Books"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>readBookInfo.jsp</title>
</head>
<body> 
<table>

	<%
		Books books = new Books();
			Connection connection = null;
			ResultSet rs = null;
			Statement statement = null;
			
			try{
		connection = MyDBUtils.getInstance().getConnectionViaDM();
		String sql = "SELECT * FROM book WHERE id = 222181;";
		statement = connection.createStatement();
		rs = statement.executeQuery(sql);
		
		while( rs.next()) {
			books.setId(rs.getInt(1));
			books.setB_name(rs.getString(2));
			books.setB_price(rs.getDouble(3));
			books.setAuthor_id(rs.getInt(4));
			books.setPublish_date(rs.getDate(5));
		}
			} catch( Exception e) {
		e.printStackTrace();
			} finally{
		MyDBUtils.close3param(connection, statement, rs);
		out.print(books);
			}
	%>
	
	<tr>
		<td>show better:</td>
	</tr>
	<tr>
		<td>id</td><td>b_name</td><td>b_price</td><td>author_id</td><td>publish_date</td>
	</tr>
	<tr>
		<td><%= books.getId() %></td><td><%= books.getB_name() %></td><td><%= books.getB_price() %></td><td><%= books.getAuthor_id() %></td><td><%= books.getPublish_date() %></td>
	</tr>
</table>
</body>
</html>