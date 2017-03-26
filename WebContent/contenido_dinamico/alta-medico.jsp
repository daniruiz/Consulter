<link rel="stylesheet" type="text/css" href="/contenido_dinamico/css/alta-medico.css">
<section>
	<form>
		<div>
			<input name="nombre" type="text" placeholder="Nombre">
			<input name="apellidos" type="text" placeholder="Apellidos">
			<input name="num" type="text" placeholder="N&ordm; colegio m&eacute;dico">

			<br><br>

			<span>&#8226; Seleccione sus especialidades</span>
			<br>

			<label><input type="checkbox" name="especialidad" value="Cardiolog&iacute;a"> Cardiolog&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Dermatolog&iacute;a"> Dermatolog&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Endocrinolog&iacute;a"> Endocrinolog&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Gastroenterolog&iacute;a"> Gastroenterolog&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Geriatr&iacute;a"> Geriatr&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Ginecolog&iacute;a"> Ginecolog&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Neurolog&iacute;a"> Neurolog&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Oftalmolog&iacute;a"> Oftalmolog&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Pediatr&iacute;a"> Pediatr&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Psiquiatr&iacute;a"> Psiquiatr&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Reumatolog&iacute;a"> Reumatolog&iacute;a</label>
			<label><input type="checkbox" name="especialidad" value="Urolog&iacute;a"> Urolog&iacute;a</label>

			<br><br>

			<span>&#8226; D&iacute;as semanales disponible: </span>
			<br><br>
		</div>
		<div class="calendario-semanal">
			<div class="dia-lunes">
				<span>L</span>
			</div>
			<div class="dia-martes">
				<span>M</span>
			</div>
			<div class="dia-miercoles">
				<span>X</span>
			</div>
			<div class="dia-jueves">
				<span>J</span>
			</div>
			<div class="dia-viernes">
				<span>V</span>
			</div>
			<div class="dia-sabado">
				<span>S</span>
			</div>
			<div class="dia-domingo">
				<span>D</span>
			</div>
		</div>

		<br>

		<input type="submit" value="enviar" class="boton-azul">
	</form>
</section>

<script src="/contenido_dinamico/js/alta-medico.js"></script>
