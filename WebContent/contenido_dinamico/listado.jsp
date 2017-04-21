<!-- citas para gestor -->
<%@page contentType="text/html; charset=UTF-8" %>

	<link rel="stylesheet" type='text/css' href="/contenido_dinamico/css/listado.css" />

	<h3 id="dia"></h3>
	<div id="menu-opciones">
		<span id="funcion-editar" class="boton-azul">editar</span>
		<span id="funcion-eliminar" class="boton-granate">eliminar</span>
		<span id="funcion-cancelar" class="boton-rojo">cancelar</span>
	</div>
	<script>
		var hoy = new Date(),
			dia = ('0' + hoy.getDate()).slice(-2),
			mes = ('0' + (hoy.getMonth() + 1)).slice(-2),
			aino = hoy.getFullYear();
		$('#dia').text(dia + '/' + mes + '/' + aino);


		var datos = [
			{
				'nombreMedico' : 'Roberto Perez',
				'especialidad' : 'Ginecología',
				'paciente' : 'Marta Feijoo',
				'dni' : '22454076G',
				'idCita' : 1,
				'hora' : '08:00'
			},
			{
				'nombreMedico' : 'Luis Moreno',
				'especialidad' : 'Odontología',
				'paciente' : 'Pedro Castellano',
				'dni' : '75215071B',
				'idCita' : 2,
				'hora' : '08:30'
			},
			{
				'nombreMedico' : 'Sofía Garrido',
				'especialidad' : 'Cardiología',
				'paciente' : 'Isabel Hernandez',
				'dni' : '52801993L',
				'idCita' : 3,
				'hora' : '08:30'
			},
			{
				'nombreMedico' : 'Sara Ruiz',
				'especialidad' : 'Psiquiatría',
				'paciente' : 'Rebeca Larrañaga',
				'dni' : '27449907M',
				'idCita' : 4,
				'hora' : '10:00'
			},
			{
				'nombreMedico' : 'Roberto Perez',
				'especialidad' : 'Neurología',
				'paciente' : 'Josefa Rodrígez',
				'dni' : '23229790C',
				'idCita' : 5,
				'hora' : '10:00'
			},
			{
				'nombreMedico' : 'Roberto Perez',
				'especialidad' : 'Pediatría',
				'paciente' : 'Adrian Ruiz',
				'dni' : '22473780C',
				'idCita' : 6,
				'hora' : '19:00'
			},
			{
				'nombreMedico' : 'Roberto Perez',
				'especialidad' : 'Oftalmología',
				'paciente' : 'Josefa Rodrígez',
				'dni' : '34784976Y',
				'idCita' : 7,
				'hora' : '19:30'
			}
		];

		var maxHora =  0, html = '', hora;
		$.each(datos, function(i, dato){
			hora = parseInt(dato['hora'].substring(0, 2));
			html = '';
			if(hora > maxHora){
				maxHora = hora;
				html = '<div class="hora"><span>' + maxHora + ':00</span><hr></div>';
			}
			html += '<section data-id="' + dato['idCita'] + '" class="con-sombra"><div id="icono-editar"></div>\
<h3>' + dato['paciente'] + '</h3>\
<h5 id="dni" data-dni="' + dato['dni'] + '">DNI: ' + dato['dni'] + '</h5>\
<h5>Médico: ' + dato['nombreMedico'] + '</h5>\
<h5>' + dato['especialidad'] + '</h5>\
<span>' + dato['hora'] + '</span>\
		</section>';
			$('main').append(html);
		});

		$('section').click(function(){
			var posicion = $(this).offset(),
				margen = parseInt($('#envoltorio').css('margin-left'));
			$('html, body').animate({scrollTop: posicion.top -200},200); 
			$(this).addClass('mostrar-cortina');
			$('#menu-opciones').addClass('mostrar-cortina');
			$('#menu-opciones').css({'top' : posicion.top, 'left' : (posicion.left - margen)});
			mostrarCortina();
		});
		$('#funcion-editar').click(function(){
			var idCita = $('section.mostrar-cortina').data('id'),
				dni = $('section.mostrar-cortina #dni').data('dni');
			$.post('/eliminar-cita', {'idCita' : idCita}).always(function(){
				cambiarPagina('/pedir-cita#' + dni);
			});
		});
		$('#funcion-eliminar').click(function(){
			var idCita = $('section.mostrar-cortina').data('id');
			$.post('/eliminar-cita', {'idCita' : idCita}).always(function(){
				cambiarPagina('/listado-citas');
			});
		});
		$('#funcion-cancelar').click(function(){ ocultarCortina(); });
	</script>
