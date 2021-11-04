<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="includeTest.jsp">
		<dl>
			<dd>
			<label for="name">이름</label>
			<input type="text" name="name" id="name" placeholder="김도현" autofocus>
			</dd>
			<dd>
			<label for="pageName">포함할페이지</label>
			<input id="pageName" name="pageName" type="text" value="includedTest.jsp" required>
			</dd>
			<dd>
			<input type="submit" value="전송">
			</dd>
		</dl>
	</form>

</body>
</html>