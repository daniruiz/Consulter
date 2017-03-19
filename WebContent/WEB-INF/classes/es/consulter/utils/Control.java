package es.consulter.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.consulter.conexion.Conexion;

public abstract class Control implements InControl{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	protected Conexion conexion;
	
	private boolean estado;
	private String resultado;
	
	protected HttpServletRequest getRequest() {
		return request;
	}
	private void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	protected HttpServletResponse getResponse() {
		return response;
	}
	private void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	protected HttpSession getSession() {
		return session;
	}
	private void setSession(HttpSession session) {
		this.session = session;
	}
	
	public boolean isEstado() {
		return estado;
	}
	protected void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public String getResultado() {
		return resultado;
	}
	protected void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public Control(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.setRequest(request);
		this.setResponse(response);
		setSession(request.getSession());
	}
	
	public abstract void iniciarInsertar();
	public abstract void iniciarActualizar();
	public abstract void iniciarEliminar();
	public abstract void cargarDatos();
	
}
