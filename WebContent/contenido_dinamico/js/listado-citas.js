var hoy = new Date(),
    dia = ('0' + hoy.getDate()).slice(-2),
    mes = ('0' + (hoy.getMonth() + 1)).slice(-2),
    aino = hoy.getFullYear();
$('#dia').val(dia + '/' + mes + '/' + aino);


function cargarListado(datos){
    datos = JSON.parse(json);
    $('#contenedor-listado').width(40);
    $.each(datos, function(i, dato){
        var especialidad = dato['especialidad'],
            contenedor, html = '';

        if($('.contenedor-especialidad[data-especialidad="'+ especialidad +'"]').length == 0){
            $('#contenedor-listado').append('<div class="contenedor-especialidad" data-especialidad="' + especialidad + '"><h3>' + especialidad + '</h3>');
            $('#contenedor-listado').width($('#contenedor-listado').width() + 293);       
        }

        var contenedor = $('.contenedor-especialidad[data-especialidad="'+ especialidad +'"]'),
            hora = parseInt(dato['hora'].substring(0, 2)),
            horaAnterior;

        if(contenedor.find('section').length > 0)
            horaAnterior = parseInt(contenedor.children('section:last-child').data('hora').substring(0, 2))
        else horaAnterior = 0;

        if(hora > horaAnterior)
            html = '<div class="hora"><span>' + hora + ':00</span><hr></div>';

        html += '<section data-id="' + dato['idCita'] + '" data-hora="' + dato['hora'] + '" class="con-sombra"><div id="icono-editar"></div><h3>' + dato['paciente'] + '</h3><h5 id="dni" data-dni="' + dato['dni'] + '">DNI: ' + dato['dni'] + '</h5><h5>Médico: ' + dato['nombreMedico'] + '</h5><span>' + dato['hora'] + '</span></section>';
        $('.contenedor-especialidad[data-especialidad="'+ especialidad +'"]').append(html);
    });
    adaptarMainListado();
    $(window).resize(adaptarMainListado);

    $('section').click(function(){
        var posicion = $(this).offset(),
            margen = parseInt($('#envoltorio').css('margin-left'));
        $('html, body').animate({scrollTop: posicion.top -200},200);
        $(this).addClass('mostrar-cortina');
        $('#menu-opciones').addClass('mostrar-cortina');
        $('#menu-opciones').css({'top' : posicion.top, 'left' : (posicion.left - margen)});
        mostrarCortina();
    });
}

function adaptarMainListado(){
    $('main').height($(window).height() - 105);
}


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


// Elimina la función onWindowResize especifica de esta página para que no afecte a las demás
$(window).bind('cambioURL', recuperaWindowResizeAntiguo);
function recuperaWindowResizeAntiguo(){
    $(window).off('resize', adaptarMainListado)
        .off('cambioURL', recuperaWindowResizeAntiguo);
}





