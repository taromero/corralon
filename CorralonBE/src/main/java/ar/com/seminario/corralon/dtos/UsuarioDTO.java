package ar.com.seminario.corralon.dtos;

import java.util.List;

import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Usuario;

public class UsuarioDTO {
	private Long id_usuario;
	private String email;
	private String nombre;
	private String apellido;
	private Long dni;// Lo uso para loguear
	private String clave;// Lo uso para loguear
	private String rol;
	private String resourceOwner;
	private String externalId;
	private String calendarId;
	private String calendarIFrame;

	private List<Long> idsMaderasPresupuestandose;
	private List<Madera> maderasParaOC;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id_usuario, String email, String nombre,
			String apellido, Long dni, String clave, String rol,
			String resourceOwner, String externalId, String calendarId,
			String calendarIFrame, List<Long> idsMaderasPresupuestandose,
			List<Madera> maderasParaOC) {
		super();
		this.id_usuario = id_usuario;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.clave = clave;
		this.rol = rol;
		this.resourceOwner = resourceOwner;
		this.externalId = externalId;
		this.calendarId = calendarId;
		this.calendarIFrame = calendarIFrame;
		this.idsMaderasPresupuestandose = idsMaderasPresupuestandose;
		this.maderasParaOC = maderasParaOC;
	}

	public UsuarioDTO(Usuario u) {
		this(u.getId_usuario(), u.getEmail(), u.getNombre(), u.getApellido(), u
				.getDni(), u.getClave(), u.getRol(), u.getResourceOwner(), u
				.getExternalId(), u.getCalendarId(), u.getCalendarIFrame(), u
				.getIdsMaderasPresupuestandose(), u.getMaderasParaOC());
	}

	public List<Long> getIdsMaderasPresupuestandose() {
		return idsMaderasPresupuestandose;
	}

	public void setIdsMaderasPresupuestandose(
			List<Long> idsMaderasPresupuestandose) {
		this.idsMaderasPresupuestandose = idsMaderasPresupuestandose;
	}

	public List<Madera> getMaderasParaOC() {
		return maderasParaOC;
	}

	public void setMaderasParaOC(List<Madera> maderasParaOC) {
		this.maderasParaOC = maderasParaOC;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getResourceOwner() {
		return resourceOwner;
	}

	public void setResourceOwner(String resourceOwner) {
		this.resourceOwner = resourceOwner;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getCalendarIFrame() {
		return calendarIFrame;
	}

	public void setCalendarIFrame(String calendarIFrame) {
		this.calendarIFrame = calendarIFrame;
	}
}
