<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.sds.icto.guestbook.vo.GuestBookVo"%>
<%@page import="com.sds.icto.guestbook.dao.GuestBookDao"%>
<%@page import="java.util.List"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
	GuestBookDao dao = new GuestBookDao();
	List<GuestBookVo> list = dao.fetch();
%>

<html>
<head>

<title>방명록</title>
</head>
<body>
	<form action="/guestBook2/GuestBookServlet" method="post">
		<table border=1 width=500>
			<input type="hidden" name="a" value="add">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<br>
	<%
		for (GuestBookVo vo : list) {
	%>
	<table width=510 border=1>
		<tr>
			<td><%=vo.getNo()%></td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getDate()%></td>
			<td><a href="/guestBook2/GuestBookServlet?a=delete&id=<%=vo.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4><%=vo.getMessage()%></td>
		</tr>
	</table>
	<%} %>
</body>
</html>