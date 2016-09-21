package modelo;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class conexionBDMySQL {

	static Connection conexionMySQL;
	private static Statement estado;
	private static String IP = "127.0.0.1";
	private static String puerto = "3306";
	private static String name_bbdd = "sql7131893"; //catálogo
	private static String user_bbdd = "root";
	private static String pass_bbdd = "";
	private static String tabla_usuario = "usuario"; 

	
	
	public static String OpenConnection(){
 
		String eval = "";
		if (conexionMySQL == null) {
			String urlConexionMySQL = "";
			urlConexionMySQL = "jdbc:mysql://" + IP + ":" + puerto + "/" + name_bbdd;

			if (urlConexionMySQL != "") {
				try {

					Class.forName("com.mysql.jdbc.Driver");
					conexionMySQL = (Connection) DriverManager.getConnection(urlConexionMySQL, user_bbdd, pass_bbdd);
					estado = (Statement) conexionMySQL.createStatement();
					
				} catch (ClassNotFoundException e) {
					eval = e.getMessage();
				} catch (SQLException e) {
					eval = e.getMessage();
				}
			}

		}
		return eval;
	}
	
	public static void CloseConnection(){
		try {
			conexionMySQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ResultSet getUsuariosBBDD(){
		ResultSet result = null;
		String peticion = "select * from " + tabla_usuario;

			try {
				result = (ResultSet) estado.executeQuery(peticion);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
	}
	
	
}
