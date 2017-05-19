package es.consulter.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Conexion {
	 
	private final String JDBC;
	//private final String USER;
	//private final String PASS;
	
	private Connection con;

	private Statement stmt;
	private PreparedStatement prepare;
	private PreparedStatement prepareQuery;
	
	private Properties prop;
	
	/**
	 * 	Clase Conexión.<br/>
	 * 	Será la encargada de administrar la conexión .<br/>
	 * */
	public Conexion (){
		//Context initCtx;
		/*prop = new Properties();
		prop.load(getClass().getClassLoader().getResourceAsStream("resources/database.properties"));
		String entorno = getPropiedad("db.entorno");*/
		//this.USER = "user";
		//this.PASS = "pass";
		this.JDBC = "jdbc:sqlite:D:/AppClinicaFISS.db";
			
	}
	
	/**
	 * 	Método para iniciar la conexión con la base de datos.<br/>
	 * 	Inicia Connection con<br/>
	 * */
	public void conectar() throws Exception{
		try{
			Class.forName("org.sqlite.JDBC"); 
			con = DriverManager.getConnection(JDBC);
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("Conexion - conectar(): ClassNotFoundException");
			cnfe.printStackTrace();
			//throw cnfe;
		}		
		catch(SQLException sqle){
			System.out.println("Conexion - conectar(): SQLException");
			sqle.printStackTrace();
			//throw sqle;
		}
		catch(Exception e){
			System.out.println("Conexion - conectar(): Exception");
			e.printStackTrace();
			//throw e;
		}
	}
	
	
	/**
	 * 	Función para realizar una SELECT.
	 * 	@return ResultSet: datos de la consulta
	 * */
	public ResultSet select (String select) throws Exception{
		try{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(select);
			//rs.next();
			return rs;
		}
		catch(SQLException sqle){
			throw sqle;
		}
		catch(Exception e){
			throw e;
		}		
	}
	
	/**
	 * 	M�todo para iniciar un 'PreparedStatement' del tipo<br/>
	 * 	SELECT<br/>
	 * @throws SQLException 
	 * */
	public void prepareSelect (String sql) throws SQLException{
		try {
			prepareQuery = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("Error al preparar la select: ");
			//e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 	Método para iniciar un 'PreparedStatement' del tipo<br/>
	 * 	INSERT, UPDATE o DELETE<br/>
	 * @throws SQLException 
	 * */
	public void prepareSTMT (String sql) throws SQLException{
		try {
			prepare = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("Error al preparar la sentencia: ");
			//e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * 	M�todo para a�adir par�metros al 'PreparedStatement' ya iniciado<br/>
	 * 	@param index (int): Posición del par�metro a usar.
	 * 	@param parametro (Object): Parametro a usar. Se podr� castear a: Integer, 
	 * 	String, Float, Double, Long y null.
	 * */
	public void addParameter (int index, Object parametro) {
		//System.out.println("parametro instanceof = " + parametro.getClass().getName());
		
		if(parametro instanceof Integer){
			try {
				prepare.setInt(index, (Integer)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro INT");
				e.printStackTrace();
			}
		}else if(parametro instanceof String){
			try {
				prepare.setString(index, (String)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro STRING");
				e.printStackTrace();
			}
		}else if(parametro instanceof Float){
			try {
				prepare.setFloat(index, (Float)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro FLOAT");
				e.printStackTrace();
			}
		}else if(parametro instanceof Double){
			try {
				prepare.setDouble(index, (Double)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro DOUBLE");
				e.printStackTrace();
			}
		}else if(parametro instanceof Long){
			System.out.println("En addParameter: parametro instanceof Long");
			try {
				prepare.setLong(index, (Long)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro LONG");
				e.printStackTrace();
			}
		}else if (parametro == null){
			try {
				prepare.setString(index, null);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro NULL");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 	M�todo para a�adir par�metros al 'PreparedStatement' ya iniciado<br/>
	 * 	@param index (int): Posición del par�metro a usar.
	 * 	@param parametro (Object): Parametro a usar. Se podr� castear a: Integer, 
	 * 	String, Float, Double, Long y null.
	 * */
	public void addParameterSelect (int index, Object parametro) {
		if(parametro instanceof Integer){
			try {
				prepareQuery.setInt(index, (Integer)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro INT");
				e.printStackTrace();
			}
		}else if(parametro instanceof String){
			try {
				prepareQuery.setString(index, (String)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro STRING");
				e.printStackTrace();
			}
		}else if(parametro instanceof Float){
			try {
				prepareQuery.setFloat(index, (Float)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro FLOAT");
				e.printStackTrace();
			}
		}else if(parametro instanceof Double){
			try {
				prepareQuery.setDouble(index, (Double)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro DOUBLE");
				e.printStackTrace();
			}
		}else if(parametro instanceof Long){
			try {
				prepareQuery.setLong(index, (Long)parametro);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro LONG");
				e.printStackTrace();
			}
		}else if (parametro == null){
			try {
				prepareQuery.setString(index, null);
			} catch (SQLException e) {
				System.out.println("Error al insertar parametro NULL");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 	M�todo para ejecutar el 'PreparedStatement' iniciado y a�adido par�metros(si tuviera).<br/>
	 * */
	public void ejecutarUpdt() {
		try {
			prepare.executeUpdate();
			//prepare.close();
		} catch (SQLException e) {
			System.out.println("Error al ejecutar Update.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 	M�todo para ejecutar el 'PreparedStatement' iniciado y a�adido par�metros(si tuviera).<br/>
	 * */
	public ResultSet ejecutarSelect() {
		try {
			ResultSet rs = prepareQuery.executeQuery();
			return rs;
			//prepare.close();
		} catch (SQLException e) {
			System.out.println("Error al ejecutar Update.");
			e.printStackTrace();
			throw null;
		}
		
	}
	
	/**
	 * 	Método para cerrar el 'PreparedStatement'
	 * */
	public void closeSelect(){
		try{
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * 	M�todo para cerrar el prepared query.
	 * */
	public void closePreparedSelect(){
		try{
			prepareQuery.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 	M�todo para cerrar el prepared statement.
	 * */
	public void closePrepared(){
		try{
			prepare.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Connection getCon() {
		return con;
	}

	public PreparedStatement getPrepare() {
		return prepare;
	}

	public PreparedStatement getPrepareQuery() {
		return prepareQuery;
	}
	
	/**
	 * 	Método para iniciar una transacción:<br/>
	 * 	Poder realizar varias ejecuciones y realizar el commit o rollback de forma manual.
	 * */
	public void transaction() throws SQLException{
		con.setAutoCommit(false);
	}
	
	/**
	 * 	Método para realizar COMMIT
	 * */
	public void commit () throws SQLException{
		con.commit();
	}
	
	/**
	 * 	Método para realizar ROLLBACK
	 * */
	public void rollback (){
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Método para desconectar la conexión a la base de datos.
	 * */
	public void desconectar(){
		try{
			if (con!=null){
				con.close();
			}	
		}catch(SQLException sqle){
			System.out.println("Error al desconectar (SQL): " + sqle.getMessage());
			sqle.printStackTrace();
			//throw sqle;
		}
		catch(Exception e){
			System.out.println("Error al desconectar (Exception): " + e.getMessage());
			e.printStackTrace();
			//throw e;
		}	
	}

	private String getPropiedad(String propiedad) {
		String valor = "";
		try {
			valor = (String)prop.get(propiedad);
		} catch (Exception e) {
			System.out.println("Excepcion al obtener la propiedad: " + propiedad);
		}
		return valor;
	}

}
