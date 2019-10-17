<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="sopo.cn.utils191007.MyDBUtils"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="sopo.cn.model191008.Books"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>acceptBookInfo.jsp</title>
</head>
<body>
<%
	Books books = new Books();
	books.setId(Integer.parseInt(request.getParameter("id")));
	books.setB_name(request.getParameter("b_name"));
	books.setB_price(Double.parseDouble(request.getParameter("price")));
	books.setAuthor_id(Integer.parseInt(request.getParameter("authorId")));
	books.setPublish_date( new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")).getTime()));
	
	out.println(books.toString());

	//获取数据库句柄
	Connection connection = MyDBUtils.getInstance().getConnectionViaDM();
	//构建preparedStatement，sql语句
	String sql = "INSERT INTO book VALUES( ?, ?, ?, ?, ?);";
	//
	PreparedStatement preparedStatement = null;
	int count;
	////
	
	
	try{
		preparedStatement = connection.prepareStatement( sql);
		preparedStatement.setInt(1, books.getId());
		preparedStatement.setString(2, books.getB_name());
		preparedStatement.setDouble(3, books.getB_price());
		preparedStatement.setInt(4, books.getAuthor_id());
		preparedStatement.setDate(5, books.getPublish_date());
		count = preparedStatement.executeUpdate();
		
		out.print("成功记录"+ count + "则信息");
	} catch( Exception e){
		e.printStackTrace();	
	} finally{
		MyDBUtils.close2param(connection, preparedStatement);
	}
%>
</body>
</html>