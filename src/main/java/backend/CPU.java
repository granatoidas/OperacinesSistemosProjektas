package backend;

/**
 * Contains methods describing each instruction
 * 
 * @author grant
 *
 */
public class CPU {

	@SuppressWarnings("unused")
	private MemoryUnit ramClass;
	protected Byte[][] ram;

	public Byte[] IC = { new Byte((byte) 0), new Byte((byte) 0) };
	public Byte[] SP = { new Byte((byte) 0), new Byte((byte) 0) };

	public Byte PTR = new Byte((byte) 0);

	public Byte MDR = new Byte((byte) 0);

	public Byte TI = new Byte((byte) 0);
	public Byte PI = new Byte((byte) 0);
	public Byte SI = new Byte((byte) 0);

	public Byte[] AR = { new Byte((byte) 0), new Byte((byte) 0), new Byte((byte) 0), new Byte((byte) 0) };
	public Byte[] CDR = { new Byte((byte) 0), new Byte((byte) 0), new Byte((byte) 0), new Byte((byte) 0),
			new Byte((byte) 0) };

	public CPU(MemoryUnit ram) {
		this.ramClass = ram;
		this.ram = ram.memory;
	}

	public Byte[] iterateRegister(Byte[] reg, int stepsAmount) {
		return iterateRegister(reg, stepsAmount, this.MDR);
	}

	public Byte[] iterateRegister(Byte[] reg, int stepsAmount, Byte mode) {
		Byte[] naujas = { reg[0], reg[1] };
		int stpAm = Math.abs(stepsAmount);
		for (int i = 0; i < stpAm; i++) {
			if (stepsAmount > 0) {
				if (++naujas[1] == 0x00) {
					if (++naujas[0] == 0x10 /* 16-ta eilute */ && mode == 0) {
						naujas[0] = 0;
					}
				}
			} else {
				if (--naujas[1] == 0xFF) {
					if (--naujas[0] == 0xFF && mode == 0) {
						naujas[0] = 15;
					}
				}
			}
		}
		return naujas;
	}

	public void decrementTimer() {
		if (TI > 0) {
			TI--;
		}
	}

	public int hex(byte a) {
		return Byte.toUnsignedInt(a);
	}

	/**
	 * Return "real"/modified address. Return a copy of array so changing it
	 * won't affect original.
	 * 
	 * @param addr
	 * @return
	 */
	private Byte[] convertAddress(Byte[] addr) {
		if (MDR == 1)
			return addr.clone();
		return MissingLink.hardwareMethods.pagingMechanism(addr);
	}

	private Byte[] iterateAndConvert(Byte[] addr, int stepsAmount) {
		Byte[] newAddress = iterateRegister(addr, stepsAmount);
		Byte[] conAddress = convertAddress(newAddress);
		return conAddress;
	}

	////////////////////////////////////////////
	// Place for methods describing instructions
	////////////////////////////////////////////

	public void ADD() {
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		short sum = (short) (val1 + val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);

		SPtmp = iterateAndConvert(SP, -2);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a1;
		SPtmp = iterateAndConvert(SP, -3);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a2;

		SPtmp = iterateRegister(this.SP, -2);
		this.SP = SPtmp;
		this.IC = iterateRegister(this.IC, 1);
	}

	public void SUB() {
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		short sum = (short) (val1 - val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);

		SPtmp = iterateAndConvert(SP, -2);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a1;
		SPtmp = iterateAndConvert(SP, -3);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a2;

		SPtmp = iterateRegister(this.SP, -2);
		this.SP = SPtmp;
		this.IC = iterateRegister(this.IC, 1);

	}

	public void MUL() {
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		short sum = (short) (val1 * val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);

		SPtmp = iterateAndConvert(SP, -2);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a1;
		SPtmp = iterateAndConvert(SP, -3);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a2;

		SPtmp = iterateRegister(this.SP, -2);
		this.SP = SPtmp;
		this.IC = iterateRegister(this.IC, 1);

	}

	public void DIV() {
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		short sum = (short) (val1 / val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);

		SPtmp = iterateAndConvert(SP, -2);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a1;
		SPtmp = iterateAndConvert(SP, -3);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a2;

		SPtmp = iterateRegister(this.SP, -2);
		this.SP = SPtmp;
		this.IC = iterateRegister(this.IC, 1);
	}

	public void CMP() {
		// Byte[] SP = convertAddress(this.SP);
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = convertAddress(this.SP);
		byte d = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		byte rez;
		if (val1 == val2) {
			rez = 1;
		} else if (val1 < val2) {
			rez = 2;
		} else {
			rez = 0;
		}
		SPtmp = iterateAndConvert(this.SP, 1);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = rez;
		this.SP = iterateRegister(this.SP, 1);
		this.IC = iterateRegister(this.IC, 1);

	}

	public void JPxy() {
		// Byte[] IC = convertAddress(this.IC);
		Byte[] ICtmp = iterateAndConvert(IC, 1);
		byte a = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		ICtmp = iterateAndConvert(IC, 2);
		byte b = ram[hex(IC[0])][hex(IC[1])];
		if (MDR == 0) {
			if (a > 0x0F) {
				SI = 2;
				return;
			}
		}
		this.IC[0] = a;
		this.IC[1] = b;
		this.IC = iterateRegister(this.IC, 3);

	}

	public void JExy() {
		// Byte[] IC = convertAddress(this.IC);
		Byte[] SP = convertAddress(this.SP);
		byte tmp = ram[hex(SP[0])][hex(SP[1])];
		if (tmp == 1) {
			Byte[] ICtmp = iterateAndConvert(IC, 1);
			byte a = ram[hex(ICtmp[0])][hex(ICtmp[1])];
			ICtmp = iterateAndConvert(IC, 2);
			byte b = ram[hex(IC[0])][hex(IC[1])];
			if (MDR == 0) {
				if (a > 0x0F) {
					SI = 2;
					return;
				}
			}
			this.IC[0] = a;
			this.IC[1] = b;
			this.SP = iterateRegister(this.SP, -1);
			this.IC = iterateRegister(this.IC, 3);
		}
	}

	public void JLxy() {
		// Byte[] IC = convertAddress(this.IC);
		Byte[] SP = convertAddress(this.SP);
		byte tmp = ram[hex(SP[0])][hex(SP[1])];
		if (tmp == 0) {
			Byte[] ICtmp = iterateAndConvert(IC, 1);
			byte a = ram[hex(ICtmp[0])][hex(ICtmp[1])];
			ICtmp = iterateAndConvert(IC, 2);
			byte b = ram[hex(IC[0])][hex(IC[1])];
			if (MDR == 0) {
				if (a > 0x0F) {
					SI = 2;
					return;
				}
			}
			this.IC[0] = a;
			this.IC[1] = b;
			this.SP = iterateRegister(this.SP, -1);
			this.IC = iterateRegister(this.IC, 3);
		}
	}

	public void JGxy() {
		// Byte[] IC = convertAddress(this.IC);
		Byte[] SP = convertAddress(this.SP);
		byte tmp = ram[hex(SP[0])][hex(SP[1])];
		if (tmp == 2) {
			Byte[] ICtmp = iterateAndConvert(IC, 1);
			byte a = ram[hex(ICtmp[0])][hex(ICtmp[1])];
			ICtmp = iterateAndConvert(IC, 2);
			byte b = ram[hex(IC[0])][hex(IC[1])];
			if (MDR == 0) {
				if (a > 0x0F) {
					SI = 2;
					return;
				}
			}
			this.IC[0] = a;
			this.IC[1] = b;
			this.SP = iterateRegister(this.SP, -1);
			this.IC = iterateRegister(this.IC, 3);
		}
	}

	public void LDxy() {
		// Byte[] IC = convertAddress(this.IC);
		Byte[] SP = convertAddress(this.SP);
		Byte[] ICtmp = iterateAndConvert(IC, 1);
		byte x = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		ICtmp = iterateAndConvert(IC, 2);
		byte y = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		Byte[] xy = new Byte[2];
		xy[0] = x;
		xy[1] = y;
		Byte[] xyPTR = convertAddress(xy);
		Byte[] SPtmp = iterateRegister(SP, 1);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = ram[hex(xyPTR[0])][hex(xyPTR[1])];
		this.SP = iterateRegister(this.SP, 1);
		this.IC = iterateRegister(this.IC, 3);
	}

	public void PTxy() {
		// Byte[] IC = convertAddress(this.IC);
		Byte[] SP = convertAddress(this.SP);
		Byte[] ICtmp = iterateAndConvert(IC, 1);
		byte x = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		ICtmp = iterateAndConvert(IC, 2);
		byte y = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		Byte[] xy = new Byte[2];
		xy[0] = x;
		xy[1] = y;
		Byte[] xyPTR = convertAddress(xy);
		ram[hex(xyPTR[0])][hex(xyPTR[1])] = ram[hex(SP[0])][hex(SP[1])];
		this.SP = iterateRegister(this.SP, -1);
		this.IC = iterateRegister(this.IC, 3);
	}

	public void PUNx() {
		// Byte[] IC = convertAddress(this.IC);
		Byte[] SP = convertAddress(this.SP);
		Byte[] ICtmp = iterateAndConvert(IC, 1);
		byte x = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		this.SP = iterateRegister(this.SP, 1);
		ram[hex(SP[0])][hex(SP[1])] = x;
		this.IC = iterateRegister(this.IC, 2);
	}

	public void PUSx() {
		Byte[] SP = convertAddress(this.SP);
		Byte[] ICtmp = iterateAndConvert(IC, 1);
		byte x = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		this.SP = iterateRegister(this.SP, 1);
		ram[hex(SP[0])][hex(SP[1])] = x;
		this.IC = iterateRegister(this.IC, 2);
	}

	public void CHNG_S() {
		MDR = 1;
		TI = 50;
		ram[14][4 * PTR - 1] = IC[0];
		ram[14][4 * PTR] = IC[1];
		ram[14][4 * PTR - 3] = SP[0];
		ram[14][4 * PTR - 2] = IC[1];
		IC[0] = 0;
		IC[1] = 0;
		SP[0] = 0;
		SP[1] = 13;
		this.IC = iterateRegister(this.IC, 1);
	}

	public void CHNG_U() {
		if (MDR == 0) {
			SI = 2;
			return;
		}
		MDR = 0;
		IC[0] = 0;
		IC[1] = 0;
		SP[0] = 0;
		SP[1] = 13;
		this.IC = iterateRegister(this.IC, 1);
	}

	public void SET_TI() {
		Byte[] SP = convertAddress(this.SP);
		TI = ram[hex(SP[0])][hex(SP[1])];
		this.SP = iterateRegister(this.SP, -1);
		this.IC = iterateRegister(this.IC, 1);
	}

	public void SET_PI() {
		Byte[] SP = convertAddress(this.SP);
		PI = ram[hex(SP[0])][hex(SP[1])];
		this.SP = iterateRegister(this.SP, -1);
		this.IC = iterateRegister(this.IC, 1);
	}

	public void SET_AR() {
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[SPtmp[0]][SPtmp[1]];
		this.AR[0] = a;
		this.AR[1] = b;
		this.AR[2] = c;
		this.AR[3] = d;
		this.SP = iterateRegister(this.SP, -4);
		this.IC = iterateRegister(this.IC, 1);
	}

	public void INICD() {
		if (MDR == 0) {
			SI = (byte) 2;
			return;
		}
		MissingLink.hardwareMethods.CD();
		this.IC = iterateRegister(this.IC, 1);
	}

	public void SET_CDR() {
		Byte[] SPtmp = iterateAndConvert(SP, -4);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -3);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte d = ram[SPtmp[0]][SPtmp[1]];
		SPtmp = iterateAndConvert(SP, 0);
		byte e = ram[SPtmp[0]][SPtmp[1]];
		this.CDR[0] = a;
		this.CDR[1] = b;
		this.CDR[2] = c;
		this.CDR[3] = d;
		this.CDR[4] = e;
		this.SP = iterateRegister(this.SP, -5);
	}
}
