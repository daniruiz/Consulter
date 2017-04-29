package es.consulter.cita;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import es.consulter.conexion.Conexion;
import es.consulter.paciente.ControlPaciente;
import es.consulter.utils.Control;

public class ControlCita extends Control{

	private ModeloCita cita;
	private ModeloMedicoDisponible [] medicos;
	
	public ModeloMedicoDisponible[] getMedicos() {
		return medicos;
	}

	public ControlCita(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void iniciarInsertar() {
		try {
			getDataParameter();
			
			int index = session.getAttribute("index") != null ? (Integer)session.getAttribute("index") : 10;
			
			ModeloCita[] dato = new Gson().fromJson(
					(String)session.getAttribute("listadoCitas"), ModeloCita[].class);
			cita.setIdCita(index);
			
			dato[index] = cita;
			index++;
			session.setAttribute("index", index);
			
			session.setAttribute("listadoCitas", new Gson().toJson(dato));
			
			/*conexion = new Conexion();
			conexion.conectar();
			
			conexion.transaction();
			cambiarCita();
			
			getDataParameter();
			
			String query =  " insert into Cita_Paciente (IDPACIENTE, IDMEDICO, HORA, " + 
							"  DIA, IDCITA) "
							+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, cita.getIdPaciente());
			conexion.addParameter(2, cita.getIdMedico());
			conexion.addParameter(3, cita.getHora());
			conexion.addParameter(4, cita.getDia());
			conexion.addParameter(5, cita.getIdCita());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			conexion.commit();
			conexion.desconectar();*/
			
			setResultado("Cita insertada correctamente.");
			System.out.println("Se ha insertado en la tabla");
		} catch (Exception e) {
			//conexion.rollback();
			e.printStackTrace();
			setResultado("Cita insertada correctamente.");
		}
	}

	private void cambiarCita() {
		try {
			int idCita = request.getParameter("idCita") == null ? 0 
					: Integer.parseInt(request.getParameter("idCita"));
			
			if(idCita != 0){
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
			//visualizarParametros();
			
			Gson gson = new Gson();
			if(datos != null){
				cita = gson.fromJson(datos, ModeloCita.class);
				
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
			
			ModeloCita[] dato = new Gson().fromJson(
					(String)session.getAttribute("listadoCitas"), ModeloCita[].class);
			
			int idCita = Integer.parseInt(request.getParameter("idCita"));
			
			//for(ModeloCita cita : dato){
			for(int i = 0; i < dato.length; i++){
				if(dato[i].getIdCita() == idCita)
					dato[i] = new ModeloCita();
			}
			
			session.setAttribute("listadoCitas", new Gson().toJson(dato));
			
			System.out.println(new Gson().toJson(dato));
			
			/*if(conexion == null){
				conexion = new Conexion();
				conexion.conectar();
			}else{
				conexionIniciada = true;
			}
			
			
			String query = "delete from Cita_Paciente where IDCITA = ?";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, cita.getIdCita());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			if(!conexionIniciada)
				conexion.desconectar();*/
			
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
			
			String query = "select (IDPACIENTE, IDMEDICO, DIA, HORA from Cita_Paciente"
					+ " where IDCITA = ?";
			
			conexion.prepareSelect(query);
			conexion.addParameterSelect(1, cita.getIdPaciente());
			ResultSet rs = conexion.ejecutarSelect();
			if (rs.next()) 
			{ 
				cita.setIdPaciente(rs.getInt ("NOMBRE"));
				cita.setIdMedico(rs.getInt ("APELLIDO"));
				cita.setDia(rs.getInt("NUM_COMPANIERO"));
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
			medicos = new ModeloMedicoDisponible[3];
			
			ModeloMedicoDisponible medico = new ModeloMedicoDisponible();
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
			medicos[2] = medico;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean consultarDNIpaciente() {
		ControlPaciente controlPaciente = new ControlPaciente(request, response);
		String dni = request.getParameter("dniComprobar");
		return controlPaciente.comprobarDNI(dni);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
