package es.consulter.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
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
        listaRecursos.add("/contenido_dinamico/js/");
        listaRecursos.add("/contenido_dinamico/css/");
        listaRecursos.add("/include/");
        listaRecursos.add("/lib/");
        listaRecursos.add("/img/");
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
            System.out.println("Checkamos: " + url);

            if(isUrlRecurso(url)){
                System.out.println("Es un recurso, seguimos.");
                continuar = true;
            } else if(isLogin(url)){
                continuar = true;
            } else {
                // Controlamos las páginas
                Usuario user = (Usuario)session.getAttribute("usuario");
                if(url == "/acceso/acceso.jsp"){
                    System.out.println("asdf");
                    continuar = false;
                    redirect = "/";
                }
                else if(isAcceso(url)){
                    // Entramos en acceso, si ya se está logado entramos al index
                    redirect = "/listado-citas";

                    continuar = ( user == null || !user.isValido() );

                    if(continuar){
                        System.out.println("Vamos a acceso, seguimos.");
                    } else {
                        System.out.println("Vamos a acceso con sesión, redirigimos a '" + redirect + "'.");
                    }
                } else {

                    // Entramos en cualquier página con obligación de autenticación
                    redirect = "/";
                    continuar = user != null && user.isValido();

                    if(continuar){
                        System.out.println("Estamos dentro de la aplicacion, seguimos.");
                    }else{
                        System.out.println("Estamos dentro de la aplicacion sin sesión, redirigimos.");
                    }
                }
                System.out.println(" ");
            }

            if(continuar){
                chain.doFilter(request, response);
            } else {
                hResponse.sendRedirect(redirect);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // pass the request along the filter chain
        //chain.doFilter(request, response);
    }

    private boolean isLogin(String url) {
        return url.toLowerCase().contains("login");
    }

    private boolean isAcceso(String url) {
        return url.toLowerCase().contains("acceso.")/* || url.equals("http://localhost:8080/")*/;
    }

    private boolean isUrlRecurso(String url) {
        boolean recurso = false;

        for(int i = 0; i < listaRecursos.size() && !recurso; i++){
            recurso = url.indexOf(listaRecursos.get(i)) != -1;
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
