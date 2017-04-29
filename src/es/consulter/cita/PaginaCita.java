package es.consulter.cita;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import es.consulter.conexion.Conexion;

public class PaginaCita {
	
	private HttpServletRequest request;
	private HttpSession session;
	private String jsonListadoCitas;
	
	public String getJsonListadoCitas() {
		return jsonListadoCitas;
	}

	public void setRequest(HttpServletRequest request){
		this.request= request;
	}
	
	public void onLoad(){
		try {
			Conexion conexion = new Conexion();
			conexion.conectar();
			
			session = request.getSession();
			
			boolean existenDatos = session.getAttribute("listadoCitas") != null;
			
			ModeloCita [] dato;
			if(!existenDatos){
				dato = new ModeloCita[7];
				
				ModeloCita modelo = new ModeloCita();
				
				modelo.setHora("08:00");
				modelo.setIdCita(1);
				modelo.setNombreMedico("Roberto Perez");
				modelo.setEspecialidad("Ginecología");
				modelo.setPaciente("Marta Feijoo");
				modelo.setDni("22454076G");
				dato[0] = modelo;
				
				
				modelo = new ModeloCita();
				
				modelo.setHora("08:30");
				modelo.setIdCita(2);
				modelo.setNombreMedico("Luis Moreno");
				modelo.setEspecialidad("Odontología");
				modelo.setPaciente("Pedro Castellano");
				modelo.setDni("75215071B");
				dato[1] = modelo;
				
				modelo = new ModeloCita();
				
				modelo.setHora("08:30");
				modelo.setIdCita(3);
				modelo.setNombreMedico("Sofía Garrido");
				modelo.setEspecialidad("Cardiología");
				modelo.setPaciente("Isabel Hernandez");
				modelo.setDni("52801993L");
				dato[2] = modelo;
				
				modelo = new ModeloCita();
				
				modelo.setHora("10:00");
				modelo.setIdCita(4);
				modelo.setNombreMedico("Sara Ruiz");
				modelo.setEspecialidad("Psiquiatría");
				modelo.setPaciente("Rebeca Larrañaga");
				modelo.setDni("27449907M");
				dato[3] = modelo;
				
				modelo = new ModeloCita();
				
				modelo.setHora("10:00");
				modelo.setIdCita(5);
				modelo.setNombreMedico("Alberto Perez");
				modelo.setEspecialidad("Neurología");
				modelo.setPaciente("Josefa Rodrígez");
				modelo.setDni("23229790C");
				dato[4] = modelo;
				
				modelo = new ModeloCita();
				
				modelo.setHora("19:00");
				modelo.setIdCita(6);
				modelo.setNombreMedico("Rigoberto Perez");
				modelo.setEspecialidad("Pediatría");
				modelo.setPaciente("Adrian Ruiz");
				modelo.setDni("22473780C");
				dato[5] = modelo;
				
				modelo = new ModeloCita();
				
				modelo.setHora("19:30");
				modelo.setIdCita(7);
				modelo.setNombreMedico("Sigismundo Perez");
				modelo.setEspecialidad("Oftalmología");
				modelo.setPaciente("Josefa Rodrígez");
				modelo.setDni("34784976Y");
				dato[6] = modelo;
				
				session.setAttribute("listadoCitas", new Gson().toJson(dato));
				session.setAttribute("index", 7);
				
				System.out.println("Almacenamos primera vez array");
			}else{
				System.out.println("Recuperamos de sessión el array");
				dato = new Gson().fromJson(
						(String)session.getAttribute("listadoCitas"), ModeloCita[].class);
				
			}
			
			jsonListadoCitas = new Gson().toJson(dato);
			System.out.println(jsonListadoCitas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
