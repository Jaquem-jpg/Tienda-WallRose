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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.Controladora;
import logica.Cliente;

public class VentanaPrincipal {

    private JFrame frame;
    private Controladora control;
    
    // Componentes de Clientes
    private JTable tablaClientes;
    private DefaultTableModel modeloClientes;
    private JTextArea txtDetalles;

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
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Tienda WallRose - Clientes");
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        // ========== TABLA DE CLIENTES ==========
        JScrollPane scrollClientes = new JScrollPane();
        scrollClientes.setBounds(150, 30, 700, 300);
        frame.getContentPane().add(scrollClientes);
        
        tablaClientes = new JTable();
        modeloClientes = new DefaultTableModel(new String[]{"ID", "Nombre", "Email"}, 0);
        tablaClientes.setModel(modeloClientes);
        scrollClientes.setViewportView(tablaClientes);
        
        // ========== BOTONES ==========
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(30, 50, 100, 25);
        frame.getContentPane().add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(30, 90, 100, 25);
        frame.getContentPane().add(btnEditar);
        
        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(30, 130, 100, 25);
        frame.getContentPane().add(btnBorrar);
        
        JButton btnVer = new JButton("Ver");
        btnVer.setBounds(30, 170, 100, 25);
        frame.getContentPane().add(btnVer);
        
        // ========== ÁREA DE DETALLES ==========
        txtDetalles = new JTextArea();
        txtDetalles.setEditable(false);
        JScrollPane scrollDetalles = new JScrollPane(txtDetalles);
        scrollDetalles.setBounds(30, 400, 820, 120);
        frame.getContentPane().add(scrollDetalles);
        
        // ========== EVENTOS ==========
        btnAgregar.addActionListener(e -> agregarCliente());
        btnEditar.addActionListener(e -> editarCliente());
        btnBorrar.addActionListener(e -> borrarCliente());
        btnVer.addActionListener(e -> verCliente());
    }
    
    // ========== CARGAR CLIENTES ==========
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
    
    // ========== AGREGAR CLIENTE ==========
    private void agregarCliente() {
        String id = JOptionPane.showInputDialog(frame, "ID del cliente:");
        if (id == null || id.isEmpty()) return;
        
        String nombre = JOptionPane.showInputDialog(frame, "Nombre:");
        if (nombre == null || nombre.isEmpty()) return;
        
        String email = JOptionPane.showInputDialog(frame, "Email:");
        if (email == null || email.isEmpty()) return;
        
        control.crearCliente(id, nombre, email);
        cargarClientes();
        txtDetalles.setText(" Cliente agregado: " + id + " - " + nombre);
    }
    
    // ========== EDITAR CLIENTE ==========
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
            control.actualizarCliente(id, nuevoNombre, nuevoEmail);
            cargarClientes();
            txtDetalles.setText(" Cliente actualizado: " + id);
        }
    }
    
    // ========== BORRAR CLIENTE ==========
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
    
    // ========== VER DETALLE CLIENTE ==========
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
}