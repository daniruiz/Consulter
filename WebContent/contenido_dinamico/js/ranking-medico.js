function cargarRanking(datos){
    var datos = JSON.parse(json), especialidad, contenedor;
    $('#contenedor-ranking').width(40);

    $.each(datos, function(i, objetoDatos){
        especialidad = objetoDatos['nombreEspecialidad']
        $('#contenedor-ranking').append('<div class="contenedor-especialidad" data-especialidad="' + especialidad + '"><h3 style="opacity:0">' + especialidad + '</h3>');
        $('#contenedor-ranking').width($('#contenedor-ranking').width() + 293); 
        contenedor = $('.contenedor-especialidad[data-especialidad="'+ especialidad +'"]');
        $.each(objetoDatos['lista'], function(i, posicion){
            contenedor.append('<section class="con-sombra pos-' + (i+1) + '" style="opacity:0"><h1>' + (i+1) + '</h1><h3>' + posicion['nombreMedico'] + '</h3><span>' + posicion['totalCitas'] + ' citas</span></section>');
        });
    });

    cargarFuncionesListado();
    colorEspecialidades();
    animacionMostrarRanking();
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

function animacionMostrarRanking(){
    var time;
    $('.contenedor-especialidad').each(function(){
        time = 0;
        $('.contenedor-especialidad * ').each(function(){
            mostrarElemento($(this), time);
            time += 5;
        })
    })
}
function mostrarElemento($e, time){
    setTimeout(function(){
        $e.css('opacity', '1');
    }, time);
}

function adaptarMainListado(){
    $('main').height($(window).height() - 105);
}

// Elimina la función onWindowResize especifica de esta página para que no afecte a las demás
$(window).on('cambioURL', recuperaWindowResizeAntiguo);
function recuperaWindowResizeAntiguo(){
    $(window).off('resize', adaptarMainListado)
        .off('cambioURL', recuperaWindowResizeAntiguo);
}