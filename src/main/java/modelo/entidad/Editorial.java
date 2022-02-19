package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "editoriales")
public class Editorial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	@Embedded
	private Direccion direccion;
	//De la relacion 1 a N con Libro
	@OneToMany(mappedBy="editorial", cascade=CascadeType.PERSIST) 
	private List<Libro> librosPublicados;
	
	
	//Constructores
	public Editorial() {
		super();
	}
	
	public Editorial(Integer id, String nombre, Direccion direccion, List<Libro> librosPublicados) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.librosPublicados = librosPublicados;
	}

	//toString()
	@Override
	public String toString() {
		return "Editorial [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", librosPublicados="
				+ librosPublicados + "]";
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
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public List<Libro> getLibrosPublicados() {
		return librosPublicados;
	}
	public void setLibrosPublicados(List<Libro> librosPublicados) {
		this.librosPublicados = librosPublicados;
	}
	
}
