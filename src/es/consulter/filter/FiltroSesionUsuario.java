package es.consulter.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.consulter.utils.Usuario;

/**
 * Servlet Filter implementation class FiltroSesionUsuaio
 */
@WebFilter("/*")
public class FiltroSesionUsuario implements Filter {

    private final List<String> listaRecursos;

	/**
     * Default constructor. 
     */
    public FiltroSesionUsuario() {
        //System.out.println("Constructor");
        listaRecursos = new ArrayList<String>();
        
        listaRecursos.add("/js/");
        listaRecursos.add("/css/");
        listaRecursos.add("/include/");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		HttpSession session = hRequest.getSession();
		boolean continuar = true;
		boolean comprobarUsuarioSesion = false;
		String redirect = "";
		try {
			String url = hRequest.getServletPath();
			
			if(isUrlRecurso(url)){
				System.out.println("Es un recurso, seguimos.");
				continuar = true;
			}else if(isLogin(url)){
				continuar = true;
			}else {
				// Controlamos las páginas
				Usuario user = (Usuario)session.getAttribute("usuario");
				
				if(isAcceso(url)){
					// Entramos en acceso, si ya se está logado entramos al index
					redirect = "index.jsp";
					continuar = user != null && user.isValido();
					
				}else{
					// Entramos en cualquier página con obligación de autenticación
					redirect = "acceso.jsp";
					continuar = user == null || (user != null && user.isValido());
				}
				
			}
			
			System.out.println("Estamos en: " + url);
			
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	private boolean isLogin(String url) {
		return url.toLowerCase().equals("login");
	}

	private boolean isAcceso(String url) {
		return url.toLowerCase().equals("acceso.jsp");
	}

	private boolean isUrlRecurso(String url) {
		boolean recurso = false;
		
		for(int i = 0; i < listaRecursos.size() && !recurso; i++){
			recurso = url.indexOf(listaRecursos.get(i)) == -1;
		}
			
		return recurso;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//Excluimos recursos en los que no hay que controlar acceso
		
	}

}
