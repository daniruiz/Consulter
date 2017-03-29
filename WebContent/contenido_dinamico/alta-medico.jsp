<link rel="stylesheet" type="text/css" href="/contenido_dinamico/css/alta-medico.css">
<link rel="stylesheet" type='text/css'href="/lib/css/timepicki.css"/>

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

		<div>
            <span id="tiempo">&#8226; Tiempo m&iacute;nimo para consulta: </span>
            <input name="min" type="text" placeholder="Minutos">
        </div>
        <br>
        <div>
            <input type="submit" value="enviar" class="boton-azul">
        </div>
	</form>
</section>
<script>
    $('.calendario-semanal > div').append('\
        <div class="horas-horario">\
            <span>\
                <input class="hora hora-inicio" type="text" value="09:00" readonly>\
                <b> - </b>\
                <input class="hora hora-descanso" type="text" value="13:00" readonly>\
            </span>\
            <span>\
                <input class="hora hora-fin-descanso" type="text" value="15:00" readonly>\
                <b> - </b>\
                <input class="hora hora-fin" type="text" value="20:00" readonly>\
            </span>\
        </div>\
        <div class="selector-horario">\
            <div class="scroll-barra-izquierda">\
                <span class="scroll-circulo scroll-izquierda"></span>\
                <span class="scroll-circulo scroll-derecha"></span>\
            </div>\
            <div class="scroll-barra-derecha">\
                <span class="scroll-circulo scroll-izquierda"></span>\
                <span class="scroll-circulo scroll-derecha"></span>\
            </div>\
        </div>\
    ');
</script>
<script src="/lib/timepicki.js"></script>
<script src="/contenido_dinamico/js/alta-medico.js"></script>
