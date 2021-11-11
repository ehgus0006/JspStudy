<%@page import="ch09.update.UpdateDBBean"%>
<%@page import="ch09.update.UpdateDataBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@ page import="java.util.*"%>
<link rel="stylesheet" href="style.css" />

<%
	List<UpdateDataBean> memberList = null;

	UpdateDBBean dbPro = UpdateDBBean.getInstance();
	memberList = dbPro.getMembers();

%>

<table>
	<tr class="label">
		<td>아이디
		<td>비밀번호 <%
	for(int i=0; i<memberList.size(); i++){
		UpdateDataBean member = (UpdateDataBean) memberList.get(i);
		
		String id = member.getId();
		String pw = member.getPasswd();
		
	
	%>
	<tr>
		<td><%=id %>
		<td><%=pw %> 
		<% }%>
</table>