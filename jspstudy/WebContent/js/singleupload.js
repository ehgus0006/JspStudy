$(document).ready(function(){

	

	$("#upForm1").ajaxForm({
		success:function(data, status){
		
			$("#upResult").html(data);
		}
	});
});