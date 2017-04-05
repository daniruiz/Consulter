<%@page contentType="text/html; charset=UTF-8" %>

	<link rel="stylesheet" type="text/css" href="/contenido_dinamico/css/pedir-cita.css">
	<link rel="stylesheet" type='text/css'href="/lib/css/timepicki.css"/>
	<link rel="stylesheet" type='text/css'href="/lib/css/bootstrap-datepicker.css"/>

	<section>
		<form>
			<div>
				<div class="datepicker_wrap">
					<input id="dia" type="text" placeholder="D&iacute;a" readonly>
				</div>
				<br>

				<span>&#8226; Especialista </span>
				<br>
				<label><input type="radio" class="especialidad" name="especialidad" value="1"> Cardiolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="2"> Dermatolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="3"> Endocrinolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="4"> Gastroenterolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="5"> Geriatr&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="6"> Ginecolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="7"> Neurolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="8"> Oftalmolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="9"> Pediatr&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="10"> Psiquiatr&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="11"> Reumatolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="12"> Urolog&iacute;a</label>

				<br><br>
				<div id="rango-horario">
					<input id="rango-inicio" type="text" value="08:00" readonly>
					<input id="rango-fin" type="text" value="20:00" readonly>
				</div>

				<div id="selector-horario" data-inicio="0" data-fin="24">
					<div id="slider-barra">
						<span class="slider-circulo slider-circulo-inicio"></span>
						<span class="slider-circulo slider-circulo-fin"></span>
					</div>
				</div>

				<br>
				<span>&#8226; M&eacute;dicos disponibles</span>
				<br>

				<div id="medicos-disponibles"></div>

				<br>
				<input type="submit" value="enviar" class="boton-azul">
			</div>
		</form>
	</section>

	<script src="/lib/timepicki.js"></script>
	<script src="/lib/bootstrap-datepicker.js"></script>
	<script src="/contenido_dinamico/js/pedir-cita.js"></script>