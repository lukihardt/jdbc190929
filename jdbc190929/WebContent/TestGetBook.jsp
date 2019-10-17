<%@page import="sopo.cn.model191008.Books"%>
<%@page import="sopo.cn.utils191007.MyDBUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>TestGetBook.jsp</title>
</head>
<body>
	<%
		String sql = "SELECT * FROM book WHERE id = ?;";
			Books book = MyDBUtils.getBooks(sql, 222181);
			out.print(book);
	%>
</body>
</html>