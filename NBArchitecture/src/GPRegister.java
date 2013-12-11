
public class GPRegister {
	//4 general purpose registers
	public static int Reg[] = new int[4];
	//2 floating point registers
	public static int FReg[] = new int[2];
	
	
	//set the register
	public static void setReg(int data, int index){
		Reg[index] = data;
	}
	//get the register
	public static int getReg(int index){
		return Reg[index];
	}
	//set the freg
	public static void setFReg(int s, int exp, int manti, int index){
		FReg[index] = (s << 31) + (exp << 24) + manti;
		System.out.println(Integer.toBinaryString(FReg[index]));
	}
	//get the freg
	public static int getFReg(int index){
		return FReg[index];
	}
//	public static void intsetFReg(int i,int index){
//		System.out.println("intset ok");
//		FReg[index]=i;
//	}
//	//show the whole four register
//	public static void showReg(){
//		System.out.println("Register0:"+Integer.toString(Reg[0]));
//		System.out.println("Register1:"+Integer.toString(Reg[1]));
//		System.out.println("Register2:"+Integer.toString(Reg[2]));
//		System.out.println("Register3:"+Integer.toString(Reg[3]));
//	}
//	//show the register by index
//	public static void showRegByIndex(int index){
//		System.out.println(Reg[index]);
//	}
}
