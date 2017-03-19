package backend;

public class HardwareMethods {

	CPU cpu;

	public HardwareMethods(CPU cpu) {
		this.cpu = cpu;
	}

	public void mainMachineCycle() {
		if (cpu.TI == 0) {

		}
		// instruction Location
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
			// cpu.JLxy();
			break;
		case 0x09:
			// cpu.JGxy();
			break;
		case 0x0A:
			// cpu.STOP();
			break;
		case 0x0B:
			cpu.LDxy();
			break;
		case 0x0C:
			cpu.PTxy();
			break;
		case 0x0D:
			// cpu.PUNx();
			break;
		case 0x0E:
			// cpu.PUSx();
			break;
		case 0x0F:
			// cpu.PRINT();
			break;
		case 0x10:
			// cpu.READ();
			break;
		case 0x11:
			// cpu.PRINT_E();
			break;
		case 0x12:
			// cpu.READ_E();
			break;
		case 0x13:
			// cpu.SET_AR();
			break;
		case 0x14:
			// cpu.GET_AR();
			break;
		case 0x15:
			// cpu.INICD();
			break;
		case 0x16:
			// cpu.CHNG_S();
			break;
		case 0x17:
			// cpu.SET_PTR();
			break;
		case 0x18:
			// cpu.SET_TI();
			break;
		case 0x19:
			// cpu.SET_PI();
			break;
		case 0x1A:
			// cpu.SET_CDR();
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
		newReg[0] = (byte) (16 * PTR + Byte.toUnsignedInt(reg[0]));
		return newReg;
	}
}
