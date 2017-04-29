<%@page contentType="text/html; charset=UTF-8" %>

	<link rel="stylesheet" type="text/css" href="/contenido_dinamico/css/pedir-cita.css">
	<link rel="stylesheet" type='text/css'href="/lib/css/timepicki.css"/>
	<link rel="stylesheet" type='text/css'href="/lib/css/bootstrap-datepicker.css"/>

	<%
		int idCita = request.getParameter("idCita") == null ? 0 
				: Integer.parseInt(request.getParameter("idCita"));
	%>

	<script type="text/javascript">
		//$(window)
		function guardar(){
			var $horaGuardada = $("span[class='hora-disponible seleccionado']");
			
			//alert($horaGuardada.html());
			
			var idCita = <%=idCita %>;
			var nombreMedico = $horaGuardada.parent().prev().html();
			var dni = $("#dni").val();
			var datos = {
				"nombreMedico" : nombreMedico,
				"hora" : $horaGuardada.html(),
				"dni" : dni,
				"paciente" : dni,
				"especialidad" : $('input[name=especialidad]:checked').attr("data-name")
			}
			
			if($horaGuardada.html() == null){
				alert("No se ha seleccionado ninguna hora disponible.");
			}else{
				var json = {
						"opcion" : "guardarCita",
						"datos" : JSON.stringify(datos),
						"idCita" : idCita
				};
				
				//alert(JSON.stringify(json));
				
				var objConfigAjax = {
						  method : "POST",
						  url: "ServletCita",
						  data : json
						}
					
				$.ajax(objConfigAjax)
					.done(function(data) {
						data = JSON.parse(data);
					    //alert(data.resultado);
					    
						
						alert("Guardada cita para " + nombreMedico + " a la hora " + $horaGuardada.html());
						cambiarPagina('/listado-citas');
					    
					  })
					  .fail(function() {
					    alert( "Ha habido un error al guardar los datos." );
					    location.reload();
					  })
					  .always(function() {
					    //alert( "complete" );
					  });
				
				
			}
		}
	
	</script>


	<section>
		<form id="formulario-usuario">
			<div>
				<input id="dni" type="text" name="dni" placeholder="DNI del paciente">
				<input id="consultarPaciente" type="button" value="enviar" class="boton-azul">
				<div id="ocultoPacienteInexistente">
					<h1>El paciente no est&aacute; dado de alta</h1>
					<div id="darAltaPaciente" class="boton-azul">Dar de alta</div>
				</div>
			</div>
		</form>
		
		<form id="formulario-cita">
			<div>
				<input id="dni" type="hidden">
				<span id="texto1">DNI del paciente: </span>
				
				<br>
				
				<div class="datepicker_wrap">
					<input id="dia" type="text" placeholder="D&iacute;a" readonly>
				</div>

				<br>

				<span>&#8226; Especialista </span>
				<br>
				<label><input type="radio" class="especialidad" name="especialidad" value="1" data-name="Cardiología"> Cardiolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="2" data-name="Dermatología"> Dermatolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="3" data-name="Endocrinología"> Endocrinolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="4" data-name="Gastroenterología"> Gastroenterolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="5" data-name="Geriatría"> Geriatr&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="6" data-name="Ginecología"> Ginecolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="7" data-name="Neurología"> Neurolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="8" data-name="Oftalmología"> Oftalmolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="9" data-name="Pediatría"> Pediatr&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="10" data-name="Psiquiatría"> Psiquiatr&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="11" data-name="Reumatología"> Reumatolog&iacute;a</label>
				<label><input type="radio" class="especialidad" name="especialidad" value="12" data-name="Urología"> Urolog&iacute;a</label>

				<br><br>
				
				<span>&#8226; Rango horario</span>
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

				<span id="texto2">&#8226; M&eacute;dicos disponibles</span>
				<div id="medicos-disponibles"></div>
				<h1 id="medicos-no-disponibles">No hay m&eacute;dicos disponibles en el d&iacute;a u hora seleccionadas</h1>

				<br>

				<input id="guardar" type="button" value="Guardar" class="boton-azul">
			</div>
		</form>
	</section>
	
	<script type="text/javascript">
	
		$('input[name=especialidad]').on("click", function(){
			//alert($(this).val());
			
			
		})
		//.val();
	
		$("#guardar").on("click", function(){
			guardar();
		});
		
		$("#consultarPaciente").on("click", function(){
			var dni = $("#dni").val();
			//alert(dni);
			if(dni == "123456789Z"){
				//pedirMedicosDisponibles();
				$('#formulario-usuario').hide();
				$('#formulario-cita').show();
				$('#dni').val(dni);
				$('#texto1').append(dni);
				
			}else{
				//$("#ocultoPacienteInexistente").show();
				
				if(confirm("El paciente no existe, ¿desea darle de alta?")){
					cambiarPagina('/alta-paciente');
					cargarDir();
				}
				
			}
		});
	
	</script>

	<script src="/lib/timepicki.js"></script>
	<script src="/lib/bootstrap-datepicker.js"></script>

	<script src="/contenido_dinamico/js/pedir-cita.js"></script>