package es.consulter.ficha;

public class ModeloFicha {
	private int idficha;
	private int idpaciente;
	private int idespecialdad;
	private int medico_mod;
	private int idespecialista;
	private String texto;
	private String fecha_mod;
	
	private String [] historicoObservacion;

	public int getIdficha() {
		return idficha;
	}

	public void setIdficha(int idficha) {
		this.idficha = idficha;
	}

	public int getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(int idpaciente) {
		this.idpaciente = idpaciente;
	}

	public int getIdespecialdad() {
		return idespecialdad;
	}

	public void setIdespecialdad(int idespecialdad) {
		this.idespecialdad = idespecialdad;
	}

	public int getMedico_mod() {
		return medico_mod;
	}

	public void setMedico_mod(int medico_mod) {
		this.medico_mod = medico_mod;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getFecha_mod() {
		return fecha_mod;
	}

	public void setFecha_mod(String fecha_mod) {
		this.fecha_mod = fecha_mod;
	}

	public int getIdespecialista() {
		return idespecialista;
	}

	public void setIdespecialista(int idespecialista) {
		this.idespecialista = idespecialista;
	}

	public ModeloFicha() {
		super();

	}

	public String [] getHistoricoObservacion() {
		return historicoObservacion;
	}

	public void setHistoricoObservacion(String [] historicoObservacion) {
		this.historicoObservacion = historicoObservacion;
	}

}
