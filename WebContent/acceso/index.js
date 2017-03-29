$(document).ready(function(){
    $('form').submit(function(e){
        e.preventDefault();
        $.post('/login', $(this).serialize(), function(data){
            var respuesta = data;
            if(respuesta == 'true'){
                history.pushState('','','/');
                $('body').fadeOut(200, function(){
                     window.location = '/';
                });
            } else {
                $('input[name=pass]').val('');
                $('.login-incorrecto').attr('style', '').show();
            }
        });
        return false;
    });
});