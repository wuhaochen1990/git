
public class ProgramCounter {
	public static int pc = 1;
	//get the program counter
	public static int getPC(){
		return pc;
	}
	//set the program counter
	public static void setPC(int newpc){
		pc = newpc;
	}
	//increment of pc
	public static void incrementPC(){
		pc += 1;
	}
	//decrement of pc
	public static void decrementPC(){
		pc -= 1 ;
	}
//	//show pc value
//	public static void showPC(){
//		System.out.println("PC: " + Integer.toString(pc));
//	}
}
