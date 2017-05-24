<%@page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="Pagina" scope="page" class="es.consulter.cita.PaginaCita">
	<jsp:setProperty name="Pagina" property="pageContext" value="<%=pageContext%>" />
</jsp:useBean>

<link rel="stylesheet" type='text/css' href="/contenido_dinamico/css/listado-citas.css" />

<input type="text" id="dia" disabled>
<div id="menu-opciones">
    <span id="funcion-observacion" class="boton-verde">añadir observación</span>
    <span id="funcion-editar" class="boton-azul">editar</span>
    <span id="funcion-eliminar" class="boton-granate">eliminar</span>
    <span id="funcion-cancelar" class="boton-rojo">cancelar</span>
</div>
<div id="contenedor-listado"></div>
<script src="/contenido_dinamico/js/listado-citas.js?version=<%=Pagina.getRandomNumber()%>"></script>
<script>
    var json = <%= Pagina.getJsonListadoCitas() %>;
    cargarListado(json);
</script>