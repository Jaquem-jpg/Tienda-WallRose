package logica;

import java.util.Date;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompra {
    
    
    static int codigo;
    private String estado;
    private Date fecha;
    static float impuesto;
    private int numeroOrden;
    private List<LineaOrden> lineas;
    
    // Constructor
    public OrdenCompra(int numeroOrden) {
        this.numeroOrden = numeroOrden;
        OrdenCompra.codigo = numeroOrden;
        this.fecha = new Date();
        this.estado = "INICIADA";
        this.lineas = new ArrayList<>();
    }
    
    // Getters y Setters
    public static int getCodigo() {
        return codigo;
    }
    
    public static void setCodigo(int codigo) {
        OrdenCompra.codigo = codigo;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public static float getImpuesto() {
        return impuesto;
    }
    
    public static void setImpuesto(float impuesto) {
        OrdenCompra.impuesto = impuesto;
    }
    
    public int getNumeroOrden() {
        return numeroOrden;
    }
    
    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
    
    public List<LineaOrden> getLineas() {
        return lineas;
    }
    
    public void setLineas(List<LineaOrden> lineas) {
        this.lineas = lineas;
    }
    
    
    
    // 1. agregarLinea(int, int, float): boolean
    public boolean agregarLinea(int cantidad, int codigoProducto, float precioUnitario) {
        // Buscar producto (simulado)
        Producto producto = new Producto(codigoProducto,  0, "Producto " + codigoProducto, 0, precioUnitario, "unidad");
        int numLinea = lineas.size() + 1;
        LineaOrden nuevaLinea = new LineaOrden(numLinea, producto, cantidad, precioUnitario);
        return lineas.add(nuevaLinea);
    }
    
    // 2. actualizarLinea(int, int, int, float): boolean
    public boolean actualizarLinea(int numLinea, int cantidad, int codigoProducto, float precioUnitario) {
        for (LineaOrden linea : lineas) {
            if (linea.getNumeroLinea() == numLinea) {
                linea.setCantidad(cantidad);
                linea.setPrecioUnitario(precioUnitario);
                return true;
            }
        }
        return false;
    }
    
    // 3. borrarLinea(int, int): boolean
    public boolean borrarLinea(int numLinea, int codigoProducto) {
        return lineas.removeIf(linea -> linea.getNumeroLinea() == numLinea);
    }
    
    // 4. crearOrden(int): int
    public int crearOrden(int clienteId) {
        this.numeroOrden = new Random().nextInt(10000);
        OrdenCompra.codigo = this.numeroOrden;
        this.fecha = new Date();
        this.estado = "INICIADA";
        this.lineas.clear();
        return this.numeroOrden;
    }
    
    // 5. obtenerLineas(int): List<Lineas>
    public List<LineaOrden> obtenerLineas(int numOrden) {
        if (this.numeroOrden == numOrden) {
            return new ArrayList<>(lineas);
        }
        return new ArrayList<>();
    }
    
    // 6. obtenerListado(): List<Orden>
    public List<OrdenCompra> obtenerListado() {
        List<OrdenCompra> lista = new ArrayList<>();
        lista.add(this);
        return lista;
    }
    
    // 7. obtenerMontoPendientes(): double
    public double obtenerMontoPendientes() {
        double total = 0;
        for (LineaOrden linea : lineas) {
            total += linea.getSubtotal();
        }
        return total + (total * impuesto);
    }
    
    
    public Map<String, Object> obtenerPorNumero(int numOrden) {
        Map<String, Object> datos = new HashMap<>();
        if (this.numeroOrden == numOrden) {
            datos.put("numeroOrden", this.numeroOrden);
            datos.put("codigo", OrdenCompra.codigo);
            datos.put("estado", this.estado);
            datos.put("fecha", this.fecha);
            datos.put("impuesto", impuesto);
            datos.put("total", obtenerMontoPendientes());
            datos.put("cantidadLineas", lineas.size());
        }
        return datos;
    }
    
   
    public boolean ponerPendiente(int numOrden) {
        if (this.numeroOrden == numOrden) {
            this.estado = "PENDIENTE";
            return true;
        }
        return false;
    }
    
    
    public boolean ponerTerminada(int numOrden) {
        if (this.numeroOrden == numOrden) {
            this.estado = "TERMINADA";
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "OrdenCompra{" +
               "numero=" + numeroOrden +
               ", estado=" + estado +
               ", fecha=" + fecha +
               ", total=" + obtenerMontoPendientes() +
               '}';
    }
}