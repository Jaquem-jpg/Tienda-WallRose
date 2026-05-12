package logica;

import java.util.ArrayList;
import java.util.List;

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
	
	
	
	
	//Metódos
	public boolean actualizarCliente(String idCliente, String nombre, String email) {
	        if (this.idCliente.equals(idCliente)) {
	            this.nombre = nombre;
	            this.email = email;
	            return true;
	        }
	        return false;
	}
	
	
	 
    public boolean borrarCliente(String idCliente) {
        return this.idCliente.equals(idCliente);
    }
    
    
    
    public boolean crearCliente(String idCliente, String nombre, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        return true;
    }
    
    public Cliente obtenerDatos(String idCliente) {
        if (this.idCliente.equals(idCliente)) {
            return this;
        }
        return null;
    }
    
    
    
    public List<Cliente> obtenerListado() {
        List<Cliente> lista = new ArrayList<>();
        lista.add(this);
        return lista;
    }
    
    public List<OrdenCompra> obtenerOrdenes(String idCliente) {
        return new ArrayList<>();
    }
    
    public List<OrdenCompra> obtenerOrdenesIniciadas(String idCliente) {
        return new ArrayList<>();
    }
    
    public List<OrdenCompra> obtenerOrdenesPendientes(String idCliente) {
        return new ArrayList<>();
    }
    
    public List<OrdenCompra> obtenerOrdenesTerminadas(String idCliente) {
        return new ArrayList<>();
    }
    
    
    @Override
    public String toString() {
        return "Cliente{" + "id=" + idCliente + ", nombre=" + nombre + ", email=" + email + '}';
    }
	
	
}



