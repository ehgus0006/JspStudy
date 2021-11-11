<%@page import="ch09.update.UpdateDBBean"%>
<%@page import="ch09.update.UpdateDataBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@ page import="java.util.*"%>
<link rel="stylesheet" href="style.css" />

<h3>암호화 전 내용</h3>
	<jsp:include page="cryptProcessList.jsp" flush="false"/>
	
	<%
		UpdateDBBean dbPro = UpdateDBBean.getInstance();
		dbPro.updateMember();
	%>
	
	<h3>암호화가 적용된 이후</h3>
	<jsp:include page="cryptProcessList.jsp"/>