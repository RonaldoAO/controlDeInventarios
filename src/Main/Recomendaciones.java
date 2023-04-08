/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Recomendaciones extends JFrame {

    private JTextField textField;
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public Recomendaciones() {
        super("Lista de recomendaciones");

        // Crea un modelo para la lista de recomendaciones
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);

        // Crea el campo de texto
        textField = new JTextField(20);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateList();
            }
        });

        // Agrega los componentes al contenedor
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(textField, BorderLayout.NORTH);
        container.add(new JScrollPane(list), BorderLayout.CENTER);

        // Configura la ventana
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateList() {
        // Obtiene el texto actual del campo de texto
        String text = textField.getText();

        // Crea una lista de recomendaciones basada en el texto
        List<String> recommendations = new ArrayList<>();
        for (String item : getList()) {
            if (item.startsWith(text)) {
                recommendations.add(item);
            }
        }

        // Actualiza el modelo de la lista con las recomendaciones
        listModel.clear();
        for (String item : recommendations) {
            listModel.addElement(item);
        }
    }

    // Simula una lista de items
    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("JavaScript");
        list.add("Python");
        list.add("C++");
        list.add("C#");
        list.add("Ruby");
        list.add("Swift");
        return list;
    }

    public static void main(String[] args) {
        new Recomendaciones();
    }
}
