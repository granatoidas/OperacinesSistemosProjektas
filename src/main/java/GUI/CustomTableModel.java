package GUI;

import javax.swing.table.AbstractTableModel;

import backend.Utilities;

public class CustomTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	Byte[][] data;

	public CustomTableModel(Byte[][] data) {
		super();
		this.data = data;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return Utilities.getByteAsHex(data[rowIndex][columnIndex]);
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
			data[rowIndex][columnIndex] = (byte) Integer.parseInt(d, 16);
		} catch (Exception e) {
		}
	}

	@Override
	public String getColumnName(int column) {
		return Utilities.getByteAsHex((byte) column);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

}
