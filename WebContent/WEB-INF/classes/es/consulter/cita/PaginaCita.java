package es.consulter.cita;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import es.consulter.conexion.Conexion;
import es.consulter.ficha.ControlFicha;
import es.consulter.ficha.ModeloFicha;
import es.consulter.utils.Pagina;

public class PaginaCita extends Pagina{
	
	private String jsonListadoCitas;
	
	public String getJsonListadoCitas() {
		return "'" + jsonListadoCitas + "'";
	}

	public void setRequest(HttpServletRequest request){
		this.request= request;
	}
	
	public void onLoad(){
		try {
			conexion = new Conexion();
			conexion.conectar();
			
			cargarDatos();
			
			conexion.desconectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void cargarDatos() {
		System.out.println("Hola");
		
		String pagina = checkPage();
		
		switch (pagina) {
		case "observaciones-paciente":
			//cargarObservaciones();
			break;
		case "listado-citas":
			cargarListadoCitas();
			break;
		case "pedir-cita":
			cargarCita();
			break;
		default:
			break;
		}
		
	}

	private void cargarObservaciones() {
		
		int idCita = Integer.parseInt(request.getParameter("idCita"));
		
		ModeloFicha ficha = ControlFicha.loadHistoryObs(idCita);
	}

	private void cargarListadoCitas() {
		try {
			boolean existenDatos = session.getAttribute("listadoCitas") != null;
			
			List <ModeloCita> dato = new ArrayList<ModeloCita>();
			
			String selectCitas = 	" SELECT " +  
									" CP.IDCITA, CP.HORA, CP.DIA, " + 
									" P.NOMBRE || ' ' || P.APELLIDOS AS PACIENTE, " + 
									" P.DNI," + 
									" M.IDMEDICO," + 
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
									" ON ME.IDESPECIALIDAD = E.ID";
			
			conexion.prepareSelect(selectCitas);
			ResultSet rs = conexion.ejecutarSelect();
			
			while(rs.next()){
				ModeloCita modelo = new ModeloCita();
				
				modelo.setHora(rs.getString("HORA"));
				modelo.setIdCita(rs.getInt("IDCITA"));
				modelo.setNombreMedico(rs.getString("MEDICO"));
				modelo.setEspecialidad(rs.getString("ESPECIALIDAD"));
				modelo.setPaciente(rs.getString("PACIENTE"));
				modelo.setDni(rs.getString("DNI"));
				dato.add(modelo);
			}
			rs.close();
			conexion.closePreparedSelect();
			
			/*if(!existenDatos){
				dato = new ArrayList<ModeloCita>();
				
				ModeloCita modelo = new ModeloCita();
				
				modelo.setHora("08:00");
				modelo.setIdCita(1);
				modelo.setNombreMedico("Roberto Perez");
				modelo.setEspecialidad("Ginecología");
				modelo.setPaciente("Marta Feijoo");
				modelo.setDni("22454076G");
				//dato[0] = modelo;
				dato.add(modelo);
				
				
				modelo = new ModeloCita();
				
				modelo.setHora("08:30");
				modelo.setIdCita(2);
				modelo.setNombreMedico("Luis Moreno");
				modelo.setEspecialidad("Odontología");
				modelo.setPaciente("Pedro Castellano");
				modelo.setDni("75215071B");
				//dato[1] = modelo;
				dato.add(modelo);
				
				modelo = new ModeloCita();
				
				modelo.setHora("08:30");
				modelo.setIdCita(3);
				modelo.setNombreMedico("Sofía Garrido");
				modelo.setEspecialidad("Cardiología");
				modelo.setPaciente("Isabel Hernandez");
				modelo.setDni("52801993L");
				//dato[2] = modelo;
				dato.add(modelo);
				
				modelo = new ModeloCita();
				
				modelo.setHora("10:00");
				modelo.setIdCita(4);
				modelo.setNombreMedico("Sara Ruiz");
				modelo.setEspecialidad("Psiquiatría");
				modelo.setPaciente("Rebeca Larrañaga");
				modelo.setDni("27449907M");
				//dato[3] = modelo;
				dato.add(modelo);
				
				modelo = new ModeloCita();
				
				modelo.setHora("10:00");
				modelo.setIdCita(5);
				modelo.setNombreMedico("Alberto Perez");
				modelo.setEspecialidad("Neurología");
				modelo.setPaciente("Josefa Rodrígez");
				modelo.setDni("23229790C");
				//dato[4] = modelo;
				dato.add(modelo);
				
				modelo = new ModeloCita();
				
				modelo.setHora("19:00");
				modelo.setIdCita(6);
				modelo.setNombreMedico("Rigoberto Perez");
				modelo.setEspecialidad("Pediatría");
				modelo.setPaciente("Adrian Ruiz");
				modelo.setDni("22473780C");
				//dato[5] = modelo;
				dato.add(modelo);
				
				modelo = new ModeloCita();
				
				modelo.setHora("19:30");
				modelo.setIdCita(7);
				modelo.setNombreMedico("Sigismundo Perez");
				modelo.setEspecialidad("Oftalmología");
				modelo.setPaciente("Josefa Rodrígez");
				modelo.setDni("34784976Y");
				//dato[6] = modelo;
				dato.add(modelo);
				
				session.setAttribute("listadoCitas", new Gson().toJson(dato));
				session.setAttribute("index", 7);
				
				System.out.println("Almacenamos primera vez array");
			}else{
				
			}*/
			
			jsonListadoCitas = new Gson().toJson(dato);
			System.out.println(jsonListadoCitas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String checkPage() {
		String pagina = "";
		
		pagina = request.getRequestURI();
		
		pagina = pagina.substring(pagina.lastIndexOf("/") + 1, pagina.lastIndexOf("."));
		
		System.out.println("PaginaCita " + pagina);
		
		return pagina;
	}

	

	private void cargarCita() {
		if(requestedChange()){
			// Si detectamos la petición de un idCita para cambiar, lo almacenamos para
			// que se use en el controlador una vez que inserte la nueva cita.
			int idCitaCambiar = Integer.parseInt(request.getParameter("idCita"));
			session.setAttribute("idCita_cambiar", idCitaCambiar);
			
			//Cargamos el DNI del paciente al que corresponde esta cita.
			
		}
	}

	private boolean requestedChange() {
		return request.getParameter("idCita") != null;
	}
	
}
