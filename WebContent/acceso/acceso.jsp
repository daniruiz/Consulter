<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui" name="viewport" />
		<link rel="stylesheet" type="text/css" href="/acceso/acceso.css">
		<link rel="stylesheet" type="text/css" href="/css/comun.css">
		<script src="/lib/jquery.js"></script>
        <script src="/acceso/acceso.js"></script>
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-title"content="Consulter">
		<link rel="icon" href="/img/icon.ico">
		<title>Consulter-login</title>
	</head>
	<body>
		<div id="cortina"></div>
		<main>
            <h1>Consulter</h1>
            <div class="login-incorrecto">login incorrecto</div>
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
        </main>
	</body>
</html>
