$(document).ready(function() {
	setTimeout(adaptacion, 800);
	cambioPestana($('nav span:first'));
	$('nav span').click(function(){ cambioPestana($(this)) });
	$(window).resize(function(){ adaptacion(); });

	$('body').click(function(){
		if($(window).width() <= 950) mostrarMenuLateral(false);
	});
	$('#mostrar_menu').click(function(e) {
		e.stopPropagation();
		mostrarMenuLateral();
	});
});

function cambioPestana(e) {
	$('nav span.seleccionado').attr('class', '');
	e.attr('class', 'seleccionado');
	var dir = 'contenido_dinamico/' + e.data().dir;
	$.get(dir, function(data){
        $('main').fadeOut(200, function(){
            $(this).html(data).fadeIn(200);
			$('body').scrollTop(0);
            $('footer').show();
        });
        tamMain();
	}).fail(function() {
		alert('Error al cargar la pagina');
	});
}

function adaptacion(){
	tamMain();
	if($(window).width() <= 950) mostrarMenuLateral(false);
	else mostrarMenuLateral(true);
}

function tamMain(){
	$('main').css('min-height', $(window).height() - 200);
}

function mostrarMenuLateral(mostrar) {
	if(mostrar == undefined)
		$('body').toggleClass('mostrar_panel_lateral');
	if(mostrar == false)
		$('body').attr('class', '');
	if(mostrar == true)
		$('body').attr('class', 'mostrar_panel_lateral');
}
