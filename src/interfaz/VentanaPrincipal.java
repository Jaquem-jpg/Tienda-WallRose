package interfaz;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import control.Controladora;
import logica.Cliente;
import logica.Producto;
import logica.OrdenCompra;
import logica.LineaOrden;

public class VentanaPrincipal {

    private JFrame frame;
    private Controladora control;
    
    // Componentes de Clientes
    private JTable tablaClientes;
    private DefaultTableModel modeloClientes;
    private JTextArea txtDetalles;
    
    // Componentes de Productos
    private JTable tablaProductos;
    private DefaultTableModel modeloProductos;
    
    // Componentes de Ordenes
    private JTable tablaOrdenes;
    private DefaultTableModel modeloOrdenes;
    private JTable tablaLineas;
    private DefaultTableModel modeloLineas;
    private int ordenSeleccionada = -1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal window = new VentanaPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaPrincipal() {
        control = Controladora.getInstance();
        initialize();
        cargarClientes();
        cargarProductos();
        cargarOrdenes();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Tienda WallRose - Sistema de Órdenes");
        frame.setBounds(100, 100, 1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        
        // ========== TABBED PANE ==========
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        // ========== PANEL CLIENTES ==========
        JPanel panelClientes = new JPanel();
        tabbedPane.addTab("Clientes", null, panelClientes, null);
        panelClientes.setLayout(null);
        
        // Tabla de clientes
        JScrollPane scrollClientes = new JScrollPane();
        scrollClientes.setBounds(150, 30, 780, 300);
        panelClientes.add(scrollClientes);
        
        tablaClientes = new JTable();
        modeloClientes = new DefaultTableModel(new String[]{"ID", "Nombre", "Email"}, 0);
        tablaClientes.setModel(modeloClientes);
        scrollClientes.setViewportView(tablaClientes);
        
        // Botones de clientes
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(30, 50, 100, 25);
        panelClientes.add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(30, 90, 100, 25);
        panelClientes.add(btnEditar);
        
        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(30, 130, 100, 25);
        panelClientes.add(btnBorrar);
        
        JButton btnVer = new JButton("Ver");
        btnVer.setBounds(30, 170, 100, 25);
        panelClientes.add(btnVer);
        
        // ========== PANEL PRODUCTOS ==========
        JPanel panelProductos = new JPanel();
        tabbedPane.addTab("Productos", null, panelProductos, null);
        panelProductos.setLayout(null);
        
        // Tabla de productos
        JScrollPane scrollProductos = new JScrollPane();
        scrollProductos.setBounds(150, 30, 780, 300);
        panelProductos.add(scrollProductos);
        
        tablaProductos = new JTable();
        modeloProductos = new DefaultTableModel(new String[]{"Código", "Nombre", "Precio", "Existencia"}, 0);
        tablaProductos.setModel(modeloProductos);
        scrollProductos.setViewportView(tablaProductos);
        
        // Botones de productos
        JButton btnAgregarProducto = new JButton("Agregar");
        btnAgregarProducto.setBounds(30, 50, 100, 25);
        panelProductos.add(btnAgregarProducto);
        
        JButton btnEditarProducto = new JButton("Editar");
        btnEditarProducto.setBounds(30, 90, 100, 25);
        panelProductos.add(btnEditarProducto);
        
        JButton btnBorrarProducto = new JButton("Borrar");
        btnBorrarProducto.setBounds(30, 130, 100, 25);
        panelProductos.add(btnBorrarProducto);
        
        JButton btnVerProducto = new JButton("Ver");
        btnVerProducto.setBounds(30, 170, 100, 25);
        panelProductos.add(btnVerProducto);
        
        // ========== PANEL ORDENES ==========
        JPanel panelOrdenes = new JPanel();
        tabbedPane.addTab("Ordenes", null, panelOrdenes, null);
        panelOrdenes.setLayout(null);
        
        // Tabla de órdenes (izquierda)
        JScrollPane scrollOrdenes = new JScrollPane();
        scrollOrdenes.setBounds(30, 30, 400, 300);
        panelOrdenes.add(scrollOrdenes);
        
        tablaOrdenes = new JTable();
        modeloOrdenes = new DefaultTableModel(new String[]{"Número", "Estado", "Fecha", "Total"}, 0);
        tablaOrdenes.setModel(modeloOrdenes);
        scrollOrdenes.setViewportView(tablaOrdenes);
        
        // Tabla de líneas (derecha)
        JScrollPane scrollLineas = new JScrollPane();
        scrollLineas.setBounds(450, 30, 500, 300);
        panelOrdenes.add(scrollLineas);
        
        tablaLineas = new JTable();
        modeloLineas = new DefaultTableModel(new String[]{"Producto", "Cantidad", "Precio", "Subtotal"}, 0);
        tablaLineas.setModel(modeloLineas);
        scrollLineas.setViewportView(tablaLineas);
        
        // Botones de órdenes
        JButton btnCrearOrden = new JButton("Crear Orden");
        btnCrearOrden.setBounds(30, 350, 120, 25);
        panelOrdenes.add(btnCrearOrden);
        
        JButton btnAgregarProductoOrden = new JButton("Agregar Producto");
        btnAgregarProductoOrden.setBounds(160, 350, 130, 25);
        panelOrdenes.add(btnAgregarProductoOrden);
        
        JButton btnPendiente = new JButton("Pendiente");
        btnPendiente.setBounds(300, 350, 100, 25);
        panelOrdenes.add(btnPendiente);
        
        JButton btnTerminada = new JButton("Terminada");
        btnTerminada.setBounds(410, 350, 100, 25);
        panelOrdenes.add(btnTerminada);
        
        JButton btnVerDetalle = new JButton("Ver Detalle");
        btnVerDetalle.setBounds(520, 350, 100, 25);
        panelOrdenes.add(btnVerDetalle);
        
        JButton btnEliminarLinea = new JButton("Eliminar Línea");
        btnEliminarLinea.setBounds(630, 350, 120, 25);
        panelOrdenes.add(btnEliminarLinea);
        
        // ========== ÁREA DE DETALLES ==========
        txtDetalles = new JTextArea();
        txtDetalles.setEditable(false);
        JScrollPane scrollDetalles = new JScrollPane(txtDetalles);
        scrollDetalles.setBounds(10, 450, 960, 150);
        frame.getContentPane().add(scrollDetalles, BorderLayout.SOUTH);
        
        // ========== EVENTOS DE CLIENTES ==========
        btnAgregar.addActionListener(e -> agregarCliente());
        btnEditar.addActionListener(e -> editarCliente());
        btnBorrar.addActionListener(e -> borrarCliente());
        btnVer.addActionListener(e -> verCliente());
        
        // ========== EVENTOS DE PRODUCTOS ==========
        btnAgregarProducto.addActionListener(e -> agregarProducto());
        btnEditarProducto.addActionListener(e -> editarProducto());
        btnBorrarProducto.addActionListener(e -> borrarProducto());
        btnVerProducto.addActionListener(e -> verProducto());
        
        // ========== EVENTOS DE ORDENES ==========
        btnCrearOrden.addActionListener(e -> crearOrden());
        btnAgregarProductoOrden.addActionListener(e -> agregarProductoOrden());
        btnPendiente.addActionListener(e -> marcarPendiente());
        btnTerminada.addActionListener(e -> marcarTerminada());
        btnVerDetalle.addActionListener(e -> verDetalleOrden());
        btnEliminarLinea.addActionListener(e -> eliminarLinea());
        
        // Seleccionar orden para mostrar sus líneas
        tablaOrdenes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarLineasOrden();
            }
        });
    }
    
    // ========== MÉTODOS DE CLIENTES ==========
    
    private void cargarClientes() {
        modeloClientes.setRowCount(0); 
        for (Cliente c : control.obtenerListadoClientes()) {
            modeloClientes.addRow(new Object[]{
                c.getIdCliente(),
                c.getNombre(),
                c.getEmail()
            });
        }
        txtDetalles.setText("Clientes cargados: " + control.obtenerListadoClientes().size());
    }
    
    private void agregarCliente() {
        String id = JOptionPane.showInputDialog(frame, "ID del cliente:");
        if (id == null || id.isEmpty()) return;
        
        String nombre = JOptionPane.showInputDialog(frame, "Nombre:");
        if (nombre == null || nombre.isEmpty()) return;
        
        String email = JOptionPane.showInputDialog(frame, "Email:");
        if (email == null || email.isEmpty()) return;
        
        control.crearCliente(id, nombre, email);
        cargarClientes();
        txtDetalles.setText("Cliente agregado: " + id + " - " + nombre);
    }
    
    private void editarCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione un cliente para editar");
            return;
        }
        
        String id = (String) modeloClientes.getValueAt(fila, 0);
        String nombreActual = (String) modeloClientes.getValueAt(fila, 1);
        String emailActual = (String) modeloClientes.getValueAt(fila, 2);
        
        String nuevoNombre = JOptionPane.showInputDialog(frame, "Nuevo nombre:", nombreActual);
        String nuevoEmail = JOptionPane.showInputDialog(frame, "Nuevo email:", emailActual);
        
        if (nuevoNombre != null && nuevoEmail != null) {
            control.actualizarCliente(id, nuevoNombre, nuevoEmail);
            cargarClientes();
            txtDetalles.setText("Cliente actualizado: " + id);
        }
    }
    
    private void borrarCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione un cliente para eliminar");
            return;
        }
        
        String id = (String) modeloClientes.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "¿Eliminar cliente " + id + "?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            control.borrarCliente(id);
            cargarClientes();
            txtDetalles.setText("Cliente eliminado: " + id);
        }
    }
    
    private void verCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione un cliente para ver detalles");
            return;
        }
        
        String id = (String) modeloClientes.getValueAt(fila, 0);
        String nombre = (String) modeloClientes.getValueAt(fila, 1);
        String email = (String) modeloClientes.getValueAt(fila, 2);
        
        txtDetalles.setText("DETALLES DEL CLIENTE\n\n" +
                           "ID: " + id + "\n" +
                           "Nombre: " + nombre + "\n" +
                           "Email: " + email);
    }
    
    // ========== MÉTODOS DE PRODUCTOS ==========
    
    private void cargarProductos() {
        modeloProductos.setRowCount(0);
        for (Producto p : control.obtenerListadoProductos()) {
            modeloProductos.addRow(new Object[]{
                p.getCodigoProducto(),
                p.getNombre(),
                p.getPrecio(),
                p.getExistencia()
            });
        }
    }
    
    private void agregarProducto() {
        String codigoStr = JOptionPane.showInputDialog(frame, "Código del producto:");
        if (codigoStr == null || codigoStr.isEmpty()) return;
        int codigo = Integer.parseInt(codigoStr);
        
        String nombre = JOptionPane.showInputDialog(frame, "Nombre:");
        if (nombre == null || nombre.isEmpty()) return;
        
        String precioStr = JOptionPane.showInputDialog(frame, "Precio:");
        if (precioStr == null || precioStr.isEmpty()) return;
        double precio = Double.parseDouble(precioStr);
        
        String existenciaStr = JOptionPane.showInputDialog(frame, "Existencia:");
        if (existenciaStr == null || existenciaStr.isEmpty()) return;
        float existencia = Float.parseFloat(existenciaStr);
        
        control.crearProducto(codigo, nombre, existencia, "unidad", precio);
        cargarProductos();
        txtDetalles.setText("Producto agregado: " + codigo + " - " + nombre);
    }
    
    private void editarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione un producto para editar");
            return;
        }
        
        int codigo = (int) modeloProductos.getValueAt(fila, 0);
        String nombreActual = (String) modeloProductos.getValueAt(fila, 1);
        double precioActual = (double) modeloProductos.getValueAt(fila, 2);
        float existenciaActual = (float) modeloProductos.getValueAt(fila, 3);
        
        String nuevoNombre = JOptionPane.showInputDialog(frame, "Nuevo nombre:", nombreActual);
        String nuevoPrecioStr = JOptionPane.showInputDialog(frame, "Nuevo precio:", precioActual);
        String nuevaExistenciaStr = JOptionPane.showInputDialog(frame, "Nueva existencia:", existenciaActual);
        
        if (nuevoNombre != null && nuevoPrecioStr != null && nuevaExistenciaStr != null) {
            double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
            float nuevaExistencia = Float.parseFloat(nuevaExistenciaStr);
            control.actualizarProducto(codigo, nuevoNombre, nuevaExistencia, "unidad", nuevoPrecio);
            cargarProductos();
            txtDetalles.setText("Producto actualizado: " + codigo);
        }
    }
    
    private void borrarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione un producto para eliminar");
            return;
        }
        
        int codigo = (int) modeloProductos.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "¿Eliminar producto " + codigo + "?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            control.borrarProducto(codigo);
            cargarProductos();
            txtDetalles.setText("Producto eliminado: " + codigo);
        }
    }
    
    private void verProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione un producto para ver detalles");
            return;
        }
        
        int codigo = (int) modeloProductos.getValueAt(fila, 0);
        String nombre = (String) modeloProductos.getValueAt(fila, 1);
        double precio = (double) modeloProductos.getValueAt(fila, 2);
        float existencia = (float) modeloProductos.getValueAt(fila, 3);
        
        txtDetalles.setText("DETALLES DEL PRODUCTO\n\n" +
                           "Código: " + codigo + "\n" +
                           "Nombre: " + nombre + "\n" +
                           "Precio: $" + precio + "\n" +
                           "Existencia: " + existencia);
    }
    
    // ========== MÉTODOS DE ORDENES ==========
    
    private void cargarOrdenes() {
        modeloOrdenes.setRowCount(0);
        for (OrdenCompra o : control.obtenerListadoOrdenes()) {
            modeloOrdenes.addRow(new Object[]{
                o.getNumeroOrden(),
                o.getEstado(),
                o.getFecha(),
                o.obtenerMontoPendientes()
            });
        }
    }
    
    private void cargarLineasOrden() {
        modeloLineas.setRowCount(0);
        int fila = tablaOrdenes.getSelectedRow();
        if (fila >= 0) {
            ordenSeleccionada = (int) modeloOrdenes.getValueAt(fila, 0);
            // Buscar la orden y sus líneas
            for (OrdenCompra o : control.obtenerListadoOrdenes()) {
                if (o.getNumeroOrden() == ordenSeleccionada) {
                    for (LineaOrden linea : o.getLineas()) {
                        modeloLineas.addRow(new Object[]{
                            linea.getProducto().getNombre(),
                            linea.getCantidad(),
                            linea.getPrecioUnitario(),
                            linea.getSubtotal()
                        });
                    }
                    break;
                }
            }
        }
    }
    
    private void crearOrden() {
        String idCliente = JOptionPane.showInputDialog(frame, "ID del cliente:");
        if (idCliente != null && !idCliente.isEmpty()) {
            int numOrden = control.crearOrden(idCliente);
            cargarOrdenes();
            txtDetalles.setText("Orden creada: " + numOrden);
        }
    }
    
    private void agregarProductoOrden() {
        int fila = tablaOrdenes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione una orden primero");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        
        String codigoStr = JOptionPane.showInputDialog(frame, "Código del producto:");
        if (codigoStr == null || codigoStr.isEmpty()) return;
        int codigo = Integer.parseInt(codigoStr);
        
        String cantidadStr = JOptionPane.showInputDialog(frame, "Cantidad:");
        if (cantidadStr == null || cantidadStr.isEmpty()) return;
        int cantidad = Integer.parseInt(cantidadStr);
        
        // Buscar el producto para obtener su precio
        Producto producto = null;
        for (Producto p : control.obtenerListadoProductos()) {
            if (p.getCodigoProducto() == codigo) {
                producto = p;
                break;
            }
        }
        
        if (producto != null) {
            control.agregarLinea(numOrden, cantidad, (float) producto.getPrecio());
            cargarOrdenes();
            cargarLineasOrden();
            txtDetalles.setText("Producto agregado a la orden " + numOrden);
        } else {
            txtDetalles.setText("Producto no encontrado");
        }
    }
    
    private void marcarPendiente() {
        int fila = tablaOrdenes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione una orden");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        control.ponerOrdenPendiente(numOrden);
        cargarOrdenes();
        txtDetalles.setText("Orden " + numOrden + " marcada como PENDIENTE");
    }
    
    private void marcarTerminada() {
        int fila = tablaOrdenes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione una orden");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        control.ponerOrdenTerminada(numOrden);
        cargarOrdenes();
        txtDetalles.setText("Orden " + numOrden + " marcada como TERMINADA");
    }
    
    private void verDetalleOrden() {
        int fila = tablaOrdenes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText("Seleccione una orden");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        OrdenCompra orden = null;
        for (OrdenCompra o : control.obtenerListadoOrdenes()) {
            if (o.getNumeroOrden() == numOrden) {
                orden = o;
                break;
            }
        }
        
        if (orden != null) {
            txtDetalles.setText("DETALLES DE ORDEN\n\n" +
                               "Numero: " + orden.getNumeroOrden() + "\n" +
                               "Estado: " + orden.getEstado() + "\n" +
                               "Fecha: " + orden.getFecha() + "\n" +
                               "Total: $" + orden.obtenerMontoPendientes() + "\n" +
                               "Impuesto: 13%\n" +
                               "Lineas: " + orden.getLineas().size());
        }
    }
    
    private void eliminarLinea() {
        int filaOrden = tablaOrdenes.getSelectedRow();
        int filaLinea = tablaLineas.getSelectedRow();
        
        if (filaOrden == -1) {
            txtDetalles.setText("Seleccione una orden");
            return;
        }
        
        if (filaLinea == -1) {
            txtDetalles.setText("Seleccione una linea para eliminar");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(filaOrden, 0);
        
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "¿Eliminar esta linea de la orden?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            control.borrarLinea(numOrden, filaLinea + 1);
            cargarOrdenes();
            cargarLineasOrden();
            txtDetalles.setText("Linea eliminada de la orden " + numOrden);
        }
    }
}