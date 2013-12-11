
public class ALU {
	public static int destination;//destination register index 0-4
	public static int source;//sources of the operation
	public static int operation;//operation means add 0 , sub 1 , mul 2  ,  div 3 ,testequality 4, and 5, or 6, not 7,shift 8,rotate 9,FADD 10,FSUB 11,VADD 12,VSUB 13,CNVRT 14
	public static int cc[] = new int[16];//condition code. cc[0] is for judge whether the result of the subtract is negative 
	public static int overflow=0;//fadd overflow
	public static int underfow=0;//fsub underflow
	public static int LR;//L <- 0,R <- 1
	public static int AL;//A <- 0,L <- 1
	public static int count;
	public static int v1;
	public static int v2;

	//set and get
	
	public static int getDestination() {
		return destination;
	}


	public static int getV1() {
		return v1;
	}


	public static void setV1(int v1) {
		ALU.v1 = v1;
	}


	public static int getV2() {
		return v2;
	}


	public static void setV2(int v2) {
		ALU.v2 = v2;
	}


	public static void setDestination(int destination) {
		ALU.destination = destination;
	}


	public static int getSource() {
		return source;
	}


	public static void setSource(int source) {
		ALU.source = source;
	}


	public static int getOperation() {
		return operation;
	}


	public static void setOperation(int operation) {
		ALU.operation = operation;
	}

	

	public static int getLR() {
		return LR;
	}


	public static void setLR(int lR) {
		LR = lR;
	}


	public static int getAL() {
		return AL;
	}


	public static void setAL(int aL) {
		AL = aL;
	}


	public static int getCount() {
		return count;
	}


	public static void setCount(int count) {
		ALU.count = count;
	}


	public static void runALU(){
		switch(operation){
			case(0):{
				//add
				int data1 = GPRegister.getReg(destination);
				int data2 = source;
				int result = data1 + data2;
				//add overflow
				GPRegister.setReg(result, destination);
				break;
			}
			case(1):{
				//sub
				int data1 = GPRegister.getReg(destination);
				int data2 = source;
				int result = data1 - data2;
				if(result >= 0){
					//set the cc
					Simulator.cc = 1;
				}else{
					Simulator.cc = 0;
				}
				GPRegister.setReg(result, destination);
				break;
			}
			case(2):{
				//mul
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				int result = rx * ry;
				int lowbit = result & 0b1111111111111111;
				int highbit = result >>> 16;
				GPRegister.setReg(lowbit, destination+1);
				GPRegister.setReg(highbit, destination);
				break;
			}
			case(3):{
				//div
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				if(ry == 0){
					//set cc(3)=1
					cc[3]=1;
				}else{
					int quotient = rx/ry;
					int remainder = rx%ry;
					GPRegister.setReg(quotient, destination);
					GPRegister.setReg(remainder, destination+1);
				}
				break;
			}
			case(4):{
				//test equality
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				if(rx == ry){
					cc[4] = 1;
				}else{
					cc[4] = 0;
				}
				break;
			}
			case(5):{
				//and
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				int result = rx & ry;
				GPRegister.setReg(result, destination);
				break;
			}
			case(6):{
				//or
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				int result = rx | ry;
				GPRegister.setReg(result, destination);
				break;
			}
			case(7):{
				//not
				int rx = GPRegister.getReg(destination);
				int result = ~rx;
				GPRegister.setReg(result, destination);
				break;
			}
			case(8):{
				//shift
				int r = GPRegister.getReg(destination);
				int result;
				if(AL == 0){
					//arithmetic shift
					if(LR == 0){
						//left
						result = r << count;
					}else{
						//right
						result = r >> count;
					}
				}else{
					//logical shift
					if(LR == 0){
						//left
						result = r << count;
					}else{
						//right
						result = r >>> count;
					}
				}
				GPRegister.setReg(result, destination);
				break;
			}
			case(9):{
				//rotate
				System.out.println("rotate");
				int r = GPRegister.getReg(destination);
				System.out.println(r);
				int result;
				if(LR == 0){
					//left
					
					result = (r << count & 0b1111111111111111) | (r >>> (16-count));
				}else{
					//right
					result = r >> count | r << (16-count);
				}
				GPRegister.setReg(result, destination);
				break;
			}
			case(10):{
				//FADD
				System.out.println("fadd");
				int data1 = GPRegister.getFReg(destination);
				int data1_s = (data1 >> 31) & 0b1;
				System.out.println("data1_s:"+Integer.toBinaryString(data1_s));
				int data1_exp = (data1 >> 24) & 0b1111111;
				System.out.println("data1_exp:"+Integer.toBinaryString(data1_exp));
				int data1_manti = data1 & 0b111111111111111111111111;
				System.out.println("data1_manti:"+Integer.toBinaryString(data1_manti));
				if(data1_s == 1){
					data1_manti = 0-data1_manti;
				}

				int data2_1 = Memory.getDataFromMemory(source);
				int data2_2 = Memory.getDataFromMemory(source+1);
				int data2_s = (data2_1 >> 15) &0b1;
				System.out.println("data2_s:"+Integer.toBinaryString(data2_s));
				int data2_exp = (data2_1 >> 8) & 0b1111111;
				System.out.println("data2_exp:"+Integer.toBinaryString(data2_exp));
				int data2_manti = (data2_1 & 0b11111111) << 16 + data2_2;
				System.out.println("data2_manti:"+Integer.toBinaryString(data2_manti));
				if(data2_s == 1){
					data2_manti = 0-data2_manti;
				}
				
				int result_manti=0;
				int result_s=0;
				//add
				int result_exp = data1_exp - data2_exp;
				System.out.println("result_exp:"+Integer.toBinaryString(result_exp));
				if(result_exp > 0){
					result_manti = (data2_manti >> result_exp) + data1_manti;
					System.out.println("result_manti:"+Integer.toBinaryString(result_manti));
					if((result_manti & 0b10000000000000000000000)!=0){
						overflow = 1;
					}
					if(result_manti < 0){
						result_s = 1;
						result_manti = 0-result_manti;
					}else{
						result_s = 0;
					}
					result_manti = result_manti & 0b111111111111111111111111;
					System.out.println("result_manti:"+Integer.toBinaryString(result_manti));
					result_exp = data1_exp;
					System.out.println("result_exp:"+Integer.toBinaryString(result_exp));
					System.out.println("result_s:"+Integer.toBinaryString(result_s));
					
					
				}else{
					result_exp = 0-result_exp;
					result_manti = (data1_manti >> result_exp) + data2_manti;
					if((result_manti & 0b10000000000000000000000)!=0){
						overflow = 1;
					}
					if(result_manti < 0){
						result_s = 1;
						result_manti = 0-result_manti;
					}else{
						result_s = 0;
					}
					result_manti = result_manti & 0b111111111111111111111111;
					result_exp = data2_exp;
					
				}
				GPRegister.setFReg(result_s, result_exp, result_manti, destination);
				break;
			}
			case(11):{
				//FSUB
				System.out.println("fsub");
				int data1 = GPRegister.getFReg(destination);
				int data1_s = (data1 >> 31) & 0b1;
				int data1_exp = data1 >> 24;
				int data1_manti = data1 & 0b111111111111111111111111;
				if(data1_s == 1){
					data1_manti = 0-data1_manti;
				}

				int data2_1 = Memory.getDataFromMemory(source);
				int data2_2 = Memory.getDataFromMemory(source+1);
				int data2_s = (data2_1 >> 15) &0b1;
				int data2_exp = (data2_1 >> 8) & 0b1111111;
				int data2_manti = (data2_1 & 0b11111111) << 16 + data2_2;
				if(data2_s == 1){
					data2_manti = 0-data2_manti;
				}
				
				int result_manti=0;
				int result_s = 0;
				//sub
				int result_exp = data1_exp - data2_exp;
				if(result_exp > 0){
					result_manti = (data2_manti >> result_exp) - data1_manti;
					if(result_manti<0){
						underfow = 1;
					}
					if(result_manti < 0){
						result_s = 1;
						result_manti = 0-result_manti;
					}else{
						result_s = 0;
					}
					result_manti = result_manti & 0b111111111111111111111111;
					result_exp = data1_exp;
				}else{
					result_manti = (data1_manti >> (-result_exp)) - data2_manti;
					if(result_manti < 0){
						result_s = 1;
						result_manti = 0-result_manti;
					}else{
						result_s = 0;
					}
					result_manti = result_manti & 0b111111111111111111111111;
					result_exp = data2_exp;
				}
				GPRegister.setFReg(result_s, result_exp, result_manti, destination);
				break;
			}
			case(12):{
				//VADD
				System.out.println("vadd");
				int length = GPRegister.getReg(0);
				int temp;
				for(int i = 0; i < length; i++){
					temp = Memory.getDataFromMemory(v1+i)+Memory.getDataFromMemory(v2+i);
					System.out.println("index:"+Integer.toString(i)+" "+"vector1:"+Integer.toString(Memory.getDataFromMemory(v1+i))+" "+"vector2:"+Integer.toString(Memory.getDataFromMemory(v2+i))+" "+"Result:"+Integer.toString(temp));
					Memory.setData2Memory(temp, v1+i);
				}
				break;
			}
			case(13):{
				//VSUB
				System.out.println("vsub");
				int length = GPRegister.getReg(0);
				int temp;
				for(int i = 0; i < length; i++){
					temp = Memory.getDataFromMemory(v1+i)-Memory.getDataFromMemory(v2+i);
					Memory.setData2Memory(temp, v1+i);
				}
				break;
			}
		}
	}
	
}
