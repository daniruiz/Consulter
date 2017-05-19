var hash =  window.location.hash,
    matchHashDni = hash.match(/dni=(\d{8}[a-zA-Z])/),
    matchHashNombre = hash.match(/nombrePaciente=([a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+)/),
    DNI, NOMBRE_PACIENTE;
if(matchHashDni == null || matchHashNombre == null) cambiarPagina('');
DNI = matchHashDni[1];
NOMBRE_PACIENTE = matchHashNombre[1];
$('#dni').text(DNI);
$('#nombre-paciente').text(NOMBRE_PACIENTE);

$('#formulario-observaciones').submit(function(e){
    e.preventDefault();
    var texto = $('#texto-observacion').val();
    if(texto != ''){
        var hoy = new Date(),
            dia = ('0' + hoy.getDate()).slice(-2),
            mes = ('0' + (hoy.getMonth() + 1)).slice(-2),
            aino = hoy.getFullYear(),
            fecha = dia + '/' + mes + '/' + aino;
        //$.post('url', {texto, fecha, 'dni' : DNI}, function(){
            ainadirObservacion(texto, fecha)
        //});
    }
    return false;
});

function ainadirObservacion(texto, fecha){
    $('#observaciones-existentes').prepend('<section style="display:none" class="con-sombra"><div class="fecha"><span>' + fecha +'</span><hr></div>' + texto + '</section>');
        $('#texto-observacion').val('');
$('#observaciones-existentes > section:first').slideDown();
}

function cargarObservaciones(json){
    var datos = JSON.parse(json), time = 0;
    $.each(datos, function(i, dato){
        setTimeout(function(){
        ainadirObservacion(dato['texto'], dato['fecha'])
        }, time);
        time += 100;
    });
}