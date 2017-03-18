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
	
	
	public void ADD(){
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -2);
		byte b = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -1);
		byte c = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];		
		byte d = MissingLink.ram.memory[SP[1]][SP[2]]; //SP nes imam be poslinkio
		short val1=(short)( ((a)<<8) | (b) );
		short val2=(short)( ((c)<<8) | (d) );
		short sum = (short)(val1+val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);
		
		SPtmp = iterateRegister(SP, -2);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = a1;
		SPtmp = iterateRegister(SP, -1);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = a2;
		
		SPtmp = iterateRegister(SP, -2);
		SP = SPtmp;		
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
	
	public void SUB(){
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -2);
		byte b = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -1);
		byte c = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];		
		byte d = MissingLink.ram.memory[SP[1]][SP[2]]; //SP nes imam be poslinkio
		short val1=(short)( ((a)<<8) | (b) );
		short val2=(short)( ((c)<<8) | (d) );
		short sum = (short)(val1-val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);
		
		SPtmp = iterateRegister(SP, -2);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = a1;
		SPtmp = iterateRegister(SP, -1);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = a2;
		
		SPtmp = iterateRegister(SP, -2);
		SP = SPtmp;		
	}
	
	public void MUL(){
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -2);
		byte b = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -1);
		byte c = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];		
		byte d = MissingLink.ram.memory[SP[1]][SP[2]]; //SP nes imam be poslinkio
		short val1=(short)( ((a)<<8) | (b) );
		short val2=(short)( ((c)<<8) | (d) );
		short sum = (short)(val1*val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);
		
		SPtmp = iterateRegister(SP, -2);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = a1;
		SPtmp = iterateRegister(SP, -1);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = a2;
		
		SPtmp = iterateRegister(SP, -2);
		SP = SPtmp;		
	}
	
	public void DIV(){
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -2);
		byte b = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -1);
		byte c = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];		
		byte d = MissingLink.ram.memory[SP[1]][SP[2]]; //SP nes imam be poslinkio
		short val1=(short)( ((a)<<8) | (b) );
		short val2=(short)( ((c)<<8) | (d) );
		short sum = (short)(val1/val2);
		byte a1 = (byte) sum;
		byte a2 = (byte) (sum >> 8);
		
		SPtmp = iterateRegister(SP, -2);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = a1;
		SPtmp = iterateRegister(SP, -1);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = a2;
		
		SPtmp = iterateRegister(SP, -2);
		SP = SPtmp;		
	}
	
	public void CMP(){
		Byte[] SPtmp = iterateRegister(SP, -3);
		byte a = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -2);
		byte b = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];
		SPtmp = iterateRegister(SP, -1);
		byte c = MissingLink.ram.memory[SPtmp[1]][SPtmp[2]];		
		byte d = MissingLink.ram.memory[SP[1]][SP[2]]; //SP nes imam be poslinkio
		short val1=(short)( ((a)<<8) | (b) );
		short val2=(short)( ((c)<<8) | (d) );
		byte rez;
		if(val1==val2){
			rez = 1;
		}else if(val1<val2){
			rez = 2;
		}else{
			rez = 0;
		}
	
		
		SPtmp = iterateRegister(SP, 1);
		MissingLink.ram.memory[SPtmp[1]][SPtmp[2]] = rez;
		SP = SPtmp;		
	}

}
