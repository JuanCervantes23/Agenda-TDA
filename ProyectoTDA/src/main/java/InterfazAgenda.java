import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList; // Importante para guardar múltiples tareas

/**
 * Autor: Juan Francisco
 * Descripción: Interfaz gráfica.
 */
public class InterfazAgenda extends JFrame {

    // --- NUEVO: Lista para guardar todas las tareas ---
    private ArrayList<TareaEquipo> listaTareas;
    
    private JTextArea consola;
    private JLabel lblStats;
    private JComboBox<String> comboSelectorTareas; // Para elegir la tarea
    
    // Controles para el formulario de entrada
    private JTextField txtTitulo;
    private JTextField txtResponsable;
    private JComboBox<Integer> cmbComplejidad;

    public InterfazAgenda() {
        // Inicializamos la lista vacía
        listaTareas = new ArrayList<>();

        setTitle("Agenda Organizador - TDA");
        setSize(700, 700); // Un poquito más alta para el selector
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(15, 20, 15, 20));

        // --- PANEL NORTE: Autor y Formulario de Creación ---
        JPanel panelNorte = new JPanel(new BorderLayout(0, 15));
        
        JLabel lblAutor = new JLabel("JUAN FRANCISCO", SwingConstants.CENTER);
        lblAutor.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblAutor.setForeground(new Color(41, 128, 185));
        panelNorte.add(lblAutor, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1, true),
                "Crear Nueva Tarea",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13), new Color(44, 62, 80)
        ));
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                panelFormulario.getBorder(), new EmptyBorder(10, 10, 10, 10)
        ));
        
        panelFormulario.add(new JLabel("Título de la actividad:"));
        txtTitulo = new JTextField();
        panelFormulario.add(txtTitulo);

        panelFormulario.add(new JLabel("Responsable: "));
        txtResponsable = new JTextField();
        panelFormulario.add(txtResponsable);

        panelFormulario.add(new JLabel("Complejidad de la tarea: "));
        Integer[] niveles = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        cmbComplejidad = new JComboBox<>(niveles);
        panelFormulario.add(cmbComplejidad);

        panelFormulario.add(new JLabel("")); // Espacio vacío
        JButton btnCrear = crearBoton("Guardar Tarea", new Color(52, 73, 94), Color.WHITE);
        panelFormulario.add(btnCrear);

        panelNorte.add(panelFormulario, BorderLayout.CENTER);
        add(panelNorte, BorderLayout.NORTH);


        // --- PANEL CENTRAL: Selector, Info y Consola ---
        JPanel panelCentro = new JPanel(new BorderLayout(0, 10));
        
        // Sub-panel para el selector de tareas y los stats
        JPanel panelInfoCentral = new JPanel(new BorderLayout(0, 10));
        
        // El Selector de Tareas
        JPanel panelSelector = new JPanel(new BorderLayout(10, 0));
        panelSelector.add(new JLabel("Seleccionar Tarea Activa:"), BorderLayout.WEST);
        comboSelectorTareas = new JComboBox<>();
        panelSelector.add(comboSelectorTareas, BorderLayout.CENTER);
        
        lblStats = new JLabel("<html><i>Aún no has creado ninguna tarea.</i></html>");
        lblStats.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblStats.setHorizontalAlignment(SwingConstants.CENTER);
        lblStats.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 224, 224), 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));
        
        panelInfoCentral.add(panelSelector, BorderLayout.NORTH);
        panelInfoCentral.add(lblStats, BorderLayout.CENTER);
        
        panelCentro.add(panelInfoCentral, BorderLayout.NORTH);

        consola = new JTextArea();
        consola.setEditable(false);
        consola.setFont(new Font("Consolas", Font.PLAIN, 13));
        consola.setBackground(new Color(248, 249, 249)); 
        consola.setForeground(new Color(44, 62, 80));
        consola.setMargin(new Insets(10, 15, 10, 15));
        
        JScrollPane scroll = new JScrollPane(consola);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        panelCentro.add(scroll, BorderLayout.CENTER);
        
        add(panelCentro, BorderLayout.CENTER);


        // --- PANEL INFERIOR: Botones de Acción ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
        panelBotones.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1, true),
                "Panel de Control",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13), new Color(44, 62, 80)
        ));
        
        JButton btnEstado = crearBoton("Ver Estado", new Color(52, 152, 219), Color.WHITE);
        JButton btnCompletar = crearBoton("Completar", new Color(46, 204, 113), Color.WHITE);
        JButton btnReiniciar = crearBoton("Reiniciar", new Color(231, 76, 60), Color.WHITE);
        JButton btnReasignar = crearBoton("Reasignar", new Color(241, 196, 15), Color.BLACK);
        JButton btnRecursivo = crearBoton("Estimar Ágil", new Color(155, 89, 182), Color.WHITE);

        panelBotones.add(btnEstado);
        panelBotones.add(btnCompletar);
        panelBotones.add(btnReiniciar);
        panelBotones.add(btnReasignar);
        panelBotones.add(btnRecursivo);
        
        add(panelBotones, BorderLayout.SOUTH);

        // --- EVENTOS DE LOS BOTONES ---
        
        // 1. Al crear, la guardamos en la lista
        btnCrear.addActionListener(e -> {
            String titulo = txtTitulo.getText();
            String resp = txtResponsable.getText();
            
            if(titulo.trim().isEmpty() || resp.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor llena el título y el responsable.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int comp = (Integer) cmbComplejidad.getSelectedItem();
            TareaEquipo nuevaTarea = new TareaEquipo(titulo, comp, resp); 
            
            // La agregamos al ArrayList
            listaTareas.add(nuevaTarea);
            // La agregamos al menú desplegable visual
            comboSelectorTareas.addItem(titulo);
            // Seleccionamos automáticamente la que acabamos de crear
            comboSelectorTareas.setSelectedIndex(listaTareas.size() - 1);
            
            imprimir("TAREA GUARDADA: '" + titulo + "' asignada a " + resp);
            
            txtTitulo.setText("");
            txtResponsable.setText("");
        });

        // 2. Evento cuando cambias la selección en el menú desplegable
        comboSelectorTareas.addActionListener(e -> {
            actualizarStats(); // Muestra los stats de la tarea que acabas de seleccionar
        });

        // 3. Eventos de los botones de acción (Ahora usan getTareaActiva())
        btnEstado.addActionListener(e -> {
            TareaEquipo activa = getTareaActiva();
            if (activa != null) imprimir(activa.evaluarEstado());
        });

        btnCompletar.addActionListener(e -> {
            TareaEquipo activa = getTareaActiva();
            if (activa != null) {
                imprimir(activa.completarActividad());
                actualizarStats();
            }
        });

        btnReiniciar.addActionListener(e -> {
            TareaEquipo activa = getTareaActiva();
            if (activa != null) {
                imprimir(activa.reiniciarActividad());
                actualizarStats();
            }
        });

        btnReasignar.addActionListener(e -> {
            TareaEquipo activa = getTareaActiva();
            if (activa != null) {
                // NUEVO CANDADO LÓGICO: Verificamos el estado antes de abrir la ventana
                if (activa.isEstaCompletada()) {
                    JOptionPane.showMessageDialog(this, 
                        "No puedes reasignar una tarea que ya está completada.\nSi necesitas cambiar al responsable, primero dale clic en 'Reiniciar'.", 
                        "Acción Denegada", 
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    // Si no está completada, abrimos la ventana normalmente
                    String nuevo = JOptionPane.showInputDialog(this, "Nuevo responsable para '" + activa.getTitulo() + "':", "Reasignar", JOptionPane.QUESTION_MESSAGE);
                    if (nuevo != null && !nuevo.trim().isEmpty()) {
                        imprimir(activa.reasignarResponsable(nuevo));
                        actualizarStats();
                    }
                }
            }
        });

        btnRecursivo.addActionListener(e -> {
            TareaEquipo activa = getTareaActiva();
            if (activa != null) {
                int complejidad = activa.getNivelComplejidad();
                int puntosAgiles = activa.calcularPuntosEsfuerzo(complejidad);
                imprimir("CÁLCULO RECURSIVO: " + activa.getTitulo() + "' de nivel " + complejidad + 
                         " requiere " + puntosAgiles + " Puntos de Esfuerzo.");
            }
        });
        
        imprimir("Sistema de Agenda.");
    }

    // --- NUEVO MÉTODO CENTRAL: Obtiene la tarea que esté seleccionada en el ComboBox ---
    private TareaEquipo getTareaActiva() {
        int index = comboSelectorTareas.getSelectedIndex();
        if (index >= 0 && index < listaTareas.size()) {
            return listaTareas.get(index);
        } else {
            JOptionPane.showMessageDialog(this, "Primero debes crear y seleccionar una tarea.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private JButton crearBoton(String texto, Color colorFondo, Color colorTexto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(colorFondo);
        btn.setForeground(colorTexto);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(colorFondo.darker(), 1),
            new EmptyBorder(5, 12, 5, 12)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void actualizarStats() {
        TareaEquipo activa = getTareaActiva();
        if (activa != null) {
            String estado = activa.isEstaCompletada() ? "<font color='green'>Completada</font>" : "<font color='#d35400'>Pendiente</font>";
            lblStats.setText("<html><b>Tarea:</b> " + activa.getTitulo() + 
                   " &nbsp;&nbsp;|&nbsp;&nbsp; <b>Responsable:</b> " + activa.getResponsable() + 
                   " &nbsp;&nbsp;|&nbsp;&nbsp; <b>Estado:</b> " + estado + "</html>");
        }
    }

    private void imprimir(String texto) {
        consola.append(texto + "\n");
        consola.setCaretPosition(consola.getDocument().getLength());
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> {
            new InterfazAgenda().setVisible(true);
        });
    }
}