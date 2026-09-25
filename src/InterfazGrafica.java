package src;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.StringReader;

public class InterfazGrafica extends JFrame {

    private JTextArea txtEntrada;
    private JTable tablaTokens;
    private DefaultTableModel modeloTabla;

    public InterfazGrafica() {
        setTitle("Analizador Lexico - Proyecto de Compiladores");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior (Entrada de código)
        txtEntrada = new JTextArea();
        txtEntrada.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollEntrada = new JScrollPane(txtEntrada);
        scrollEntrada.setBorder(BorderFactory.createTitledBorder("Escribe el codigo fuente aqui:"));
        add(scrollEntrada, BorderLayout.CENTER);

        // Panel derecho (Tabla de resultados)
        String[] columnas = {"Lexema", "Token / Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTokens = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaTokens);
        scrollTabla.setPreferredSize(new Dimension(350, 0));
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Tabla de Simbolos"));
        add(scrollTabla, BorderLayout.EAST);

        // Panel inferior (Botón)
        JButton btnAnalizar = new JButton("Ejecutar Analisis Lexico");
        btnAnalizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAnalizar.setBackground(new Color(40, 167, 69));
        btnAnalizar.setForeground(Color.WHITE);
        add(btnAnalizar, BorderLayout.SOUTH);

        // Acción del botón
        btnAnalizar.addActionListener(e -> realizarAnalisis());
    }

    private void realizarAnalisis() {
        // Limpiar la tabla antes de un nuevo análisis
        modeloTabla.setRowCount(0);
        String codigo = txtEntrada.getText();

        if (codigo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa codigo para analizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Inicializar el Lexer generado por JFlex
            Lexer lexer = new Lexer(new StringReader(codigo));
            String resultado;

            // Leer tokens hasta que termine el archivo
            while ((resultado = lexer.yylex()) != null) {
                // El Lexer devuelve un String como "TIPO|lexema"
                String[] partes = resultado.split("\\|");
                if (partes.length == 2) {
                    String tipo = partes[0];
                    String lexema = partes[1];
                    modeloTabla.addRow(new Object[]{lexema, tipo});
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error durante el analisis: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            new InterfazGrafica().setVisible(true);
        });
    }
}