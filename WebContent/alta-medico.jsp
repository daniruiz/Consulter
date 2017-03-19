<style scoped>
	input[name=apellidos] { float: right;}
	input[name=esp] { width: 100%; }
</style>
<section>
	<form>
		<input name="nombre" type="text" placeholder="Nombre">
		<input name="apellidos" type="text" placeholder="Apellidos">
		<input name="num" type="text" placeholder="N&ordm; colegio m&eacute;dico">

		<br>
		<label for="esp">&#8226; Separadas por comas</label>
		<input name="esp" type="text" placeholder="Especialidades">

		<label>&#8226; D&iacute;as semanales disponible: </label>
		<div class="calendario-semanal">
			<div>L</div><div>M</div><div>X</div><div>J</div><div>V</div><div>S</div><div>D</div>
		</div>

		<br>
		<input type="submit" value="enviar" class="boton-azul">
	</form>
</section>
