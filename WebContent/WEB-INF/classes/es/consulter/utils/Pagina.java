package es.consulter.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import es.consulter.conexion.Conexion;

public abstract class Pagina implements InControl{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected PageContext pageContext;
	protected Conexion conexion;
	
	
	protected HttpServletRequest getRequest() {
		return request;
	}
	private void setRequest(HttpServletRequest request) {
		this.request = request;
		setSession(request.getSession());
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
	
	protected PageContext getPageContext() {
		return pageContext;
	}
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
		setRequest((HttpServletRequest) this.pageContext.getRequest());
		setResponse((HttpServletResponse) this.pageContext.getResponse());
		
		onLoad();
	}
	
	public double getRandomNumber(){
		return (Math.random() / Math.random()) * 10;
	}
	
	public Pagina() {
		super();
	}
	
	protected abstract void onLoad();
	protected abstract void cargarDatos();
	
}
