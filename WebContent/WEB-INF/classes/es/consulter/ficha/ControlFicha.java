package es.consulter.ficha;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.consulter.conexion.Conexion;
import es.consulter.utils.Control;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.consulter.conexion.Conexion;
import es.consulter.utils.Control;

public class ControlFicha extends Control {
	private ModeloFicha ficha;

	public ControlFicha(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}
	
	/**
	 * 	Método para administrar la inserción de una especialidad
	 * @throws Exception 
	 * */
	@Override
	public void iniciarInsertar()  {
		
		try {
			
			String query = 	" insert into Ficha (IDPACIENTE, IDESPECIALIDAD)" + 
							" values (?, ?)";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, ficha.getIdpaciente());
			conexion.addParameter(2, ficha.getIdespecialdad());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			System.out.println("Se ha insertado en la tabla");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void iniciarInsertar(int idPaciente, int idEspecialidad)  {
		try {
			ficha = new ModeloFicha();
			ficha.setIdpaciente(idPaciente);
			ficha.setIdespecialdad(idEspecialidad);
			
			iniciarInsertar();
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
			
			String query = "update ficha set FECHA_ULT_MOD= ?, MED_ULT_MOD= ?, IDESPECIALIDAD= ?,"
					+ " IDPACIENTE= ?, TEXTO_CONSULTA= ?, IDESPECIALISTA= ? WHERE IDFICHA= ? ";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, ficha.getFecha_mod());
			conexion.addParameter(2, ficha.getMedico_mod());
			conexion.addParameter(3, ficha.getIdespecialdad());
			conexion.addParameter(4, ficha.getIdpaciente());
			conexion.addParameter(5, ficha.getTexto());
			conexion.addParameter(6, ficha.getIdespecialista());
			conexion.addParameter(7, ficha.getIdficha());
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			conexion.desconectar();
			
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
			
			String query = "delete from Ficha where IDFICHA = ?";
			
			conexion.prepareSTMT(query);
			conexion.addParameter(1, ficha.getIdficha());
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
		
		ficha = new ModeloFicha();
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String query = "select IDPACIENTE, IDESPECIALISTA,IDESPECIALIDAD, TEXTO_CONSULTA,"
					+ "MED_ULT_MOD, FECHA_ULT_MOD FROM FICHA where IDFICHA = ? ";
			
			conexion.prepareSelect(query);
			conexion.addParameterSelect(1, ficha.getIdficha());
			ResultSet rs = conexion.ejecutarSelect();
			while (rs.next()) 
			{ 
			   ficha.setIdpaciente(rs.getInt ("ID"));
			   ficha.setIdespecialista(rs.getInt ("NOMBRE"));
			   ficha.setIdespecialdad(rs.getInt("DESCRIPCION"));
			   ficha.setTexto(rs.getString("PLANTA"));
			   ficha.setMedico_mod(rs.getInt ("ID"));
			   ficha.setFecha_mod(rs.getString ("NOMBRE"));
			   
				 
			}
			rs.close();

			
			conexion.closePreparedSelect();
			
			conexion.desconectar();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean comprobarFicha(int idespecialidad){

			boolean ok=false;
			 int idpaciente =  ( (Integer) session.getAttribute("id_paciente_cita") ).intValue();
			
			try {
				conexion = new Conexion();
				conexion.conectar();
				
				String query = "select IDFICHA from FICHA where IDPACIENTE = ? AND IDESPECIALIDAD=? ";
				
				conexion.prepareSelect(query);
				conexion.addParameterSelect(1, idespecialidad);
				conexion.addParameterSelect(2, idpaciente);
				ResultSet rs = conexion.ejecutarSelect();
				
					
				 if( rs.next()){
					 int id= rs.getInt("IDFICHA");
					 ok = true;
					 session.setAttribute("id_ficha_paciente", id);
					
				 }
				
				
				conexion.closePreparedSelect();
				
				conexion.desconectar();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ok;
			
		}
	

	private void ejemploConexionSelect(){
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String select = " SELECT 2 + ? AS SUMA FROM DUAL ";
			
			conexion.prepareSelect(select);
			conexion.addParameterSelect(1, 5);
			ResultSet rs = conexion.ejecutarSelect();
			
			int resultado = Integer.MIN_VALUE;
			while(rs.next()){
				resultado = rs.getInt("SUMA");
			}
			rs.close();
			conexion.closePreparedSelect();
			
			conexion.desconectar();
			
			System.out.println("Hemos obtenido " + resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void ejemploConexionActualizacion(){
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			String insert = " INSERT INTO TABLA (COLUMNA1, COLUMNA2) VALUES (?, ?) ";
			
			conexion.prepareSTMT(insert);
			conexion.addParameter(1, "Texto");
			conexion.addParameter(2, 3);
			conexion.ejecutarUpdt();
			conexion.closePrepared();
			
			conexion.desconectar();
			
			System.out.println("Se ha insertado/actualizado en la tabla");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
