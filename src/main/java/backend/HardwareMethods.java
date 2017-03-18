package backend;

public class HardwareMethods {

	CPU cpu;

	public HardwareMethods(CPU cpu) {
		this.cpu = cpu;
	}

	public void mainMachineCycle() {
		if (cpu.TI == 0) {

		}
		//instruction Location
		Byte[] iL = pagingMechanism(cpu.IC);
		Byte instruction = cpu.ram[cpu.hex(iL[0])][cpu.hex(iL[1])];
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
		case 0:
			cpu.PTR = 20;
			cpu.ram[10][10] = 0x40;
			break;
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:

			break;
		case 8:

			break;
		case 9:

			break;
		case 10:

			break;
		case 11:

			break;
		case 12:

			break;
		case 13:

			break;
		case 14:

			break;
		case 15:

			break;
		case 16:

			break;
		default:
			cpu.SI = 2;
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
		newReg[0] = (byte) (16*PTR + Byte.toUnsignedInt(reg[0]));
		return newReg;
	}
}
