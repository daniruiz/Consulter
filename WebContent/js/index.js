$(document).ready(function() {
	if(localStorage.getItem('usuario') == null) location.href = '/acceso';
	$('header h1').text(localStorage.getItem('usuario'));
    
    
	setTimeout(function(){ adaptacion(); cargarDir() }, 800);   // Delay para efecto de entrada

    
	$('nav span').click(function(){
		if(!$(this).hasClass('seleccionado'))
			cambiarPagina($(this).data('dir'));
	});

    // funciones mostrar/ocultar menu lateral
	$('body').click(function(){
		if($(window).width() <= 950) mostrarMenuLateral(false);
	});
	$('#mostrar_menu').click(function(e){
		e.stopPropagation();
		mostrarMenuLateral();
	});
    
    // funciones mostrar/oculta cortina
	$('#cortina').click(function(){ ocultarCortina() });
	$('#cerrar-session').click(function(){
		localStorage.clear();
		window.location.href = '/acceso';
	});
    
    
    $(window).resize(adaptacion);
});

window.addEventListener("popstate", cargarDir, false); // Activar botones navegación


function cambiarPagina(dir){
	ocultarCortina();
	history.pushState("", "", '/' + dir);
	cargarDir();
}

function cargarDir() {
	var dir = location.pathname.substring(1);
	if(/acceso/.test(dir)) window.location.href = '/acceso'; // redirección a login

	var pestana = $('nav span[data-dir="' + dir + '"]');
	if(pestana.length == 0 && dir != 'observaciones-paciente') {
		pestana = $('nav span:first');
		dir = pestana.data('dir');
	}
	cambioPestana(pestana);

	
	dir = '/contenido_dinamico/' + dir + '.jsp';
	$.get(dir, function(data){
		$('main').fadeOut(200, function(){
			$(this).html(data).fadeIn(200);
			$('body').scrollTop(0);
			$('footer').show();
		});
		tamMain();
	});
    $(window).trigger('cambioURL');
}

function cambioPestana(e) {
	$('nav span.seleccionado').removeClass('seleccionado');
	e.addClass('seleccionado');
}

function adaptacion(){
	tamMain();
	if($(window).width() <= 950) mostrarMenuLateral(false);
	else mostrarMenuLateral(true);
}

function tamMain(){
	$('main').css({'min-height' : $(window).height() - 200, 'height' : 'inherit'});
}

function mostrarMenuLateral(mostrar) {
	if(mostrar == undefined)
		$('body').toggleClass('mostrar_panel_lateral');
	if(mostrar == false)
		$('body').removeClass('mostrar_panel_lateral');
	if(mostrar == true)
		$('body').addClass('mostrar_panel_lateral');
}

function mostrarCortina(){
	$('#cortina').show();
}
function ocultarCortina(){
	$('#cortina').hide();
	$('body').removeClass('mostrar-cortina');
	$('.mostrar-cortina').removeClass('mostrar-cortina');
}