package backend;

import java.util.Arrays;

/**
 * Contains methods describing each instruction
 * 
 * @author grant
 *
 */
public class CPU {
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

	public CPU() {
		System.out.println(Arrays.toString(IC));
	}

	// Place for methods describing instructions
	public void ADD() {
		// veliau pridesiu komandas
	}

	private Byte[] iterateRegister(Byte[] reg, int stepsAmount) {
		Byte[] naujas = { reg[0], reg[1] };
		int stpAm = Math.abs(stepsAmount);
		for (int i = 0; i < stpAm; i++) {
			if (stepsAmount > 0) {
				if (++naujas[1] == 0x00){
					if(++naujas[0] == 16 && MDR == 0){
						naujas[0] = 0;
					}
				}
			} else {
				if (--naujas[1] == 0xFF){
					if(--naujas[0] == 0xFF && MDR == 0){
						naujas[0] = 15;
					}
				}
			}
		}
		return naujas;
	}
}
