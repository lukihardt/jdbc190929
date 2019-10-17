<%@page import="sopo.cn.utils191007.MyDBUtils"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>transactionLevel.jsp</title>
</head>
<body>
<%
	Connection connection = MyDBUtils.getInstance().getConnection();
%>
TransactionLevel:<%=connection.getTransactionIsolation() %>
</body>
</html>