package logica;

import java.util.Date;
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
}