package ar.com.seminario.corralon.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@DiscriminatorValue(value="MAD")
public class Madera extends MaterialConUnidad{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8823183601777371036L;
	// Clase Madera (extiende de Material) que tenga como atributos: largo,
	// maderaPadre (es de la clase Madera y seria la madera de la que se
	// corto) maderasHijas (es un vector de MAdera y serian las maderas que
	// se cortaron y estan presupuestadas) , enPresupuesto (booleano que
	// indica si no se puede tocar porque esta en un presupuesto, en este
	// caso no podria teener hijas)
	private float largo;
	private Madera maderaMadre;
	private Set<Madera> maderasHijas = new HashSet<Madera>();
	private boolean enPresupuesto;
	private boolean conservarMadre;
	
	public Madera(){
		enPresupuesto = false;
		maderaMadre = null;
		conservarMadre = true;
	}
	
	public float getLargo() {
		return largo;
	}

	public void setLargo(float largo) {
		this.largo = largo;
	}
	@JsonBackReference
	@ManyToOne
	public Madera getMaderaMadre() {
		return maderaMadre;
	}

	public void setMaderaMadre(Madera maderaMadre) {
		this.maderaMadre = maderaMadre;
	}
	
	//Al principio solo quiero traer las maderas que no estan en un presupuestoa 
	//No me interesa cascade
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy="maderaMadre", cascade= CascadeType.PERSIST)
	public Set<Madera> getMaderasHijas() {
		return maderasHijas;
	}

	public void setMaderasHijas(Set<Madera> maderasHijas) {
		this.maderasHijas = maderasHijas;
	}

	public boolean isEnPresupuesto() {
		return enPresupuesto;
	}

	public void setEnPresupuesto(boolean enPresupuesto) {
		this.enPresupuesto = enPresupuesto;
	}

	public boolean isConservarMadre() {
		return conservarMadre;
	}

	public void setConservarMadre(boolean conservar) {
		this.conservarMadre = conservar;
	}

	@Override
	public String toString() {
		return "Madera [id_material=" + getId_material() + ", descripcion="
		+ getDescripcion() + ", precioVenta=" + getPrecioVenta()+"largo=" + largo + ", enPresupuesto="
				+ enPresupuesto + "]";
	}
}
