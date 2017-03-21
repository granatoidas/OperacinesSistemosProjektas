package backend;

import java.awt.Component;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import GUI.CustomTableModel;
import GUI.RowNumberTable;

public class HardwareMethods {

	CPU cpu;
	MemoryUnit HardDrive;
	Byte[][] hdd;
	JFrame frame;

	private boolean waitingForInput = false;
	private Byte[] lastCDR;
	private LinkedList<String> inputQueue = new LinkedList<String>();

	public HardwareMethods(CPU cpu) {
		this.cpu = cpu;
		this.HardDrive = new MemoryUnit();
		this.hdd = this.HardDrive.memory;
		this.create_HDD_GUI();
	}

	public void mainMachineCycle() {
		if (cpu.TI == 0) {

		}
		// instruction Location
		Byte[] iL = (cpu.MDR == 1)? cpu.IC : pagingMechanism(cpu.IC);
		Byte instruction = cpu.ram[cpu.hex(iL[0])][cpu.hex(iL[1])];
		cpu.decrementTimer();
		executeInstruction(instruction);

		MissingLink.frame.refreshData();
	}

	/**
	 * Calls instruction according to the code passed to it
	 * 
	 * @param instruction
	 *            code of the instruction
	 */
	private void executeInstruction(Byte instruction) {
		switch (instruction) {
		case 0x00:
			// cpu.CHNG_S();
			break;
		case 0x01:
			cpu.ADD();
			break;
		case 0x02:
			cpu.SUB();
			break;
		case 0x03:
			cpu.MUL();
			break;
		case 0x04:
			cpu.DIV();
			break;
		case 0x05:
			cpu.CMP();
			break;
		case 0x06:
			cpu.JPxy();
			break;
		case 0x07:
			cpu.JExy();
			break;
		case 0x08:
			cpu.JLxy();
			break;
		case 0x09:
			cpu.JGxy();
			break;
		case 0x0A:
			cpu.STOP();
			break;
		case 0x0B:
			cpu.LDxy();
			break;
		case 0x0C:
			cpu.PTxy();
			break;
		case 0x0D:
			cpu.PUNx();
			break;
		case 0x0E:
			cpu.PUSx();
			break;
		case 0x0F:
			cpu.PRINT();
			break;
		case 0x10:
			cpu.READ();
			break;
		case 0x11:
			cpu.PRINT_E();
			break;
		case 0x12:
			cpu.READ_E();
			break;
		case 0x13:
			cpu.SET_AR();
			break;
		case 0x14:
			cpu.GET_AR();
			break;
		case 0x15:
			cpu.INICD();
			break;
		case 0x16:
			cpu.CHNG_S();
			break;
		case 0x17:
			// cpu.SET_PTR();
			break;
		case 0x18:
			cpu.SET_TI();
			break;
		case 0x19:
			cpu.SET_PI();
			break;
		case 0x1A:
			cpu.SET_CDR();
			break;
		default:
			cpu.SI = 2;
		}
	}

	/**
	 * This is the code of channel device
	 */
	public void CD() {
		lastCDR = cpu.CDR;
		switch (lastCDR[0]) {
		case 1:
			if (!inputQueue.isEmpty())
				writeFromInputToRam();
			else
				waitingForInput = true;
			break;
		case 2:
			String output = generateOutputString();
			MissingLink.frame.printDataToOutput(output);
			break;
		case 3:
			writeToHdd();
			break;
		case 4:
			readFromHdd();
			break;
		}
		MissingLink.frame.refreshData();
		this.frame.repaint();
	}

	public void passInput(String s) {
		inputQueue.add(s);
		if (waitingForInput)
			writeFromInputToRam();
	}

	private void writeFromInputToRam() {
		waitingForInput = false;
		String s = inputQueue.removeFirst();
		byte[] bytes = s.getBytes(StandardCharsets.US_ASCII);
		Byte[] address = { lastCDR[1], lastCDR[2] };
		int i = 0;
		for (byte b : bytes) {
			cpu.ram[address[0]][address[1]] = b;
			address = cpu.iterateRegister(address, 1, (byte) 1);
			if (++i == 100)
				break;
		}
		cpu.PI = (byte) 6;
		MissingLink.frame.refreshData();
	}

	private String generateOutputString() {
		int i = 0;
		String out = "";
		Byte[] address = { lastCDR[1], lastCDR[2] };
		while (i++ < 100) {
			byte b = cpu.ram[address[0]][address[1]];
			address = cpu.iterateRegister(address, 1, (byte) 1);
			String add = new String(new byte[] { b }, StandardCharsets.US_ASCII);
			if (add.equals("$"))
				break;
			out += add;
		}
		return out;
	}

	private void writeToHdd() {
		int i = 0;
		Byte[] addressRam = { lastCDR[1], lastCDR[2] };
		Byte[] addressHdd = { lastCDR[3], lastCDR[4] };
		while (i++ < 100) {
			byte b = cpu.ram[addressRam[0]][addressRam[1]];
			if (b == (byte) 0x62)
				break;
			hdd[addressHdd[0]][addressHdd[1]] = b;
			addressRam = cpu.iterateRegister(addressRam, 1, (byte) 1);
			addressHdd = cpu.iterateRegister(addressHdd, 1, (byte) 1);
		}
	}

	private void readFromHdd() {
		int i = 0;
		Byte[] addressRam = { lastCDR[1], lastCDR[2] };
		Byte[] addressHdd = { lastCDR[3], lastCDR[4] };
		while (i++ < 100) {
			byte b = hdd[addressHdd[0]][addressHdd[1]];
			if (b == (byte) 0x62)
				break;
			cpu.ram[addressRam[0]][addressRam[1]] = b;
			addressRam = cpu.iterateRegister(addressRam, 1, (byte) 1);
			addressHdd = cpu.iterateRegister(addressHdd, 1, (byte) 1);
		}
	}

	/**
	 * Calls pagingMechanism and uses current CPU PTR as second parameter
	 * 
	 * @param reg
	 * @return
	 */
	public Byte[] pagingMechanism(Byte[] reg) {
		return pagingMechanism(reg, cpu.PTR);
	}

	/**
	 * Convert 2 byte address according to which virtual machine is used (PTR)
	 * value.
	 * 
	 * @param reg
	 *            2 element size array representing register
	 * @param PTR
	 *            byte representing PTR value
	 * @return changed register
	 */
	private Byte[] pagingMechanism(Byte[] reg, Byte PTR) {
		Byte[] newReg = { (byte) 00, reg[1] };
		newReg[0] = (byte) (16 * PTR + Byte.toUnsignedInt(reg[0]));
		return newReg;
	}

	private void create_HDD_GUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(300 + 800, 150, 640, 480);
		frame.setTitle("External Memory (HDD)");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.setContentPane(scrollPane);

		JTable HDDtable = new JTable();
		HDDtable.setAlignmentY(Component.TOP_ALIGNMENT);
		HDDtable.setAlignmentX(Component.LEFT_ALIGNMENT);
		HDDtable.setRowSelectionAllowed(false);
		HDDtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(HDDtable);

		JTable rowTable = new RowNumberTable(HDDtable);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());

		HDDtable.setModel(new CustomTableModel(hdd));

		Utilities.resizeColumnWidth(HDDtable);
		frame.setVisible(true);
	}
}
