//-------- INICIALIZACIÓN
var diaManana = new Date(new Date().getTime() + 24 * 60 * 60 * 1000),
	dia = ('0' + diaManana.getDate()).slice(-2),
	mes = ('0' + (diaManana.getMonth() + 1)).slice(-2),
	aino = diaManana.getFullYear(),
	semana = new Date(new Date().getTime() + 7 * 24 * 60 * 60 * 1000)
$('#dia').val(dia + '/' + mes + '/' + aino).datepicker({
	startDate: diaManana,
	endDate: semana,
	format: "dd/mm/yyyy",
	maxViewMode: 0,
	language: "es",
	autoclose: true,
	container: ".datepicker_wrap"
});

$('#rango-inicio').timepicki({start_time: ["08", "00"]});
$('#rango-fin').timepicki({start_time: ["20", "00"]});


//-------- ELEMENTOS
MINUTOS_POR_PIXEL = 2.5;
TAM_BORDE_BARRA = 20;
HORA_LIMITE_INICIO = 8;

$('.slider-circulo').on('mousedown touchstart', function(e){
	$('.timepicker_wrap').hide();
	$('html, body').css('cursor','ew-resize');
	var lado = $(this).hasClass('slider-circulo-inicio') ? 'inicio' : 'fin';
	$(window).on('mousemove touchmove', function(e){
		var posicionX = (e.touches == undefined) ? e.pageX : e.touches[0].pageX,
			posicionSlider = $('#selector-horario').offset().left + TAM_BORDE_BARRA,
			posicionRaton = (posicionX - posicionSlider);
		moverSlider(posicionRaton, lado);
		actualizarHora();
	}).on('mouseup touchend', function(){
		$(this).off('mousemove touchmove');
		$('html, body').css('cursor','initial');
	});
});

$('.time .action-next').click(function(){
	var input = $(this).parents('.time_pick').children('input'),
		lado = input.is('#rango-inicio') ? 'inicio' : 'fin',
		desplazamiento = 2; /* 1 hora */
	moverSlider(lado, desplazamiento);
});
$('.time .action-prev').click(function(){
	var input = $(this).parents('.time_pick').children('input'),
		lado = input.is('#rango-inicio') ? 'inicio' : 'fin',
		desplazamiento = -2; /* -1 hora */
	moverSlider(lado, desplazamiento);
});
$('.mins .action-next').click(function(){
	var input = $(this).parents('.time_pick').children('input'),
		lado = input.is('#rango-inicio') ? 'inicio' : 'fin',
		desplazamiento = 1; /* 30 minutos */
	moverSlider(lado, desplazamiento);
});
$('.mins .action-prev').click(function(){
	var input = $(this).parents('.time_pick').children('input'),
		lado = input.is('#rango-inicio') ? 'inicio' : 'fin',
		desplazamiento = -1; /* -30 minutos */
	moverSlider(lado, desplazamiento);
});

function moverSlider(lado, desplazamiento) {
	var inicio = $('#selector-horario').data('inicio'),
		fin = $('#selector-horario').data('fin');
	if(lado == 'inicio'){
		var nuevoInicio = inicio + desplazamiento;
		if(nuevoInicio >= 0 && nuevoInicio < fin)
			inicio = nuevoInicio;
	} else if(lado == 'fin') {
		var nuevoFin = fin + desplazamiento;
		if(nuevoFin > inicio && nuevoFin <= 24)
			fin = nuevoFin;
	}
	$('#selector-horario').data({'inicio' : inicio, 'fin' : fin});
	$('#slider-barra').css({
		'margin-left' : inicio * 30 / MINUTOS_POR_PIXEL,
		'width' : (fin - inicio) * 30 / MINUTOS_POR_PIXEL
	});
	actualizarHora();
}

function actualizarHora(){
	var inicio = $('#selector-horario').data('inicio'),
		fin = $('#selector-horario').data('fin'),
		horaInicio = devolverHora(inicio * 30),
		minutoInicio = devolverMinuto(inicio * 30),
		horaFin = devolverHora(fin * 30),
		minutoFin = devolverMinuto(fin * 30);
	$('#rango-inicio').val(horaInicio+':'+minutoInicio)
		.data({'timepicki-tim' : horaInicio, 'timepicki-mini' : minutoInicio});
	$('#rango-fin').val(horaFin+':'+minutoFin)
		.data({'timepicki-tim' : horaFin, 'timepicki-mini' : minutoFin});
}

function devolverHora(m){
	return ('0' + (Math.floor(m / 60) + HORA_LIMITE_INICIO)).slice(-2);
}

function devolverMinuto(m){
	return ('0' + Math.floor(m % 60)).slice(-2);
}


//------- CARGAR MEDICOS

$('.especialidad, #dia').change(function(){pedirMedicosDisponibles()});

function pedirMedicosDisponibles(){
	var medicos = {
		'Roberto Perez' : {
			'id' : 2,
			'horas' : ['08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00','12:30','13:00','13:30','14:00']
		},
		'Luis Moreno' : {
			'id' : 3,
			'horas' : ['08:00','08:30','09:30','10:30','11:00','12:00','12:30','13:30','14:00']
		},
		'Isabel Hernandez' : {
			'id' : 1,
			'horas' : ['08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00','12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00']
		}
	}
	var html = '';
	$.each(medicos, function(nombre, medico){
		html += '<div data-id="' + medico.id + '"><h4>' + nombre + '</h4><div>';
		$.each(medico.horas, function(i, hora){
			html += '<span>' + hora + '</span> ';
		});
		html += '</div></div>'
	});
	$('#medicos-disponibles').html(html);
	
	$('#medicos-disponibles span').click(function(){
		$('#medicos-disponibles span.seleccionado').removeClass('seleccionado');
		$(this).addClass('seleccionado');
	});
}


//-------- ENVIAR FORMULARIO
$('section form').submit(function(e){
	e.preventDefault();
	if(validarFormulario()){
		var datos = {
			'dia': $('#dia').val(),
			'hora': $('#hora').val(),
			'especialista': $('.especialidad:checked').val(),
			'medico': $('.medico:checked').val()
		},
			json = JSON.stringify(datos);
		console.log(json);
	}  else $('html, body').animate({sliderTop: 0},200); 
	return false;
});

function validarFormulario(){
	$('.formulario-incorrecto').removeClass('formulario-incorrecto');
	if($('.especialidad:checked').length == 0)
		$('form > div > span:eq(0)').addClass('formulario-incorrecto');
	return $('.formulario-incorrecto').length == 0;
}