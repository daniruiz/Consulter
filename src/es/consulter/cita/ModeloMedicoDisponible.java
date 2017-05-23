package es.consulter.cita;

import java.util.ArrayList;
import java.util.List;

public class ModeloMedicoDisponible {
	private int idMedico;
	private String nombreMedico;
	private List<String> horas;
	
	public ModeloMedicoDisponible(){
		horas = new ArrayList<String>();
	}
	
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
	
	public List<String> getHoras() {
		return horas;
	}
	public void setHoras(List<String> horas) {
		this.horas = horas;
	}
	
	public void addHora(String hora){
		horas.add(hora);
	}
}
