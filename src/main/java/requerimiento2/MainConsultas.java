package requerimiento2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.entidad.Autor;
import modelo.entidad.Libreria;
import modelo.entidad.Libro;

public class MainConsultas {

	// incializaos el entity manager y la factoria de entity manager a null
	public static EntityManagerFactory emf = null;
	public static EntityManager em = null;
	
	public static void main(String[] args) {
		

		// incializaos el entity manager y la factoria de entity manager con la persistencia correspondiente
		emf = Persistence.createEntityManagerFactory("ActividadJPA");
		em = emf.createEntityManager();
		
		/*
		 * Primera consulta:
		 * Mostrar todos los libros dados de alta, con su editorial y su autor
		 */
		System.out.println("** Listado de libros con su editorial y su autor **");
		// realizamos la consulta JPQL
		Query query = em.createQuery("SELECT l FROM Libro l ORDER BY l.titulo");
		// guardamos el resultado en una lista de tipo Libro
		List<Libro> libros = query.getResultList();
		// imprimos el resultado con el metodo correspondiente
		listarLibros1(libros);
		
		/*
		 * Segunda consulta:
		 * Mostrar todos los autores dados de alta, con sus libros asociados
		 */
		System.out.println("** Listado de autores con sus libros **");
		// realizamos la consulta JPQL
		query = em.createQuery("SELECT a FROM Autor a ORDER BY a.nombre");
		// guardamos el resultado en una lista de tipo Autor
		List<Autor> autores = query.getResultList();
		// imprimos el resultado con el metodo correspondiente
		listarAutores(autores);
		
		/*
		 * Tercera consulta:
		 * Mostrar todas las librerías, con solamente sus libros asociados
		 */
		
		System.out.println("** Listado de librerias con sus libros **");
		// realizamos la consulta JPQL
		query = em.createQuery("SELECT lib FROM Libreria lib ORDER BY lib.nombre");
		// guardamos el resultado en una lista de tipo Libreria
		List<Libreria> librerias = query.getResultList();
		listarLibrerias(librerias);
		
		/*
		 * Cuarta consulta:
		 * Mostrar todos los libros dados de alta, y en la librería en la que están
		 */
		System.out.println("** Listado de libros y la libreria en la que estan **");
		// realizamos la consulta JPQL
		query = em.createQuery("SELECT l FROM Libro ORDER BY l.titulo");
		// guardamos el resultado en una lista de tipo Libro
		libros = query.getResultList();
		// imprimos el resultado con el metodo correspondiente
		listarLibros2(libros);
		
		// cerramos el entity manager y la factoria de entity manager (NECESARIO CERRARLAS)
		em.close();
		emf.close();
	}
	
	/**
	 * metodo que imprime por pantalla el nombre de los libros con el de sus autores y editoriales
	 * @param listaLibros lista de libros obtenida de la consulta
	 */
	public static void listarLibros1(List<Libro> listaLibros) {
		for(Libro l : listaLibros) {
			System.out.println("Titulo: " + l.getTitulo() + ", autor: " + l.getAutor().getNombre() + " " + l.getAutor().getApellidos() + ", editorial: " + l.getEditorial().getNombre());
		}
	}
	
	/**
	 * metodo que imprime por pantalla el nombre de los autores con el titulo de sus libros
	 * @param listaAutores lista de autores obtenida de la consulta
	 */
	public static void listarAutores(List<Autor> listaAutores) {
		for(Autor a : listaAutores) {
			System.out.println(a.getNombre() + a.getApellidos() + ", libros escritos:" );
			for(Libro l : a.getLibros()) {
				System.out.println("\t📖" + l.getTitulo());
			}
			System.out.println();	
		}
	}
	
	/**
	 * metodo que imprime por pantalla el nombre de de las librerias con el titulo de sus libros editados
	 * @param listaLibrerias lista de librerias obteida de la consulta
	 */
	public static void listarLibrerias(List<Libreria> listaLibrerias) {
		for(Libreria lib : listaLibrerias) {
			System.out.println(lib.getNombre() + ", libros editados:" );
			for(Libro l : lib.getListaLibros()) {
				System.out.println("\t📖" + l.getTitulo());
			}
			System.out.println();	
		}
	}
	
	/**
	 * metodo que imprime por pantalla el nombre de los libros con las librerias donde se venden y sus direcciones
	 * @param listaLibros lista de libros obtenida de la consulta
	 */
	public static void listarLibros2(List<Libro> listaLibros) {
		for(Libro l : listaLibros) {
			System.out.println(l.getTitulo() + ", diponible en:");
			for(Libreria lib : l.getListaLibrerias()) {
				System.out.println("\t🏠" + lib.getNombre() + ", direccion:" + lib.getDireccion().dirString());
			}
			System.out.println();	
		}
	}

}
