<link rel="stylesheet" type="text/css" href="/contenido_dinamico/css/pedir-cita.css">
<link rel="stylesheet" type='text/css'href="/lib/css/timepicki.css"/>
<link rel="stylesheet" type='text/css'href="/lib/css/datepicker.css"/>

<section>
	<form>
		<div>
            <input name="dia" type="text" placeholder="D&iacute;a" readonly>
            <input name="hora" type="text" value="09:00" readonly>
        </div>
        
        <div class="selector-horario">
            <div class="scroll-barra">
                <span class="scroll-circulo"></span>
            </div>
        </div>
        
        <br>
        
        <div>
            <span>&#8226; Especialista </span>
            <br>
            <label><input type="radio" name="especialidad" value="1"> Cardiolog&iacute;a</label>
			<label><input type="radio" name="especialidad" value="2"> Dermatolog&iacute;a</label>
			<label><input type="radio" name="especialidad" value="3"> Endocrinolog&iacute;a</label>
			<label><input type="radio" name="especialidad" value="4"> Gastroenterolog&iacute;a</label>
			<label><input type="radio" name="especialidad" value="5"> Geriatr&iacute;a</label>
			<label><input type="radio" name="especialidad" value="6"> Ginecolog&iacute;a</label>
			<label><input type="radio" name="especialidad" value="7"> Neurolog&iacute;a</label>
			<label><input type="radio" name="especialidad" value="8"> Oftalmolog&iacute;a</label>
			<label><input type="radio" name="especialidad" value="9"> Pediatr&iacute;a</label>
			<label><input type="radio" name="especialidad" value="10"> Psiquiatr&iacute;a</label>
			<label><input type="radio" name="especialidad" value="11"> Reumatolog&iacute;a</label>
			<label><input type="radio" name="especialidad" value="12"> Urolog&iacute;a</label>
            
			<br><br>
            
			<span>&#8226; M&eacute;dico que desea que le atienda (opcional)</span>
            <br>
			<label><input type="radio" name="medico" value="1"> Roberto Perez</label>
			<label><input type="radio" name="medico" value="2"> Luis Moreno</label>
			<label><input type="radio" name="medico" value="3"> Sofia Garrrido</label>
			<label><input type="radio" name="medico" value="4"> Sara Ruiz</label>
			<label><input type="radio" name="medico" value="5"> Isabel Hernandez</label>

			<br>
			<input type="submit" value="enviar" class="boton-azul">
		</div>
	</form>
</section>

<script src="/lib/timepicki.js"></script>
<script src="/lib/bootstrap-datepicker.js"></script>
<script src="/contenido_dinamico/js/pedir-cita.js"></script>