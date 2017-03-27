<style scoped>
	#envoltorio { background: #f3f3f3 }
	section{
		margin-bottom: 0;
		border-bottom: 1px solid #f3f3f3;
	}
	section > h3 {
		color: black;
	}
	section > div:first-child{
		float: left;
		width: 250px;
	}
	.calendario-semanal {
		margin-top: 20px;
		float: right;
		display: block;
		width: 365px;
	}
	section > div:first-child > span { margin-left: 20px; }
	@media only screen and (max-width: 1050px) {
		main { max-width: 600px; }
		section > div:first-child{
			float: inherit;
			margin-bottom: 20px;
			width: 100%;
			text-align: center;
		}
		section > div:first-child > span { margin-left: 0; }
		.calendario-semanal {
			float: inherit;
			margin: auto;
		}
	}
	@media only screen and (max-width: 500px) {
		.calendario-semanal { width: 255px; }
		.calendario-semanal > div > span, .calendario-semanal > span {
			height: 30px;
			width: 30px;
			line-height: 30px;
			font-size: 11px;
		}
	}
</style>
<section class="con-sombra">
	<div>
		<h3>Roberto Perez</h3>
		<span>Dermatolog&iacute;a</span>
	</div>
	<div class="calendario-semanal">
		<span class="dia-seleccionado">L</span>
		<span class="selected-day">M</span>
		<span>X</span>
		<span>J</span>
		<span>V</span>
		<span class="dia-seleccionado">S</span>
		<span>D</span>
	</div>
</section>
<section class="con-sombra">
	<div>
		<h3>Luis Moreno</h3>
		<span>Odontolog&iacute;a</span>
	</div>
	<div class="calendario-semanal">
		<span class="dia-seleccionado">L</span>
		<span class="dia-seleccionado">M</span>
		<span>X</span>
		<span>J</span>
		<span>V</span>
		<span class="dia-seleccionado">S</span>
		<span>D</span>
	</div>
</section>
<section class="con-sombra">
	<div>
		<h3>Sofia Garrido</h3>
		<span>Oftalmolog&iacute;a</span>
	</div>
	<div class="calendario-semanal">
		<span class="dia-seleccionado">L</span>
		<span class="dia-seleccionado">M</span>
		<span class="dia-seleccionado">X</span>
		<span class="dia-seleccionado">J</span>
		<span class="dia-seleccionado">V</span>
		<span>S</span>
		<span>D</span>
	</div>
</section>
<section class="con-sombra">
	<div>
		<h3>Sara Ruiz</h3>
		<span>Estomatolog&iacute;a</span>
	</div>
	<div class="calendario-semanal">
		<span class="dia-seleccionado">L</span>
		<span>M</span>
		<span>X</span>
		<span>J</span>
		<span>V</span>
		<span class="dia-seleccionado">S</span>
		<span class="dia-seleccionado">D</span>
	</div>
</section>
<section class="con-sombra">
	<div>
		<h3>Isabel Hernandez</h3>
		<span>Dermatolog&iacute;a</span>
	</div>
	<div class="calendario-semanal">
		<span class="dia-seleccionado">L</span>
		<span class="dia-seleccionado">M</span>
		<span class="dia-seleccionado">X</span>
		<span class="dia-seleccionado">J</span>
		<span>V</span>
		<span>S</span>
		<span >D</span>
	</div>
</section>
