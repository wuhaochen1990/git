import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//read instruction from a file
public class ReadFile {
	// Input file path
	public static String filePath = System.getProperty("user.dir") + "/Instruction for program 2.txt";
	//string from the certain file
	public static String instruction = "";
	
	//set filePath
	public static void setfilePath(String f){
		filePath = f;
	}

	//readfile function
	public static void readfile(String path){
		//try read file from that path
				try{
					//create a input stream to read a file
					File file = new File(path);
					FileReader fileread = new FileReader(file);  
					BufferedReader br = new BufferedReader(fileread);
					//start read the file
					while((instruction=br.readLine())!=null){
						//print the instruction
						System.out.println(instruction+"\n");
						//turn the instruction string to binary and write to the memory
						Memory.setInstr2Memory(Instr2Binary(instruction));
					}
				}catch(Exception ex){
					//for exception situation
					System.out.println(ex.getMessage());  
				}
	}
	
	//turn instruction string to its opcode binary
	public static int Instr2Opcode(String instruction){
		if(instruction.equals("LDR")){
			return 1 << 10;
		}else if(instruction.equals("STR")){
			return 2 << 10;
		}else if(instruction.equals("LDA")){
			return 3 << 10;
		}else if(instruction.equals("LDX")){
			return 41 << 10;
		}else if(instruction.equals("STX")){
			return 42 << 10;
		}else if(instruction.equals("JZ")){
			return 10 << 10;
		}else if(instruction.equals("JNE")){
			return 11 << 10;
		}else if(instruction.equals("JCC")){
			return 12 << 10;
		}else if(instruction.equals("JMP")){
			return 13 << 10;
		}else if(instruction.equals("JSR")){
			return 14 << 10;
		}else if(instruction.equals("RFS")){
			return 15 << 10;
		}else if(instruction.equals("SOB")){
			return 16 << 10;
		}else if(instruction.equals("JGE")){
			return 17 << 10;
		}else if(instruction.equals("AMR")){
			return 4 << 10;
		}else if(instruction.equals("SMR")){
			return 5 << 10;
		}else if(instruction.equals("AIR")){
			return 6 << 10;
		}else if(instruction.equals("SIR")){
			return 7 << 10;
		}else if(instruction.equals("MUL")){
			return 20 << 10;
		}else if(instruction.equals("DIV")){
			return 21 << 10;
		}else if(instruction.equals("TER")){
			return 22 << 10;
		}else if(instruction.equals("AND")){
			return 23 << 10;
		}else if(instruction.equals("ORR")){
			return 24 << 10;
		}else if(instruction.equals("NOT")){
			return 25 << 10;
		}else if(instruction.equals("SRC")){
			return 31 << 10;
		}else if(instruction.equals("RRC")){
			return 32 << 10;
		}else if(instruction.equals("IN")){
			return 61 << 10;
		}else if(instruction.equals("OUT")){
			return 62 << 10;
		}else if(instruction.equals("CHK")){
			return 63 << 10;
		}else if(instruction.equals("FADD")){
			return 33 << 10;
		}else if(instruction.equals("FSUB")){
			return 34 << 10;
		}else if(instruction.equals("VADD")){
			return 35 << 10;
		}else if(instruction.equals("VSUB")){
			return 36 << 10;
		}else if(instruction.equals("CNVRT")){
			return 37 << 10;
		}else if(instruction.equals("LDFR")){
			return 8 << 10;
		}else if(instruction.equals("STFR")){
			return 9 << 10;
		}
		return 0;
	}
	//turn the rest of the instruction string into operands binary
	//first kind of instruction format
	public static int Instr2Operands1(String instruction){
		String[] splited = instruction.split(",");
		//for three operands instruction
		if(splited.length == 3){
			//extract three operands and change them to binary
			int operands1 = Integer.parseInt(splited[0])<<6;
			int operands2 = Integer.parseInt(splited[1])<<8;
			int operands3 = Integer.parseInt(splited[2]);
			//merge them into a binary
			int operands = operands1 + operands2  + operands3;
			return operands;
		}else if(splited.length == 2){
			//extract three operands and change them to binary
			int operands1 = Integer.parseInt(splited[0])<<6;
			int operands2 = Integer.parseInt(splited[1]);
			//merge them into a binary
			int operands = operands1 + operands2 ;
			return operands;
		}else if(splited.length == 1){
			int operands1 = Integer.parseInt(splited[0]);
			return operands1;
		}
		return 0;
	}
	//second kind of instruction format for mul and div
	public static int Instr2Operands2(String instruction){
		String[] splited = instruction.split(",");
		if(splited.length == 2){
			//extract three operands and change them to binary
			int operands1 = Integer.parseInt(splited[0])<<8;
			int operands2 = Integer.parseInt(splited[1])<<6;
			//merge them into a binary
			int operands = operands1 + operands2;
			return operands;
		}else if(splited.length == 1){
			int operands1 = Integer.parseInt(splited[0])<<8;
			return operands1;
		}
		return 0;
	}
	//third kind of instruction format for shift/rotate operation
	public static int Instr2Operands3(String instruction){
		String[] splited = instruction.split(",");
		int operands1 = Integer.parseInt(splited[0]) << 7;//r
		int operands2 = Integer.parseInt(splited[1]);//count
		int operands3 = Integer.parseInt(splited[2]) << 9;//L/R
		int operands4 = Integer.parseInt(splited[3]) << 6;
		int operands = operands1 + operands2 + operands3 +operands4;
		return operands;
	}
	//fourth kind of instruction format for I/O operation
	public static int Instr2Operands4(String instruction){
		String[] splited = instruction.split(",");
		int operands1 = Integer.parseInt(splited[0]) << 7;
		int operands2 = Integer.parseInt(splited[1]);
		int operands = operands1 + operands2;
	
		return operands;
	}
	//turn the whole instruction string into binary
	public static int Instr2Binary(String instruction){
		String[] splited = instruction.split(" ");
		//deal with the splited parts with Instr2Opcode and Instr2Operands and return
		//select the different kind of instr format
		int max = 25 << 10;//max of the second format
		int min = 20 << 10;//min of the second format
		int firstpart = Instr2Opcode(splited[0]);
		if(firstpart <= max & firstpart >= min ){
			//mul and div instruction have the second kind of instr format
			return Instr2Opcode(splited[0]) + Instr2Operands2(splited[1]);
		}else if(firstpart==31<<10 | firstpart==32<<10){
			//shift and rotate instruction have the third kind of instr format
			return Instr2Opcode(splited[0]) + Instr2Operands3(splited[1]);
		}else if(firstpart==61<<10 | firstpart==62<<10 | firstpart==63<<10){
			
			return Instr2Opcode(splited[0]) + Instr2Operands4(splited[1]);
		}else{
			//other instructions have the first kind of instr format
			return Instr2Opcode(splited[0]) + Instr2Operands1(splited[1]);
		}
		
	}
	//entrance of this system
//	public static void main(String arg[]){
//		//read the input file and write the instruction into memory
//		readfile(filePath);
//		
//		//first set data memory or register for test
//		Memory.setData2Memory(23, 54);
//		
//		
//		//run the instruction of that file
//		Simulator.run();
//		
//		//show memory after run the simulator
//		Memory.showMemory();
//		GPRegister.showReg();
//		X0Reg.showX0();
//		ProgramCounter.showPC();
//	}
}
