<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*, javax.sql.*, javax.naming.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr class="label">
		<td>아이디
		<td>비밀번호
		<td>이름
		<td>가입날짜
		<td>주소
		<td>전화번호
		
		<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			
			String url = "jdbc:mysql://127.0.0.1:3306/study?serverTimezone=UTC&amp;useSSL=false";
			String ids = "root";
			String pw = "rlaehgus12!";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url,ids,pw);
			
			
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			
			while(rs.next()){
				String id = rs.getString("id");
				String passwd = rs.getString("passwd");
				String name = rs.getString("name");
				Timestamp register = rs.getTimestamp("reg_date");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
			%>
			<tr>
			<td><%=id %>
			<td><%=passwd %>
			<td><%=name %>
			<td><%=register %>
			<td><%=address %>
			<td><%=tel %>
			
			<% }
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(rs != null){
					try{rs.close();}catch(SQLException sqle){}
				}
				if(pstmt != null){
					try{pstmt.close();}catch(SQLException sqle){}
				}
				if(conn != null){
					try{conn.close();}catch(SQLException sqle){}
				}
			
			}
		
		%>
	</table>

</body>
</html>