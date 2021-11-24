<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css"/>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/jquery.form.min.js"></script>
<script src="../js/singleupload.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="upForm1" action="singleUploadPro.jsp" method="post" enctype="multipart/form-data">
	<div id="form">
		<ul>
			<li>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" size="20" maxlength="50" placeholder="제목입력" autofocus="autofocus">
			</li>
			<li>
				<label for="file1">파일선택</label>
				<input type="file" id="file1" name="file1">
			</li>
			<li>
				<input type="submit" value="단일 파일 업로드" id="upPro1">
			</li>
		</ul>
	</div>
	</form>
	
	<div id="upResult">
	
	</div>

</body>
</html>