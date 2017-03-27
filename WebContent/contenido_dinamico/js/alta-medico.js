$('.calendario-semanal *[class^="dia"] > span').click(function(){
	$(this).toggleClass('dia-seleccionado');
});


$('.scroll-circulo').on('mousedown touchstart', function(e){
	var lado = 'derecha';
	var contenedor = $(this).closest('.selector-horario');
	var esBarraIzquierda = $(this).parent().hasClass('scroll-barra-izquierda');
	if($(this).hasClass('scroll-izquierda') && esBarraIzquierda) lado = 'izquierda';
	else if(($(this).hasClass('scroll-derecha') && esBarraIzquierda) ||
			($(this).hasClass('scroll-izquierda') && !esBarraIzquierda))
		lado = 'centro';
	$('html, body, .scroll-circulo').css('cursor','ew-resize');
	$(window).on('mousemove touchmove', function(e){
		moverSlider(e, lado, contenedor);
		actualizarHorario(contenedor);
	}).on('mouseup touchend', function(){
		$(this).off('mousemove touchmove');
		$('html, body, .scroll-circulo').css('cursor','initial');
	});
});

function moverSlider(e, lado, contenedor) {
	var pageX = (e.touches == undefined) ? e.pageX : e.touches[0].pageX;
	var posContenedor =  contenedor.offset().left,
			diferencia = (pageX - posContenedor),
			margenIzquierda = parseFloat($(contenedor).children('.scroll-barra-izquierda').css('margin-left')),
			anchoIzquierda = $(contenedor).children('.scroll-barra-izquierda').width(),
			anchoDescanso = parseFloat($(contenedor).children('.scroll-barra-derecha').css('margin-left')),
			anchoDerecha = $(contenedor).children('.scroll-barra-derecha').width();
	switch (lado) {
		case 'izquierda':
			var desplazamiento = diferencia - margenIzquierda;
			if(((anchoIzquierda > 5) || (desplazamiento < 0)) && (diferencia >= 0)) {
				margenIzquierda = diferencia;
				anchoIzquierda = anchoIzquierda - desplazamiento;
			}
			break;
		case 'derecha':
			var posicionDerecha = margenIzquierda + anchoIzquierda + anchoDescanso + anchoDerecha,
					desplazamiento = diferencia - posicionDerecha;
			if((posicionDerecha < 575) || (desplazamiento < 0))
				anchoDerecha = anchoDerecha + desplazamiento;
			break;
		case 'centro':
			var posicionCentro = margenIzquierda + anchoIzquierda,
					desplazamiento = diferencia - posicionCentro;
			if(((anchoIzquierda > 5) && (anchoDerecha > 5)) ||
					((anchoIzquierda <= 5) && (anchoDerecha > 5) && (desplazamiento > 0)) ||
					((anchoDerecha <= 5) && (anchoIzquierda > 5) && (desplazamiento < 0))) {
				anchoIzquierda = anchoIzquierda + desplazamiento;
				anchoDerecha = anchoDerecha - desplazamiento;
			}
	}
	ajustarSlider(margenIzquierda, anchoIzquierda, anchoDescanso, anchoDerecha, contenedor);
}

function ajustarSlider(margenIzquierda, anchoIzquierda, anchoDescanso, anchoDerecha, contenedor){
	while((margenIzquierda + anchoIzquierda + anchoDescanso + anchoDerecha) > 575){
		var ajuste = margenIzquierda + anchoIzquierda + anchoDescanso;
		if(anchoDerecha > anchoIzquierda) anchoDerecha = 575 - ajuste;
		else anchoIzquierda -= 1;
	}
	actualizarPosicionesSlider(margenIzquierda, anchoIzquierda, anchoDerecha, contenedor);
}

function actualizarPosicionesSlider(margenIzquierda, anchoIzquierda, anchoDerecha, contenedor){
	$(contenedor).children('.scroll-barra-izquierda').css('margin-left',margenIzquierda);
	$(contenedor).children('.scroll-barra-izquierda').width(anchoIzquierda);
	$(contenedor).children('.scroll-barra-derecha').width(anchoDerecha);
}

function actualizarBarrasHorario(contenedor){
	return 0;
}

function actualizarHorario(contenedor){
	var posContenedor = $(contenedor).offset().left,
			tam = $(contenedor).children('.selector-horario').width(),
			inicio = parseFloat($(contenedor).children('.scroll-barra-izquierda').css('margin-left')),
			descanso = inicio + $(contenedor).children('.scroll-barra-izquierda').width(),
			finDescanso = descanso + 48,
			fin = finDescanso + $(contenedor).children('.scroll-barra-derecha').width(),
			minutosPorPixel = 2.5,
			contenedorHorario = $(contenedor).siblings('.horas-horario'),
			horaInicio = devolverHora(inicio*minutosPorPixel),
			minutoInicio = devolverMinuto(inicio*minutosPorPixel)
			horaDescanso = devolverHora(descanso*minutosPorPixel),
			minutoDescanso = devolverMinuto(descanso*minutosPorPixel)
			horaFinDescanso = devolverHora(finDescanso*minutosPorPixel),
			minutoFinDescanso = devolverMinuto(finDescanso*minutosPorPixel),
			horaFin = devolverHora(fin*minutosPorPixel),
			minutoFin = devolverMinuto(fin*minutosPorPixel);
	$(contenedorHorario).find('.hora-inicio').val(horaInicio+':'+minutoInicio)
			.attr({'data-timepicki-tim' : horaInicio, 'data-timepicki-mini' : minutoInicio});
	$(contenedorHorario).find('.hora-descanso').val(horaDescanso+':'+minutoDescanso)
			.attr({'data-timepicki-tim' : horaDescanso, 'data-timepicki-mini' : minutoDescanso});
	$(contenedorHorario).find('.hora-fin-descanso').val(horaFinDescanso+':'+minutoFinDescanso)
			.attr({'data-timepicki-tim' : horaFinDescanso, 'data-timepicki-mini' : minutoFinDescanso});
	$(contenedorHorario).find('.hora-fin').val(horaFin+':'+minutoFin)
			.attr({'data-timepicki-tim' : horaFin, 'data-timepicki-mini' : minutoFin});
}

function devolverHora(m){
	return ('0' + Math.floor(m / 60)).slice(-2);
}

function devolverMinuto(m){
	return ('0' + Math.floor(m % 60)).slice(-2);
}
