<%@page contentType="text/html; charset=UTF-8" %>

<!doctype html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui" name="viewport" />
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<link rel="stylesheet" type="text/css" href="css/comun.css">
		<script src="lib/jquery.js"></script>
		<script src="js/index.js"></script>
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-title"content="Consulter">
		<link rel="icon" href="img/icon.ico">
		<title>Consulter</title>
	</head>
	<body>
		<nav>
			<!-- gestor -->
			<span data-dir="listado-citas">lista de citas</span>
			<span data-dir="listado-medicos">listado medicos</span>
			<span data-dir="listado-pacientes">listado pacientes</span>
			<span data-dir="alta-medico">alta m&eacute;dico</span>
			<span data-dir="alta-paciente">alta paciente</span>
			<span data-dir="pedir-cita">nueva cita</span>

			
			<!-- medico
			<span data-dir="ficha.jsp">ficha de paciente</span>
			<span data-dir="citas-medico.jsp">Mis citas</span> -->


		</nav>
		<div id="envoltorio">
			<header>
				<div>
					<div id="mostrar_menu">
						<div></div>
						<div></div>
						<div></div>
					</div>
					<h1>Usuario</h1>
				</div>
				<!--<div id="search">
					<img src="img/search.svg">
				</div>-->
			</header>
			<main></main>
			<footer></footer>
		</div>
	</body>
