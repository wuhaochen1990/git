
public class Simulator {

	//instruction code
	public static final int LDR = 1;
	public static final int STR = 2;
	public static final int LDA = 3;
	public static final int LDX = 41;
	public static final int STX = 42;
	public static final int JZ = 10;
	public static final int JNE = 11;
	public static final int JCC = 12;
	public static final int JMP = 13;
	public static final int JSR = 14;
	public static final int RFS = 15;
	public static final int SOB = 16;
	public static final int JGE = 17;
	public static final int AMR = 4;
	public static final int SMR = 5;
	public static final int AIR = 6;
	public static final int SIR = 7;
	public static final int MUL = 20;
	public static final int DIV = 21;
	public static final int TER = 22;
	public static final int AND = 23;
	public static final int ORR = 24;
	public static final int NOT = 25;
	public static final int SRC = 31;
	public static final int RRC = 32;
	public static final int IN = 61;
	public static final int OUT = 62;
	public static final int CHK = 63;
	public static final int FADD = 33;
	public static final int FSUB = 34;
	public static final int VADD = 35;
	public static final int VSUB = 36;
	public static final int CNVRT = 37;
	public static final int LDFR = 8;
	public static final int STFR = 9;
	//the value of some key words
	public static int opcode;//opcode
	public static int operands;//numbers after the opcode
	public static int ac;
	public static int x;
	public static int Address;
	public static int immed;//immediate number
	public static int rx;//rx and ry is for mul and div instr
	public static int ry;
	public static int cc;//condition code for jcc
	public static int count;
	public static int LR;
	public static int AL;
	public static int r;
	public static int devid;//device id

	//mdr and mar and ir
	public static int mdr;
	public static int mar;
	public static int ir;

	//get the ea! effective address by switch the x value
	public static int getEA(int x){
		int ea = 0;
		switch(x){
		case 0:{
			ea = Address;
			break;
		}
		case 1:{
			ea = X0Reg.getX0()+Address;
			break;
		}
		case 2:{
			ea = Memory.getDataFromMemory(Address);
			break;
		}
		case 3:{
			ea = Memory.getDataFromMemory(X0Reg.getX0() + Address);
			break;
		}
		}
		return ea;
	}
	//cache read
	public static void cache_read(){
		Cache.isMiss(mar);
		if(Cache.miss == 1){
			//cache hit
			mdr = Cache.cacheHit(mar);
		}else{
			//cache miss, get data from memory
			Cache.cacheMiss(mar);
			mdr = Memory.getDataFromMemory(mar);
		}
	}
	//cache write
	public static void cache_write(){
		Cache.isMiss(mar);
		if(Cache.miss == 1){
			//cache hit, write the content to both cache and memory
			Cache.cacheWrite(mar, mdr);
			Memory.setData2Memory(mdr, mar);
		}else{
			//cache miss, just write the content to memory, and then change the cache
			Memory.setData2Memory(mdr, mar);
			Cache.cacheWrite(mar, mdr);
		}
	}
	//run the instruction
	public static void run(){
		//run until there is not instruction in the memory where pc point to
		while(Memory.getInstrFromMemory(ProgramCounter.getPC()) != 0){
		if(Keyboard.getInterrupt() == 0){
			break;
		}
		//fetch the instruction from the memory
		mar = ProgramCounter.getPC();//mar <- pc
		ProgramCounter.incrementPC();//pc ++
		mdr = Memory.getInstrFromMemory(mar);//mdr <- c(mar)
		ir = mdr;//ir <- mdr
		
		//decode the instruction
		operands = ir & 0b1111111111;// the last ten bits is operands
		int temp = ir>>>10;// right shift ten bits
		opcode = temp & 0b111111;// get the six bits opcode
		
		//choose the instruction function
		switch(opcode){
		case LDR:{
			System.out.println("LDR");
			
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
			
			//cache operation
			
			
			GPRegister.setReg(Memory.getDataFromMemory(mar), ac);
			break;
		}
		case STR:{
			System.out.println("STR");

			Address = operands & 0b111111;
			operands = operands>>>6;
			ac = operands & 0b11;
			operands = operands>>>2;
			x = operands & 0b11;
			

			mar = getEA(x);
			mdr = GPRegister.getReg(ac);
			//cache operation
			cache_write();
			break;
		}
		case LDA:{
			System.out.println("LDA");
			Address = operands & 0b111111;
			operands = operands>>>6;
			ac = operands & 0b11;
			operands = operands>>>2;
			x = operands & 0b11;
			mar = getEA(x);
			//cache operation
			cache_read();
			if(x == 2 || x == 3){
				GPRegister.setReg(mdr, ac);
			}else{
				GPRegister.setReg(mar, ac);
			}
			break;
		}
		case LDX:{
			
			System.out.println("LDX");
			Address = operands & 0b111111;
			operands = operands>>>6;
			x = operands&0b11;
			mar = getEA(x);
			cache_read();
			X0Reg.setX0(mdr);
			System.out.println("X0 register:"+Integer.toString(mdr));
			break;
		}
		case STX:{
			System.out.println("STX");
			Address = operands & 0b111111;
			operands = operands>>>6;
			x = operands&0b11;
			mar = getEA(x);
			mdr = X0Reg.getX0();
			//cache operation
			cache_write();
			break;
		}
		case JZ:{
			System.out.println("JZ");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
			cache_read();
			if(GPRegister.getReg(ac) == 0){
				if(x == 2 || x == 3){
					//because we have do incrementPC above, so we have to decrement
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(mdr);
				}else{
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(getEA(x));
				}
			}else{
				
				ProgramCounter.decrementPC();
				ProgramCounter.incrementPC();
			}
			break;
		}
		case JNE:{
			System.out.println("JNE");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
			cache_read();
			if(GPRegister.getReg(ac) != 0){
				if(x == 2 || x == 3){
					//because we have do incrementPC above, so we have to decrement
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(mdr);
				}else{
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(getEA(x));
				}
			}else{
				
				ProgramCounter.decrementPC();
				ProgramCounter.incrementPC();
			}
			break;
		}
		case JCC:{
			System.out.println("JCC");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
//			cc = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
			cache_read();
			if(cc == 1){
				if(x == 2 || x == 3){
					//because we have do incrementPC above, so we have to decrement
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(mdr);
				}else{
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(getEA(x));
				}
			}else{
				
				ProgramCounter.decrementPC();
				ProgramCounter.incrementPC();
			}
			break;
		}
		case JGE:{
			System.out.println("JGE");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
			cache_read();
			if(GPRegister.getReg(ac) >= 0){
				if(x == 2 || x == 3){
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(mdr);
				}else{
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(getEA(x));
				}
			}else{
				ProgramCounter.decrementPC();
				ProgramCounter.incrementPC();
			}
			break;
		}
		case JMP:{
			System.out.println("JMP");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			x = operands & 0b11;//I IX 
			mar = getEA(x);
			cache_read();
			if(x == 2 || x == 3){
				//because we have do incrementPC above, so we have to decrement
				ProgramCounter.decrementPC();
				ProgramCounter.setPC(mdr);
			}else{
				ProgramCounter.decrementPC();
				ProgramCounter.setPC(getEA(x));
			}		
			break;
		}
		case JSR:{
			System.out.println("JSR");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			x = operands & 0b11;//I IX
			mar = getEA(x);
			cache_read();
			GPRegister.setReg(ProgramCounter.getPC(), 3);//R3 <- PC+1
			if(x == 2 || x == 3){
				//because we have do incrementPC above, so we have to decrement
				ProgramCounter.decrementPC();
				ProgramCounter.setPC(mdr);
				//in the memory, argument starts at 1000
				GPRegister.setReg(Memory.getDataFromMemory(1000), 0);
			}else{
				ProgramCounter.decrementPC();
				ProgramCounter.setPC(getEA(x));
				//in the memory, argument starts at 1000
				GPRegister.setReg(Memory.getDataFromMemory(1000), 0);
			}		
			break;
		}
		case RFS:{
			System.out.println("RFS");
			immed = operands;
			GPRegister.setReg(immed, 0);
			ProgramCounter.setPC(GPRegister.getReg(3));
			break;
		}
		case SOB:{
			System.out.println("SOB");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
			cache_read();
			GPRegister.setReg(GPRegister.getReg(ac) - 1,ac); //r <- c(r)-1
			if(GPRegister.getReg(ac) > 0){
				if(x == 2 || x == 3){
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(mdr);
				}else{
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(getEA(x));
				}
			}
			break;
		}
		case AMR:{
			System.out.println("AMR");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
//			cache_read();
			ALU.setDestination(ac);//set destination register
			ALU.setSource(Memory.getDataFromMemory(mar));//set source content
			ALU.setOperation(0);//add operation number is 0
			ALU.runALU();
			break;
		}
		case SMR:{
			System.out.println("SMR");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
			cache_read();
			ALU.setDestination(ac);//set destination register
			ALU.setSource(mdr);//set source content
			ALU.setOperation(1);//add operation number is 0
			ALU.runALU();
			break;
		}
		case AIR:{
			System.out.println("AIR");
			immed = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			if(immed != 0){
				if(GPRegister.getReg(ac)==0){
					GPRegister.setReg(immed, ac);
				}else{
					ALU.setDestination(ac);//set destination register
					ALU.setSource(immed);//set source content
					ALU.setOperation(0);//add operation number is 0
					ALU.runALU();
				}
			}else{
				//if immed is 0, do nothing
			
			}
			break;
		}
		case SIR:{
			System.out.println("SIR");
			immed = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			if(immed != 0){
				if(GPRegister.getReg(ac)==0){
					GPRegister.setReg(0-immed, ac);
				}else{
					ALU.setDestination(ac);//set destination register
					ALU.setSource(immed);//set source content
					ALU.setOperation(1);//add operation number is 0
					ALU.runALU();
				}
			}else{
				//if immed is 0, do nothing
			
			}
			break;
		}
		case MUL:{
			System.out.println("MUL");
			operands = operands >>> 6;
			ry = operands & 0b11;
			operands = operands >>> 2;
			rx = operands & 0b11;
			ALU.setDestination(rx);//set destination register
			ALU.setSource(ry);//set source content
			ALU.setOperation(2);//mul operation number is 2
			ALU.runALU();
			break;
		}
		case DIV:{
			System.out.println("DIV");
			operands = operands >>> 6;
			ry = operands & 0b11;
			operands = operands >>> 2;
			rx = operands & 0b11;
			ALU.setDestination(rx);//set destination register
			ALU.setSource(ry);//set source content
			ALU.setOperation(3);//div operation number is 3
			ALU.runALU();
			break;
		}
		case TER:{
			System.out.println("TER");
			operands = operands >>> 6;
			ry = operands & 0b11;
			operands = operands >>> 2;
			rx = operands & 0b11;
			ALU.setDestination(rx);//set destination register
			ALU.setSource(ry);//set source content
			ALU.setOperation(4);//ter operation number is 4
			ALU.runALU();
			break;
		}
		case AND:{
			System.out.println("AND");
			operands = operands >>> 6;
			ry = operands & 0b11;
			operands = operands >>> 2;
			rx = operands & 0b11;
			ALU.setDestination(rx);//set destination register
			ALU.setSource(ry);//set source content
			ALU.setOperation(5);//and operation number is 5
			ALU.runALU();
			break;
		}
		case ORR:{
			System.out.println("ORR");
			operands = operands >>> 6;
			ry = operands & 0b11;
			operands = operands >>> 2;
			rx = operands & 0b11;
			ALU.setDestination(rx);//set destination register
			ALU.setSource(ry);//set source content
			ALU.setOperation(6);//and operation number is 6
			ALU.runALU();
			break;
		}
		case NOT:{
			System.out.println("NOT");
			operands = operands >>> 8;
			rx = operands & 0b11;
			ALU.setDestination(rx);//set destination register
			ALU.setOperation(7);//and operation number is 7
			ALU.runALU();
			break;
		}
		case SRC:{
			System.out.println("SRC");
			count = operands & 0b1111;
			operands = operands >>> 6;
			AL = operands &0b1;
			operands = operands >>>1;
			r = operands & 0b11;
			operands = operands >>>2;
			LR = operands & 0b1;
			ALU.setDestination(r);
			ALU.setOperation(8);//shift operation number is 8
			ALU.setAL(AL);
			ALU.setLR(LR);
			ALU.setCount(count);
			ALU.runALU();
			break;
		}
		case RRC:{
			System.out.println("RRC");
			count = operands & 0b1111;
			operands = operands >>> 6;
			AL = operands &0b1;
			operands = operands >>>1;
			r = operands & 0b11;
			operands = operands >>>2;
			LR = operands & 0b1;
			ALU.setDestination(r);
			ALU.setOperation(9);//rotate operation number is 8
			ALU.setAL(AL);
			ALU.setLR(LR);
			ALU.setCount(count);
			ALU.runALU();
			break;
		}
		case IN:{
			System.out.println("IN");
			devid = operands & 0b11111;
			operands = operands >>> 7;
			r = operands & 0b11;

			IO.setDevid(devid);
			IO.setR(r);
			IO.setOperation(0);//IN operation is 0
			IO.runIO();
			break;
		}
		case OUT:{
			System.out.println("OUT");
			devid = operands & 0b11111;
			operands = operands >>> 7;
			r = operands & 0b11;
			IO.setDevid(devid);
			IO.setR(r);
			IO.setOperation(1);//OUT operation is 1
			IO.runIO();
			break;
		}
		case CHK:{
			System.out.println("CHK");
			devid = operands & 0b11111;
			operands = operands >>> 7;
			r = operands & 0b11;
			IO.setDevid(devid);
			IO.setR(r);
			IO.setOperation(2);//CHK operation is 2
			IO.runIO();
			break;
		}
		case LDFR:{
			System.out.println("LDFR");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			if(x == 2 | x == 3){
				int data1 = Memory.getDataFromMemory(Memory.getDataFromMemory(getEA(x)));
				int data2 = Memory.getDataFromMemory(Memory.getDataFromMemory(getEA(x)+1));
				int s = (data1 >> 15) & 0b1;
				int exp = (data1 >> 8) & 0b1111111;
				int manti = ((data1 & 0b11111111) << 16) + data2;
				System.out.println(s);
				System.out.println(Integer.toBinaryString(exp));
				System.out.println(Integer.toBinaryString(manti));
				GPRegister.setFReg(s, exp, manti, ac);
			}else{
				int data1 = Memory.getDataFromMemory(getEA(x));
				int data2 = Memory.getDataFromMemory(getEA(x)+1);
				int s = (data1 >> 15) & 0b1;
				int exp = (data1 >> 8) & 0b1111111;
				int manti = ((data1 & 0b11111111) << 16) + data2;
				System.out.println(s);
				System.out.println(Integer.toBinaryString(exp));
				System.out.println(Integer.toBinaryString(manti));
				GPRegister.setFReg(s, exp, manti, ac);
			}
			break;
		}
		case STFR:{
			System.out.println("STFR");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			if(x == 2 | x == 3){
				int data = GPRegister.getFReg(ac);
				int data1 = data & 0b1111111111111111;
				int data2 = (data >> 16) & 0b1111111111111111;
				Memory.setData2Memory(data1, Memory.getDataFromMemory(getEA(x)));
				Memory.setData2Memory(data2,  Memory.getDataFromMemory(getEA(x)+1));
			}else{
				int data = GPRegister.getFReg(ac);
				int data1 = data & 0b1111111111111111;
				int data2 = (data >> 16) & 0b1111111111111111;
				Memory.setData2Memory(data2, getEA(x));
				Memory.setData2Memory(data1, getEA(x)+1);
			}
			break;
		}
		case FADD:{
			System.out.println("FADD");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			ALU.setDestination(ac);//set destination register
			if(x == 2 | x == 3){//set source content
				ALU.setSource(Memory.getDataFromMemory(getEA(x)));
			}else{
				ALU.setSource(getEA(x));
			}
			ALU.setOperation(10);//fadd operation number is 10
			ALU.runALU();
			break;
		}
		case FSUB:{
			System.out.println("FSUB");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			ALU.setDestination(ac);//set destination register
			if(x == 2 | x == 3){//set source content
				ALU.setSource(Memory.getDataFromMemory(getEA(x)));
			}else{
				ALU.setSource(getEA(x));
			}
			ALU.setOperation(11);//fsub operation number is 10
			ALU.runALU();
			break;
		}
		case VADD:{
			System.out.println("VADD");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			if(x == 2 | x == 3){
				ALU.setV1(Memory.getDataFromMemory(Memory.getDataFromMemory(getEA(x))));
				ALU.setV2(Memory.getDataFromMemory(Memory.getDataFromMemory(getEA(x)+1)));
			}else{
				ALU.setV1(Memory.getDataFromMemory(getEA(x)));
				ALU.setV2(Memory.getDataFromMemory(getEA(x)+1));
			}
			ALU.setOperation(12);
			ALU.runALU();
			break;
		}
		case VSUB:{
			System.out.println("VSUB");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			if(x == 2 | x == 3){
				ALU.setV1(Memory.getDataFromMemory(getEA(x)));
				ALU.setV2(Memory.getDataFromMemory(getEA(x)+1));
			}else{
				ALU.setV1(Memory.getDataFromMemory(Memory.getDataFromMemory(getEA(x))));
				ALU.setV2(Memory.getDataFromMemory(Memory.getDataFromMemory(getEA(x)+1)));
			}
			ALU.setOperation(13);
			ALU.runALU();
			break;
		}
		}
		}
		
	}
}
