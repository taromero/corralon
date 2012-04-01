package ar.com.seminario.corralon.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class DummyParentParche implements Serializable{
	
	private static final long serialVersionUID = -2422098576711040292L;
	
	private Key id_dummy;
	private List<Usuario> usuarios;
	private List<Presupuesto> presupuestos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Key getId_dummy() {
		return id_dummy;
	}
	public void setId_dummy(Key id_dummy) {
		this.id_dummy = id_dummy;
	}
	@OneToMany
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@OneToMany
	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}
	public void setPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}
	
	
}
