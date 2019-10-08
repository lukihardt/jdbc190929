<%@page import="sopo.cn.utils191007.DBUtils"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>queryViaRS.jsp</title>
</head>
<body>
<%
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	try{
		connection = DBUtils.getInstance().getConnection();
		String sql = "SELECT * FROM book WHERE id = 345;";
		statement = connection.createStatement();
		rs = statement.executeQuery(sql);
		
		while( rs.next()){
			int id = rs.getInt(1);
			String name = rs.getString("b_name");
			double price = rs.getDouble("b_price");
%>
			<%="id: " + id + ", bookname: " + name + ", price: " + price%>
<%
		}
	} catch( Exception e){
		e.printStackTrace();
	} finally{
		DBUtils.getInstance().close3param(connection, statement, rs);
	}
%>


</body>
</html>