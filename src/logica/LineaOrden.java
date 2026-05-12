package logica;

public class LineaOrden {
	private int numeroLinea;
	private int cantidad;
	private Producto producto;
	private double precioUnitario;
	private double subtotal;
	
	
	// Constructor
	public LineaOrden(int numeroLinea, Producto producto, int cantidad, double precioUnitario) {
		this.numeroLinea = numeroLinea;
		this.producto = producto;
		this.cantidad = cantidad;
	    this.precioUnitario = precioUnitario;
	   this.subtotal = cantidad * precioUnitario;
	}
	
	
	//Getters y setters
	 public int getNumeroLinea() { return numeroLinea; }
	    public void setNumeroLinea(int numeroLinea) { this.numeroLinea = numeroLinea; }
	    
	    public Producto getProducto() { return producto; }
	    public void setProducto(Producto producto) { this.producto = producto; }
	    
	    public int getCantidad() { return cantidad; }
	    public void setCantidad(int cantidad) { 
	        this.cantidad = cantidad; 
	        this.subtotal = this.cantidad * this.precioUnitario;
	    }
	    
	    public double getPrecioUnitario() { return precioUnitario; }
	    public void setPrecioUnitario(double precioUnitario) { 
	        this.precioUnitario = precioUnitario;
	        this.subtotal = this.cantidad * this.precioUnitario;
	    }
	    
	    public double getSubtotal() { return subtotal; }
	    
	    
	 // Método para calcular subtotal
	    public double calcularSubtotal() {
	        this.subtotal = cantidad * precioUnitario;
	        return subtotal;
	    }
	    
	    @Override
	    public String toString() {
	        return "LineaOrden{" +
	               "numero=" + numeroLinea +
	               ", producto=" + (producto != null ? producto.getNombre() : "null") +
	               ", cantidad=" + cantidad +
	               ", subtotal=" + subtotal +
	               '}';
	    }

	
}
