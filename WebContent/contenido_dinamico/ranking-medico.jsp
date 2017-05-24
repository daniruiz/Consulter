<%@page contentType="text/html; charset=UTF-8" %>


<jsp:useBean id="Pagina" class="es.consulter.ranking.PaginaRanking">
		<jsp:setProperty name="Pagina" property="pageContext" value="<%=pageContext%>" />
</jsp:useBean>

<%@page contentType="text/html; charset=UTF-8" %>
    <link rel="stylesheet" type='text/css' href="/contenido_dinamico/css/ranking-medico.css" />
    <div id="contenedor-ranking"></div>
    <script src="/contenido_dinamico/js/ranking-medico.js?version=<%=Pagina.getRandomNumber()%>"></script>
    <script>        
        json = <%=Pagina.getJsonRanking()%>;
        
        cargarRanking(json);
    </script>