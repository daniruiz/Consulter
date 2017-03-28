$('section form').submit(function(e){
    e.preventDefault();
    var dias = $('.dia-seleccionado').map(function(){
        var contenedor = $(this).siblings('.horas-horario');
        return {[$(this).text()] : {
            'inicio': contenedor.find('.hora-inicio').val(),
            'descanso': contenedor.find('.hora-descanso').val(),
            'fin-descanso': contenedor.find('.hora-fin-descanso').val(),
            'fin': contenedor.find('.hora-fin').val()
        }};
    }).get();
    var esp = $('input[name=especialidad]:checked').map(function(){
                return this.value;
    }).get();
        
    var datos = {
        'nombre': $('input[name=nombre]').val(),
        'apellidos': $('input[name=apellidos]').val(),
        'num': $('input[name=num]').val(),
        'esp': esp,
        'dias': dias,
        'minutos': $('input[name=min]').val()
    };
    json = JSON.stringify(datos);
    console.log(json);
});

$('.calendario-semanal *[class^="dia"] > span').click(function(){
	$(this).toggleClass('dia-seleccionado');
});

$('.scroll-circulo').on('mousedown touchstart', function(e){
    $('.timepicker_wrap').hide();
	var lado = 'derecha',
			contenedor = $(this).closest('.selector-horario'),
			esBarraIzquierda = $(this).parent().hasClass('scroll-barra-izquierda');
	if($(this).hasClass('scroll-izquierda') && esBarraIzquierda) lado = 'izquierda';
	else if(($(this).hasClass('scroll-derecha') && esBarraIzquierda) ||
			($(this).hasClass('scroll-izquierda') && !esBarraIzquierda))
		lado = 'centro';
	$('html, body, .scroll-circulo').css('cursor','ew-resize');
	$(window).on('mousemove touchmove', function(e){
		var pageX = (e.touches == undefined) ? e.pageX : e.touches[0].pageX,
			diferencia = (pageX - contenedor.offset().left);
		moverSlider(diferencia, lado, contenedor);
	}).on('mouseup touchend', function(){
		$(this).off('mousemove touchmove');
		$('html, body, .scroll-circulo').css('cursor','initial');
	});
});

$('.time .action-next').click(function(){
    var contenedor = $(this).parents('.horas-horario').siblings('.selector-horario'),
    contenedorHora = $(this).parents('.time_pick').children('.hora'),
    desplazamiento = 24;		/* 1 hora en pixeles*/
    actualizarSlider(contenedor, contenedorHora, desplazamiento);
    $(this).parents('.time_pick').find(".ti_tx input").val(contenedorHora.data('timepicki-tim'));
	$(this).parents('.time_pick').find(".mi_tx input").val(contenedorHora.data('timepicki-mini'));
});
$('.time .action-prev').click(function(){
var contenedor = $(this).parents('.horas-horario').siblings('.selector-horario'),
    contenedorHora = $(this).parents('.time_pick').children('.hora'),
    desplazamiento = -24;		/* 1 hora en pixeles*/
    actualizarSlider(contenedor, contenedorHora, desplazamiento);
    $(this).parents('.time_pick').find(".ti_tx input").val(contenedorHora.data('timepicki-tim'));
	$(this).parents('.time_pick').find(".mi_tx input").val(contenedorHora.data('timepicki-mini'));
});
$('.mins .action-next').click(function(){
var contenedor = $(this).parents('.horas-horario').siblings('.selector-horario'),
    contenedorHora = $(this).parents('.time_pick').children('.hora'),
    desplazamiento = 2;			/* 5 minutos en pixeles*/
    actualizarSlider(contenedor, contenedorHora, desplazamiento);
    $(this).parents('.time_pick').find(".ti_tx input").val(contenedorHora.data('timepicki-tim'));
	$(this).parents('.time_pick').find(".mi_tx input").val(contenedorHora.data('timepicki-mini'));
});
$('.mins .action-prev').click(function(){
var contenedor = $(this).parents('.horas-horario').siblings('.selector-horario'),
    contenedorHora = $(this).parents('.time_pick').children('.hora'),
    desplazamiento = -2;		/* 5 minutos en pixeles*/
    actualizarSlider(contenedor, contenedorHora, desplazamiento);
    $(this).parents('.time_pick').find(".ti_tx input").val(contenedorHora.data('timepicki-tim'));
	$(this).parents('.time_pick').find(".mi_tx input").val(contenedorHora.data('timepicki-mini'));
});



ANCHO_DESCANSO = 48;        /* 2 horas */
ANCHO_BARRRA_HORARIO = 575; /* horas: 00.00 - 23.57 */
ANCHO_MINIMO_BARRA = 5;
MINUTOS_POR_PIXEL = 2.5;

function moverSlider(diferencia, lado, contenedor) {
	var  margenIzquierda = parseFloat(contenedor.children('.scroll-barra-izquierda').css('margin-left')),
			anchoIzquierda = contenedor.children('.scroll-barra-izquierda').width(),
			anchoDerecha = contenedor.children('.scroll-barra-derecha').width();
	switch (lado) {
		case 'izquierda':
			var desplazamiento = diferencia - margenIzquierda;
			if(((anchoIzquierda > ANCHO_MINIMO_BARRA) || (desplazamiento < 0)) && (diferencia >= 0)) {
				margenIzquierda = diferencia;
				anchoIzquierda = anchoIzquierda - desplazamiento;
			}
			break;
		case 'derecha':
			var posicionDerecha = margenIzquierda + anchoIzquierda + ANCHO_DESCANSO + anchoDerecha,
					desplazamiento = diferencia - posicionDerecha;
			if((posicionDerecha < ANCHO_BARRRA_HORARIO) || (desplazamiento < 0))
				anchoDerecha = anchoDerecha + desplazamiento;
			break;
		case 'centro':
			var posicionCentro = margenIzquierda + anchoIzquierda,
					desplazamiento = diferencia - posicionCentro;
			if(((anchoIzquierda > ANCHO_MINIMO_BARRA) && (anchoDerecha > ANCHO_MINIMO_BARRA)) ||
					((anchoIzquierda <= ANCHO_MINIMO_BARRA) && (anchoDerecha > ANCHO_MINIMO_BARRA) && (desplazamiento > 0)) ||
					((anchoDerecha <= ANCHO_MINIMO_BARRA) && (anchoIzquierda > ANCHO_MINIMO_BARRA) && (desplazamiento < 0))) {
				anchoIzquierda = anchoIzquierda + desplazamiento;
				anchoDerecha = anchoDerecha - desplazamiento;
			}
	}
	ajustarSlider(margenIzquierda, anchoIzquierda, anchoDerecha, contenedor);
    actualizarHorario(contenedor);
}

function ajustarSlider(margenIzquierda, anchoIzquierda, anchoDerecha, contenedor){
	while((margenIzquierda + anchoIzquierda + ANCHO_DESCANSO + anchoDerecha) > ANCHO_BARRRA_HORARIO){
		var ajuste = margenIzquierda + anchoIzquierda + ANCHO_DESCANSO;
		if(anchoDerecha > anchoIzquierda) anchoDerecha = ANCHO_BARRRA_HORARIO - ajuste;
		else anchoIzquierda -= 1;
	}
	actualizarPosicionesSlider(margenIzquierda, anchoIzquierda, anchoDerecha, contenedor);
}

function actualizarPosicionesSlider(margenIzquierda, anchoIzquierda, anchoDerecha, contenedor){
	if(margenIzquierda != undefined) contenedor.children('.scroll-barra-izquierda').css('margin-left',margenIzquierda);
	if(anchoIzquierda != undefined) contenedor.children('.scroll-barra-izquierda').width(anchoIzquierda);
	if(anchoDerecha != undefined) contenedor.children('.scroll-barra-derecha').width(anchoDerecha);
}

function actualizarSlider(contenedor, contenedorHora, desplazamiento){
	var inicio = parseInt(contenedor.children('.scroll-barra-izquierda').css('margin-left')),
			descanso = inicio + contenedor.children('.scroll-barra-izquierda').width(),
			finDescanso = descanso + ANCHO_DESCANSO,
			fin = finDescanso + contenedor.children('.scroll-barra-derecha').width(),
            lado, posicionX;
    if(contenedorHora.hasClass('hora-inicio')){
        lado = 'izquierda';
        posicionX = desplazamiento + inicio;
    } else if(contenedorHora.hasClass('hora-descanso') || contenedorHora.hasClass('hora-fin-descanso')){
        lado = 'centro';
        posicionX = descanso + desplazamiento;
    } else {
        lado = 'derecha';
        posicionX = fin + desplazamiento
    }
	moverSlider(posicionX, lado, contenedor);
}

function actualizarHorario(contenedor){
	var inicio = parseInt(contenedor.children('.scroll-barra-izquierda').css('margin-left')),
			descanso = inicio + contenedor.children('.scroll-barra-izquierda').width(),
			finDescanso = descanso + ANCHO_DESCANSO,
			fin = finDescanso + contenedor.children('.scroll-barra-derecha').width(),
			contenedorHorario = contenedor.siblings('.horas-horario'),
			horaInicio = devolverHora(inicio*MINUTOS_POR_PIXEL),
			minutoInicio = devolverMinuto(inicio*MINUTOS_POR_PIXEL)
			horaDescanso = devolverHora(descanso*MINUTOS_POR_PIXEL),
			minutoDescanso = devolverMinuto(descanso*MINUTOS_POR_PIXEL)
			horaFinDescanso = devolverHora(finDescanso*MINUTOS_POR_PIXEL),
			minutoFinDescanso = devolverMinuto(finDescanso*MINUTOS_POR_PIXEL),
			horaFin = devolverHora(fin*MINUTOS_POR_PIXEL),
			minutoFin = devolverMinuto(fin*MINUTOS_POR_PIXEL);
	contenedorHorario.find('.hora-inicio').val(horaInicio+':'+minutoInicio)
			.data({'timepicki-tim' : horaInicio, 'timepicki-mini' : minutoInicio});
	contenedorHorario.find('.hora-descanso').val(horaDescanso+':'+minutoDescanso)
			.data({'timepicki-tim' : horaDescanso, 'timepicki-mini' : minutoDescanso});
	contenedorHorario.find('.hora-fin-descanso').val(horaFinDescanso+':'+minutoFinDescanso)
			.data({'timepicki-tim' : horaFinDescanso, 'timepicki-mini' : minutoFinDescanso});
	contenedorHorario.find('.hora-fin').val(horaFin+':'+minutoFin)
			.data({'timepicki-tim' : horaFin, 'timepicki-mini' : minutoFin});
}

function devolverHora(m){
	return ('0' + Math.floor(m / 60)).slice(-2);
}

function devolverMinuto(m){
	return ('0' + Math.floor(m % 60)).slice(-2);
}
