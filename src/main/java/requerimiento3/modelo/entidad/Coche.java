package requerimiento3.modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "coches")
public class Coche {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String color;
	private String matricula;
	private String marca;
	
	
	@OneToOne(mappedBy = "coche", cascade=CascadeType.ALL)
	private InfoCoche infoCoche;
	
	@ManyToOne
	// En una relacion de "uno a muchos", la FK siempre esta en el lado de "Muchos"
	// Por lo tanto la @JoinColumn siempre estar� en este lado en este tipo de relaciones
	@JoinColumn(name="fk_id_parking", referencedColumnName="id")
	private Parking parking;
	
	@ManyToMany(mappedBy="coches", cascade=CascadeType.PERSIST) 
	private List<VentaWeb> webs;

	public Coche() {
		super();
	}
	
	public Coche(int id, String color, String matricula, String marca, InfoCoche infoCoche, Parking parking,
			List<VentaWeb> webs) {
		super();
		this.id = id;
		this.color = color;
		this.matricula = matricula;
		this.marca = marca;
		this.infoCoche = infoCoche;
		this.parking = parking;
		this.webs = webs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public InfoCoche getInfoCoche() {
		return infoCoche;
	}

	public void setInfoCoche(InfoCoche infoCoche) {
		this.infoCoche = infoCoche;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public List<VentaWeb> getWebs() {
		return webs;
	}

	public void setWebs(List<VentaWeb> webs) {
		this.webs = webs;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", color=" + color + ", matricula=" + matricula + ", marca=" + marca + ", infoCoche="
				+ infoCoche + ", parking=" + parking + ", webs=" + webs + "]";
	}
	
}
