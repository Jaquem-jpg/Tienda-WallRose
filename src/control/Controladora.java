package control;

import logica.*;
import java.util.*;

public class Controladora {
    
    // Singleton
    private static Controladora instancia;
    
    // Listas para almacenar datos en memoria
    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<OrdenCompra> ordenes;
    
    // Constructor privado 
    private Controladora() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
        ordenes = new ArrayList<>();
        // Inicializamos impuesto por defecto
        OrdenCompra.setImpuesto(0.13f);
    }
    
    // Método para obtener la única instancia
    public static Controladora getInstance() {
        if (instancia == null) {
            instancia = new Controladora();
        }
        return instancia;
    }
    
    // ========== MÉTODOS DE CLIENTES ==========
    
    public boolean crearCliente(String id, String nombre, String email) {
        Cliente nuevo = new Cliente(id, nombre, email);
        return clientes.add(nuevo);
    }
    
    public boolean actualizarCliente(String id, String nombre, String email) {
        for (Cliente c : clientes) {
            if (c.getIdCliente().equals(id)) {
                c.setNombre(nombre);
                c.setEmail(email);
                return true;
            }
        }
        return false;
    }
    
    public void borrarCliente(String id) {
        clientes.removeIf(c -> c.getIdCliente().equals(id));
    }
    
    public Cliente obtenerDatosCliente(String id) {
        for (Cliente c : clientes) {
            if (c.getIdCliente().equals(id)) {
                return c;
            }
        }
        return null;
    }
    
    public List<Cliente> obtenerListadoClientes() {
        return new ArrayList<>(clientes);
    }
    
    public Map<String, Object> obtenerClientesId(String id) {
        Map<String, Object> mapa = new HashMap<>();
        for (Cliente c : clientes) {
            if (c.getIdCliente().equals(id)) {
                mapa.put("id", c.getIdCliente());
                mapa.put("nombre", c.getNombre());
                mapa.put("email", c.getEmail());
                return mapa;
            }
        }
        return mapa;
    }
    
    // ========== MÉTODOS DE PRODUCTOS ==========
    
    public boolean crearProducto(int codigoProducto, String nombre, float existencia, String unidad, double precio) {
        Producto nuevo = new Producto(codigoProducto, existencia, nombre, 0, precio, unidad);
        return productos.add(nuevo);
    }
    
    public boolean actualizarProducto(int codigo, String nombre, float existencia, String unidad, double precio) {
        for (Producto p : productos) {
            if (p.getCodigoProducto() == codigo) {
                p.setNombre(nombre);
                p.setExistencia(existencia);
                p.setUnidad(unidad);
                p.setPrecio(precio);
                return true;
            }
        }
        return false;
    }
    
    public boolean borrarProducto(int codigo) {
        return productos.removeIf(p -> p.getCodigoProducto() == codigo);
    }
    
    public Producto obtenerDatosProducto(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigoProducto() == codigo) {
                return p;
            }
        }
        return null;
    }
    
    public List<Producto> obtenerListadoProductos() {
        return new ArrayList<>(productos);
    }
    
    // ========== MÉTODOS DE ÓRDENES ==========
    
    public int crearOrden(String idCliente) {
        int numOrden = ordenes.size() + 1;
        OrdenCompra nueva = new OrdenCompra(numOrden);
        ordenes.add(nueva);
        return numOrden;
    }
    
    public boolean ponerOrdenPendiente(int numOrden) {
        for (OrdenCompra o : ordenes) {
            if (o.getNumeroOrden() == numOrden) {
                o.setEstado("PENDIENTE");
                return true;
            }
        }
        return false;
    }
    
    public boolean ponerOrdenTerminada(int numOrden) {
        for (OrdenCompra o : ordenes) {
            if (o.getNumeroOrden() == numOrden) {
                o.setEstado("TERMINADA");
                return true;
            }
        }
        return false;
    }
    
    public double obtenerMontoTotalPendientes() {
        double total = 0;
        for (OrdenCompra o : ordenes) {
            if (o.getEstado().equals("PENDIENTE")) {
                total += o.obtenerMontoPendientes();
            }
        }
        return total;
    }
    
    public List<OrdenCompra> obtenerListadoOrdenes() {
        return new ArrayList<>(ordenes);
    }
    
    public Map<String, Object> obtenerPorNumero(int numOrden) {
        for (OrdenCompra o : ordenes) {
            if (o.getNumeroOrden() == numOrden) {
                Map<String, Object> mapa = new HashMap<>();
                mapa.put("numeroOrden", o.getNumeroOrden());
                mapa.put("estado", o.getEstado());
                mapa.put("fecha", o.getFecha());
                mapa.put("total", o.obtenerMontoPendientes());
                return mapa;
            }
        }
        return new HashMap<>();
    }
    
    // ========== MÉTODOS DE LÍNEAS ==========
    
    public boolean agregarLinea(int numOrden, int cantidad, float precioUnitario) {
        for (OrdenCompra o : ordenes) {
            if (o.getNumeroOrden() == numOrden) {
                return o.agregarLinea(cantidad, 0, precioUnitario);
            }
        }
        return false;
    }
    
    public boolean actualizarLinea(int numOrden, int numLinea, int cantidad, float precioUnitario) {
        for (OrdenCompra o : ordenes) {
            if (o.getNumeroOrden() == numOrden) {
                return o.actualizarLinea(numLinea, cantidad, 0, precioUnitario);
            }
        }
        return false;
    }
    
    public boolean borrarLinea(int numOrden, int numLinea) {
        for (OrdenCompra o : ordenes) {
            if (o.getNumeroOrden() == numOrden) {
                return o.borrarLinea(numLinea, 0);
            }
        }
        return false;
    }
    
    public List<LineaOrden> obtenerLineasOrden(int numOrden) {
        for (OrdenCompra o : ordenes) {
            if (o.getNumeroOrden() == numOrden) {
                return o.getLineas();
            }
        }
        return new ArrayList<>();
    }
    
    // ========== MÉTODOS COMBINADOS ==========
    
    public List<OrdenCompra> obtenerOrdenesCliente(String idCliente) {
        
        return new ArrayList<>(ordenes);
    }
    
    public List<OrdenCompra> obtenerOrdenesIniciadasCliente(String idCliente) {
        List<OrdenCompra> resultado = new ArrayList<>();
        for (OrdenCompra o : ordenes) {
            if (o.getEstado().equals("INICIADA")) {
                resultado.add(o);
            }
        }
        return resultado;
    }
    
    public List<OrdenCompra> obtenerOrdenesPendientesCliente(String idCliente) {
        List<OrdenCompra> resultado = new ArrayList<>();
        for (OrdenCompra o : ordenes) {
            if (o.getEstado().equals("PENDIENTE")) {
                resultado.add(o);
            }
        }
        return resultado;
    }
    
    public List<OrdenCompra> obtenerOrdenesTerminadasCliente(String idCliente) {
        List<OrdenCompra> resultado = new ArrayList<>();
        for (OrdenCompra o : ordenes) {
            if (o.getEstado().equals("TERMINADA")) {
                resultado.add(o);
            }
        }
        return resultado;
    }
    
    public OrdenCompra obtenerPorNumeroOrden(int numOrden) {
        for (OrdenCompra o : ordenes) {
            if (o.getNumeroOrden() == numOrden) {
                return o;
            }
        }
        return null;
    }
}