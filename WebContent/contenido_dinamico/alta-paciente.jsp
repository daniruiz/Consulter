<style scoped>
	input[name=dni] { float: right;}
	input[name=seguro], input[name=apellidos]{ width: 100%; }
	section { padding: 20px 0 0!important; }
</style>
<section>
	<form>
		<div>
			<input name="nombre" type="text" placeholder="Nombre">
			<input name="dni" type="text" placeholder="DNI">
			<input name="apellidos" type="text" placeholder="Apellidos">

			<br>
			<span>&#8226; Campo opcional</span>
			<input name="seguro" type="text" placeholder="Compa&ntilde;&iacute;a de seguro m&eacute;dico">

			<br>
			<input type="submit" value="enviar" class="boton-azul">
		</div>
	</form>
</section>
