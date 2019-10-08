<%@page import="sopo.cn.utils191007.DBUtils"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>updateViaStatement.jsp</title>
</head>
<body>
<%
	Connection connection = null;
	Statement statement = null;
	
	try{ 
		connection = DBUtils.getInstance().getConnectionViaDM();
		String sql = "UPDATE book SET id = 888 WHERE id = 967976;";
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
		
		out.print("更新完毕");
	}
%>
</body>
</html>