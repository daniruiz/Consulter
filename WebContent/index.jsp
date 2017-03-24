<!doctype html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui" name="viewport" />
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<script src="lib/jquery.js"></script>
		<script src="js/index.js"></script>
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-title"content="Consulter">
		<link rel="icon" href="img/icon.ico">
		<title>Consulter</title>
	</head>
	<body>
		<div id="cortina"></div>
		<div id="login">
			<form autocomplete="off">
				<input type="text" placeholder="Usuario" name="usuario" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" autofocus>
				<input type="password" placeholder="Contrase&ntilde;a" name="pass">
				<div>
					<div>
						<span>recordar</span>
						<input type="checkbox" id="onoffswitch" name="recordar">
						<label for="onoffswitch">
							<div></div>
							<span></span>
						</label>
					</div>
					<input type="submit" value="entrar" class="boton-azul">
				</div>
			</form>
		</div>
		<div id="denegado">
			<div>ACCESO<br>DENEGADO</div>
		</div>
		<div id="envoltorio">
			<header>
				<div>
					<div id="mostrar_menu">
						<div></div>
						<div></div>
						<div></div>
					</div>
					<h1>&nbsp;</h1>
				</div>
				<nav>
					<span class="seleccionado" data-dir="listado-especialistas.jsp">listado especialistas</span>
					<span data-dir="alta-medico.jsp">alta m&eacute;dico</span>
					<span data-dir="alta-paciente.jsp">alta paciente</span>
					<!--<span data-dir="">nueva cita</span>
					<span data-dir="">ficha paciente</span>-->
					<div></div>
				</nav>
				<div id="search">
					<img src="img/search.svg">
				</div>
			</header>
			<main></main>
		</div>
		<footer></footer>
	</body>
