var hash =  window.location.hash,
    matchHashDni = hash.match(/dni=(\d{8}[a-zA-Z])/),
    matchHashNombre = hash.match(/nombrePaciente=([a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+)/),
    DNI, NOMBRE_PACIENTE;

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
        var json = {
                "opcion" : "aniadirObservacion",
                "texto" : texto,
                "fecha" : fecha
            },
            objConfigAjax = {
                method : "POST",
                url: "ServletCita",
                data : json
            }

        $.ajax(objConfigAjax).done(function(data) {
            ainadirObservacion(texto, fecha);
        }).fail(function() {
            alert( "Ha habido un error al guardar los datos." );
            location.reload();
        });
    }
    return false;
});

function ainadirObservacion(texto, fecha){
        $('#observaciones-existentes > h1').slideUp();
        $('#observaciones-existentes').prepend('<section style="display:none" class="con-sombra"><div class="fecha"><span>' + fecha +'</span><hr></div>' + texto + '</section>');
        $('#texto-observacion').val('');
        $('#observaciones-existentes > section:first').slideDown();
}

function cargarObservaciones(json){
    var datos = JSON.parse(json)['listaObservaciones'];
    if(datos.length != 0){
        time = 0;
        $.each(datos, function(i, dato){
            setTimeout(function(){
                ainadirObservacion(dato['observacion'], dato['fecha'])
            }, time);

            time += 100;
        });
    } else {
        $('#observaciones-existentes').html('<h1>No existen observaciones anteriores</h1>');
    }
}