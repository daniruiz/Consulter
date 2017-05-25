package es.consulter.medico;


public class ModeloMedico {
	 
	 private String nombre;
	 private String apellido;
	 private String numColegiado;
	 private String [] diasSemana;
	 private int tiempo_consulta;
	 
	 private String dni;
	 private int id_usuario;
	 private int idmedico;
	 
	 private int [] especialidad;
	 
	 
	 public String[] getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(String[] diasSemana) {
		this.diasSemana = diasSemana;
	}

	 public int getIdMedico() {
		 return idmedico;
	 }
	 public void setIdMedico(int idmedico) {
		 this.idmedico = idmedico;
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
	 
	public String getNumColegiado() {
		return numColegiado;
	}

	public void setNumColegiado(String numColegiado) {
		this.numColegiado = numColegiado;
	}

	public int[] getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(int[] especialidad) {
		this.especialidad = especialidad;
	}

	public int getTiempoConsulta() {
		return tiempo_consulta;
	}

	public void setTiempoConsulta(int tiempo_consulta) {
		this.tiempo_consulta = tiempo_consulta;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getIdUsuario() {
		return id_usuario;
	}

	public void setIdUsuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	 
	 
	public ModeloMedico() {
		super();

	}
}

