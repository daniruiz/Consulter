var hoy = new Date(),
    dia = ('0' + hoy.getDate()).slice(-2),
    mes = ('0' + (hoy.getMonth() + 1)).slice(-2),
    aino = hoy.getFullYear();
$('#dia').val(dia + '/' + mes + '/' + aino);


function cargarListado(datos){
    datos = JSON.parse(json);
    $('#contenedor-listado').width(40);
    $.each(datos, function(i, dato){
        var especialidad = dato['especialidad'], html = '';
        if($('.contenedor-especialidad[data-especialidad="'+ especialidad +'"]').length == 0){
            $('#contenedor-listado').append('<div class="contenedor-especialidad" data-especialidad="' + especialidad + '"><h3 style="opacity:0">' + especialidad + '</h3>');
            $('#contenedor-listado').width($('#contenedor-listado').width() + 293);       
        }
        var contenedor = $('.contenedor-especialidad[data-especialidad="'+ especialidad +'"]'),
            hora = parseInt(dato['hora'].substring(0, 2)),
            horaAnterior;
        if(contenedor.find('section').length > 0)
            horaAnterior = parseInt(contenedor.children('section:last-child').data('hora').substring(0, 2))
        else horaAnterior = 0;
        if(hora > horaAnterior)
            html = '<div class="hora" style="opacity:0"><span>' + hora + ':00</span><hr></div>';
        html += '<section data-id="' + dato['idCita'] + '" data-hora="' + dato['hora'] + '" class="con-sombra"  style="opacity:0"><div id="icono-editar"></div><h3>' + dato['paciente'] + '</h3><h5 id="dni" data-dni="' + dato['dni'] + '">DNI: ' + dato['dni'] + '</h5><h5>Médico: ' + dato['nombreMedico'] + '</h5><span>' + dato['hora'] + '</span></section>';
        contenedor.append(html);
    });

    cargarFuncionesListado();
    colorEspecialidades();
    animacionMostrarLista();
}

function cargarFuncionesListado(){
    adaptarMainListado();
    $(window).resize(adaptarMainListado);

    $('main').scroll(function(){
        $('.contenedor-especialidad > h3').css('margin-top', $(this).scrollTop()-40)
    });

    $('section').click(function(){
        var $elemento = $(this),
            posicion = $elemento.offset(),
            margen = parseInt($('#envoltorio').css('margin-left')),
            diferenciaTam = $elemento.outerHeight() - 137;
        $elemento.addClass('mostrar-cortina');
        $('#menu-opciones').addClass('mostrar-cortina');
        $('#menu-opciones').css({'top' : posicion.top + diferenciaTam, 'left' : (posicion.left - margen)});
        mostrarCortina();
    });
}

function colorEspecialidades(){
    var colores = ['#2980b9', '#ed6f45', '#2c3e50', '#A32C36', '#329d8a', '#3E50B4', '#16a085'], i = 0;
    $('.contenedor-especialidad > h3').each(function(){
        if(i == colores.length) i = 0;
        $(this).css('border-color', colores[i]);
        i++;
    })
}

function animacionMostrarLista(){
    var time;
    $('.contenedor-especialidad').each(function(){
        time = 0;
        $('.contenedor-especialidad * ').each(function(){
            mostrarElemento($(this), time);
            time += 1;
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


$('#funcion-observacion').click(function(){
    var dni = $('section.mostrar-cortina #dni').data('dni'),
        nombre = $('section.mostrar-cortina h3').text(),
        idCita = $('section.mostrar-cortina').data('id');
    cambiarPagina('observaciones-paciente#dni=' + dni + '&nombrePaciente=' + nombre);
    //cambiarPagina('observaciones-paciente?idCita=' + idCita);
});
$('#funcion-editar').click(function(){
    var idCita = $('section.mostrar-cortina').data('id'),
        dni = $('section.mostrar-cortina #dni').data('dni'),
        idCita = $('section.mostrar-cortina').data('id');
    //cambiarPagina('pedir-cita#dni=' + dni + '&idCita=' + idCita);
    cambiarPagina('pedir-cita?dni=' + dni + '&idCita=' + idCita);
});
$('#funcion-eliminar').click(function(){
    var idCita = $('section.mostrar-cortina').data('id');
    $.post('/eliminar-cita', {'idCita' : idCita}).always(function(){
        cambiarPagina('listado-citas');
    });
});
$('#funcion-cancelar').click(function(){ ocultarCortina(); });


// Elimina la función onWindowResize especifica de esta página para que no afecte a las demás
$(window).on('cambioURL', recuperaWindowResizeAntiguo);
function recuperaWindowResizeAntiguo(){
    $(window).off('resize', adaptarMainListado)
        .off('cambioURL', recuperaWindowResizeAntiguo);
}