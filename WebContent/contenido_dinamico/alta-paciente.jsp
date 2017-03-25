<style scoped>
	input[name=dni] { float: right;}
	input[name=seguro], input[name=apellidos]{ width: 100%; }
</style>
<section>
	<form>
		<input name="nombre" type="text" placeholder="Nombre">
		<input name="dni" type="text" placeholder="DNI">
		<input name="apellidos" type="text" placeholder="Apellidos">

		<br>
		<label for="seguro">&#8226; Campo opcional</label>
		<input name="seguro" type="text" placeholder="Compa&ntilde;&iacute;a de seguro m&eacute;dico">

		<br>
		<input type="submit" value="enviar" class="boton-azul">
	</form>
</section>
