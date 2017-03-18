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

	
	//Place for methods describing instructions
	
	//reikia busenu patikrinimo
	public void ADD(){
		byte a = MissingLink.ram.memory[SP[1]][SP[2]-3];
		byte b = MissingLink.ram.memory[SP[1]][SP[2]-2];
		byte c = MissingLink.ram.memory[SP[1]][SP[2]-1];
		byte d = MissingLink.ram.memory[SP[1]][SP[2]];
		short val1=(short)( ((a)<<8) | (b) );
		short val2=(short)( ((c)<<8) | (d) );
		short sum = (short)(val1+val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);
		//reikia ikelt i memory
		//reikia sumazint SP
		
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
