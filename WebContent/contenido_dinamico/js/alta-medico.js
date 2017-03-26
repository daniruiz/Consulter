$('.calendario-semanal *[class^="dia"]').append('\
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
	<div class="horas-horario">\
		<span>09:00</span>\
		<span>13:00 - </span>\
		<span>15:00</span>\
		<span>20:00</span>\
	</div>\
');

$('.calendario-semanal *[class^="dia"] > span').click(function(){
	$(this).toggleClass('dia-seleccionado');
});


$('.scroll-circulo').mousedown(function(e){
	var lado = 'derecha';
	var contenedor = $(this).closest('.selector-horario');
	var esBarraIzquierda = $(this).parent().hasClass('scroll-barra-izquierda');
	if($(this).hasClass('scroll-izquierda') && esBarraIzquierda) lado = 'izquierda';
	else if(($(this).hasClass('scroll-derecha') && esBarraIzquierda) ||
			($(this).hasClass('scroll-izquierda') && !esBarraIzquierda))
		lado = 'centro';
	$('html, body, .scroll-circulo').css('cursor','ew-resize');
	$(window).mousemove(function(e){
		moverSlider(e, lado, contenedor);
		actualizarHorario(contenedor);
	}).mouseup(function(){
		$(this).off('mousemove');
		$('html, body, .scroll-circulo').css('cursor','initial');
	});
});

function moverSlider(e, lado, contenedor) {
	var posContenedor =  contenedor.offset().left,
			diferencia = e.pageX - posContenedor,
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
			if((posicionDerecha < contenedor.width()) || (desplazamiento < 0))
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
	if((margenIzquierda + anchoIzquierda + anchoDescanso + anchoDerecha) > $(contenedor).width()){
		var ajuste = margenIzquierda + anchoIzquierda + anchoDescanso;
		if(anchoDerecha > anchoIzquierda) anchoDerecha = $(contenedor).width() - ajuste;
		else anchoIzquierda -= 1;
	}
	actualizarPosicionesSlider(margenIzquierda, anchoIzquierda, anchoDerecha, contenedor);
}

function actualizarPosicionesSlider(margenIzquierda, anchoIzquierda, anchoDerecha, contenedor){
	$(contenedor).children('.scroll-barra-izquierda').css('margin-left',margenIzquierda);
	$(contenedor).children('.scroll-barra-izquierda').width(anchoIzquierda);
	$(contenedor).children('.scroll-barra-derecha').width(anchoDerecha);
}


function actualizarHorario(contenedor){
	var posContenedor = $(contenedor).offset().left,
			tam = $(contenedor).children('.selector-horario').width(),
			inicio = parseInt($(contenedor).children('.scroll-barra-izquierda').css('margin-left')),
			descanso = inicio + $(contenedor).children('.scroll-barra-izquierda').width(),
			finDescanso = descanso + 48,
			fin = finDescanso + $(contenedor).children('.scroll-barra-derecha').width(),
			minutosPorPixel = 2.5,
			contenedorHorario = $(contenedor).siblings('.horas-horario');
	$(contenedorHorario).children('span:nth-child(1)').text(devolverHora(inicio*minutosPorPixel));
	$(contenedorHorario).children('span:nth-child(2)').text(devolverHora(descanso*minutosPorPixel));
	$(contenedorHorario).children('span:nth-child(3)').text(devolverHora(finDescanso*minutosPorPixel));
	$(contenedorHorario).children('span:nth-child(4)').text(devolverHora(fin*minutosPorPixel));
}

function devolverHora(m){
	var horas = ('0' + Math.floor(m / 60)).slice(-2);
	var minutos = ('0' + Math.floor(m % 60)).slice(-2);
	return horas + ':' + minutos;
}
