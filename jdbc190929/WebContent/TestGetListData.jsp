<%@page import="sopo.cn.model191008.Books"%>
<%@page import="java.util.List"%>
<%@page import="sopo.cn.dao191011.BaseDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TestGetListData.jsp</title>
</head>
<body>
	<%
		String sql = "SELECT * FROM book WHERE id < ?;";
		List<Books> listBooks = BaseDao.getListData(Books.class, sql, 9999999);
		out.println(listBooks);
		
		int count = listBooks.size();
	%>
	<table>
		<tr>
			<td>show better:</td>
		</tr>
		<tr>
			<td>id</td>
			<td>b_name</td>
			<td>b_price</td>
			<td>author_id</td>
			<td>publish_date</td>
		</tr>
		<tr>
			<td><%=listBooks.get(0).getId()%></td>
			<td><%=listBooks.get(0).getB_name()%></td>
			<td><%=listBooks.get(0).getB_price()%></td>
			<td><%=listBooks.get(0).getAuthor_id()%></td>
			<td><%=listBooks.get(0).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(1).getId()%></td>
			<td><%=listBooks.get(1).getB_name()%></td>
			<td><%=listBooks.get(1).getB_price()%></td>
			<td><%=listBooks.get(1).getAuthor_id()%></td>
			<td><%=listBooks.get(1).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(2).getId()%></td>
			<td><%=listBooks.get(2).getB_name()%></td>
			<td><%=listBooks.get(2).getB_price()%></td>
			<td><%=listBooks.get(2).getAuthor_id()%></td>
			<td><%=listBooks.get(2).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(3).getId()%></td>
			<td><%=listBooks.get(3).getB_name()%></td>
			<td><%=listBooks.get(3).getB_price()%></td>
			<td><%=listBooks.get(3).getAuthor_id()%></td>
			<td><%=listBooks.get(3).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(4).getId()%></td>
			<td><%=listBooks.get(4).getB_name()%></td>
			<td><%=listBooks.get(4).getB_price()%></td>
			<td><%=listBooks.get(4).getAuthor_id()%></td>
			<td><%=listBooks.get(4).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(5).getId()%></td>
			<td><%=listBooks.get(5).getB_name()%></td>
			<td><%=listBooks.get(5).getB_price()%></td>
			<td><%=listBooks.get(5).getAuthor_id()%></td>
			<td><%=listBooks.get(5).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(6).getId()%></td>
			<td><%=listBooks.get(6).getB_name()%></td>
			<td><%=listBooks.get(6).getB_price()%></td>
			<td><%=listBooks.get(6).getAuthor_id()%></td>
			<td><%=listBooks.get(6).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(7).getId()%></td>
			<td><%=listBooks.get(7).getB_name()%></td>
			<td><%=listBooks.get(7).getB_price()%></td>
			<td><%=listBooks.get(7).getAuthor_id()%></td>
			<td><%=listBooks.get(7).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(8).getId()%></td>
			<td><%=listBooks.get(8).getB_name()%></td>
			<td><%=listBooks.get(8).getB_price()%></td>
			<td><%=listBooks.get(8).getAuthor_id()%></td>
			<td><%=listBooks.get(8).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(9).getId()%></td>
			<td><%=listBooks.get(9).getB_name()%></td>
			<td><%=listBooks.get(9).getB_price()%></td>
			<td><%=listBooks.get(9).getAuthor_id()%></td>
			<td><%=listBooks.get(9).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(10).getId()%></td>
			<td><%=listBooks.get(10).getB_name()%></td>
			<td><%=listBooks.get(10).getB_price()%></td>
			<td><%=listBooks.get(10).getAuthor_id()%></td>
			<td><%=listBooks.get(10).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(11).getId()%></td>
			<td><%=listBooks.get(11).getB_name()%></td>
			<td><%=listBooks.get(11).getB_price()%></td>
			<td><%=listBooks.get(11).getAuthor_id()%></td>
			<td><%=listBooks.get(11).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(12).getId()%></td>
			<td><%=listBooks.get(12).getB_name()%></td>
			<td><%=listBooks.get(12).getB_price()%></td>
			<td><%=listBooks.get(12).getAuthor_id()%></td>
			<td><%=listBooks.get(12).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(13).getId()%></td>
			<td><%=listBooks.get(13).getB_name()%></td>
			<td><%=listBooks.get(13).getB_price()%></td>
			<td><%=listBooks.get(13).getAuthor_id()%></td>
			<td><%=listBooks.get(13).getPublish_date()%></td>
		</tr>
		<tr>
			<td><%=listBooks.get(14).getId()%></td>
			<td><%=listBooks.get(14).getB_name()%></td>
			<td><%=listBooks.get(14).getB_price()%></td>
			<td><%=listBooks.get(14).getAuthor_id()%></td>
			<td><%=listBooks.get(14).getPublish_date()%></td>
		</tr>
		
	</table>
</body>
</html>