<%@page import="java.sql.PreparedStatement"%>
<%@page import="sopo.cn.utils191007.DBUtils"%>
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
	books.setName(request.getParameter("b_name"));
	books.setPrice(Double.parseDouble(request.getParameter("price")));
	books.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
	books.setDate( new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")).getTime()));
	
	out.println(books.toString());

	//获取数据库句柄
	Connection connection = DBUtils.getInstance().getConnectionViaDM();
	//构建preparedStatement，sql语句
	String sql = "INSERT INTO book VALUES( ?, ?, ?, ?, ?);";
	//
	PreparedStatement preparedStatement = null;
	int count;
	////
	
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, books.getId());
		preparedStatement.setString(2, books.getName());
		preparedStatement.setDouble(3, books.getPrice());
		preparedStatement.setInt(4, books.getAuthorId());
		preparedStatement.setDate(5, books.getDate());
		count = preparedStatement.executeUpdate();
		out.print("成功记录"+ count + "则信息");
	} catch( Exception e){
		e.printStackTrace();	
	} finally{
		DBUtils.close2param(connection, preparedStatement);
	}
%>
</body>
</html>