var hoy = new Date(),
	dia = ('0' + hoy.getDate()).slice(-2),
	mes = ('0' + (hoy.getMonth() + 1)).slice(-2),
	aino = hoy.getFullYear();
$('#dia').text(dia + '/' + mes + '/' + aino);


var datos = [
	{
		'nombreMedico' : 'Roberto Perez',
		'especialidad' : 'Ginecología',
		'paciente' : 'Marta Feijoo',
		'idCita' : 1,
		'hora' : '08:00'
	},
	{
		'nombreMedico' : 'Luis Moreno',
		'especialidad' : 'Odontología',
		'paciente' : 'Pedro Castellano',
		'idCita' : 2,
		'hora' : '08:30'
	},
	{
		'nombreMedico' : 'Sofía Garrido',
		'especialidad' : 'Cardiología',
		'paciente' : 'Isabel Hernandez',
		'idCita' : 3,
		'hora' : '08:30'
	},
	{
		'nombreMedico' : 'Sara Ruiz',
		'especialidad' : 'Psiquiatría',
		'paciente' : 'Rebeca Larrañaga',
		'idCita' : 4,
		'hora' : '10:00'
	},
	{
		'nombreMedico' : 'Roberto Perez',
		'especialidad' : 'Neurología',
		'paciente' : 'Josefa Rodrígez',
		'idCita' : 5,
		'hora' : '10:00'
	},
	{
		'nombreMedico' : 'Roberto Perez',
		'especialidad' : 'Pediatría',
		'paciente' : 'Adrian Ruiz',
		'idCita' : 6,
		'hora' : '19:00'
	},
	{
		'nombreMedico' : 'Roberto Perez',
		'especialidad' : 'Oftalmología',
		'paciente' : 'Josefa Rodrígez',
		'idCita' : 7,
		'hora' : '19:30'
	}
];

var maxHora =  0, html = '', hora;
$.each(datos, function(i, dato){
	hora = parseInt(dato['hora'].substring(0, 2));
	html = '';
	if(hora > maxHora){
		maxHora = hora;
		html = '<div class="hora"><span>' + maxHora + ':00</span><hr></div>';
	}
	html += '<section data-id="' + dato['idCita'] + '" class="con-sombra">\
				<h3>' + dato['paciente'] + '</h3>\
				<h5>' + dato['nombreMedico'] + '</h5>\
				<h5>' + dato['especialidad'] + '</h5>\
				<span>' + dato['hora'] + '</span>\
			</section>';
	$('main').append(html);
});
