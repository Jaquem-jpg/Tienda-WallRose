package logica;

public class Cliente {
	private String idCliente;
	private String email;
	private String nombre;
	
	// Constructor
	public Cliente(	String idCliente, String email, String nombre) {
		this.idCliente = idCliente;
		this.email = email;
		this.nombre = nombre;
	}
	
	
	// Getters y Setters
	public String getIdCliente() {
		return idCliente;
	}

	public void setCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String toString() {
		return "Cliente{" + "id=" + idCliente + ", nombre=" + nombre + ", email=" + email + '}';
	}
	
}



