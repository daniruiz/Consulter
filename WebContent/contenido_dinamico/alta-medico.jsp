<style scoped>
	input[name=apellidos] { float: right;}
	input[name=esp] { width: 100%; }
	.calendario-semanal span { cursor: pointer; }
	form span {
		width: 200px;
		display: inline-block;
	}
</style>
<section>
	<form>
		<input name="nombre" type="text" placeholder="Nombre">
		<input name="apellidos" type="text" placeholder="Apellidos">
		<input name="num" type="text" placeholder="N&ordm; colegio m&eacute;dico">

		<br><br>

		<label for="esp">&#8226; Seleccione sus especialidades</label>
		<span><input type="checkbox" name="especialidad" value="Cardiolog&iacute;a"> Cardiolog&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Dermatolog&iacute;a"> Dermatolog&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Endocrinolog&iacute;a"> Endocrinolog&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Gastroenterolog&iacute;a"> Gastroenterolog&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Geriatr&iacute;a"> Geriatr&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Ginecolog&iacute;a"> Ginecolog&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Neurolog&iacute;a"> Neurolog&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Oftalmolog&iacute;a"> Oftalmolog&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Pediatr&iacute;a"> Pediatr&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Psiquiatr&iacute;a"> Psiquiatr&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Reumatolog&iacute;a"> Reumatolog&iacute;a</span>
		<span><input type="checkbox" name="especialidad" value="Urolog&iacute;a"> Urolog&iacute;a</span>

		<br><br>

		<label>&#8226; D&iacute;as semanales disponible: </label>
		<div class="calendario-semanal">
			<span>L</span>
			<span>M</span>
			<span>X</span>
			<span>J</span>
			<span>V</span>
			<span>S</span>
			<span>D</span>
		</div>

		<br>

		<input type="submit" value="enviar" class="boton-azul">
	</form>
</section>

<script>
	$('.calendario-semanal span').click(function(){
		$(this).toggleClass('dia-seleccionado');
	});
</script>
