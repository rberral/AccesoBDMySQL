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
	private static String name_bbdd = "sql7131893"; // catálogo
	private static String user_bbdd = "root";
	private static String pass_bbdd = "";
	private static String tabla_usuario = "usuario";

	public static String OpenConnection() {
		String urlConexionMySQL = "";
		String eval = "";
		estado = null;
		//if (conexionMySQL == null) {
			
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
			//}

		}
		return eval;
	}

	public static void CloseConnection() {
		try {
			conexionMySQL.close();
			estado.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	public static void CloseStatement(){
		try {
			estado.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public static ResultSet getUsuariosBBDD() {
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
	
	public static int addUsuario(){
		int eval = 0;
		String apel = "Apellidos desde Java";
		String name = "Usuario desde Java";
		String sql = "INSERT INTO usuario(nombre,apellidos) VALUES ('"+name+"','"+apel+"')";

		conexionBDMySQL.OpenConnection();
		try {
			estado.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			eval = 400;
		}

		conexionBDMySQL.CloseConnection();
		//conexionBDMySQL.CloseStatement();
		
		return eval;
	}
	
	public static int deleteUsuario(int id){
		int eval = 200;
		boolean oper = false;
		String sql = "DELETE FROM usuario WHERE id_usuario = '"+id+"'";

		conexionBDMySQL.OpenConnection();
		try {
			estado.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			eval = 400;
		}

		conexionBDMySQL.CloseConnection();
		//conexionBDMySQL.CloseStatement();
		
		return eval;
	}
	
	public static int updateUsuario(int id){
		int eval = 0;
		String apel = "Apellidos MODIF desde Java";
		String name = "Usuario MODIF desde Java";
		//String sql = "INSERT INTO usuario(nombre,apellidos) VALUES ('"+name+"','"+apel+"')";
		String sql = "UPDATE usuario SET nombre = '"+name+"', apellidos = '"+apel+"' WHERE id_usuario = '"+id+"'";
		conexionBDMySQL.OpenConnection();
		try {
			 estado.executeUpdate(sql);
			 
				switch(estado.getUpdateCount()){
				case 1: 
					eval = 200;
					break;
				case 0: 
					eval = 300; // No ha actualizado porque no existe Usuario con ese Id
					break;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			eval = 400;
		}

		

		conexionBDMySQL.CloseConnection();
		//conexionBDMySQL.CloseStatement();
		
		return eval;
	}
	
	public static int getUsuario(int id){
		int eval = 0;
		ResultSet rs = null;
		//String sql = "SELECT * FROM usuario WHERE id_usuario = '"+id+"'";
		String sql = "SELECT * FROM usuario WHERE id_usuario = '"+id+"'";
		conexionBDMySQL.OpenConnection();
		try {
			rs = (ResultSet) estado.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			eval = 400;
		}
		try {
			//rs.beforeFirst();
			if(rs.next()) {
				String ident = rs.getString(1);
				String nombreByPosCol = rs.getString(2); 
				String apelByNameCol = rs.getString("apellidos"); 

				System.out.println(ident + " ," + nombreByPosCol + " ," + apelByNameCol);
				}
			else{
				eval = 404;
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		conexionBDMySQL.CloseConnection();
		//conexionBDMySQL.CloseStatement();
		
		return eval;
	}
	

}
