<%@page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="Pagina" scope="page" class="es.consulter.cita.PaginaCita">
	<jsp:setProperty name="Pagina" property="pageContext" value="<%=pageContext%>" />
</jsp:useBean>
	
<link rel="stylesheet" type="text/css" href="/contenido_dinamico/css/observaciones-paciente.css">
<section id="seccion-principal">
    <form id="formulario-observaciones">
        <div>
            <div id="nombre-paciente"></div>
            <div id="dni"></div>
            <textarea id="texto-observacion" placeholder="Escriba aquí sus observaciones" autofocus></textarea>
            <button type="submit" class="boton-azul">Enviar</button>
        </div>
    </form>
</section>
<section id="observaciones-existentes">
</section>
<script src="/contenido_dinamico/js/observaciones-paciente.js?version=<%=Pagina.getRandomNumber()%>"></script>
<script>
    json = <%=Pagina.getObservacionesCita()%>
    cargarObservaciones(json);
</script>