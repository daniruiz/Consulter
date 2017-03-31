var diaManana = new Date(new Date().getTime() + 24 * 60 * 60 * 1000),
        dia = ('0' + diaManana.getDate()).slice(-2),
        mes = ('0' + (diaManana.getMonth() + 1)).slice(-2),
        aino = diaManana.getFullYear();
$('input[name=dia]').val(dia + '/' + mes + '/' + aino);
$('input[name="hora"]').timepicki({start_time: ["09", "00"]});
$('input[name=dia]').datepicker('hide');



$('section form').submit(function(e){
    e.preventDefault();
    if(validarFormulario()){
        var datos = {
                    'dia': $('input[name=dia]').val(),
                    'hora': $('input[name=hora]').val(),
                    'especialista': $('input[name=especialidad]:checked').val(),
                    'medico': $('input[name=medico]:checked').val()
                },
                json = JSON.stringify(datos);
        console.log(json);
    }  else $('html, body').animate({scrollTop: 0},200); 
    return false;
});


function validarFormulario(){
    $('.formulario-incorrecto').removeClass('formulario-incorrecto');
    if($('input[name=especialidad]:checked').length == 0)
            $('form > div > span:eq(0)').addClass('formulario-incorrecto');
    return $('.formulario-incorrecto').length == 0;
}

var MINUTOS_POR_PIXEL = 5,
    ANCHO_BARRRA_HORARIO = 288;

$('.scroll-circulo').on('mousedown touchstart', function(e){
    $('.timepicker_wrap').hide();
	$('html, body, .scroll-circulo').css('cursor','ew-resize');
	$(window).on('mousemove touchmove', function(e){
		var pageX = (e.touches == undefined) ? e.pageX : e.touches[0].pageX,
			desplazamiento = (pageX - $('.selector-horario').offset().left);
		moverSlider(desplazamiento);
        actualizarHora();
	}).on('mouseup touchend', function(){
		$(this).off('mousemove touchmove');
		$('html, body, .scroll-circulo').css('cursor','initial');
	});
});

$('.time .action-next').click(function(){
    var desplazamiento = $('.scroll-barra').width() + (60.0/MINUTOS_POR_PIXEL);		/* 1 hora en px*/
    moverSlider(desplazamiento);
    actualizarTimePick();
});
$('.time .action-prev').click(function(){
    var desplazamiento = $('.scroll-barra').width() + (-60.0/MINUTOS_POR_PIXEL);		/* 1 hora en px*/
    moverSlider(desplazamiento);
    actualizarTimePick();
});
$('.mins .action-next').click(function(){
    var desplazamiento = $('.scroll-barra').width() + (5.0/MINUTOS_POR_PIXEL);			/* 5 minutos en px*/
    moverSlider(desplazamiento);
    actualizarTimePick();
});
$('.mins .action-prev').click(function(){
    var desplazamiento = $('.scroll-barra').width() + (-5.0/MINUTOS_POR_PIXEL);		/* 5 minutos en px*/
    moverSlider(desplazamiento);
    actualizarTimePick();
});

function actualizarTimePick(){
    actualizarHora();
    $(".ti_tx input").val($('input[name=hora]').data('timepicki-tim'));
	$(".mi_tx input").val($('input[name=hora]').data('timepicki-mini'));
}

function moverSlider(desplazamiento) {
    var ancho = (desplazamiento > ANCHO_BARRRA_HORARIO) ? ANCHO_BARRRA_HORARIO : desplazamiento;
    ancho = (ancho < 0) ? 0 : ancho;
    $('.scroll-barra').width(ancho);
}

function actualizarHora(){
    var desplazamiento  = $('.scroll-barra').width(),
            hora = devolverHora(desplazamiento * MINUTOS_POR_PIXEL),
            minuto = devolverMinuto(desplazamiento * MINUTOS_POR_PIXEL)
    $('input[name=hora]').val(hora+':'+minuto)
        .data({'timepicki-tim' : hora, 'timepicki-mini' : minuto});
}

function devolverHora(m){
	return ('0' + Math.floor(m / 60)).slice(-2);
}

function devolverMinuto(m){
	return ('0' + Math.floor(m % 60)).slice(-2);
}