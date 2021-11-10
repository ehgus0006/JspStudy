<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	#result{
		width: 200px;
		height: 200px;
		border: 5px double #6699FF;
	}
</style>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		$("#b1").click(function() {
			var query = {
				name : "kingdora",
				stus : "homebody"
			}; // process.jsp 페이지에 요청 데이터를 보낸 후 결과를 받음
			
			$.ajax({
				type : "post",
				url : "process.jsp",
				data : query,
				success:function(data){
					$('#result').html(data);
				}
			});
		});
	});
</script>
</head>
<body>
	<button id="b1">결과</button>
	<div id="result"></div>
</body>
</html>