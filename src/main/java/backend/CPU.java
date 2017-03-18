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

	// Place for methods describing instructions

	public void ADD() {
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		byte d = ram[SP[1]][SP[2]]; // SP nes imam be poslinkio
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		short sum = (short) (val1 + val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);

		SPtmp = iterateRegister(SP, -2);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a1;
		SPtmp = iterateRegister(SP, -1);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a2;

		SPtmp = iterateRegister(SP, -2);
		SP = SPtmp;
		decrementTimer();

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

	public void SUB() {
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		byte d = ram[SP[1]][SP[2]]; // SP nes imam be poslinkio
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		short sum = (short) (val1 - val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);

		SPtmp = iterateRegister(SP, -2);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a1;
		SPtmp = iterateRegister(SP, -1);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a2;

		SPtmp = iterateRegister(SP, -2);
		SP = SPtmp;
		decrementTimer();
	}

	public void MUL() {
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		byte d = ram[SP[1]][SP[2]]; // SP nes imam be poslinkio
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		short sum = (short) (val1 * val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);

		SPtmp = iterateRegister(SP, -2);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a1;
		SPtmp = iterateRegister(SP, -1);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a2;

		SPtmp = iterateRegister(SP, -2);
		SP = SPtmp;
		decrementTimer();
	}

	public void DIV() {
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		byte d = ram[SP[1]][SP[2]]; // SP nes imam be poslinkio
		short val1 = (short) (((a) << 8) | (b));
		short val2 = (short) (((c) << 8) | (d));
		short sum = (short) (val1 / val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);

		SPtmp = iterateRegister(SP, -2);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a1;
		SPtmp = iterateRegister(SP, -1);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = a2;

		SPtmp = iterateRegister(SP, -2);
		SP = SPtmp;
		decrementTimer();
	}

	public void CMP() {
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -2);
		byte b = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		SPtmp = iterateRegister(SP, -1);
		byte c = ram[hex(SPtmp[0])][hex(SPtmp[1])];
		byte d = ram[SP[1]][SP[2]]; // SP nes imam be
									// poslinkio
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

		SPtmp = iterateRegister(SP, 1);
		ram[hex(SPtmp[0])][hex(SPtmp[1])] = rez;
		SP = SPtmp;
		decrementTimer();
	}

	public void JPxy() {
		Byte[] ICtmp = iterateRegister(IC, 1);
		byte a = ram[ICtmp[1]][ICtmp[2]];
		ICtmp = iterateRegister(IC, 2);
		byte b = ram[IC[1]][IC[2]];
		IC[0] = a;
		IC[1] = b;
		decrementTimer();
	}

	public void JExy() {
		byte tmp = ram[SP[1]][SP[2]];
		if (tmp == 1) {
			Byte[] ICtmp = iterateRegister(IC, 1);
			byte a = ram[ICtmp[1]][ICtmp[2]];
			ICtmp = iterateRegister(IC, 2);
			byte b = ram[IC[1]][IC[2]];
			IC[0] = a;
			IC[1] = b;
			SP = iterateRegister(SP, -1);
		}
	}

	public void JLxy() {
		byte tmp = ram[SP[1]][SP[2]];
		if (tmp == 0) {
			Byte[] ICtmp = iterateRegister(IC, 1);
			byte a = ram[ICtmp[1]][ICtmp[2]];
			ICtmp = iterateRegister(IC, 2);
			byte b = ram[IC[1]][IC[2]];
			IC[0] = a;
			IC[1] = b;
			SP = iterateRegister(SP, -1);
		}
	}

	public void JGxy() {
		byte tmp = ram[SP[1]][SP[2]];
		if (tmp == 2) {
			Byte[] ICtmp = iterateRegister(IC, 1);
			byte a = ram[ICtmp[1]][ICtmp[2]];
			ICtmp = iterateRegister(IC, 2);
			byte b = ram[IC[1]][IC[2]];
			IC[0] = a;
			IC[1] = b;
			SP = iterateRegister(SP, -1);
		}
	}

	public void STOP() {
		PI = 5;
	}

	public void CHNG_S() {
		MDR = 1;
		TI = 50;
		// reikia issaugoti IC i adresa
		// reikia issaugoti SP i adresa
		IC[0] = 00;
		IC[1] = 00;
		SP[0] = 13;
		SP[1] = 00;
	}

	public void CHNG_U() {
		if (MDR == 0){
			SI = 2;
			return;
		}
		MDR = 0;
		TI = 50;
		IC[0] = 00;
		IC[1] = 00;
		SP[0] = 13;
		SP[1] = 00;
	}
}
