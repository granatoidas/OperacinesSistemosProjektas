package backend;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import GUI.RealMachine;

/**
 * This class connects GUI and backend. Is controlled by EntryPoint
 * 
 * @author grant
 *
 */
public class MissingLink {
	public static CPU cpu;
	public static MemoryUnit ram;
	public static RealMachine frame;
	public static HardwareMethods hardwareMethods;

	public MissingLink() {
		frame = new RealMachine();
		frame.setVisible(true);
		
		
		ram = new MemoryUnit();
		cpu = new CPU(ram);
		
		hardwareMethods = new HardwareMethods(cpu);
		
		setUpRamTable();
	}

	private void setUpRamTable() {
		TableModel model = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;
			Byte[][] data = ram.memory;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return Integer.toHexString(Byte.toUnsignedInt(data[rowIndex][columnIndex]));
			}

			@Override
			public int getRowCount() {
				return 256;
			}

			@Override
			public int getColumnCount() {
				return 256;
			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				try {
					String d = (String) aValue;
					data[rowIndex][columnIndex] = (byte)Integer.parseInt(d, 16);
				} catch(Exception e){}
			}
			
			@Override
			public String getColumnName(int column){
				return Integer.toHexString(column);
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return true;
			}
		};

		frame.RAMtable.setModel(model);

		this.resizeColumnWidth(frame.RAMtable);

	}

	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			columnModel.getColumn(column).setMinWidth(25);
			columnModel.getColumn(column).setMaxWidth(25);
			columnModel.getColumn(column).setWidth(25);
		}
	}
}
