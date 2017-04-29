package es.consulter.cita;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.consulter.medico.ControlMedico;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ServletCita
 */
@WebServlet("/ServletCita")
public class ServletCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCita() {
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
		doGet(request, response);
		
		String opcion = request.getParameter("opcion");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ControlCita control = new ControlCita(request, response);
		JSONObject json = new JSONObject();
		
		switch (opcion) {
		case "consultarDNI":
			boolean existe = control.consultarDNIpaciente();
			json.put("existePaciente", existe);
			break;
		case "consultarMedicosEspecialidad":
			control.consultarMedicosEspecialidad();
			json.put("medicosDisponibles", control.getMedicos());
			break;
		case "guardarCita":
			control.iniciarInsertar();
			break;
		case "eliminarCita":
			control.iniciarEliminar();
			break;
		default:
			break;
		}
		
		PrintWriter out = response.getWriter();
		
		json.put("estado", control.isEstado());
		json.put("resultado", control.getResultado());
		
		out.print(json);
		
	}

}
