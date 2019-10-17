<%@page import="java.sql.Statement"%>
<%@page import="sopo.cn.utils191007.MyDBUtils"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>deleteViaStatement.jsp</title>
</head>
<body>
<%
	Connection connection = null;
	Statement statement = null;
	
	try{
		connection = MyDBUtils.getInstance().getConnectionViaDM();
		String sql = "DELETE FROM book WHERE id = 213452;";
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
		
		out.print("执行完毕.");
	}
%>
</body>
</html>