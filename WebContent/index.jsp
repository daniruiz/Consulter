<!doctype html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<script src="lib/jquery.js"></script>
		<script src="js/index.js"></script>
	</head>
	<body>
		<div id="cortina"></div>
		<div id="login">
			<form autocomplete="off">
				<input type="text" placeholder="Usuario" name="usuario">
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
		<footer></footer>
	</body>
</html>
