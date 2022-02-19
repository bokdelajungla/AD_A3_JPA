package requerimiento3.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "info_coches")
public class InfoCoche {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String propietario;
	private String ciudadDeCompra;
	
	// Relaci de uno a uno
		//-----------------------
		// Al ser una relacion bidireccional, tambien ponemos la anotacion @OneToOne sobre
		// este atributo
		@OneToOne
			
		// Mediante @JoinColumn establecemos que la FK estar� en esta tabla (info_coche).
		// Le podemos dar el nombre de la columna y a que columna de la tabla
		// cliente estamos referenciando, que normalmente es la Primary Key (PK)
		@JoinColumn(name = "fk_id_coche", referencedColumnName = "id")
		private Coche coche;

		public InfoCoche() {
			super();
		}
		
		public InfoCoche(int id, String propietario, String ciudadDeCompra, Coche coche) {
			super();
			this.id = id;
			this.propietario = propietario;
			this.ciudadDeCompra = ciudadDeCompra;
			this.coche = coche;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPropietario() {
			return propietario;
		}

		public void setPropietario(String propietario) {
			this.propietario = propietario;
		}

		public String getCiudadDeCompra() {
			return ciudadDeCompra;
		}

		public void setCiudadDeCompra(String ciudadDeCompra) {
			this.ciudadDeCompra = ciudadDeCompra;
		}

		public Coche getCoche() {
			return coche;
		}

		public void setCoche(Coche coche) {
			this.coche = coche;
		}

		@Override
		public String toString() {
			return "InfoCoche [id=" + id + ", propietario=" + propietario + ", ciudadDeCompra=" + ciudadDeCompra
					+ ", coche=" + coche + "]";
		}
		
	

}
