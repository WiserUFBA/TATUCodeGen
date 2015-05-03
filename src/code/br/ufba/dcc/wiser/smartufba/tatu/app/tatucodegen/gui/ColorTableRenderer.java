package br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui;

import br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.TATUCodeGen;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class ColorTableRenderer implements TableCellRenderer{
    @Override
    public Component getTableCellRendererComponent( JTable table, Object value,
                                                    boolean isSelected,
                                                    boolean hasFocus,
                                                    int row, int col) {
        Component renderer = new DefaultTableCellRenderer()
                                    .getTableCellRendererComponent
                                    (table, value, isSelected, hasFocus, row, col);
        renderer.setBackground(TATUCodeGen.digital_pin_color[col]);
        return renderer;
    }
}
