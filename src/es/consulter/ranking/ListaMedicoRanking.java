package es.consulter.ranking;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ListaMedicoRanking extends ArrayList<ListaMedicoRanking.MedicoRanking>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8987076448729817985L;

	
	
	
	protected class MedicoRanking{
		private int idMedico;
		private String nombreMedico;
		private int totalCitas;
		
		public int getIdMedico() {
			return idMedico;
		}
		public void setIdMedico(int idMedico) {
			this.idMedico = idMedico;
		}
		
		public String getNombreMedico() {
			return nombreMedico;
		}
		public void setNombreMedico(String nombreMedico) {
			this.nombreMedico = nombreMedico;
		}
		
		public int getTotalCitas() {
			return totalCitas;
		}
		public void setTotalCitas(int totalCitas) {
			this.totalCitas = totalCitas;
		}
		
	}

	public void mapResultSet(ResultSet rs) {
		try {
			
			MedicoRanking medico = new MedicoRanking();
			medico.setIdMedico(rs.getInt("IDMEDICO"));
			medico.setNombreMedico(rs.getString("MEDICO"));
			medico.setTotalCitas(rs.getInt("TOTAL_CITAS"));
			
			add(medico);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}















