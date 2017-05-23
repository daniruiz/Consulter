<%@page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="Pagina" scope="page" class="es.consulter.utils.PaginaIndex">
	<jsp:setProperty name="Pagina" property="pageContext" value="<%=pageContext%>" />
</jsp:useBean>

    <!doctype html>

    <html>
        <head>
            <meta charset="UTF-8">
            <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui" name="viewport" />
            <link rel="stylesheet" type="text/css" href="css/index.css">
            <link rel="stylesheet" type="text/css" href="css/comun.css">
            <script src="lib/jquery.js"></script>
            <script src="js/index.js?version=<%=Pagina.getRandomNumber()%>"></script>
            <meta name="apple-mobile-web-app-capable" content="yes" />
            <meta name="apple-mobile-web-app-title" content="Consulter">
            <link rel="icon" href="img/icon.ico">
            <title>Consulter</title>
            
            <script type="text/javascript">
            
            	perfil = <%=Pagina.getPerfil()%>
            	//alert(index);
            </script>
            
        </head>
        <body>
            <div id="cortina"></div>
            <nav>
                <img src="/img/logo.svg">
                <span data-dir="listado-citas">lista de citas</span>
                <span data-dir="alta-medico">alta m&eacute;dico</span>
                <span data-dir="alta-paciente">alta paciente</span>
                <span data-dir="pedir-cita">nueva cita</span>
                <span data-dir="ranking-medico">ranking medico</span>
            </nav>
            <div id="envoltorio">
                <header>
                    <div>
                        <div id="mostrar_menu" title="mostrar/ocultar panel lateral">
                            <div></div>
                            <div></div>
                            <div></div>
                        </div>
                        <h1></h1>
                    </div>
                    <div id="cerrar-session" title="cerrar sesión">
                        <img src="img/close.svg">
                    </div>
                </header>
                <main></main>
                <footer>
                    <img src="/img/logo.svg">
                    <div>
                        <div>
                            Guillermo Ramírez Barber<br>
                            Daniel Ruiz de Alegría<br>
                            Hector Dimove
                        </div>
                        <div>
                            Manuel Rodriguez Ajo<br>
                            David Ramírez Ruiz<br>
                            Jason
                        </div>
                    </div>
                </footer>
            </div>
        </body>
