<%@page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="Pagina" scope="page" class="es.consulter.cita.PaginaCita">
	<jsp:setProperty name="Pagina" property="pageContext" value="<%=pageContext%>" />
</jsp:useBean>

	
	<script>
		
		
	</script>
	
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
        var datos = [
            {
                'fecha' : '1/9/2017',
                'texto' : 'ejemplo texto 1'
            },
            {
                'fecha' : '1/3/2017',
                'texto' : 'ejemplo texto 2'
            },
            {
                'fecha' : '23/2/2017',
                'texto' : 'ejemplo texto 3'
            },
            {
                'fecha' : '1/3/2016',
                'texto' : 'ejemplo texto 4'
            }
        ];
        json = JSON.stringify(datos);
        json = <%=Pagina.getObservacionesCita()%>
        cargarObservaciones(json);
    </script>