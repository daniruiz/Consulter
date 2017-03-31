$('section form').submit(function(e){
    e.preventDefault();
    if(validarFormulario()){
        var datos = {
                    'nombre': $('input[name=nombre]').val(),
                    'apellidos': $('input[name=apellidos]').val(),
                    'dni': $('input[name=dni]').val(),
                    'seguro': $('input[name=seguro]').val()
                },
                json = JSON.stringify(datos);
    } else $('html, body').animate({scrollTop: 0},200); 
    return false;
});

function validarFormulario(){
    $('.formulario-incorrecto').removeClass('formulario-incorrecto');
    if(!/^[A-Za-z]+$/.test($('input[name=nombre]').val()))
            $('input[name=nombre]').addClass('formulario-incorrecto');
    if(!/^[A-Za-z]+$/.test($('input[name=apellidos]').val()))
            $('input[name=apellidos]').addClass('formulario-incorrecto');
    if(!/^\d{8}[a-zA-Z]$/.test($('input[name=dni]').val()))
            $('input[name=dni]').addClass('formulario-incorrecto');
    return $('.formulario-incorrecto').length == 0;
}