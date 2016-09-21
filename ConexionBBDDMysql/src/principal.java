

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import modelo.conexionBDMySQL;

public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResultSet listado = null;
		String eval = conexionBDMySQL.OpenConnection();
		if(eval.compareTo("")==0){
			listado = conexionBDMySQL.getUsuariosBBDD();
		}
		if(listado != null){
			muestraListado(listado);
			conexionBDMySQL.CloseConnection();
		}
		
		
	}
	
	public static void muestraListado(ResultSet listaUsuarios){
			System.out.println("--- Recorremos contenido de tabla ---\n");
			try {
				while (listaUsuarios.next()) {
					String id = listaUsuarios.getString(1);
					String nombreByPosCol = listaUsuarios.getString(2); // Obtiene valor por posicion de columna
					String nombreByNameCol = listaUsuarios.getString("nombre"); // Obtiene valor por nombre de columna

					System.out.println(id + " ," + nombreByPosCol + " ," + nombreByNameCol);
					id="";
					nombreByNameCol = "";
					nombreByPosCol = "";
				}
				System.out.println("\n--- Fin contenido de tabla ---");		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
}

