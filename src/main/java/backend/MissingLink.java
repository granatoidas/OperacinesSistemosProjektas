package backend;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	private static CPU cpu;
	private static RAM ram;
	private static RealMachine frame;

	public MissingLink() {
		frame = new RealMachine();
		frame.setVisible(true);
		cpu = new CPU();
		ram = new RAM();
		setUpRamTable();

		ram.memory[0][0] = new Byte((byte) 0xFF);
	}

	private void setUpRamTable() {
		List<Integer> range = IntStream.rangeClosed(0, 255).boxed().collect(Collectors.toList());

		TableModel model = new AbstractTableModel() {
			Byte[][] data = ram.memory;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return data[rowIndex][columnIndex];
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
					data[rowIndex][columnIndex] = Byte.parseByte(d);
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
