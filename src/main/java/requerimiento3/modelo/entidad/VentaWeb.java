package requerimiento3.modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "venta_web")
public class VentaWeb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String web;
	private String pais;
	
	//Solo hacemos cascade cuando damos de alta
		@ManyToMany(cascade=CascadeType.PERSIST)
		//En este caso @JoinTable daremos instrucciones para crear la tabla intermedia
		//que JPA creara para hacer la realcion "Many to Many" 
		//Usa los siguientes parametros
		//1. name -> El nombre de la tabla intermedia
		//2. joinColumns -> las columnas FK y PK que aporta esta entidad (WEB)
		//3. inverseJoinColumns -> las columnas FK y PK que me aporta la otra Entidad (COCHE)
		@JoinTable(name="webs_coches",
				   joinColumns= { @JoinColumn(name="fk_id_web", referencedColumnName="id") }, //FK que aporta web
				   inverseJoinColumns= { @JoinColumn(name="fk_id_coche", referencedColumnName="id")}) //FKs que aportan el resto de entidades
		private List<Coche> coches;

		public VentaWeb() {
			super();
		}
		
		public VentaWeb(int id, String web, String pais, List<Coche> coches) {
			super();
			this.id = id;
			this.web = web;
			this.pais = pais;
			this.coches = coches;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getWeb() {
			return web;
		}

		public void setWeb(String web) {
			this.web = web;
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

		public List<Coche> getCoches() {
			return coches;
		}

		public void setCoches(List<Coche> coches) {
			this.coches = coches;
		}

		@Override
		public String toString() {
			return "VentaWeb [id=" + id + ", web=" + web + ", pais=" + pais + ", coches=" + coches + "]";
		}
}
