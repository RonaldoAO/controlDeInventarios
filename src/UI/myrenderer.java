
package UI;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class myrenderer extends JLabel implements TableCellRenderer {

    boolean isBordered = true;
    private String name;
    
    public myrenderer(boolean isBordered, String name) {
        this.isBordered = isBordered;
        this.name = name;
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
        return new JButton(name);
    }
}