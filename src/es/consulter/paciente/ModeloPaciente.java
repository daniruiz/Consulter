package es.consulter.paciente;

public class ModeloPaciente {
	 private String nombre;
	 private String apellido;
     private String compania_seguros;
	 private String dni;
	 private int idpaciente;
	 
	 
	public int getIdPaciente() {
		return idpaciente;
	}

	public void setIdPaciente(int id) {
		this.idpaciente = idpaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCompaniaSeguros() {
		return compania_seguros;
	}

	public void setCompaniaSeguros(String compania_seguros) {
		this.compania_seguros = compania_seguros;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public ModeloPaciente() {
		super();

	}
}


