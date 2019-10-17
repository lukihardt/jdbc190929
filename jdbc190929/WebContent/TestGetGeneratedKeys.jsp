<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="sopo.cn.utils191007.MyDBUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TestGetGeneratedKeys.jsp</title>
</head>
<body>

<%
	String sql = "INSERT INTO students(`sname`, `ssex`, `sbirthday`, `class`, `age_id`) VALUES('海军陆战队', '男', '1993-11-1', '11661', 41);";
	Connection connection = MyDBUtils.getInstance().getConnectionViaDM();
	PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	preparedStatement.executeUpdate();
	
	ResultSet rs = preparedStatement.getGeneratedKeys();
	
	while( rs.next()) {
		out.println( "第" + rs.getInt(1) + "条记录插入成功");
	}
%>

</body>
</html>