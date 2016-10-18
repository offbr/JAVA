package designcomponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable.*;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class BTable extends JTable implements Cinfo {
	public BTable(DefaultTableModel model) {
		// TODO Auto-generated constructor stub
		super(model);
		setBackground(BUTTON_COLOR);
		setForeground(TEXT_COLOR);
		setGridColor(FRAME_COLOR);
		MyHeaderRenderer render = new MyHeaderRenderer();

		for (int i = 0; i < model.getColumnCount(); i++) {
			this.getColumnModel().getColumn(i).setHeaderRenderer(render);
			this.getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRenderer());
		}
	
	}

	public BTable(AbstractTableModel model) {
		// TODO Auto-generated constructor stub
		super(model);
		setBackground(BUTTON_COLOR);
		setForeground(TEXT_COLOR);
		setGridColor(FRAME_COLOR);
		
		MyHeaderRenderer render = new MyHeaderRenderer();
		for (int i = 0; i < model.getColumnCount(); i++) {
			this.getColumnModel().getColumn(i).setHeaderRenderer(render);
			this.getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRenderer());
		}
		
		
		
	}

	static class MyHeaderRenderer extends JLabel implements TableCellRenderer {

		public MyHeaderRenderer() {
			setFont(new Font("SanSerif", Font.BOLD, 12));
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
			setForeground(TEXT_COLOR);
			setBackground(PANEL_COLOR);
			setBorder(BorderFactory.createEtchedBorder());
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(value!=null){
				setText(value.toString());
			}else{
				setText("");
			}
			
			return this;
		}

	}
	static class MyTableCellRenderer extends JLabel implements TableCellRenderer {

		public MyTableCellRenderer() {
			
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
			setForeground(TEXT_COLOR);
			setBackground(BUTTON_COLOR);
			setBorder(BorderFactory.createEtchedBorder());
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(value!=null){
				setText(value.toString());
			}else{
				setText("");
			}
			
			return this;
		}

	}
	

	
}
