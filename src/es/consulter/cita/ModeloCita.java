package es.consulter.cita;

import java.util.ArrayList;
import java.util.List;

public class ModeloCita {

	 private int idCita;
	 private int idmedico;
	 private String dia;
	 private String hora;
	 private int idpaciente;
	 private int idFicha;
	 
	 private String nombreMedico,
	 				especialidad,
	 				paciente,
	 				dni;
	 
	 private List<ObservacionCita> listaObservaciones;
	 
	public int getIdmedico() {
		return idmedico;
	}
	public void setIdmedico(int idmedico) {
		this.idmedico = idmedico;
	}
	public int getIdpaciente() {
		return idpaciente;
	}
	public void setIdpaciente(int idpaciente) {
		this.idpaciente = idpaciente;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getIdPaciente() {
		  return idpaciente;
		 }
	public void setIdPaciente(int idpaciente) {
		  this.idpaciente = idpaciente;
		 }
	 
	public int getIdMedico() {
		  return idmedico;
		 }
	public void setIdMedico(int idmedico) {
		  this.idmedico = idmedico;
		 }
	public int getIdCita() {
		  return idCita;
		 }
	public void setIdCita(int idcita) {
		  this.idCita = idcita;
		 }
	public String getHora() {
		  return hora;
		 }
	public void setHora(String hora) {
		  this.hora = hora;
		 }
	public String getDia() {
		  return dia;
		 }
	public void setDia(String dia) {
		  this.dia = dia;
		 }

	public ModeloCita() {
		super();
		listaObservaciones= new ArrayList<ObservacionCita>();
	}
	public int getIdFicha() {
		return idFicha;
	}
	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
	}
	
	public void addObservacion(String observacion, String fecha){
		listaObservaciones.add(new ObservacionCita(observacion, fecha));
	}
	
	private class ObservacionCita{
		private String 	observacion,
						fecha;
		
		protected ObservacionCita (String observacion, String fecha){
			this.observacion = observacion;
			this.fecha = fecha;
		}
		
		protected String getObservacion() {
			return observacion;
		}
		protected void setObservacion(String observacion) {
			this.observacion = observacion;
		}

		protected String getFecha() {
			return fecha;
		}
		protected void setFecha(String fecha) {
			this.fecha = fecha;
		}
		
	}
}


