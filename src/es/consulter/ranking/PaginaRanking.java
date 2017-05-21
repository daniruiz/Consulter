package es.consulter.ranking;

import java.sql.ResultSet;

import com.google.gson.Gson;

import es.consulter.conexion.Conexion;
import es.consulter.utils.Pagina;

public class PaginaRanking extends Pagina{
	
	
	private ListaRanking ranking;
	public String getJsonRanking() {
		return "'" + new Gson().toJson(ranking) + "'";
	}

	@Override
	protected void onLoad() {
		try {
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void cargarDatos(){
		try {
			String selectRanking = 	" SELECT " +  
									" COUNT(CP.IDCITA) AS TOTAL_CITAS, " + 
									" M.IDMEDICO, " + 
									" M.NOMBRE || ' ' || M.APELLIDO AS MEDICO, " + 
									" ME.IDESPECIALIDAD, E.NOMBRE AS ESPECIALIDAD " + 
									" FROM CITAS_PACIENTES CP " + 
									" INNER JOIN PACIENTES P " + 
									" ON CP.IDPACIENTE = P.IDPACIENTE " + 
									" INNER JOIN PERSONAL_MEDICO M " + 
									" ON CP.IDMEDICO = M.IDMEDICO " + 
									" INNER JOIN MEDICO_ESPECIALIDAD ME " + 
									" ON M.IDMEDICO = ME.IDMEDICO " + 
									" AND ME.ACTUAL = 1 " + 
									" INNER JOIN ESPECIALIDAD E " + 
									" ON ME.IDESPECIALIDAD = E.ID " + 
									" GROUP BY  M.IDMEDICO, M.NOMBRE, M.APELLIDO, ME.IDESPECIALIDAD, E.NOMBRE";
			
			System.out.println(selectRanking);
			
			conexion = new Conexion();
			conexion.conectar();
			conexion.prepareSelect(selectRanking);
			ResultSet rs = conexion.ejecutarSelect();
			ranking = new ListaRanking();
			ranking.mapResultSet(rs);
			rs.close();
			conexion.closePreparedSelect();
			conexion.desconectar();
			
			System.out.println("ranking => " + new Gson().toJson(ranking));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
