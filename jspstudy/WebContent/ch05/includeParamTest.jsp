<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>

<%
	String name = "김도현";
	String pageName = "includedParamTest.jsp";
%>

포함하는 페이지 includeParamTest.jsp 이빈다

<hr>
포함되는 페이지에 파라미터 값을 전달합니다.

<jsp:include page="<%=pageName %>">
	<jsp:param value="<%=name %>" name="name"/>
	<jsp:param value="<%=pageName %>" name="pageName"/>
</jsp:include>



includeParamTest.jsp 마지막 내용입니다