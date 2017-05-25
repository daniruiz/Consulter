package es.consulter.utils;

public class PaginaIndex extends Pagina {

	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void cargarDatos() {
		// TODO Auto-generated method stub

	}

	public int getPerfil(){
		return (Integer) session.getAttribute("perfil");
	}
	
}
