<%@page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="Pagina" scope="page" class="es.consulter.cita.PaginaCita">
	<jsp:setProperty name="Pagina" property="pageContext" value="<%=pageContext%>" />
</jsp:useBean>

<link rel="stylesheet" type='text/css' href="/contenido_dinamico/css/listado-citas.css" />

<input type="text" id="dia" disabled>
<div id="menu-opciones">
    <span id="funcion-observacion" class="boton-verde">añadir observación</span>
    <span id="funcion-editar" class="boton-azul">editar</span>
    <span id="funcion-eliminar" class="boton-granate">eliminar</span>
    <span id="funcion-cancelar" class="boton-rojo">cancelar</span>
</div>
<div id="contenedor-listado"></div>
<script src="/contenido_dinamico/js/listado-citas.js"></script>
<script>
    var json = <%= Pagina.getJsonListadoCitas() %>;
    //var json = '[{"idCita":1,"idmedico":0,"dia":0,"hora":"08:00","idpaciente":0,"nombreMedico":"Roberto Perez","especialidad":"Ginecología","paciente":"Acosta González, Álvaro","dni":"22454076G"},{"idCita":2,"idmedico":0,"dia":0,"hora":"08:00","idpaciente":0,"nombreMedico":"Luis Moreno","especialidad":"Odontología","paciente":"Alonso de Paz, Mª Isabel","dni":"75215071B"},{"idCita":3,"idmedico":0,"dia":0,"hora":"08:00","idpaciente":0,"nombreMedico":"Sofía Garrido","especialidad":"Cardiología","paciente":"Andreo Gázquez, Francisco","dni":"52801993L"},{"idCita":4,"idmedico":0,"dia":0,"hora":"08:00","idpaciente":0,"nombreMedico":"Sara Ruiz","especialidad":"Psiquiatría","paciente":"Armas Acosta, Carlos","dni":"27449907M"},{"idCita":5,"idmedico":0,"dia":0,"hora":"08:00","idpaciente":0,"nombreMedico":"Alberto Perez","especialidad":"Neurología","paciente":"Armendáriz Artacho, Natalia","dni":"23229790C"},{"idCita":6,"idmedico":0,"dia":0,"hora":"08:00","idpaciente":0,"nombreMedico":"Rigoberto Perez","especialidad":"Pediatría","paciente":"Arranz Arranz, Beatriz","dni":"22473780C"},{"idCita":7,"idmedico":0,"dia":0,"hora":"08:30","idpaciente":0,"nombreMedico":"Sigismundo Perez","especialidad":"Oftalmología","paciente":"Josefa Rodrígez","dni":"34784976Y"},{"idCita":1,"idmedico":0,"dia":0,"hora":"08:00","idpaciente":0,"nombreMedico":"Roberto Perez","especialidad":"Ginecología","paciente":"Marta Feijoo","dni":"22454076G"},{"idCita":2,"idmedico":0,"dia":0,"hora":"08:30","idpaciente":0,"nombreMedico":"Luis Moreno","especialidad":"Odontología","paciente":"Asenjo Orive, Rosa","dni":"75215071B"},{"idCita":3,"idmedico":0,"dia":0,"hora":"08:30","idpaciente":0,"nombreMedico":"Sofía Garrido","especialidad":"Cardiología","paciente":"Isabel Hernandez","dni":"52801993L"},{"idCita":4,"idmedico":0,"dia":0,"hora":"08:30","idpaciente":0,"nombreMedico":"Sara Ruiz","especialidad":"Psiquiatría","paciente":"Atanasova Rafaelova, Asya","dni":"27449907M"},{"idCita":5,"idmedico":0,"dia":0,"hora":"08:30","idpaciente":0,"nombreMedico":"Alberto Perez","especialidad":"Neurología","paciente":"Josefa Rodrígez","dni":"23229790C"},{"idCita":6,"idmedico":0,"dia":0,"hora":"08:30","idpaciente":0,"nombreMedico":"Rigoberto Perez","especialidad":"Pediatría","paciente":"Adrian Ruiz","dni":"22473780C"},{"idCita":7,"idmedico":0,"dia":0,"hora":"08:30","idpaciente":0,"nombreMedico":"Sigismundo Perez","especialidad":"Oftalmología","paciente":"Josefa Rodrígez","dni":"34784976Y"},{"idCita":1,"idmedico":0,"dia":0,"hora":"09:00","idpaciente":0,"nombreMedico":"Roberto Perez","especialidad":"Ginecología","paciente":"Bautista Fernández, Fernando","dni":"22454076G"},{"idCita":2,"idmedico":0,"dia":0,"hora":"09:00","idpaciente":0,"nombreMedico":"Luis Moreno","especialidad":"Odontología","paciente":"Pedro Castellano","dni":"75215071B"},{"idCita":3,"idmedico":0,"dia":0,"hora":"09:30","idpaciente":0,"nombreMedico":"Sofía Garrido","especialidad":"Cardiología","paciente":"Isabel Hernandez","dni":"52801993L"},{"idCita":4,"idmedico":0,"dia":0,"hora":"10:00","idpaciente":0,"nombreMedico":"Sara Ruiz","especialidad":"Psiquiatría","paciente":"Rebeca Larrañaga","dni":"27449907M"},{"idCita":5,"idmedico":0,"dia":0,"hora":"10:00","idpaciente":0,"nombreMedico":"Alberto Perez","especialidad":"Neurología","paciente":"Josefa Rodrígez","dni":"23229790C"},{"idCita":6,"idmedico":0,"dia":0,"hora":"11:30","idpaciente":0,"nombreMedico":"Rigoberto Perez","especialidad":"Pediatría","paciente":"Adrian Ruiz","dni":"22473780C"},{"idCita":7,"idmedico":0,"dia":0,"hora":"11:30","idpaciente":0,"nombreMedico":"Sigismundo Perez","especialidad":"Oftalmología","paciente":"Josefa Rodrígez","dni":"34784976Y"},{"idCita":1,"idmedico":0,"dia":0,"hora":"11:30","idpaciente":0,"nombreMedico":"Roberto Perez","especialidad":"Ginecología","paciente":"Marta Feijoo","dni":"22454076G"},{"idCita":2,"idmedico":0,"dia":0,"hora":"11:30","idpaciente":0,"nombreMedico":"Luis Moreno","especialidad":"Odontología","paciente":"Briz Ponce, Laura","dni":"75215071B"},{"idCita":3,"idmedico":0,"dia":0,"hora":"11:30","idpaciente":0,"nombreMedico":"Sofía Garrido","especialidad":"Cardiología","paciente":"Bueno Martínez, Laura","dni":"52801993L"},{"idCita":4,"idmedico":0,"dia":0,"hora":"11:30","idpaciente":0,"nombreMedico":"Sara Ruiz","especialidad":"Psiquiatría","paciente":"Rebeca Larrañaga","dni":"27449907M"},{"idCita":5,"idmedico":0,"dia":0,"hora":"11:30","idpaciente":0,"nombreMedico":"Alberto Perez","especialidad":"Neurología","paciente":"Josefa Rodrígez","dni":"23229790C"},{"idCita":6,"idmedico":0,"dia":0,"hora":"11:30","idpaciente":0,"nombreMedico":"Rigoberto Perez","especialidad":"Pediatría","paciente":"Caballero Casillas, Concepción","dni":"22473780C"},{"idCita":7,"idmedico":0,"dia":0,"hora":"12:00","idpaciente":0,"nombreMedico":"Sigismundo Perez","especialidad":"Oftalmología","paciente":"Josefa Rodrígez","dni":"34784976Y"},{"idCita":1,"idmedico":0,"dia":0,"hora":"12:00","idpaciente":0,"nombreMedico":"Roberto Perez","especialidad":"Ginecología","paciente":"Caballero Sánchez, Mª Jesús","dni":"22454076G"},{"idCita":2,"idmedico":0,"dia":0,"hora":"12:00","idpaciente":0,"nombreMedico":"Luis Moreno","especialidad":"Odontología","paciente":"Cachero Alonso, Carmen ","dni":"75215071B"},{"idCita":3,"idmedico":0,"dia":0,"hora":"12:00","idpaciente":0,"nombreMedico":"Sofía Garrido","especialidad":"Cardiología","paciente":"Cano de la Selva, Fernando","dni":"52801993L"},{"idCita":4,"idmedico":0,"dia":0,"hora":"12:00","idpaciente":0,"nombreMedico":"Sara Ruiz","especialidad":"Psiquiatría","paciente":"Castilla Hernández, María del Rocío","dni":"27449907M"},{"idCita":5,"idmedico":0,"dia":0,"hora":"12:00","idpaciente":0,"nombreMedico":"Alberto Perez","especialidad":"Neurología","paciente":"Josefa Rodrígez","dni":"23229790C"},{"idCita":6,"idmedico":0,"dia":0,"hora":"12:30","idpaciente":0,"nombreMedico":"Rigoberto Perez","especialidad":"Pediatría","paciente":"Adrian Ruiz","dni":"22473780C"},{"idCita":7,"idmedico":0,"dia":0,"hora":"13:30","idpaciente":0,"nombreMedico":"Sigismundo Perez","especialidad":"Oftalmología","paciente":"Josefa Rodrígez","dni":"34784976Y"},{"idCita":1,"idmedico":0,"dia":0,"hora":"13:30","idpaciente":0,"nombreMedico":"Roberto Perez","especialidad":"Ginecología","paciente":"Castro Estévez, José","dni":"22454076G"},{"idCita":2,"idmedico":0,"dia":0,"hora":"13:30","idpaciente":0,"nombreMedico":"Luis Moreno","especialidad":"Odontología","paciente":"García-Andrade Narro, Carlos","dni":"75215071B"},{"idCita":3,"idmedico":0,"dia":0,"hora":"14:00","idpaciente":0,"nombreMedico":"Sofía Garrido","especialidad":"Cardiología","paciente":"García Reyes, Juan Carlos","dni":"52801993L"},{"idCita":4,"idmedico":0,"dia":0,"hora":"14:00","idpaciente":0,"nombreMedico":"Sara Ruiz","especialidad":"Psiquiatría","paciente":"Rebeca Larrañaga","dni":"27449907M"},{"idCita":5,"idmedico":0,"dia":0,"hora":"14:30","idpaciente":0,"nombreMedico":"Alberto Perez","especialidad":"Neurología","paciente":"Josefa Rodrígez","dni":"23229790C"},{"idCita":6,"idmedico":0,"dia":0,"hora":"14:30","idpaciente":0,"nombreMedico":"Rigoberto Perez","especialidad":"Pediatría","paciente":"García Pardeiro, Raquel","dni":"22473780C"},{"idCita":7,"idmedico":0,"dia":0,"hora":"15:00","idpaciente":0,"nombreMedico":"Sigismundo Perez","especialidad":"Oftalmología","paciente":"Josefa Rodrígez","dni":"34784976Y"},{"idCita":1,"idmedico":0,"dia":0,"hora":"15:00","idpaciente":0,"nombreMedico":"Roberto Perez","especialidad":"Ginecología","paciente":"Marta Feijoo","dni":"22454076G"},{"idCita":2,"idmedico":0,"dia":0,"hora":"15:00","idpaciente":0,"nombreMedico":"Luis Moreno","especialidad":"Odontología","paciente":"Pedro Castellano","dni":"75215071B"},{"idCita":3,"idmedico":0,"dia":0,"hora":"15:00","idpaciente":0,"nombreMedico":"Sofía Garrido","especialidad":"Cardiología","paciente":"Cayo Ventura, Leif Erikson","dni":"52801993L"},{"idCita":4,"idmedico":0,"dia":0,"hora":"15:30","idpaciente":0,"nombreMedico":"Sara Ruiz","especialidad":"Psiquiatría","paciente":"Rebeca Larrañaga","dni":"27449907M"},{"idCita":5,"idmedico":0,"dia":0,"hora":"16:00","idpaciente":0,"nombreMedico":"Alberto Perez","especialidad":"Neurología","paciente":"Corral Alonso, José","dni":"23229790C"},{"idCita":6,"idmedico":0,"dia":0,"hora":"16:00","idpaciente":0,"nombreMedico":"Rigoberto Perez","especialidad":"Pediatría","paciente":"García de Fernando Moreno, José Luis","dni":"22473780C"},{"idCita":7,"idmedico":0,"dia":0,"hora":"16:30","idpaciente":0,"nombreMedico":"Sigismundo Perez","especialidad":"Oftalmología","paciente":"Fuentes Vega, Alicia","dni":"34784976Y"},{"idCita":1,"idmedico":0,"dia":0,"hora":"17:00","idpaciente":0,"nombreMedico":"Roberto Perez","especialidad":"Ginecología","paciente":"Marta Feijoo","dni":"22454076G"},{"idCita":2,"idmedico":0,"dia":0,"hora":"17:30","idpaciente":0,"nombreMedico":"Luis Moreno","especialidad":"Odontología","paciente":"Fontán Grech, Pilar","dni":"75215071B"},{"idCita":3,"idmedico":0,"dia":0,"hora":"17:30","idpaciente":0,"nombreMedico":"Sofía Garrido","especialidad":"Cardiología","paciente":"Ceballos Bedoya, Ainhoa","dni":"52801993L"},{"idCita":4,"idmedico":0,"dia":0,"hora":"18:00","idpaciente":0,"nombreMedico":"Sara Ruiz","especialidad":"Psiquiatría","paciente":"Rebeca Larrañaga","dni":"27449907M"},{"idCita":5,"idmedico":0,"dia":0,"hora":"18:00","idpaciente":0,"nombreMedico":"Alberto Perez","especialidad":"Neurología","paciente":"Josefa Rodrígez","dni":"23229790C"},{"idCita":6,"idmedico":0,"dia":0,"hora":"19:00","idpaciente":0,"nombreMedico":"Rigoberto Perez","especialidad":"Pediatría","paciente":"Díaz Platero, Leticia","dni":"22473780C"},{"idCita":7,"idmedico":0,"dia":0,"hora":"20:00","idpaciente":0,"nombreMedico":"Sigismundo Perez","especialidad":"Oftalmología","paciente":"Díaz Martín, Sara","dni":"34784976Y"}]';               

    cargarListado(json);
</script>