package modelo.entidad;

import javax.persistence.Embeddable;

//Aqui le decimos que esta clase no va a ser entidad propia
//por ello no la anotamos con @Entity, sino que sus atributos
//van a formar parte de los campos de otra tabla, en este caso
//formara parte de la tabla Cliente
@Embeddable
public class Direccion {
	private String nombreVia;
	private String codigoPostal;
	private String ciudad;
	
	//Constructores
	public Direccion(){
		super();
	}
	
	public Direccion(String nombreVia, String codigoPostal, String ciudad) {
		super();
		this.nombreVia = nombreVia;
		this.codigoPostal = codigoPostal;
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Direccion [nombreVia=" + nombreVia + ", codigoPostal=" + codigoPostal + ", ciudad=" + ciudad + "]";
	}
	
	//GETERS & SETTERS
	public String getNombreVia() {
		return nombreVia;
	}
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String dirString() {
		return nombreVia + ", " + codigoPostal + ", " + ciudad + ".";
	}
	
}
