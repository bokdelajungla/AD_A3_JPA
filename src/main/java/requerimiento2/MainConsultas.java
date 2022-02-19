package requerimiento2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tech.tablesaw.api.Table;


public class MainConsultas {
	
	private static Connection conexion;
	
	public static void main(String[] args) {
		
		/*Consulta primera:
		 * Mostrar todos los libros dados de alta, con su editorial y su autor
		 */
		abrirConexion();
		
		String query = "SELECT libros.id, libros.titulo, libros.precio, editoriales.nombre, autores.nombre, autores.apellidos "
				+ "FROM libros "
				+ "JOIN editoriales ON editoriales.id = libros.fk_id_editorial "
				+ "JOIN autores ON autores.id = libros.fk_id_autor";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
				
			ResultSet rs = ps.executeQuery();
			
			/*
			//Usamos la libreria TableSaw (incluida en las dependencias de Maven)
			System.out.println(Table.read().db(rs).print());
			System.out.println();
			*/
			
			while(rs.next()){
				int id = (rs.getInt(1));
				String titulo = rs.getString(2);
				Double precio = rs.getDouble(3);
				String nombreEditorial = rs.getString(4);
				String nombreAutor = rs.getString(5);
				String apellidosAutor = rs.getString(6);
				System.out.println(id + " - " + titulo + " - " + precio + " - " + nombreEditorial + 
									" - " + nombreAutor + " " + apellidosAutor);
			}
			
		} catch (SQLException e) {
				System.out.println("Error al realizar la primera consulta");
				e.printStackTrace();
		} finally {
				cerrarConexion();
		}
		
		/*Consulta segunda:
		 * Mostrar todos los autores dados de alta, con sus libros asociados
		 */
		abrirConexion();
		
		query = "SELECT autores.id, autores.nombre, autores.apellidos, libros.titulo "
				+ "FROM autores, libros "
				+ "WHERE autores.id = libros.fk_id_autor "
				+ "ORDER BY autores.id";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
				
			ResultSet rs = ps.executeQuery();
			//Usamos la libreria TableSaw (incluida en las dependencias de Maven)
			System.out.println(Table.read().db(rs).print());
			System.out.println();
			
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la segunda consulta");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		/*Consulta tercera:
		 * Mostrar todas las librerías, con solamente sus libros asociados
		 */
		abrirConexion();
		
		query = "SELECT librerias.id, librerias.nombre, libros.titulo "
				+ "FROM librerias "
				+ "JOIN librerias_libros ON librerias.id = librerias_libros.fk_id_libreria "
				+ "JOIN libros ON libros.id = librerias_libros.fk_id_libro "
				+ "ORDER BY librerias.id";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
				
			ResultSet rs = ps.executeQuery();
			//Usamos la libreria TableSaw (incluida en las dependencias de Maven)
			System.out.println(Table.read().db(rs).print());
			System.out.println();
			
			
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la tercera consulta");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		/*Consulta cuarta:
		 * Mostrar todos los libros dados de alta, y en la librería en la que están.
		 */
		abrirConexion();
		
		query = "SELECT libros.id, libros.titulo, librerias.nombre "
			   +"FROM libros "
			   +"JOIN librerias_libros ON libros.id = librerias_libros.fk_id_libro "
			   +"JOIN librerias ON librerias.id = librerias_libros.fk_id_libreria "
			   +"ORDER BY libros.id"; 
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
				
			ResultSet rs = ps.executeQuery();
			//Usamos la libreria TableSaw (incluida en las dependencias de Maven)
			System.out.println(Table.read().db(rs).print());
			
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la cuarta consulta");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
			
	}
	
	public static boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/a3_jpa";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
