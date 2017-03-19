<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Acceso</title>
<jsp:include page="include/imports.jsp"></jsp:include>

<script type="text/javascript">
	$(window).ready(function(){
		
		
		$("#botonEntrar").on("click", function(){
			var param = { 
					usuario : $("#usuario").val(), 
					pass : $("#pass").val()
			}
			
			var objConfigAjax = {
				  method : "POST",
				  url: "login",
				  data : param
				}
			
			$.ajax(objConfigAjax)
				.done(function(data) {
				    alert( data);
				  })
				  .fail(function() {
				    alert( "error" );
				  })
				  .always(function() {
				    alert( "complete" );
				  });
		
		});
		
		
	});
</script>
</head>
<body>
<input type="text" id="usuario" style="width: 100px"/>
<input type="password" id="pass" style="width: 100px"/>
<div id="botonEntrar" style="width: 100px; height: 60px; background-color: Gainsboro;">Entrar</div>
</body>
</html>