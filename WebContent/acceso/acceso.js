$(document).ready(function(){
    $('form').submit(function(e){
        e.preventDefault();
        
		var usuario = $('input[name=usuario]').val();
		
        $.post('/login', $(this).serialize(), function(data){
            var respuesta = data;
            if(respuesta){
				localStorage.setItem('usuario', usuario);
                history.pushState('','','/listado-citas');
                $('body').fadeOut(200, function(){
                     window.location = '/listado-citas';
                });
            } else {
                $('input[name=pass]').val('');
                $('.login-incorrecto').attr('style', '').show();
            }
        });
        return false;
    });
});