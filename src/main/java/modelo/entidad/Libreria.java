package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "librerias")
public class Libreria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String duenno;
	@Embedded
	private Direccion direccion;
	//Relación N a N con Libro
	/* En este caso @JoinTable daremos instrucciones para crear la tabla intermedia
	 * que JPA creara para hacer la realcion "Many to Many" 
	 * Usa los siguientes parametros
	 * 1. name -> El nombre de la tabla intermedia
	 * 2. joinColumns -> las columnas FK y PK que aporta esta entidad (Libreria)
	 * 3. inverseJoinColumns -> las columnas FK y PK que me aporta la otra Entidad (Libro)
	 */
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="librerias_libros",
			   joinColumns= { @JoinColumn(name="fk_id_libreria", referencedColumnName="id") }, //FK que aporta Libreria
			   inverseJoinColumns= { @JoinColumn(name="fk_id_libro", referencedColumnName="id")}) //FKs que aportan el resto de entidades
	private List<Libro> listaLibros;
	
	
	//Constructor Genérico
	public Libreria() {
		super();
	}	
	
	public Libreria(Integer id, String nombre, String duenno, Direccion direccion, List<Libro> listaLibros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duenno = duenno;
		this.direccion = direccion;
		this.listaLibros = listaLibros;
	}

	//toString()
	@Override
	public String toString() {
		return "Libreria [id=" + id + ", nombre=" + nombre + ", duenno=" + duenno + ", direccion=" + direccion
				+ ", listaLibros=" + listaLibros + "]";
	}
	
	//GETTERS & SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDuenno() {
		return duenno;
	}
	public void setDuenno(String duenno) {
		this.duenno = duenno;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public List<Libro> getListaLibros() {
		return listaLibros;
	}
	public void setListaLibros(List<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}
	
}
