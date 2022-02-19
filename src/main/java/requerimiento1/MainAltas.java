package requerimiento1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.entidad.Autor;
import modelo.entidad.Direccion;
import modelo.entidad.Editorial;
import modelo.entidad.Libreria;
import modelo.entidad.Libro;

public class MainAltas {
	
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory("ActividadJPA");
	private static EntityManager em = null;
	
	private static Autor autor1, autor2, autor3;
	
	private static Editorial editorial1, editorial2;

	private static Libro libro1, libro2, libro3, libro4,libro5,libro6, libro7, libro8;
	
	private static Libreria libreria1, libreria2;
	
	public static void main(String[] args) {

			
			//Damos de alta los autores
			altaAutores();
						
			// Dar de alta 2 editoriales.
			altaEditoriales();
			
			//Dar de alta 8 libros
			//En realidad no se dan de alta, porque necesitamos crear las librerias primero
			altaLibros();
			
			//Dar de alta 2 librerías
			//Junto con los 8 libros mediante Cascade
			altaLibrerias();
			
	}

	private static void altaAutores() {
		
		// Para convertir las fechas de String a Date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

		// Creamos los objetos
		try {
			// Dar de alta 3 autores.
			// Autor 1
			autor1 = new Autor(null, "George", "Caldero", formatter.parse("10-10-2010"), null);
			// Autor 2
			autor2 = new Autor(null, "Agapito", "DaSoussa", formatter.parse("6-9-1969"), null);
			// Autor 3
			autor3 = new Autor(null, "Aitor", "Tazo", formatter.parse("19-12-1912"), null);
			
			em =factoria.createEntityManager();
			em.getTransaction().begin();
			em.persist(autor1); 
			em.persist(autor2); 
			em.persist(autor3);
			em.getTransaction().commit();
			em.close();
			System.out.println("----- Autores Creados -----");
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void altaEditoriales() {
		
		//Editorial 1
		editorial1 = new Editorial(null, "Cardamomo", new Direccion("C/ Filomena, 22", "18004", "Granada"), null);
		//Editorial 2
		editorial2 = new Editorial(null, "Estragón", new Direccion("C/ Hermenegildo, 35B", "11550", "Chipiona"), null);
		
		em =factoria.createEntityManager();
		em.getTransaction().begin();
		em.persist(editorial1); 
		em.persist(editorial2);
		em.getTransaction().commit();
		em.close();
		System.out.println("----- Editoriales Creadas -----");	
	
	}
	
	private static void altaLibros() {
		//Cada libro será escrito por uno de los autores dados de alta previamente y 
		//pertenecerá a uno de las editoriales dadas de alta previamente
		//Libro 1
		libro1 = new Libro(null, "Lo que el JPA se llevó", 45, null, null, null);
		libro1.setAutor(autor1);
		libro1.setEditorial(editorial1);
		//Libro 2
		libro2 = new Libro(null, "JPA para dummies", 45, null, null, null);
		libro2.setAutor(autor1);
		libro2.setEditorial(editorial2);
		//Libro 3
		libro3 = new Libro(null, "La venganza de JPA", 45, null, null, null);
		libro3.setAutor(autor2);
		libro3.setEditorial(editorial1);
		//Libro 4
		libro4 = new Libro(null, "Juego de JPAs", 45, null, null, null);
		libro4.setAutor(autor2);
		libro4.setEditorial(editorial2);
		//Libro 5
		libro5 = new Libro(null, "JPA, SQL y yo", 45, null, null, null);
		libro5.setAutor(autor3);
		libro5.setEditorial(editorial2);
		//Libro 6
		libro6 = new Libro(null, "Con qué sueña JPA", 45, null, null, null);
		libro6.setAutor(autor3);
		libro6.setEditorial(editorial2);
		//Libro 7
		libro7 = new Libro(null, "El JPA que odiaba a los alumnos", 45, null, null, null);
		libro7.setAutor(autor1);
		libro7.setEditorial(editorial1);
		//Libro 8
		libro8 = new Libro(null, "JPA, el perfecto desconocido", 45, null, null, null);
		libro8.setAutor(autor2);
		libro8.setEditorial(editorial2);

		/*
		 * No podemos persistir los libros hasta crear las librerias
		 * Daría un error después
		em =factoria.createEntityManager();
		em.getTransaction().begin();
		em.persist(libro1); 
		em.persist(libro2);
		em.persist(libro3); 
		em.persist(libro4);
		em.persist(libro5); 
		em.persist(libro6);
		em.persist(libro7); 
		em.persist(libro8);
		em.getTransaction().commit();
		em.close();
		System.out.println("----- Libros Creados -----");
		*/
	}
	
	private static void altaLibrerias() {
		//Cada librería tendrá 4 libros dados de alta previamente.
		//Libreria 1
		libreria1 =  new Libreria(null, "Libreria Cantamañanas", "Aitor Tilla", 
				new Direccion("C/ Castaña, 56", "08008", "Barcelona"), null);
		libreria2 =  new Libreria(null, "La Memesfera", "Pepe Clown", 
				new Direccion("C/ Costra, 25", "46006", "Valencia"), null);
		
		//Para hacer la relación N a M, añadimos las librerias a los libros y los libros a las librerias
		//Libro 1
		List<Libreria> libreriasLibro1 = new ArrayList<Libreria>();
		libreriasLibro1.add(libreria1);
		libreriasLibro1.add(libreria2);
		libro1.setListaLibrerias(libreriasLibro1);
		
		//Libro 2
		List<Libreria> libreriasLibro2 = new ArrayList<Libreria>();
		libreriasLibro2.add(libreria1);
		libro1.setListaLibrerias(libreriasLibro2);
		
		//Libro 3
		List<Libreria> libreriasLibro3 = new ArrayList<Libreria>();
		libreriasLibro3.add(libreria1);
		libro1.setListaLibrerias(libreriasLibro3);
		
		//Libro 4
		List<Libreria> libreriasLibro4 = new ArrayList<Libreria>();
		libreriasLibro4.add(libreria1);
		libro1.setListaLibrerias(libreriasLibro4);
		
		//Libro 5
		List<Libreria> libreriasLibro5 = new ArrayList<Libreria>();
		libreriasLibro5.add(libreria2);
		libro1.setListaLibrerias(libreriasLibro5);
		
		//Libro 6
		List<Libreria> libreriasLibro6 = new ArrayList<Libreria>();
		libreriasLibro6.add(libreria2);
		libro1.setListaLibrerias(libreriasLibro6);
		
		//Libro 7
		List<Libreria> libreriasLibro7 = new ArrayList<Libreria>();
		libreriasLibro7.add(libreria2);
		libro1.setListaLibrerias(libreriasLibro7);
		
		//Libro 8
		List<Libreria> libreriasLibro8 = new ArrayList<Libreria>();
		libreriasLibro8.add(libreria1);
		libreriasLibro8.add(libreria2);
		libro1.setListaLibrerias(libreriasLibro8);
		
		//Ahora las Librerias
		List<Libro> librosLib1 = new ArrayList<Libro>();
		librosLib1.add(libro1);
		librosLib1.add(libro2);
		librosLib1.add(libro3);
		librosLib1.add(libro4);
		librosLib1.add(libro8);
		libreria1.setListaLibros(librosLib1);
		
		List<Libro> librosLib2 = new ArrayList<Libro>();
		librosLib2.add(libro1);
		librosLib2.add(libro5);
		librosLib2.add(libro6);
		librosLib2.add(libro7);
		librosLib2.add(libro8);
		libreria2.setListaLibros(librosLib2);
		
		//Persistimos los objetos
		em =factoria.createEntityManager();
		em.getTransaction().begin();
		
		//Está configurado el CascadeType.PERSIST así que al añadir las librerias
		//añadirá también los libros (que no se añadieron antes de forma deliverada)
		em.persist(libreria1);
		em.persist(libreria2);
		em.getTransaction().commit();
		em.close();
		factoria.close();
		System.out.println("----- Libros Creados -----");
		System.out.println("----- Librerias Creadas -----");
	}
	
}
