function cargarRanking(datos){
    datos = JSON.parse(json);
    $('#contenedor-ranking').width(40);
    $.each(datos, function(especialidad, posiciones){
        $('#contenedor-ranking').append('<div class="contenedor-especialidad" data-especialidad="' + especialidad + '"><h3>' + especialidad + '</h3>');
        $('#contenedor-ranking').width($('#contenedor-ranking').width() + 293); 
        var contenedor = $('.contenedor-especialidad[data-especialidad="'+ especialidad +'"]'), html = '';
        $.each(posiciones, function(i, posicion){
            contenedor.append('<section class="con-sombra pos-' + (i+1) + '"><h1>' + (i+1) + '</h1><h3>' + posicion[0] + '</h3><span>' + posicion[1] + ' citas</span></section>');
        });
    });

    cargarFuncionesListado();
    colorEspecialidades();
}

function cargarFuncionesListado(){
    adaptarMainListado();
    $(window).resize(adaptarMainListado);

    $('main').scroll(function(){
        $('.contenedor-especialidad > h3').css('margin-top', $(this).scrollTop()-80)
    });
}

function colorEspecialidades(){
    var colores = ['#2980b9', '#ed6f45', '#2c3e50', '#A32C36', '#329d8a', '#3E50B4', '#16a085'], i = 0;
    $('.contenedor-especialidad > h3').each(function(){
        if(i == colores.length) i = 0;
        $(this).css('border-color', colores[i]);
        i++;
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
$(window).on('cambioURL', recuperaWindowResizeAntiguo);
function recuperaWindowResizeAntiguo(){
    $(window).off('resize', adaptarMainListado)
        .off('cambioURL', recuperaWindowResizeAntiguo);
}