package es.consulter.paciente;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ServletEspecialidad
 */
@WebServlet("/ServletPaciente")
public class ServletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPaciente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String opcion = request.getParameter("opcion");
			
			ControlPaciente control = new ControlPaciente(request, response);
			
			switch (opcion) {
			case "insertar":
				control.iniciarInsertar();
				break;
			case "actualizar":
				control.iniciarActualizar();
				break;
			case "eliminar":
				control.iniciarEliminar();
				break;
				
			default:
				break;
			}
			
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("estado", control.isEstado());
			json.put("resultado", control.getResultado());
			
			out.print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
