package requerimiento3.modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Parking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ciudad;
	private int capacidad;
	
	@OneToMany(mappedBy="parking", cascade=CascadeType.ALL) 
	private List<Coche> coches;

	
	public Parking () {
		super();
	}
	
	public Parking(int id, String ciudad, int capacidad, List<Coche> coches) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.capacidad = capacidad;
		this.coches = coches;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}

	@Override
	public String toString() {
		return "Parking [id=" + id + ", ciudad=" + ciudad + ", capacidad=" + capacidad + ", coches=" + coches + "]";
	}
	
	

}
