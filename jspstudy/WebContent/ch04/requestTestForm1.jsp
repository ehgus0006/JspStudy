<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<title>Insert title here</title>
</head>
<body>
	<h2>request 예제 - 요청 메소드</h2>
	<form method="post" action="requestTest1.jsp">
		<dl>
			<dd>
				<label for="name">이름</label> <input id="name" name="name"
					type="text" placeholder="김도현" autofocus required>
			</dd>
			<dd>
				<label for="age">나이</label> <input type="text" id="age" name="age"
					max="99" min="20" value="20" required>
			</dd>
			<dd>
				<fieldset>
					<legend>성별</legend>
					<input id="gender" name="gender" type="radio" value="m" checked>
					<label for="gender">남</label> 
					<input id="gender" name="gender" type="radio" value="f"> 
					<label for="gender">여</label>
				</fieldset>
			</dd>
			<dd>
				<label for="hobby">취미</label>
				<select id="hobby" name="hobby" required>
					<option value="잠자기" selected>잠자기
					<option value="만화보기" selected>만화보기
					<option value="운동하기" selected>운동하기
					<option value="술먹기" selected>술먹기
				</select>
			</dd>
			<dd>
				<input type="submit" value="전송">
			</dd>
		</dl>
	</form>

</body>
</html>