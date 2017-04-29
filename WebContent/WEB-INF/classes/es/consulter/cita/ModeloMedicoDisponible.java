package es.consulter.cita;

public class ModeloMedicoDisponible {
	private int idMedico;
	private String nombreMedico;
	private String [] horas;
	
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}
	
	public String[] getHoras() {
		return horas;
	}
	public void setHoras(String[] horas) {
		this.horas = horas;
	}
	
}
