var hoy = new Date(),
	dia = ('0' + hoy.getDate()).slice(-2),
	mes = ('0' + (hoy.getMonth() + 1)).slice(-2),
	aino = hoy.getFullYear();
$('#dia').text(dia + '/' + mes + '/' + aino);

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
