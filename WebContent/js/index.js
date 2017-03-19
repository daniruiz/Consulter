$(document).ready(function(){
	adaptacion();
	//------------------LOGIN
	$('#login form').submit(function(){
		opacidadCortina(1);
		var usuario = $('input[name="usuario"]').val();
		$.post('login', $(this).serialize(), function(data){
			var estado = $.trim(data);
			if(estado == "true"){
				$.get($('nav span:first').data().dir,
					function(data){
						$('main').html(data);
						$('header h1').text(usuario);
					}
				).fail(function() {
					alert('Error al cargar la pagina');
				});
				$('body').css('overflow', 'auto');
				$('#login, #cortina').fadeOut(600);
			} else accesoDenegado();
		}).fail(function() {
			alert('Error al iniciar sesion');
		});
		return false;
	});

	$('nav span').click(function(){ cambioPestana($(this)) });

	$(window).resize(function(){ adaptacion(); });

	$( window ).scroll(function(){
		if($(window).width() > 800)
			if($(window).scrollTop() > 164)
				$('#search').css({'position' : 'fixed',
						'top': $(window).height() - 85});
			else
				$('#search').css({'position' : 'absolute',
						'top' : 190});
	});
});


function adaptacion(){ // adapta elementos al tamaño de pantalla
	//Adapta marcador menu
	$('nav div').width($('nav .seleccionado').outerWidth());
	//Adapta botón #search
	if($(window).scrollTop() < 164){
		if($(window).width() < 800){
			$('#search').css({'position' : 'fixed',
					'top': $(window).height() - 85});
		} else {
			$('#search').css({'position' : 'absolute', 'top' : 190});
		}
	}
	//Adapta .calendario-semanal
	$('.calendario-semanal div').each(function(){
		$(this).css({'height': $(this).width(),
				'line-height': $(this).width() + 'px'});
	});
}

function opacidadCortina(valor){
	$('#cortina').css('background', 'rgba(0, 0, 0, ' + valor + ')');
}

function accesoDenegado(){
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
	$('nav span.seleccionado').attr('class', '');
	e.attr('class', 'seleccionado');
	$('nav div').css({'margin-left': (e.position().left - 20),
			'width': e.outerWidth()});
	var dir = e.data().dir;
	if(dir != ''){
		$.get(dir, function(data){
			$('main').html(data);
		}).fail(function() {
			alert('Error al cargar la pagina');
		});
	}
	else $('main').html('');
}
