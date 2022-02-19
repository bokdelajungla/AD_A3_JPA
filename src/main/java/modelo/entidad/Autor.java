//Esta clase contiene anotaciones adicionales para ayudar al repaso
package modelo.entidad;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/* Con esta anotacion podemos decirle a JPA que esta clase sera
 * una tabla de base de datos, por defecto la tabla se llamará
 * como la clase en lowercamelcase (autor). 
 * La clase debe de seguir la convención JavaBean
 */
@Entity
/* Si queremos cambiar el nombre de la tabla, podemos hacerlo
 * con la siguiente anotacion (por defecto usaría el nombre de la clase en minúsculas)
 */
@Table(name = "autores")
public class Autor {
	/* la entidad debe de tener un id, en este caso sera 
	 * la propiedad id, esto lo hacemos poniendo @Id jusnto
	 * encima de la propiedad que queremos que sea primary key
	 */
	@Id
	/* Podemos decirle al motor de bbdd que nos autogenere 
	 * el id cada vez que mandemos un objeto para persistir.
	 * Suele ser la mejor opción
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellidos;
	/* Al trabajar con fechas hay que incluir anotaciones adicionales para indicarle a JPA que las
	 * trate como tal. Hay varias posibilidades:
	 * @Temporal(TemporalType.DATE)
	 * Ignora la hora, quedando el campo acotado sólo a la fecha.
	 * Ej: 2021-02-07
     * @Temporal(TemporalType.TIME)
	 * Ignora la fecha, quedando el campo acotado sólo a la hora.
	 * Ej: 20:44:34
  	 * @Temporal(TemporalType.TIMESTAMP)
	 * Tiene en cuanta la fecha y la hora.
	 * Ej: 2021-02-07 20:44:34
	 */
	@Temporal(TemporalType.DATE)//Sólo nos interesa el día/mes/año
	private Date fechaNacimiento;
	/* ---------------------------
	 * Relacion de "uno a muchos"
	 * ---------------------------
	 * Esta anotacion en este extremo es para hacer relaciones bidireccionales.
	 * La anotacion que es obligatoria es la que tiene la FK, es decir, la clase
	 * Libro. 
	 * Evitamos el CascadeType.ALL, ya que no queremos que se borren los libros si borramos al autor
	 */
	@OneToMany(mappedBy="autor", cascade=CascadeType.PERSIST) 
	private List<Libro> libros;

	
	//Constructores
	public Autor() {
		super();
	}
	
	public Autor(Integer id, String nombre, String apellidos, Date fecha, List<Libro> libros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fecha;
		this.libros = libros;
	}
	
	//toString()	
	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", libros=" + libros + "]";
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public List<Libro> getLibros() {
		return libros;
	}
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
}
