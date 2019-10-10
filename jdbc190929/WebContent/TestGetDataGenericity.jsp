<%@page import="sopo.cn.model191008.Books"%>
<%@page import="sopo.cn.utils191007.DBUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>TestGetDataGenericity.jsp</title>
</head>
<body>
	<%
		String sql = "SELECT * FROM book WHERE id = ?;";
		Books book = DBUtils.getData(Books.class, sql, 222181);
		out.print(book);
	%>
</body>
</html>