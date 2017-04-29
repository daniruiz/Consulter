<%@page contentType="text/html; charset=UTF-8" %>

<style scoped>
	section { padding: 20px 0 0!important; }
	#apellidos { float: right; }
	form label {
		width: 183px;
		display: inline-block;
	}
	.formulario-incorrecto::after {	content: "seleccione al menos 1"; }
	.calendario-semanal {
		width: 365px;
		margin: auto;
	}
	.calendario-semanal > div { cursor: pointer; }
	@media only screen and (max-width: 600px) {
		.calendario-semanal { width: 255px; }
		.calendario-semanal > div > span, .calendario-semanal > span {
			height: 30px;
			width: 30px;
			line-height: 30px;
			font-size: 11px;
		}
		section > form > div { width: 90%; }
	}
</style>

<section>
	<form>
		<div>
			<input id="nombre" type="text" placeholder="Nombre"> 
			<input id="apellidos" type="text" placeholder="Apellidos">
			<input id="num" type="text" placeholder="N&ordm; colegio m&eacute;dico">

			<br><br>

			<span id="texto1">&#8226; Seleccione sus especialidades</span>
			<br>

			<label><input type="checkbox" class="especialidad" name="especialidad" value="1"> Cardiolog&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="2"> Dermatolog&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="3"> Endocrinolog&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="4"> Gastroenterolog&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="5"> Geriatr&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="6"> Ginecolog&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="7"> Neurolog&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="8"> Oftalmolog&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="9"> Pediatr&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="10"> Psiquiatr&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="11"> Reumatolog&iacute;a</label>
			<label><input type="checkbox" class="especialidad" name="especialidad" value="12"> Urolog&iacute;a</label>

			<br><br>

			<span id="texto2">&#8226; D&iacute;as semanales disponible: </span>
			<br>
			<div class="calendario-semanal">
				<div>
					<span>L</span>
				</div>
				<div>
					<span>M</span>
				</div>
				<div>
					<span>X</span>
				</div>
				<div>
					<span>J</span>
				</div>
				<div>
					<span>V</span>
				</div>
				<div>
					<span>S</span>
				</div>
				<div>
					<span>D</span>
				</div>
			</div>

			<br><br>
			<input type="submit" value="guardar" class="boton-azul">
			</form>
		</section>
	<script>
		$('section form').submit(function(e){
			e.preventDefault();
			if(validarFormulario()){
				var dias = $('.dia-seleccionado').map(function(){
						return $(this).text();
					}).get(),
					esp = $('input[name=especialidad]:checked').map(function(){
						return this.value;
					}).get(),
					datos = {
						'nombre': $('#nombre').val(),
						'apellidos': $('#apellidos').val(),
						'num': $('#num').val(),
						'esp': esp,
						'dias': dias,
					},
					json = {
						"opcion" : "insertar",
						"datos" : JSON.stringify(datos)
					},
					objConfigAjax = {
						method : "POST",
						url: "ServletMedico",
						data : json
					}
					
				$.ajax(objConfigAjax)
					.done(function(data) {
						data = JSON.parse(data);					    
					    alert("Médico insertado correctamente");
					    cambiarPagina('/')
					})
					.fail(function() {
						alert("Ha habido un error al guardar los datos.");
						location.reload();
					});
				
			} else $('html, body').animate({scrollTop: 0},200); 
			return false;
		});

		$('.calendario-semanal span').click(function(){
			$(this).toggleClass('dia-seleccionado');
		});


		function validarFormulario(){
			$('.formulario-incorrecto').removeClass('formulario-incorrecto');
			if(!/^[A-záéíóúñ]+$/.test($('#nombre').val()))
				$('#nombre').addClass('formulario-incorrecto');
			if(!/^[A-záéíóúñ\s]+$/.test($('#apellidos').val()))
				$('#apellidos').addClass('formulario-incorrecto');
			if(!/^[\w\d]+$/.test($('#num').val()))
				$('#num').addClass('formulario-incorrecto');
			if($('.especialidad:checked').length == 0)
				$('#texto1').addClass('formulario-incorrecto');
			if($('.dia-seleccionado').length == 0)
				$('#texto2').addClass('formulario-incorrecto');
			return $('.formulario-incorrecto').length == 0;
		}
	</script>
