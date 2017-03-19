package backend;

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

		Utilities.resizeColumnWidth(frame.RAMtable);

	}

}
