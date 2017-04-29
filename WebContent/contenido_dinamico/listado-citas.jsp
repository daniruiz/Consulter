
<%@page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="Page" scope="page" class="es.consulter.cita.PaginaCita"></jsp:useBean>

<%
	Page.setRequest(request);
	Page.onLoad();
%>

<link rel="stylesheet" type='text/css' href="/contenido_dinamico/css/listado-citas.css" />

<h3 id="dia"></h3>
<div id="menu-opciones">
	<span id="funcion-editar" class="boton-azul">editar</span>
	<span id="funcion-eliminar" class="boton-granate">eliminar</span>
	<span id="funcion-cancelar" class="boton-rojo">cancelar</span>
</div>
<div id="contenedor-listado"></div>
<script>
	
var json = '<%= Page.getJsonListadoCitas() %>',
	datos = JSON.parse(json);

var maxHora =  0, html = '', hora;
$.each(datos, function(i, dato){
	hora = parseInt(dato['hora'].substring(0, 2));
	html = '';
	if(hora > maxHora){
		maxHora = hora;
		html = '<div class="hora"><span>' + maxHora + ':00</span><hr></div>';
	}
	html += '<section data-id="' + dato['idCita'] + '" class="con-sombra"><div id="icono-editar"></div>\
<h3>' + dato['paciente'] + '</h3>\
<h5 id="dni" data-dni="' + dato['dni'] + '">DNI: ' + dato['dni'] + '</h5>\
<h5>Médico: ' + dato['nombreMedico'] + '</h5>\
<h5>' + dato['especialidad'] + '</h5>\
<span>' + dato['hora'] + '</span>\
</section>';
	$('#contenedor-listado').append(html);
});
</script>
<script src="/contenido_dinamico/js/listado-citas.js"></script>
