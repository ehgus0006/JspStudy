<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	
<% request.setCharacterEncoding("utf-8"); %>

<% String pageName = request.getParameter("pageName"); %>

포함하는 페이지는 <%= pageName%> 입니다 <br>

<hr>

<jsp:include page="<%=pageName %>"  flush="false"/>
includeTest.jsp의 나머지 내용입니다 