// 18:07 23.04

$(document).ready(function() {
	//------------------RESPONSIVE DESIGN
	adaptacion();
	$(window).resize(function() { adaptacion(); });

	//------------------LOGIN
	$('#login form').submit(function() {
		opacidadCortina(1);
		var usuario = $('input[name="usuario"]').val();
		$.post('login', $(this).serialize(), function(data){
			var estado = $.trim(data);
			if(estado == 'true') {
				$('header h1').text(usuario);
				cambioPestana($('nav span:first'));
				$('body').css('overflow', 'auto');
				$('#login, #cortina').fadeOut(600);
			} else accesoDenegado();
		}).fail(function() {
			alert('Error al iniciar sesion');
		});
		return false;
	});

	$('input[name="usuario"]').val('admin');	// borrar
	$('input[name="pass"]').val('1234');		// borrar
	$('#login form').submit();			// borrar

	$('nav span').click(function(){ cambioPestana($(this)) });

	//------------------MENU LATERAL
	$('body').click(function() {
		if($(window).width() <= 580) mostrarMenuLateral(false);
	});
	$('#mostrar_menu').click(function(e) {
		e.stopPropagation();
		mostrarMenuLateral();
	});

	$( window ).scroll(function() {
		var width = $(window).width();
		var scroll = $(window).scrollTop();
		if(width > 800) {
			if(scroll > 164){
				$('#search').css({'position' : 'fixed',
						'top': $(window).height() -76});
			} else {
				$('#search').css({'position' : 'absolute',
						'top' : 190});
			}
		}
		if(width > 580) {
			if(scroll >= $('header > div').outerHeight()){
				$('header').css('padding-bottom',
					$('nav').height());
				$('nav').css('position', 'fixed');
			} else {
				$('header').css('padding-bottom', 0);
				$('nav').css('position', '');
			}
		}
	});
});

function opacidadCortina(valor) {
	$('#cortina').css('background', 'rgba(0, 0, 0, ' + valor + ')');
}

function adaptacion() { // adapta elementos al tamaño de pantalla
	posicionarMarcador();
	mostrarMenuLateral(false);
	if($(window).width() <= 580) $('nav').css('opacity', 0);
	else $('nav').css('opacity', 1);
	// footer siempre al fondo
	$('#envoltorio').css('min-height', $(window).height()-100)
	if($(window).scrollTop() < 164) {	//Adapta botón #search
		if($(window).width() < 800)
			$('#search').css({'position' : 'fixed',
					'top': $(window).height() - 76});
		else $('#search').css({'position' : 'absolute', 'top' : 190});
	}
	$('.calendario-semanal span').each(function() {
		$(this).css({'height': $(this).width(),
				'line-height': $(this).width() + 'px'});
	});
}

function mostrarMenuLateral(mostrar) {
	var yaDesplegado = $('body').hasClass('mostar-panel-izquierdo');
	if(mostrar || (mostrar == undefined && !yaDesplegado)) {
		$('body').animate({scrollTop:0}, 200);
		$('nav').css('opacity', 1);
		$('html').css('position', 'fixed');
		$('body').addClass('mostar-panel-izquierdo');
	} else if(!mostrar || (mostrar == undefined && yaDesplegado)) {
		$('html').css('position', 'relative');
		$('body').attr('class', '');
	}
}

function accesoDenegado() {
	$('#login').fadeOut(200);
	opacidadCortina(1);
	var texto = $('#denegado div');
	$('#denegado').show();
	var maxAncho = $(window).width() - 120;
	var maxAlto = $(window).height() - 120;
	if((texto.width() > maxAncho+20) || (texto.height()+20 > maxAlto))
		$('#denegado').css('font-size', 0);
	while(maxAncho > texto.width() && maxAlto > texto.height()){
		var size = parseInt($('#denegado').css('font-size'));
		$('#denegado').css('font-size', size + 20);
	}
	$('#denegado').css('margin-bottom', String(-texto.height() / 2) + 'px');
	texto.animate({ 'opacity' : 1 }, 1000, function(){
		setTimeout(function(){
			texto.animate({ 'opacity' : 0 }, 1000, function(){
				opacidadCortina(0.8);
				$('#denegado').hide();
				$('#login').fadeIn(200);
			});
		}, 1000);
	});
}

function cambioPestana(e) {
	$('body').animate({scrollTop:0},200);
	$('nav span.seleccionado').attr('class', '');
	e.attr('class', 'seleccionado');
	posicionarMarcador();
	var dir = 'contenido_dinamico/' + e.data().dir;
	$.get(dir, function(data){
		$('main').html(data);
		adaptacion();
	}).fail(function() {
		alert('Error al cargar la pagina');
	});
}

function posicionarMarcador() {
	var e = $('nav span.seleccionado');
	$('nav div').css({'margin-left': (e.position().left - 20),
			'width': e.outerWidth()});
}
