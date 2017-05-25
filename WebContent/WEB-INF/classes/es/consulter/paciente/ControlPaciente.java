package es.consulter.paciente;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import es.consulter.conexion.Conexion;
import es.consulter.medico.ModeloMedico;
import es.consulter.utils.Control;


public class ControlPaciente extends Control {
	private ModeloPaciente paciente;

	public ControlPaciente(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 	Método para administrar la inserción de una especialidad
	 * @throws Exception 
	 * */
	@Override
	public void iniciarInsertar()  {
		
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			getDataParameter();
			
			String query =  " insert into Pacientes (NOMBRE, APELLIDOS, " + 
							" COMPANIA_SEGUROS, DNI) " + 
							" values (?, ?, ?, UPPER(?))";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, paciente.getNombre());
			conexion.addParameter(2, paciente.getApellido());
			conexion.addParameter(3, paciente.getCompaniaSeguros());
			conexion.addParameter(4, paciente.getDni());
			//conexion.addParameter(5, paciente.getIdPaciente());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			conexion.desconectar();
			
			setResultado("Paciente insertado correctamente.");
			
			System.out.println("Se ha insertado en la tabla");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 	Método para administrar la actualización de una especialidad
	 * */
	@Override
	public void iniciarActualizar() {
		
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String query = "update Pacientes set NOMBRE = ?, APELLIDOS= ?, COMPANIA_SEGUROS= ?,  DNI= ? WHERE IDPACIENTE= ?";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, paciente.getNombre());
			conexion.addParameter(2, paciente.getApellido());
			conexion.addParameter(3, paciente.getCompaniaSeguros());
			conexion.addParameter(4, paciente.getDni());
			conexion.addParameter(5, paciente.getIdPaciente());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			conexion.desconectar();
			
			this.estado = true;
			
			System.out.println("Se ha actualizado de la tabla");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 	Método para administrar la eliminación de una especialidad
	 * */
	@Override
	public void iniciarEliminar() {
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String query = "delete from Pacientes where IDPACIENTE = ?";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, paciente.getIdPaciente());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			conexion.desconectar();
			
			System.out.println("Se ha eliminado de la tabla");
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		

	}

	/**
	 * 	Método para administrar la carga de una especialidad
	 * */
	@Override
	public void cargarDatos() {
		
		paciente = new ModeloPaciente();
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String query = "select NOMBRE, APELLIDOS, COMPANIA_SEGUROS, DNI from Pacientes where IDPACIENTE = ?";
			
			conexion.prepareSelect(query);
			conexion.addParameterSelect(1, paciente.getIdPaciente());
			ResultSet rs = conexion.ejecutarSelect();
			if (rs.next()) 
			{ 
				paciente.setNombre(rs.getString ("NOMBRE"));
				paciente.setApellido(rs.getString ("APELLIDOS"));
				paciente.setCompaniaSeguros(rs.getString("COMPANIA_SEGUROS"));
				paciente.setDni(rs.getString("DNI"));
			   
				 
			}
			rs.close();

			
			conexion.closePreparedSelect();
			
			conexion.desconectar();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDataParameter() {
		try {
			String datos = request.getParameter("datos");
			
			//visualizarParametros();
			
			Gson gson = new Gson();
			if(datos != null){
				paciente = gson.fromJson(datos, ModeloPaciente.class);
				
				System.out.println(gson.toJson(paciente));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean comprobarDNI(String dni) {
		boolean ok = false;

		try {
			conexion = new Conexion();
			conexion.conectar();

			String query = "select IDPACIENTE from Pacientes where UPPER(DNI) = UPPER(?) ";
			
			System.out.println("Comprobamos dni: " + dni);
			conexion.prepareSelect(query);
			conexion.addParameterSelect(1, dni);
			ResultSet rs = conexion.ejecutarSelect();

			if (rs.next()) {
				int id = rs.getInt("IDPACIENTE");
				ok = true;
				session.setAttribute("id_paciente_nuevacita", id);

			}
			rs.close();
			conexion.closePreparedSelect();
			conexion.desconectar();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;

	}
	
}