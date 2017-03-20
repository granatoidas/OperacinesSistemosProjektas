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

	private Byte[] iterateRegister(Byte[] reg, int stepsAmount) {
		Byte[] naujas = { reg[0], reg[1] };
		int stpAm = Math.abs(stepsAmount);
		for (int i = 0; i < stpAm; i++) {
			if (stepsAmount > 0) {
				if (++naujas[1] == 0x00) {
					if (++naujas[0] == 16 && MDR == 0) {
						naujas[0] = 0;
					}
				}
			} else {
				if (--naujas[1] == 0xFF) {
					if (--naujas[0] == 0xFF && MDR == 0) {
						naujas[0] = 15;
					}
				}
			}
		}
		return naujas;
	}

	private void decrementTimer() {
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
		// Byte[] SP = convertAddress(this.SP);
		// #TODO adresas turi but paiteruojamas originalioje formoje tada
		// konvertuojamas
		// Tai svarbu nes pakonvertuoto adreso nera kaip tikrint ar VM ribose
		// yra
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[SPtmp[0]][SPtmp[1]];
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
		decrementTimer();
	}

	public void SUB() {
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[SPtmp[0]][SPtmp[1]];
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
		decrementTimer();
	}

	public void MUL() {
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[SPtmp[0]][SPtmp[1]];
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
		decrementTimer();
	}

	public void DIV() {
		Byte[] SPtmp = iterateAndConvert(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateAndConvert(SP, 0);
		byte d = ram[SPtmp[0]][SPtmp[1]];
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
		decrementTimer();
	}

	public void CMP() {
		//Byte[] SP = convertAddress(this.SP);
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
		decrementTimer();
	}

	public void JPxy() {
		//Byte[] IC = convertAddress(this.IC);
		Byte[] ICtmp = iterateAndConvert(IC, 1);
		byte a = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		ICtmp = iterateAndConvert(IC, 2);
		byte b = ram[hex(IC[0])][hex(IC[1])];
		this.IC[0] = a;
		this.IC[1] = b;
		decrementTimer();
	}

	public void JExy() {
		//Byte[] IC = convertAddress(this.IC);
		Byte[] SP = convertAddress(this.SP);
		byte tmp = ram[hex(SP[0])][hex(SP[1])];
		if (tmp == 1) {
			Byte[] ICtmp = iterateAndConvert(IC, 1);
			byte a = ram[hex(ICtmp[0])][hex(ICtmp[1])];
			ICtmp = iterateAndConvert(IC, 2);
			byte b = ram[hex(IC[0])][hex(IC[1])];
			this.IC[0] = a;
			this.IC[1] = b;
			this.SP = iterateRegister(this.SP, -1);
			decrementTimer();
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
		decrementTimer();
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
		decrementTimer();
	}

	public void PUNx() {
		// Byte[] IC = convertAddress(this.IC);
		Byte[] SP = convertAddress(this.SP);
		Byte[] ICtmp = iterateAndConvert(IC, 1);
		byte x = ram[hex(ICtmp[0])][hex(ICtmp[1])];
		this.SP = iterateRegister(this.SP, 1);
		ram[hex(SP[0])][hex(SP[1])] = x;
		decrementTimer();
	}
}
