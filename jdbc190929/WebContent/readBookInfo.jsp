<%@page import="sopo.cn.utils191007.DBUtils"%>
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
			connection = DBUtils.getInstance().getConnectionViaDM();
			String sql = "SELECT * FROM book WHERE id = 222181;";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			
			while( rs.next()) {
				books.setId(rs.getInt(1));
				books.setName(rs.getString(2));
				books.setPrice(rs.getDouble(3));
				books.setAuthorId(rs.getInt(4));
				books.setDate(rs.getDate(5));
			}
		} catch( Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.close3param(connection, statement, rs);
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
		<td><%= books.getId() %></td><td><%= books.getName() %></td><td><%= books.getPrice() %></td><td><%= books.getAuthorId() %></td><td><%= books.getDate() %></td>
	</tr>
</table>
</body>
</html>