
import java.io.StringWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import modelo.conexionBDMySQL;

public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResultSet listado = null;
		String eval = conexionBDMySQL.OpenConnection();
		if (eval.compareTo("") == 0) {
			listado = conexionBDMySQL.getUsuariosBBDD();
		}		
		try {
		if (listado != null) {
			muestraListado(listado);
			listado.beforeFirst(); // Necesario para poder volver a recorrer el listado
			StreamResult fileXML = convertToXML(listado);
			
			conexionBDMySQL.CloseConnection();
			//conexionBDMySQL.CloseStatement();//Cerramos ResultSet
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static void muestraListado(ResultSet listaUsuarios) {
		System.out.println("--- Recorremos contenido de tabla ---");
		try {
			while (listaUsuarios.next()) {
				String id = listaUsuarios.getString(1);
				String nombreByPosCol = listaUsuarios.getString(2); // Obtiene
																	// valor por
																	// posicion
																	// de
																	// columna
				String nombreByNameCol = listaUsuarios.getString("nombre"); // Obtiene
																			// valor
																			// por
																			// nombre
																			// de
																			// columna

				System.out.println(id + " ," + nombreByPosCol + " ," + nombreByNameCol);
				id = "";
				nombreByNameCol = "";
				nombreByPosCol = "";
			}
			System.out.println("--- Fin contenido de tabla ---");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static StreamResult convertToXML(ResultSet listaUsuarios) {
		int colCount = 0;
		StreamResult sr = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = builder.newDocument();
		Element results = doc.createElement("Results");
		doc.appendChild(results);

		ResultSetMetaData rsmd;
		try {
			rsmd = listaUsuarios.getMetaData();
			colCount = rsmd.getColumnCount();

			while (listaUsuarios.next()) {
				Element row = doc.createElement("Row");
				results.appendChild(row);
				for (int i = 1; i <= colCount; i++) {
					String columnName = rsmd.getColumnName(i);
					Object value = listaUsuarios.getObject(i);
					Element node = doc.createElement(columnName);
					if (value == null) {
						node.appendChild(doc.createTextNode(""));

					} else {
						node.appendChild(doc.createTextNode(value.toString()));
					}
					row.appendChild(node);
				}
			}


		DOMSource domSource = new DOMSource(doc);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			StringWriter sw = new StringWriter();
			sr = new StreamResult(sw);
			transformer.transform(domSource, sr);

			System.out.println("\n\n--- Listado convertido a XML ---");
			System.out.println(sw.toString());
			// listaUsuarios.close();

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sr;
	}

}
