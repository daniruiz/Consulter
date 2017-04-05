<%@page contentType="text/html; charset=UTF-8" %>

<style scoped>
	#dni { float: right;}
	#seguro, #apellidos { width: 100%; }
	section { padding: 20px 0 0!important; }
</style>

<section>
	<form>
		<div>
			<input id="nombre" type="text" placeholder="Nombre">
			<input id="dni" type="text" placeholder="DNI">
			<input id="apellidos" type="text" placeholder="Apellidos">

			<br><br>

			<span>&#8226; Campo opcional</span>
			<br>
			<input id="seguro" type="text" placeholder="Compa&ntilde;&iacute;a de seguro m&eacute;dico">

			<br><br>
			<input type="submit" value="enviar" class="boton-azul">
		</div>
	</form>
</section>

<script>
	$('section form').submit(function(e){
		e.preventDefault();
		if(validarFormulario()){
			var datos = {
				'nombre': $('#nombre').val(),
				'apellidos': $('#apellidos').val(),
				'dni': $('#dni').val(),
				'seguro': $('#seguro').val()
			},
				json = JSON.stringify(datos);
		} else $('html, body').animate({scrollTop: 0},200); 
		return false;
	});

	function validarFormulario(){
		$('.formulario-incorrecto').removeClass('formulario-incorrecto');
		if(!/^[A-záéíóúñ]+$/.test($('#nombre').val()))
			$('#nombre').addClass('formulario-incorrecto');
		if(!/^[A-záéíóúñ\s]+$/.test($('#apellidos').val()))
				$('#apellidos').addClass('formulario-incorrecto');
		if(!/^\d{8}[a-zA-Z]$/.test($('#dni').val()))
			$('#dni').addClass('formulario-incorrecto');
		return $('.formulario-incorrecto').length == 0;
	}
</script>
