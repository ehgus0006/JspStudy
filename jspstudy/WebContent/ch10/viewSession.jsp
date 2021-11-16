<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Enumeration <String> attr = session.getAttributeNames();
	while(attr.hasMoreElements()){
		String attrName = attr.nextElement();
		String attrValue = (String)session.getAttribute(attrName);
		out.println("세션의 속성명은:"+ attrName);
		out.println("세션의 속성값은:"+ attrValue);
		
	}
%>