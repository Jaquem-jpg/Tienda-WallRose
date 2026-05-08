package logica;

public class Producto {
	static int codigo;
	private  int codigoProducto;
	private float existencia;
	private String nombre;
	private int numero;
	private double precio;
	private String unidad;
	
	
	// Constructor
	public Producto(int codigoProducto, float existencia, String nombre, int numero, double precio, String unidad) {
		this.codigoProducto = codigoProducto;
		this.existencia = existencia;
		this.nombre = nombre;
		this.numero =  numero;
		this.precio = precio;
		this.unidad = unidad;
	}
	
	
	//Getters y Setters
	public int getCodigoProducto() {
		return codigoProducto;
	}
	
	
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
	
	public float getExistencia() { 
		return existencia;
	}
	
	
	 public void setExistencia(float existencia) { 
		 this.existencia = existencia;
    }
	    
	 
	 public String getNombre() {
		 return nombre; 
     }
	  
	 public void setNombre(String nombre) { 
	    	this.nombre = nombre; 
	  }
	    
	 public int getNumero() { 
		 return numero;
	 }
	    
	 public void setNumero(int numero) { 
		 this.numero = numero; 
		 }
	    
	 public double getPrecio() { 
		 return precio;
		 }
	    
	 public void setPrecio(double precio) {
		 this.precio = precio; 
		 }
	    
	 public String getUnidad() { 
		 return unidad;
		 }
	    
	 public void setUnidad(String unidad) { 
		 this.unidad = unidad; 
		 }
	    
	
}
