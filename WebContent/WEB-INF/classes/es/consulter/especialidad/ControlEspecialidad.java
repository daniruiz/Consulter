package es.consulter.especialidad;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.consulter.conexion.Conexion;
import es.consulter.utils.Control;

public class ControlEspecialidad extends Control {

	public ControlEspecialidad(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 	Método para administrar la inserción de una especialidad
	 * */
	@Override
	public void iniciarInsertar() {
		// TODO Auto-generated method stub

	}

	/**
	 * 	Método para administrar la actualización de una especialidad
	 * */
	@Override
	public void iniciarActualizar() {
		// TODO Auto-generated method stub

	}

	/**
	 * 	Método para administrar la eliminación de una especialidad
	 * */
	@Override
	public void iniciarEliminar() {
		// TODO Auto-generated method stub

	}

	/**
	 * 	Método para administrar la carga de una especialidad
	 * */
	@Override
	public void cargarDatos() {
		// TODO Auto-generated method stub

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
