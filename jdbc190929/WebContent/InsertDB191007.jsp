<%@page import="java.sql.Statement"%>
<%@page import="sopo.cn.utils191007.DBUtils"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	String id = request.getParameter("id");
	String bookname = request.getParameter("bookname");
	
	/* 
		运用try catch改进此部分
		if( id != null && !("".equals("id")) && bookname != null && !("".equals("bookname"))){
		Connection connection = DBUtils.getInstance().getConnection();
		String sql = "INSERT INTO book VALUES( '"+id+"', '"+bookname+"', '66.1', '698114', '2020-1-1');";
		Statement statement = connection.createStatement();
		statement.execute(sql);
		
		statement.close();
		connection.close();
		
		out.print("insert success!");
	} */
		
	Connection connection = null;
	Statement statement = null;
	
	try{
		connection = DBUtils.getInstance().getConnection();
		String sql = "INSERT INTO book VALUES( '"+id+"', '"+bookname+"', '62.1', '6657814', '2025-1-1');";
		statement = connection.createStatement();
		statement.executeUpdate(sql);
	} catch( Exception e){
		e.printStackTrace();
	} finally{
		if( statement != null){
			statement.close();
		}
		if( connection != null){
			connection.close();
		}
		
		out.print("insert success!");
	}
%>

</body>
</html>