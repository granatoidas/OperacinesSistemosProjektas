package backend;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

public class Utilities {

	public static String getByteAsHex(Byte b) {
		String a = Integer.toHexString(Byte.toUnsignedInt(b)).toUpperCase();
		if (a.length() == 1) {
			a = "0" + a;
		}
		return a;
	}

	public static void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			columnModel.getColumn(column).setMinWidth(25);
			columnModel.getColumn(column).setMaxWidth(25);
			columnModel.getColumn(column).setWidth(25);
		}
	}
}
