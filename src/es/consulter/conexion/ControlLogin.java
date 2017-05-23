package es.consulter.conexion;

import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.consulter.utils.Usuario;

public class ControlLogin {
	private static Hashtable <String, String> usuarios = new Hashtable<String, String>(){
		private static final long serialVersionUID = 4171706330009621015L;
	{
		put("admin", "1234");
		put("gestor", "1234");
		put("medico", "1234");
	}};
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	private HttpSession session;

	public ControlLogin(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		session = request.getSession(true);
	}

	protected void autenticar() {
		try {
			PrintWriter out = response.getWriter();
			
			if(comprobarUsuario()){
				out.print(true);
			}else{
				out.print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private boolean comprobarUsuario() {
		boolean existe = false;
		
		String user = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		
		String passStored = usuarios.get(user);
		if(passStored != null && passStored.equals(pass)){
			existe = true;
			Usuario usuario = new Usuario();
			usuario.setUsuario(user);
			usuario.setValido(true);
			session.setAttribute("usuario", usuario);
			int perfil = user.equals("gestor") ? 1 : 2;
			session.setAttribute("perfil", perfil);
			try {
				//response.sendRedirect("listado-citas");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return existe;
	}

}
