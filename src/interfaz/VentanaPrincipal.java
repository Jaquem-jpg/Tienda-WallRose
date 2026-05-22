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
    
    // ========== MÉTODOS DE VALIDACIÓN ==========
    
    private boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        return email.contains("@") && email.contains(".");
    }
    
    private boolean validarPositivo(double valor) {
        return valor > 0;
    }
    
    private boolean validarPositivo(int valor) {
        return valor > 0;
    }
    
    private boolean validarPositivo(float valor) {
        return valor >= 0;
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
        txtDetalles.setText(" Clientes cargados: " + control.obtenerListadoClientes().size());
    }
    
    private void agregarCliente() {
        String id = JOptionPane.showInputDialog(frame, "ID del cliente:");
        if (id == null || id.trim().isEmpty()) {
            txtDetalles.setText(" El ID no puede estar vacio");
            return;
        }
        
        // Validar que el ID no exista ya
        for (Cliente c : control.obtenerListadoClientes()) {
            if (c.getIdCliente().equals(id)) {
                txtDetalles.setText(" Ya existe un cliente con ID: " + id);
                return;
            }
        }
        
        String nombre = JOptionPane.showInputDialog(frame, "Nombre:");
        if (nombre == null || nombre.trim().isEmpty()) {
            txtDetalles.setText(" El nombre no puede estar vacio");
            return;
        }
        
        String email = JOptionPane.showInputDialog(frame, "Email:");
        if (email == null || email.trim().isEmpty()) {
            txtDetalles.setText(" El email no puede estar vacio");
            return;
        }
        
        if (!validarEmail(email)) {
            txtDetalles.setText(" Email invalido. Debe contener @ y .");
            return;
        }
        
        control.crearCliente(id, nombre, email);
        cargarClientes();
        txtDetalles.setText(" Cliente agregado: " + id + " - " + nombre);
    }
    
    private void editarCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione un cliente para editar");
            return;
        }
        
        String id = (String) modeloClientes.getValueAt(fila, 0);
        String nombreActual = (String) modeloClientes.getValueAt(fila, 1);
        String emailActual = (String) modeloClientes.getValueAt(fila, 2);
        
        String nuevoNombre = JOptionPane.showInputDialog(frame, "Nuevo nombre:", nombreActual);
        String nuevoEmail = JOptionPane.showInputDialog(frame, "Nuevo email:", emailActual);
        
        if (nuevoNombre != null && nuevoEmail != null) {
            if (!validarEmail(nuevoEmail)) {
                txtDetalles.setText(" Email invalido. Debe contener @ y .");
                return;
            }
            control.actualizarCliente(id, nuevoNombre, nuevoEmail);
            cargarClientes();
            txtDetalles.setText(" Cliente actualizado: " + id);
        }
    }
    
    private void borrarCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione un cliente para eliminar");
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
            txtDetalles.setText(" Cliente eliminado: " + id);
        }
    }
    
    private void verCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione un cliente para ver detalles");
            return;
        }
        
        String id = (String) modeloClientes.getValueAt(fila, 0);
        String nombre = (String) modeloClientes.getValueAt(fila, 1);
        String email = (String) modeloClientes.getValueAt(fila, 2);
        
        txtDetalles.setText(" DETALLES DEL CLIENTE\n\n" +
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
        txtDetalles.setText(" Productos cargados: " + control.obtenerListadoProductos().size());
    }
    
    private void agregarProducto() {
        try {
            String codigoStr = JOptionPane.showInputDialog(frame, "Codigo del producto (numero positivo):");
            if (codigoStr == null || codigoStr.isEmpty()) return;
            
            int codigo;
            try {
                codigo = Integer.parseInt(codigoStr);
                if (!validarPositivo(codigo)) {
                    txtDetalles.setText(" El codigo debe ser un numero positivo");
                    return;
                }
            } catch (NumberFormatException e) {
                txtDetalles.setText(" Error: El codigo debe ser un numero");
                return;
            }
            
            // Validar que el código no exista ya
            for (Producto p : control.obtenerListadoProductos()) {
                if (p.getCodigoProducto() == codigo) {
                    txtDetalles.setText(" Ya existe un producto con código: " + codigo);
                    return;
                }
            }
            
            String nombre = JOptionPane.showInputDialog(frame, "Nombre:");
            if (nombre == null || nombre.trim().isEmpty()) {
                txtDetalles.setText(" El nombre no puede estar vacio");
                return;
            }
            
            String precioStr = JOptionPane.showInputDialog(frame, "Precio (numero positivo):");
            if (precioStr == null || precioStr.isEmpty()) return;
            
            double precio;
            try {
                precio = Double.parseDouble(precioStr);
                if (!validarPositivo(precio)) {
                    txtDetalles.setText(" El precio debe ser mayor a 0");
                    return;
                }
            } catch (NumberFormatException e) {
                txtDetalles.setText(" Error: El precio debe ser un numero");
                return;
            }
            
            String existenciaStr = JOptionPane.showInputDialog(frame, "Existencia (numero positivo o cero):");
            if (existenciaStr == null || existenciaStr.isEmpty()) return;
            
            float existencia;
            try {
                existencia = Float.parseFloat(existenciaStr);
                if (!validarPositivo(existencia)) {
                    txtDetalles.setText(" La existencia no puede ser negativa");
                    return;
                }
            } catch (NumberFormatException e) {
                txtDetalles.setText(" Error: La existencia debe ser un numero");
                return;
            }
            
            boolean resultado = control.crearProducto(codigo, nombre, existencia, "unidad", precio);
            
            if (resultado) {
                cargarProductos();
                txtDetalles.setText(" Producto agregado: " + codigo + " - " + nombre);
            } else {
                txtDetalles.setText(" Error: No se pudo agregar el producto");
            }
        } catch (Exception e) {
            txtDetalles.setText(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void editarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione un producto para editar");
            return;
        }
        
        int codigo = (int) modeloProductos.getValueAt(fila, 0);
        String nombreActual = (String) modeloProductos.getValueAt(fila, 1);
        double precioActual = (double) modeloProductos.getValueAt(fila, 2);
        float existenciaActual = (float) modeloProductos.getValueAt(fila, 3);
        
        String nuevoNombre = JOptionPane.showInputDialog(frame, "Nuevo nombre:", nombreActual);
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            txtDetalles.setText(" El nombre no puede estar vacio");
            return;
        }
        
        String nuevoPrecioStr = JOptionPane.showInputDialog(frame, "Nuevo precio:", precioActual);
        if (nuevoPrecioStr == null || nuevoPrecioStr.isEmpty()) return;
        
        double nuevoPrecio;
        try {
            nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
            if (!validarPositivo(nuevoPrecio)) {
                txtDetalles.setText(" El precio debe ser mayor a 0");
                return;
            }
        } catch (NumberFormatException e) {
            txtDetalles.setText(" Error: El precio debe ser un numero");
            return;
        }
        
        String nuevaExistenciaStr = JOptionPane.showInputDialog(frame, "Nueva existencia:", existenciaActual);
        if (nuevaExistenciaStr == null || nuevaExistenciaStr.isEmpty()) return;
        
        float nuevaExistencia;
        try {
            nuevaExistencia = Float.parseFloat(nuevaExistenciaStr);
            if (!validarPositivo(nuevaExistencia)) {
                txtDetalles.setText(" La existencia no puede ser negativa");
                return;
            }
        } catch (NumberFormatException e) {
            txtDetalles.setText(" Error: La existencia debe ser un numero");
            return;
        }
        
        control.actualizarProducto(codigo, nuevoNombre, nuevaExistencia, "unidad", nuevoPrecio);
        cargarProductos();
        txtDetalles.setText(" Producto actualizado: " + codigo);
    }
    
    private void borrarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione un producto para eliminar");
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
            txtDetalles.setText(" Producto eliminado: " + codigo);
        }
    }
    
    private void verProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione un producto para ver detalles");
            return;
        }
        
        int codigo = (int) modeloProductos.getValueAt(fila, 0);
        String nombre = (String) modeloProductos.getValueAt(fila, 1);
        double precio = (double) modeloProductos.getValueAt(fila, 2);
        float existencia = (float) modeloProductos.getValueAt(fila, 3);
        
        txtDetalles.setText(" DETALLES DEL PRODUCTO\n\n" +
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
        txtDetalles.setText(" Ordenes cargadas: " + control.obtenerListadoOrdenes().size());
    }
    
    private void cargarLineasOrden() {
        modeloLineas.setRowCount(0);
        int fila = tablaOrdenes.getSelectedRow();
        if (fila >= 0) {
            ordenSeleccionada = (int) modeloOrdenes.getValueAt(fila, 0);
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
        if (idCliente == null || idCliente.trim().isEmpty()) {
            txtDetalles.setText(" El ID del cliente no puede estar vacío");
            return;
        }
        
        // Validar que el cliente exista
        boolean clienteExiste = false;
        for (Cliente c : control.obtenerListadoClientes()) {
            if (c.getIdCliente().equals(idCliente)) {
                clienteExiste = true;
                break;
            }
        }
        
        if (!clienteExiste) {
            txtDetalles.setText(" No existe un cliente con ID: " + idCliente);
            return;
        }
        
        int numOrden = control.crearOrden(idCliente);
        cargarOrdenes();
        txtDetalles.setText(" Orden creada: " + numOrden + " para cliente: " + idCliente);
    }
    
    private void agregarProductoOrden() {
        int fila = tablaOrdenes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione una orden primero");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        
        String codigoStr = JOptionPane.showInputDialog(frame, "Codigo del producto:");
        if (codigoStr == null || codigoStr.isEmpty()) return;
        
        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
            if (!validarPositivo(codigo)) {
                txtDetalles.setText(" El codigo debe ser un numero positivo");
                return;
            }
        } catch (NumberFormatException e) {
            txtDetalles.setText(" El codigo debe ser un numero");
            return;
        }
        
        // Validar que el producto exista
        Producto producto = null;
        for (Producto p : control.obtenerListadoProductos()) {
            if (p.getCodigoProducto() == codigo) {
                producto = p;
                break;
            }
        }
        
        if (producto == null) {
            txtDetalles.setText(" No existe un producto con codigo: " + codigo);
            return;
        }
        
        String cantidadStr = JOptionPane.showInputDialog(frame, "Cantidad (numero positivo):");
        if (cantidadStr == null || cantidadStr.isEmpty()) return;
        
        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (!validarPositivo(cantidad)) {
                txtDetalles.setText(" La cantidad debe ser mayor a 0");
                return;
            }
        } catch (NumberFormatException e) {
            txtDetalles.setText(" La cantidad debe ser un numero");
            return;
        }
        
        // Validar que haya suficiente existencia
        if (producto.getExistencia() < cantidad) {
            txtDetalles.setText(" Existencia insuficiente. Disponible: " + producto.getExistencia());
            return;
        }
        
        control.agregarLinea(numOrden, cantidad, (float) producto.getPrecio());
        cargarOrdenes();
        cargarLineasOrden();
        txtDetalles.setText(" Producto agregado a la orden " + numOrden);
    }
    
    private void marcarPendiente() {
        int fila = tablaOrdenes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione una orden");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        control.ponerOrdenPendiente(numOrden);
        cargarOrdenes();
        txtDetalles.setText("  Orden " + numOrden + " marcada como PENDIENTE");
    }
    
    private void marcarTerminada() {
        int fila = tablaOrdenes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione una orden");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        control.ponerOrdenTerminada(numOrden);
        cargarOrdenes();
        txtDetalles.setText(" Orden " + numOrden + " marcada como TERMINADA");
    }
    
    private void verDetalleOrden() {
        int fila = tablaOrdenes.getSelectedRow();
        if (fila == -1) {
            txtDetalles.setText(" Seleccione una orden");
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
            txtDetalles.setText(" DETALLES DE ORDEN\n\n" +
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
            txtDetalles.setText(" Seleccione una orden primero");
            return;
        }
        
        if (filaLinea == -1) {
            txtDetalles.setText(" Seleccione una linea para eliminar");
            return;
        }
        
        int numOrden = (int) modeloOrdenes.getValueAt(filaOrden, 0);
        int numLinea = filaLinea + 1;
        
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "¿Eliminar esta linea de la orden " + numOrden + "?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean resultado = control.borrarLinea(numOrden, numLinea);
            
            if (resultado) {
                cargarOrdenes();
                cargarLineasOrden();
                txtDetalles.setText(" Linea eliminada de la orden " + numOrden);
                
                // Volver a seleccionar la misma orden
                for (int i = 0; i < tablaOrdenes.getRowCount(); i++) {
                    if ((int) modeloOrdenes.getValueAt(i, 0) == numOrden) {
                        tablaOrdenes.setRowSelectionInterval(i, i);
                        break;
                    }
                }
            } else {
                txtDetalles.setText(" Error: No se pudo eliminar la linea");
            }
        }
    }
}