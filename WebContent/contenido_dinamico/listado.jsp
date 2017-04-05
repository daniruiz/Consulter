<!-- citas para gestor -->

<link rel="stylesheet" type='text/css' href="/contenido_dinamico/css/listado-citas.css"/>
<link rel="stylesheet" type='text/css' href="/lib/css/datepicker.css"/>

<input name="dia">


<div class="hora"><span>9:00</span><hr></div>
<section class="con-sombra">
	<h3>Roberto Perez</h3>
	<h5>paciente: Francisco Javier	Teixeira</h5>
	<span>9:43</span>
</section>
<section class="con-sombra">
	<h3>Roberto Perez</h3>
	<h5>paciente: Josefa Rodr&iacute;guez</h5>
	<span>9:50</span>
</section>
<section class="con-sombra">
	<h3>Luis Moreno</h3>
	<h5>paciente: Manuel	Vidal</h5>
	<span>9:50</span>
</section>
<section class="con-sombra">
	<h3>Roberto Perez</h3>
	<h5>paciente: Juan Jos&eacute;	Santos</h5>
	<span>9:52</span>
</section>

<div class="hora"><span>11:00</span><hr></div>
<section class="con-sombra">
	<h3>Sofia Garrido</h3>
	<h5>paciente: Josefa	Chaves</h5>
	<span>11:20</span>
</section>

<div class="hora"><span>12:00</span><hr></div>
<section class="con-sombra">
	<h3>Sara Ruiz</h3>
	<h5>paciente: Mar&iacute;a Teresa	Parra</h5>
	<span>12:00</span>
</section>
<section class="con-sombra">
	<h3>Isabel Hernandez</h3>
	<h5>paciente: Manuel	L&oacute;pez</h5>
	<span>12:10</span>
</section>

<script src="/lib/timepicki.js"></script>
<script src="/lib/bootstrap-datepicker.js"></script>
<script>
	var hoy = new Date(),
		dia = ('0' + hoy.getDate()).slice(-2),
		mes = ('0' + (hoy.getMonth() + 1)).slice(-2),
		aino = hoy.getFullYear();
	$('input[name=dia]').val(dia + '/' + mes + '/' + aino);
	$('input[name=dia]').datepicker('hide');
</script>