<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JQuery Test Page</title>
<style type="text/css">
	#displayArea{
		width: 200px;
		height: 200px;
		border: 5px double #6699FF;
	}
</style>
<script src="../js/jquery-3.6.0.slim.min.js"></script>
<script>
	$(document).ready(function () {
		$("button").click(function() {
			alert("클릭했다.");
		});
	});
</script>
</head>
<body>
	<div id="displayArea">내용 변경
		<button>표시</button>
	</div>

</body>
</html>