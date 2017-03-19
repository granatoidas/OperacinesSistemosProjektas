package backend;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import GUI.CustomTableModel;
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
		TableModel model = new CustomTableModel(ram.memory);

		frame.RAMtable.setModel(model);

		resizeColumnWidth(frame.RAMtable);

	}

	public static void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			columnModel.getColumn(column).setMinWidth(25);
			columnModel.getColumn(column).setMaxWidth(25);
			columnModel.getColumn(column).setWidth(25);
		}
	}

	public static String getByteAsHex(Byte b) {
		String a = Integer.toHexString(Byte.toUnsignedInt(b)).toUpperCase();
		if (a.length() == 1) {
			a = "0" + a;
		}
		return a;
	}
}
