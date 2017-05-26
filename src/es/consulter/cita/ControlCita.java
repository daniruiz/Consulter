package es.consulter.cita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import es.consulter.conexion.Conexion;
import es.consulter.ficha.ControlFicha;
import es.consulter.paciente.ControlPaciente;
import es.consulter.utils.Control;

public class ControlCita extends Control{

	private ModeloCita cita;
	private List<ModeloMedicoDisponible> medicos;
	
	public List<ModeloMedicoDisponible> getMedicos() {
		return medicos;
	}
	public String getJsonMedicosDisponibles(){
		return "'" + new Gson().toJson(medicos) + "'";
	}

	public ControlCita(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void iniciarInsertar() {
		try {
			/*int index = session.getAttribute("index") != null ? (Integer)session.getAttribute("index") : 10;
			
			ModeloCita[] dato = new Gson().fromJson(
					(String)session.getAttribute("listadoCitas"), ModeloCita[].class);
			cita.setIdCita(index);
			
			dato[index] = cita;
			index++;
			session.setAttribute("index", index);
			
			session.setAttribute("listadoCitas", new Gson().toJson(dato));*/
			
			conexion = new Conexion();
			conexion.conectar();
			
			conexion.transaction();
			cambiarCita();
			
			getDataParameter();
			
			cargarDatosPaciente();
			
			insertarCita();
			
			conexion.commit();
			conexion.desconectar();
			
			setResultado("Cita insertada correctamente.");
			System.out.println("Se ha insertado en la tabla cita");
		} catch (Exception e) {
			//conexion.rollback();
			e.printStackTrace();
			conexion.rollback();
			conexion.desconectar();
			setResultado("Cita no fue insertada correctamente.");
		}
	}

	private void insertarCita() throws SQLException {
		String query =  " insert into Citas_Pacientes (IDPACIENTE, HORA, " + 
						"  DIA, IDFICHA, IDMEDICO) " + 
						" values (?, ?, ?, ?, ?)";
		
		conexion.prepareSTMT(query);
		conexion.addParameter(1, cita.getIdPaciente());
		conexion.addParameter(2, cita.getHora());
		conexion.addParameter(3, cita.getDia());
		conexion.addParameter(4, cita.getIdFicha());
		conexion.addParameter(5, cita.getIdMedico());
		conexion.ejecutarUpdt();
		conexion.closePrepared();
	}
	private void cargarDatosPaciente() {
		try {
			boolean tieneFicha = false;
			int idEspecialidad = 0;
			String selectEspecialidad = " SELECT ME.IDESPECIALIDAD " + 
										" FROM MEDICO_ESPECIALIDAD ME " + 
										" WHERE ME.IDMEDICO = ? " + 
										" AND ME.ACTUAL = 1";
			
			conexion.prepareSelect(selectEspecialidad);
			conexion.addParameterSelect(1, cita.getIdMedico());
			ResultSet rs = conexion.ejecutarSelect();
			
			try {
				if(rs.next()){
					idEspecialidad = rs.getInt("IDESPECIALIDAD");
				}else{
					throw new Exception("Médico sin especialidad");
				}
			} finally {
				rs.close();
				conexion.closePreparedSelect();
			}
			
			
			tieneFicha = recogerFicha(idEspecialidad);
			
			if(!tieneFicha){
				crearFicha(idEspecialidad);
				recogerFicha(idEspecialidad);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private boolean recogerFicha(int idEspecialidad) {
		boolean tieneFicha = false;
		try {
			String selectFicha = 	" SELECT F.IDFICHA " + 
									" FROM FICHA F " + 
									" WHERE F.IDPACIENTE = ? " + 
									" AND F.IDESPECIALIDAD = ? ";
			
			conexion.prepareSelect(selectFicha);
			conexion.addParameterSelect(1, cita.getIdPaciente());
			conexion.addParameterSelect(2, idEspecialidad);
			ResultSet rs = conexion.ejecutarSelect();
			
			try {
				if(rs.next()){
					cita.setIdFicha(rs.getInt("IDFICHA"));
					tieneFicha = true;
				}else{
					tieneFicha = false;
				}
			} finally {
				rs.close();
				conexion.closePreparedSelect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tieneFicha;
	}

	private void crearFicha(int idEspecialidad) {
		ControlFicha control = new ControlFicha(request, response);
		control.setConexion(conexion);
		control.iniciarInsertar(cita.getIdPaciente(), idEspecialidad);
	}

	private void cambiarCita() {
		try {
			/*int idCita = request.getParameter("idCita") == null ? 0 
					: Integer.parseInt(request.getParameter("idCita"));*/
			
			int idCita = (Integer)session.getAttribute("idCita_cambiar");
			
			if(idCita != 0){
				// Si eliminamos cita, recogemos primera el id del paciente
				String selectIdPaciente = 	" SELECT IDPACIENTE " + 
											" FROM CITAS_PACIENTES " + 
											" WHERE IDCITA = ? ";
				
				conexion.prepareSelect(selectIdPaciente);
				conexion.addParameterSelect(1, idCita);
				ResultSet rs = conexion.ejecutarSelect();
				if(rs.next()){
					session.setAttribute("id_paciente_nuevacita", rs.getInt("IDPACIENTE"));
				}
				rs.close();
				conexion.closePreparedSelect();
				
				System.out.println("Eliminamos la cita antigua: " + idCita);
				
				cita = new ModeloCita();
				cita.setIdCita(idCita);
				iniciarEliminar();
				cita = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void getDataParameter() {
		try {
			String datos = request.getParameter("datos");
			System.out.println("datos: " + datos);
			
			Gson gson = new Gson();
			if(datos != null){
				cita = gson.fromJson(datos, ModeloCita.class);
				
				// Despues de comprobar el DNI, se ha almacenado el idPaciente suyo.
				// lo recuperamos para insertar la cita a su id
				cita.setIdPaciente((Integer)session.getAttribute("id_paciente_nuevacita"));
				// Eliminamos de sesión el id recogido con la comprobación.
				session.removeAttribute("id_paciente_nuevacita");
				
				System.out.println(gson.toJson(cita));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarActualizar() {
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String query = "update Cita_Paciente set IDPACIENTE = ?, IDMEDICO= ?, HORA= ?,  DIA= ? WHERE IDCITA= ?";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, cita.getIdPaciente());
			conexion.addParameter(2, cita.getIdMedico());
			conexion.addParameter(3, cita.getHora());
			conexion.addParameter(4, cita.getDia());
			conexion.addParameter(5, cita.getIdCita());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			conexion.desconectar();
			
			this.estado = true;
			
			System.out.println("Se ha actualizado de la tabla");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarEliminar() {
		try {
			boolean conexionIniciada = false;
			
			/*ModeloCita[] dato = new Gson().fromJson(
					(String)session.getAttribute("listadoCitas"), ModeloCita[].class);
			
			int idCita = Integer.parseInt(request.getParameter("idCita"));
			
			//for(ModeloCita cita : dato){
			for(int i = 0; i < dato.length; i++){
				if(dato[i].getIdCita() == idCita)
					dato[i] = new ModeloCita();
			}
			
			session.setAttribute("listadoCitas", new Gson().toJson(dato));
			
			System.out.println(new Gson().toJson(dato));*/
			
			if(conexion == null){
				conexion = new Conexion();
				conexion.conectar();
			}else{
				conexionIniciada = true;
			}
			
			if(cita == null){
				cita = new ModeloCita();
				int idCita = Integer.parseInt(request.getParameter("idCita"));
				cita.setIdCita(idCita);
			}
			
			String query = "delete from Citas_Pacientes where IDCITA = ?";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, cita.getIdCita());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			if(!conexionIniciada)
				conexion.desconectar();
			
			System.out.println("Se ha eliminado la cita.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cargarDatos() {
		cita = new ModeloCita();
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String query = 	" select (IDPACIENTE, IDMEDICO, DIA, HORA from Cita_Paciente" + 
							" where IDCITA = ?";
			
			conexion.prepareSelect(query);
			conexion.addParameterSelect(1, cita.getIdPaciente());
			ResultSet rs = conexion.ejecutarSelect();
			if (rs.next()) 
			{ 
				cita.setIdPaciente(rs.getInt ("NOMBRE"));
				cita.setIdMedico(rs.getInt ("APELLIDO"));
				//cita.setDia(rs.getInt("NUM_COMPANIERO"));
				cita.setHora(rs.getString("DNI"));
			   
				 
			}
			rs.close();

			
			conexion.closePreparedSelect();
			
			conexion.desconectar();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void consultarMedicosEspecialidad(){
		try {
			medicos = new ArrayList<ModeloMedicoDisponible>();
			//System.out.println("\tConsultaMedicos");
			
			int idEspecialidad = Integer.parseInt(request.getParameter("idEspecialidad"));
			
			String selectMedicosDisponibles = 
					" SELECT H.HORA, M.IDMEDICO, M.NOMBRE || ' ' || M.APELLIDO AS MEDICO " + 
					" FROM HORAS_CITA H " + 
					" JOIN PERSONAL_MEDICO M " + 
					" INNER JOIN MEDICO_ESPECIALIDAD ME ON M.IDMEDICO = ME.IDMEDICO " +
					" AND ME.ACTUAL = 1 " + 
					" WHERE NOT EXISTS ( " + 
					" 	SELECT * FROM CITAS_PACIENTES CP " +  
					" 	WHERE CP.HORA = H.HORA AND CP.IDMEDICO = M.IDMEDICO " + 
					" ) " + 
					" AND ME.IDESPECIALIDAD = ? " + 
					" ORDER BY M.IDMEDICO, H.IDHORA";
			
			conexion.prepareSelect(selectMedicosDisponibles);
			conexion.addParameterSelect(1, idEspecialidad);
			ResultSet rs = conexion.ejecutarSelect();
			
			ModeloMedicoDisponible medico = null;
			int idMedicoActual = -1;
			
			while(rs.next()){
				int idMedico = rs.getInt("IDMEDICO");
				
				if(idMedicoActual != idMedico){
					if(idMedicoActual != -1){
						medicos.add(medico);
					}
					
					medico = new ModeloMedicoDisponible();
					medico.setIdMedico(idMedico);
					medico.setNombreMedico(rs.getString("MEDICO"));
					
				}
				
				idMedicoActual = idMedico;
				medico.addHora(rs.getString("HORA"));
				
			}
			rs.close();
			conexion.closePreparedSelect();
			if(medico != null)	
				medicos.add(medico);
			
			System.out.println(getJsonMedicosDisponibles());
			/*ModeloMedicoDisponible medico = new ModeloMedicoDisponible();
			medico.setIdMedico(1);
			medico.setNombreMedico("Roberto Pérez");
			String [] arrayHoras = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30"};
			medico.setHoras(arrayHoras);
			medicos[0] = medico;
			
			medico = new ModeloMedicoDisponible();
			medico.setIdMedico(2);
			medico.setNombreMedico("Luis Moreno");
			String [] arrayHoras2 = {"09:30", "10:30", "11:00", "11:30", "12:00", "12:30"};
			medico.setHoras(arrayHoras2);
			medicos[1] = medico;
			
			medico = new ModeloMedicoDisponible();
			medico.setIdMedico(3);
			medico.setNombreMedico("Isabel Hernández");
			String [] arrayHoras3 = {"08:00", "08:30", "09:00", "09:30", "10:30", 
					"11:00", "11:30", "12:00", "12:30", "13:00", "15:00", "15:30"};
			medico.setHoras(arrayHoras3);
			medicos[2] = medico;*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean consultarDNIpaciente() {
		ControlPaciente controlPaciente = new ControlPaciente(request, response);
		String dni = request.getParameter("dniComprobar");
		return controlPaciente.comprobarDNI(dni);
	}

	public void desconectar() {
		try {
			conexion.desconectar();
		} catch (Exception e) { }
	}

	public void iniciarConexion() {
		try {
			conexion = new Conexion();
			conexion.conectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void aniadirObservacion() {
		try {
			System.out.println("Añadimos observación");
			int idCita = (Integer)session.getAttribute("idCita_observacion");
			String texto = request.getParameter("texto");
			String fecha = request.getParameter("fecha");
			
			String insertObservacion = 	" INSERT INTO HISTORICO_PACIENTE " + 
										" (TEXTO_CONSULTA, FECHA_MOD, IDCITA) " + 
										" VALUES (?, ?, ?) ";
			
			conexion.prepareSTMT(insertObservacion);
			conexion.addParameter(1, texto);
			conexion.addParameter(2, fecha);
			conexion.addParameter(3, idCita);
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void cargarArchivoCitas() {
		try {
			String datos = request.getParameter("datos");
			
			System.out.println("ArchivoCitas => " + datos);
			
			int cntInsertado = 0;
			if(datos != null && !datos.equals("")){
				
				ModeloArchivoCitas [] listadoCitas = new Gson().fromJson(datos, ModeloArchivoCitas[].class);
				
				String selectDatosPaciente = 
						" SELECT P.IDPACIENTE, F.IDFICHA, ME.IDMEDICO " + 
						" FROM PACIENTES P " +  
						" INNER JOIN FICHA F ON P.IDPACIENTE = F.IDPACIENTE " + 
						" INNER JOIN ESPECIALIDAD E ON F.IDESPECIALIDAD = E.ID " + 
						" INNER JOIN MEDICO_ESPECIALIDAD ME ON E.ID = ME.IDESPECIALIDAD " + 
						" AND ME.IDMEDICO = ? " + 
						" WHERE DNI = ? ";
				
				ResultSet rs = null;
				for(ModeloArchivoCitas citaArchivo : listadoCitas){
					int idPaciente = 0, idFicha = 0;
					boolean cargadoDatos = false;
					conexion.prepareSelect(selectDatosPaciente);
					conexion.addParameterSelect(1, citaArchivo.idMedico);
					conexion.addParameterSelect(2, citaArchivo.dniPaciente);
					rs = conexion.ejecutarSelect();
					if(rs.next()){
						idPaciente = rs.getInt("IDPACIENTE");
						idFicha = rs.getInt("IDFICHA");
						cargadoDatos = true;
					}
					rs.close();
					conexion.closePreparedSelect();
					
					if(cargadoDatos){
						cita = new ModeloCita();
						cita.setIdPaciente(idPaciente);
						cita.setIdFicha(idFicha);
						cita.setIdmedico(Integer.valueOf(citaArchivo.idMedico));
						cita.setHora(citaArchivo.hora);
						cita.setDia(citaArchivo.dia);
						insertarCita();
						
						cntInsertado++;
						System.out.println("Cita insertada.");
					}else{
						System.out.println("Cita no insertada!!!");
					}
					
				}
				
			}
			
			System.out.println("Se han insertado " + cntInsertado + " citas.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private class ModeloArchivoCitas{
		String 	dia,
				hora,
				idMedico,
				dniPaciente;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
