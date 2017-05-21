package es.consulter.ranking;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ListaRanking extends ArrayList<ListaRanking.DatoRanking>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1155609322435139004L;


	public ListaRanking(){
		
	}
	
	
	protected class DatoRanking {
		private String nombreEspecialidad;
		private int idEspecialidad;
		private ListaMedicoRanking lista;
		
		protected DatoRanking(){
			lista = new ListaMedicoRanking();
		}

		protected String getNombreEspecialidad() {
			return nombreEspecialidad;
		}
		protected void setNombreEspecialidad(String nombreEspecialidad) {
			this.nombreEspecialidad = nombreEspecialidad;
		}

		protected int getIdEspecialidad() {
			return idEspecialidad;
		}
		protected void setIdEspecialidad(int idEspecialidad) {
			this.idEspecialidad = idEspecialidad;
		}

		protected ListaMedicoRanking getLista() {
			return lista;
		}
		protected void setLista(ListaMedicoRanking lista) {
			this.lista = lista;
		}
		
	}


	public void mapResultSet(ResultSet rs) {
		try {
			int idEspecialidadActual = -1;
			ListaRanking.DatoRanking datoRanking = null;
			ListaMedicoRanking lista = null;
			while(rs.next()){
				int idEspecialidad = rs.getInt("IDESPECIALIDAD");
				
				if(idEspecialidadActual != idEspecialidad){
					if(idEspecialidadActual != -1){
						datoRanking.setLista(lista);
						add(datoRanking);
					}
					
					datoRanking = new ListaRanking.DatoRanking();
					datoRanking.setIdEspecialidad(idEspecialidad);
					datoRanking.setNombreEspecialidad(rs.getString("ESPECIALIDAD"));
					lista = new ListaMedicoRanking();
				}
				
				idEspecialidadActual = idEspecialidad;
				lista.mapResultSet(rs);
				
			}
			datoRanking.setLista(lista);
			add(datoRanking);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
