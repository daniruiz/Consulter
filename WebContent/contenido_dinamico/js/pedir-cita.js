var hash =  window.location.hash,
    matchHashDni = hash.match(/dni=(\d{8}[a-zA-Z])/),
    matchHashIdCita = hash.match(/idCita=(\d+)/),
    ID_CITA = 0, DNI = 0;
if(matchHashDni != null && matchHashIdCita != null){
    DNI = matchHashDni[1];
    ID_CITA = matchHashIdCita[1];
}
if(DNI != 0){
    $('#formulario-dni-paciente').hide();
    $('#dni').val(DNI);
    $('#texto1').append(DNI);
    $('#formulario-cita').css('display', 'block');
}

//-------- FOMULARIO USUARIO

$("#consultarPaciente").on("click", function(){
    var dni = $('#dni').val();
    if(/\d{8}[a-zA-Z]/.test(dni)){

        var json = {
            "opcion" : "consultarDNI",
            "dniComprobar" : dni
        },
            objConfigAjax = {
                method : "POST",
                url: "ServletCita",
                data : json
            }

        $.ajax(objConfigAjax).done(function(data) {
            var json = JSON.parse(data);

            //alert(json.existePaciente);

            if(json.existePaciente){
                $('#formulario-dni-paciente').hide();
                $('#formulario-cita').show();
                $('#dni').val(dni);
                $('#texto1').append(dni);

            }else{
                $('#ocultoPacienteInexistente').show();
            }

            /*alert("Guardada cita para " + medico + " a la hora " + hora);
            cambiarPagina('listado-citas');*/

        }).fail(function() {
            alert( "Ha habido un error al guardar los datos." );
            location.reload();
        });


    } else alert('introduzca un DNI valido');

});

$('#formulario-dni-paciente').submit(function(e){
    e.preventDefault();
    var dni = $('#dni').val();
    if(/\d{8}[a-zA-Z]/.test(dni)){

        var json = {
                "opcion" : "consultarDNI",
                "dniComprobar" : dni
            },
            objConfigAjax = {
                method : "POST",
                url: "ServletCita",
                data : json
            }

        $.ajax(objConfigAjax).done(function(data) {
            var json = JSON.parse(data);

            if(json.existePaciente){
                $('#formulario-dni-paciente').hide();
                $('#formulario-cita').show();
                $('#dni').val(dni);
                $('#texto1').append(dni);

            } else {
                $('#ocultoPacienteInexistente').show();
            }

        }).fail(function() {
            alert( "Ha habido un error al guardar los datos." );
            location.reload();
        });


    } else alert('introduzca un DNI valido');
    return false;
});

$('#darAltaPaciente').click(function(){
    cambiarPagina('alta-paciente#dni=' + $('#dni').val());
});


//-------- INICIALIZACIÓN
var diaManana = new Date(new Date().getTime() + 24 * 60 * 60 * 1000),
    dia = ('0' + diaManana.getDate()).slice(-2),
    mes = ('0' + (diaManana.getMonth() + 1)).slice(-2),
    aino = diaManana.getFullYear(),
    semana = new Date(new Date().getTime() + 7 * 24 * 60 * 60 * 1000)
$('#dia').val(dia + '/' + mes + '/' + aino).datepicker({
    startDate: diaManana,
    endDate: semana,
    format: "dd/mm/yyyy",
    maxViewMode: 0,
    language: "es",
    autoclose: true,
    container: ".datepicker_wrap"
});

$('#rango-inicio').timepicki({start_time: ["08", "00"]});
$('#rango-fin').timepicki({start_time: ["20", "00"]});


//-------- ELEMENTOS
MINUTOS_POR_PIXEL = 2.5;
TAM_BORDE_BARRA = 20;
HORA_LIMITE_INICIO = 8;

$('.slider-circulo').on('mousedown touchstart', function(e){
    $('.timepicker_wrap').hide();
    $('html, body').css('cursor','ew-resize');
    var lado = $(this).hasClass('slider-circulo-inicio') ? 'inicio' : 'fin';
    $(window).on('mousemove touchmove', function(e){
        var posicionX = (e.touches == undefined) ? e.pageX : e.touches[0].pageX,
            desplazamiento = posicionX < $('.slider-circulo-' + lado).offset().left ? -1 : 1;
        moverSlider(lado, desplazamiento);
    }).on('mouseup touchend', function(){
        $(this).off('mousemove touchmove');
        $('html, body').css('cursor','initial');
    });
});

$('.time .action-next').click(function(){
    var input = $(this).parents('.time_pick').children('input'),
        lado = input.is('#rango-inicio') ? 'inicio' : 'fin',
        desplazamiento = 2; /* 1 hora */
    moverSlider(lado, desplazamiento);
});
$('.time .action-prev').click(function(){
    var input = $(this).parents('.time_pick').children('input'),
        lado = input.is('#rango-inicio') ? 'inicio' : 'fin',
        desplazamiento = -2; /* -1 hora */
    moverSlider(lado, desplazamiento);
});
$('.mins .action-next').click(function(){
    var input = $(this).parents('.time_pick').children('input'),
        lado = input.is('#rango-inicio') ? 'inicio' : 'fin',
        desplazamiento = 1; /* 30 minutos */
    moverSlider(lado, desplazamiento);
});
$('.mins .action-prev').click(function(){
    var input = $(this).parents('.time_pick').children('input'),
        lado = input.is('#rango-inicio') ? 'inicio' : 'fin',
        desplazamiento = -1; /* -30 minutos */
    moverSlider(lado, desplazamiento);
});

function moverSlider(lado, desplazamiento) {
    var inicio = $('#selector-horario').data('inicio'),
        fin = $('#selector-horario').data('fin');
    if(lado == 'inicio'){
        var nuevoInicio = inicio + desplazamiento;
        if(nuevoInicio >= 0 && nuevoInicio < fin)
            inicio = nuevoInicio;
    } else if(lado == 'fin') {
        var nuevoFin = fin + desplazamiento;
        if(nuevoFin > inicio && nuevoFin <= 24)
            fin = nuevoFin;
    }
    $('#selector-horario').data({'inicio' : inicio, 'fin' : fin});
    $('#slider-barra').css({
        'margin-left' : inicio * 30 / MINUTOS_POR_PIXEL,
        'width' : (fin - inicio) * 30 / MINUTOS_POR_PIXEL
    });
    actualizarHora();
    if($('.especialidad:checked').length == 1) quitaMedicosFueraDeRango()
}

function actualizarHora(){
    var inicio = $('#selector-horario').data('inicio'),
        fin = $('#selector-horario').data('fin'),
        horaInicio = devolverHora(inicio * 30),
        minutoInicio = devolverMinuto(inicio * 30),
        horaFin = devolverHora(fin * 30),
        minutoFin = devolverMinuto(fin * 30);
    $('#rango-inicio').val(horaInicio+':'+minutoInicio)
        .data({'timepicki-tim' : horaInicio, 'timepicki-mini' : minutoInicio});
    $('#rango-fin').val(horaFin+':'+minutoFin)
        .data({'timepicki-tim' : horaFin, 'timepicki-mini' : minutoFin});
}

function devolverHora(m){
    return ('0' + (Math.floor(m / 60) + HORA_LIMITE_INICIO)).slice(-2);
}

function devolverMinuto(m){
    return ('0' + Math.floor(m % 60)).slice(-2);
}


//------- CARGAR MEDICOS
$('.especialidad, #dia').change(function(){pedirMedicosDisponibles()});

function pedirMedicosDisponibles() {
    if($('.especialidad:checked').length == 1){
        var medicos = {
            'Roberto Perez' : {
                'id' : 2,
                'horas' : ['08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00','12:30','13:00','13:30','14:00']
            },
            'Luis Moreno' : {
                'id' : 3,
                'horas' : ['08:00','08:30','09:30','10:30','11:00','12:00','12:30','13:30','14:00']
            },
            'Isabel Hernandez' : {
                'id' : 1,
                'horas' : ['08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00','12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00']
            }
        }

        var html = '';

        $.each(medicos, function(nombre, medico){
            html += '<div data-id="' + medico.id + '"><h4>' + nombre + '</h4><div>';
            $.each(medico.horas, function(i, hora){
                html += '<span class="hora-disponible">' + hora + '</span> ';
            });
            html += '</div></div>'
        });
        $('#medicos-disponibles').html(html);

        $('#medicos-disponibles span').click(function(){
            $('#medicos-disponibles span.seleccionado').removeClass('seleccionado');
            $(this).addClass('seleccionado');
        });
        quitaMedicosFueraDeRango();
    }
}

function quitaMedicosFueraDeRango() {
    $('#medicos-no-disponibles').hide();
    $('#medicos-disponibles div').removeClass('oculto');
    $('.hora-disponible').removeClass('oculto');
    var inicio = parseInt($('#rango-inicio').val().replace(':', '')),
        fin = parseInt($('#rango-fin').val().replace(':', ''));
    $('.hora-disponible').each(function(){
        var hora = parseInt($(this).text().replace(':', ''));
        if(inicio > hora || hora > fin) $(this).addClass('oculto');
    });
    $('#medicos-disponibles div').each(function(){
        if($(this).children(':not(.oculto)').length == 0) $(this).parent('div').addClass('oculto');
    });
    if($('#medicos-disponibles > div:not(.oculto)').length == 0) $('#medicos-no-disponibles').show();
}


//-------- ENVIAR FORMULARIO
$('#formulario-cita').submit(function(e){
    e.preventDefault();
    var $hora = $('#medicos-disponibles .hora-disponible.seleccionado'),
        hora = $hora.text();
    medico = $hora.parents('div[data-id]').data('id');
    if(validarFormulario()){
        var datos = {
            'dni': $('#dni').val(),
            'dia': $('#dia').val(),
            'hora': hora,
            'idmedico': medico,
            "especialidad" : $('input[name=especialidad]:checked').attr("data-name")
        },
            json = {
                "opcion" : "guardarCita",
                "datos" : JSON.stringify(datos),
                "idCita" : ID_CITA
            },
            objConfigAjax = {
                method : "POST",
                url: "ServletCita",
                data : json
            }

        $.ajax(objConfigAjax).done(function(data) {
            alert("Guardada cita para " + medico + " a la hora " + hora);
            cambiarPagina('listado-citas');
        }).fail(function() {
            alert( "Ha habido un error al guardar los datos." );
            location.reload();
        });
    }  else $('html, body').animate({scrollTop: 0},200); 
    return false;
});

function validarFormulario(){
    $('.formulario-incorrecto').removeClass('formulario-incorrecto');
    if($('.hora-disponible.seleccionado:not(.oculto)').length == 0)
        $('#texto2').addClass('formulario-incorrecto');
    return $('.formulario-incorrecto').length == 0;
}