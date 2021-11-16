<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	String id = "dohyeon@naver.com";
	String pw = "123456";
	
	session.setAttribute("id", id);
	session.setAttribute("pw",pw);
	
	out.print("passwd : "+pw);
%>

<form method="post" action="viewSession.jsp">
	<table>
		<tr>
			<td><input type="submit" value="세션속성확인 "></td>
		</tr>
	</table>
</form>