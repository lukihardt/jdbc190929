<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>bookInfoFill.jsp</title>
</head>
<body>
	<form action="acceptBookInfo.jsp" method="post">
		<table>
			<tr>
				<td>书籍id</td><td><input type="text" name = "id"/></td>
			</tr>
			<tr>
				<td>书籍名字</td><td><input type="text" name = "b_name"/></td>
			</tr>
			<tr>
				<td>书籍价钱</td><td><input type="text" name = "price"/></td>
			</tr>
			<tr>
				<td>作者id</td><td><input type="text" name = "authorId"/></td>
			</tr>
			<tr>
				<td>日期</td><td><input type="date" name = "date"/></td>
			</tr>
			<tr>
				<td><input type="submit"/></td>
			</tr>
		</table>
	</form>
</body>
</html>