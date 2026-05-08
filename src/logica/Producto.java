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
	
}
