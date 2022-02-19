package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "libros")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private double precio;
	// Para la relación N a 1 con Autor
	/* -------------------------
	 * Relación de muchos a uno
	 *--------------------------
	 * Usaremos la etiqueta @ManyToOne, con esta etiqueta sería suficiente
	 * para hacer una relacion unidireccional.
	 * OJO con los cascades en esta etiqueta, poner un CascadeType.ALL hace que al
	 * borrar un libro se borrare también el autor de dicho libro... MUY PELIGROSO
	 */
	@ManyToOne
	/* En una relacion de "uno a muchos", la FK siempre esta en el lado de "Muchos", en una
	 * relacion de "uno a uno" podemos poner la FK donde queramos.
	 * Por lo tanto la @JoinColumn siempre estará en este lado en este tipo de relaciones
	 */
	@JoinColumn(name="fk_id_autor", referencedColumnName="id")
	private Autor autor;
	//Para la relación N a 1 con Editorial
	@ManyToOne
	@JoinColumn(name="fk_id_editorial", referencedColumnName="id")
	private Editorial editorial;
	
	// Para la relación N a N con Libreria
	// NOTA: Es mejor crear una tabla intermedia y hacer relaciones 1 a N y N a 1
	// pero se ha optado por esta solución por practicar todos los casos.
	/* ------------------------------
	 * Relacion de "muchos a muchos"
	 * ------------------------------
	 * En este tipo de relaciones se nos creara una tabla intermedia automacticamente
	 * con las FK a las PK de las otras tablas.
	 * Es una relacion que muchas veces es mejor crear una entidad nueva intermedia
	 * y hacer relaciones 1 a N y N a 1 con esa entidad nueva creada, porque así
	 * podemos crear esa tabla intermedia a nuestro gusto, con los atributos
	 * que queramos. 
	 * Si hacemos una relación ManyToMany se nos creara esta tabla intermedia, 
	 * pero no podremos modificar sus campos.
	 * 
	 * OJO! CascadeType.ALL es PELIGROSISIMO, ya que 
	 * si borramos un libro, borraremos tambien las librerias
	 * asociadas, y seguramente esos librerias sean usados por
	 * otros libros.
	 */
	@ManyToMany(mappedBy="listaLibros", cascade=CascadeType.PERSIST) 
	private List<Libreria> listaLibrerias;
	
	
	//Constructor Genérico
	public Libro() {
		super();
	}
	
	public Libro(Integer id, String titulo, double precio, Autor autor, Editorial editorial,
			List<Libreria> listaLibrerias) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.precio = precio;
		this.autor = autor;
		this.editorial = editorial;
		this.listaLibrerias = listaLibrerias;
	}

	//toString()
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", precio=" + precio + ", editorial=" + editorial + ", autor="
				+ autor + "]";
	}

	//GETTERS & SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Editorial getEditorial() {
		return editorial;
	}
	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Libreria> getListaLibrerias() {
		return listaLibrerias;
	}

	public void setListaLibrerias(List<Libreria> listaLibrerias) {
		this.listaLibrerias = listaLibrerias;
	}
	
}
