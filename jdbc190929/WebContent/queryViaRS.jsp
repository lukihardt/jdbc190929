<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="sopo.cn.utils191007.MyDBUtils"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>queryViaRS.jsp</title>
</head>
<body>
<%
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	try{
		connection = MyDBUtils.getInstance().getConnection();
		String sql = "SELECT * FROM book WHERE id = 345;";
		statement = connection.createStatement();
		rs = statement.executeQuery(sql);
		
		//获取rs的结构
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		System.out.println(columnCount);
		for (int ik = 1; ik <= columnCount; ik++) {
	System.out.println(rsmd.getColumnName(ik) + "\t" + rsmd.getColumnTypeName(ik) + "\t" + rsmd.getColumnType(ik) + "\t");
	//12.JDBC驱动元数据处理
	out.print("getColumnName(int column):");out.println(rsmd.getColumnName(ik));out.print("<br>");
	out.print("getColumnCount():");out.println(rsmd.getColumnCount());out.print("<br>");
	out.print("getColumnLabel(int column):");out.println(rsmd.getColumnLabel(ik));out.print("<br>");
	out.print("getColumnTypeName(int column):");out.println(rsmd.getColumnTypeName(ik));out.print("<br>");
	out.print("getColumnDisplaySize(int column):");out.println(rsmd.getColumnDisplaySize(ik));out.print("<br>");
	out.print("isNullable(int column):");out.println(rsmd.isNullable(ik));out.print("<br>");
	out.print("isAutoIncrement(int column):");out.println(rsmd.isAutoIncrement(ik));out.print("<br>");
	out.println("====");out.print("<br>");
		}
		
		//12.JDBC驱动元数据处理
		
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
		MyDBUtils.close3param(connection, statement, rs);
	}
%>


</body>
</html>