package UI;

import PDF.generator;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import models.Registro;

/*
* Clase para darle evento a los botones de eliminar
 */
public class myeditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    Boolean currentValue;
    JButton button;
    protected static final String EDIT = "edit";
    protected static final String SEE_MORE = "see_more";
    protected static final String PRINT = "print";
    private JTable jTable1;
    private List<Registro> registros;
    private Main.Main main; 
    public myeditor(JTable jTable1, String event, List<Registro> registros, Main.Main main ) {
        button = new JButton();
        //button.setSize(50, 10);
        button.setActionCommand(event);
        button.addActionListener(this);
        button.setBorderPainted(false);
        this.jTable1 = jTable1;
        this.registros = registros; 
        this.main  = main;
    }

    public void actionPerformed(ActionEvent e) {
        Registro registro = null; 
               
        if(registros != null){
            System.out.println("INDICE: " + jTable1.getSelectedRow());
                registro = registros.get(registros.size() - jTable1.getSelectedRow() - 1);
        }
        switch (e.getActionCommand()) {
            case "edit":
                int numero = jTable1.getEditingRow();
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.removeRow(numero);
                fireEditingCanceled();
                model.fireTableDataChanged();
                jTable1.setModel(model);
                break;  
            case "see_more":
                Main.Registro ventanaRegistro = new Main.Registro(registros, registros.size() - jTable1.getSelectedRow() - 1, this.main);
                ventanaRegistro.setVisible(true);
                ventanaRegistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
            case "print":
                
                generator generadorPDF = new generator();
                if(registro.getMovimiento().equals("Entrada") )generadorPDF.generarEntrada(registro);else generadorPDF.generarSalida(registro); 
                //Abrimos el PDF
                try {
                    File file = new File(registro.getMovimiento().equals("Entrada") ? "Entrada.pdf" : "Salida.pdf");
                    if (!Desktop.isDesktopSupported()) {
                        System.out.println("not supported");
                        return;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    if (file.exists()) {
                        desktop.open(file);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

        }

    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
        return currentValue;
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentValue = (Boolean) value;
        return button;
    }
}
