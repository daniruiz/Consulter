package es.consulter.medico;

import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import es.consulter.conexion.Conexion;
import es.consulter.utils.Control;

public class ControlMedico extends Control {
	private ModeloMedico medico;

	public ControlMedico(HttpServletRequest request, HttpServletResponse response) {
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
			
			/*String query =  " insert into Personal_Medico (NOMBRE, APELLIDO, NUM_COMPANIERO, " + 
							" ESPECIALIDAD, TIEMPO_CONSULTA, DNI, IDUSUARIO, IDMEDICO) " + 
							" values (?, ?, ?, ?, ?, ?, ?, ?)";*/
			
			String query =  " insert into Personal_Medico (NOMBRE, APELLIDO, NUM_COMPANIERO, " + 
							" DNI) " + 
							" values (?, ?, ?, ?)";
			
			String insertMedicoEspecialidad = 
					" INSERT INTO MEDICO_ESPECIALIDAD (IDMEDICO, IDESPECIALIDAD, ACTUAL) " + 
					" VALUES (?, ?, ?) ";
			
			String insertMedicoDiaSemana = 
					" INSERT INTO MEDICO_DIA_SEMANA (IDMEDICO, DIA_SEMANA) " + 
					" VALUES (?, ?) ";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, medico.getNombre());
			conexion.addParameter(2, medico.getApellido());
			conexion.addParameter(3, medico.getNumColegiado());
			conexion.addParameter(4, medico.getDni());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			String selectIdMedicoNuevo = 	" SELECT IDMEDICO " + 
											" FROM PERSONAL_MEDICO " + 
											" WHERE NOMBRE = ? " + 
											" AND APELLIDO = ? ";
			
			conexion.prepareSelect(selectIdMedicoNuevo);
			conexion.addParameterSelect(1, medico.getNombre());
			conexion.addParameterSelect(2, medico.getApellido());
			ResultSet rs = conexion.ejecutarSelect();
			if(rs.next()){
				int idMedico = rs.getInt("IDMEDICO");
				System.out.println("Recuperamos el id " + idMedico + " del medico insertado.");
				medico.setIdMedico(idMedico);
			}
			rs.close();
			conexion.closePreparedSelect();
			
			int activo = 1;
			
			for(int i = 0; i < medico.getEspecialidad().length; i++){
				conexion.prepareSTMT(insertMedicoEspecialidad);
				conexion.addParameter(1, medico.getIdMedico());
				conexion.addParameter(2, medico.getEspecialidad()[i]);
				conexion.addParameter(3, activo);
				
				conexion.ejecutarUpdt();
				conexion.closePrepared();
				
				if (activo == 1) activo = 0;
			}
			
			for(int i = 0; i < medico.getDiasSemana().length; i++){
				conexion.prepareSTMT(insertMedicoDiaSemana);
				conexion.addParameter(1, medico.getIdMedico());
				conexion.addParameter(2, medico.getDiasSemana()[i]);
				
				conexion.ejecutarUpdt();
				conexion.closePrepared();
				
			}
			
			conexion.desconectar();
			
			System.out.println("Se ha insertado en la tabla");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getDataParameter() {
		try {
			String datos = request.getParameter("datos");
			
			System.out.println("Viene un médico!! => " + datos);
			
			//visualizarParametros();
			
			Gson gson = new Gson();
			if(datos != null){
				medico = gson.fromJson(datos, ModeloMedico.class);
				
				System.out.println(gson.toJson(medico));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void visualizarParametros() {
		try {
			Enumeration<String> params = request.getParameterNames();
			
			while(params.hasMoreElements()){
				String param = params.nextElement();
				System.out.println(param + " : " + request.getParameter(param));
			}
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
			
			String query = "update Personal_Medico set NOMBRE = ?, APELLIDO= ?, NUM_COMPANIERO= ?, ESPECIALIDAD= ?, TIEMPO_CONSULTA= ?, DNI= ? WHERE IDMEDICO= ?";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, medico.getNombre());
			conexion.addParameter(2, medico.getApellido());
			//conexion.addParameter(3, medico.getNum_companiero());
			conexion.addParameter(4, medico.getEspecialidad());
			conexion.addParameter(5, medico.getTiempoConsulta());
			conexion.addParameter(6, medico.getDni());
			conexion.addParameter(7, medico.getIdMedico());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			conexion.desconectar();
			
			this.estado = true;
			setResultado("Se han insertado los datos.");
			
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
			
			String query = "delete from Personal_Medico where IDMEDICO = ?";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, medico.getIdMedico());
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
		
		medico = new ModeloMedico();
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String query = "select (NOMBRE, APELLIDO, NUM_COMPANIERO, ESPECIALIDAD, TIEMPO_CONSULTA, DNI, IDUSUARIO,IDMEDICO from Personal_Medico where IDMEDICO = ?";
			
			conexion.prepareSelect(query);
			conexion.addParameterSelect(1, medico.getIdMedico());
			ResultSet rs = conexion.ejecutarSelect();
			if (rs.next()) 
			{ 
				medico.setNombre(rs.getString ("NOMBRE"));
				medico.setApellido(rs.getString ("APELLIDO"));
				//medico.setNum_companiero(rs.getInt("NUM_COMPANIERO"));
				//medico.setEspecialidad(rs.getInt("ESPECIALIDAD"));
				//medico.setTiempoConsulta(rs.getInt("TIEMPO_CONSULTA"));
				medico.setDni(rs.getString("DNI"));
				medico.setIdUsuario(rs.getInt("IDUSUARIO"));
			   
				 
			}
			rs.close();

			
			conexion.closePreparedSelect();
			
			conexion.desconectar();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
