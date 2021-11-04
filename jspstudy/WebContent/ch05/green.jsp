<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<style>
img{
		border: 0;
		width: 70px;
		height: 30;
	}
</style>

<% 
	String name = request.getParameter("name");
	String selectedColor = request.getParameter("selectedColor");
%>

<h2>포워딩하는 페이지 - <%=selectedColor+".jsp" %></h2>
<%= name%>님이 좋아하는색은 <%= selectedColor%> 입니다

<img src="<%=selectedColor+".jpg"%>">