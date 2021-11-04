<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<% request.setCharacterEncoding("utf-8");%>

<h2>포워딩하는 페이지: forwardParamTo.jsp</h2>


<%
	String name = request.getParameter("name");
	String selectedColor = request.getParameter("color");
	String selectedPage = selectedColor + ".jsp";
	
	out.print(name);
	out.print(selectedColor);
	out.print(selectedPage);

	
%>
	
	<jsp:forward page="<%=selectedPage %>">
		<jsp:param value="<%=selectedColor %>" name="selectedColor"/>
		<jsp:param value="<%=name %>" name="name"/>
	</jsp:forward>