
public class Memory {
	
	//the max address of the data memory
	public static final int MAX_ADDRESS=9999;
	//memory content
	public static final int[] instructionMemory = new int[500];
	public static final int[] memory = new int[MAX_ADDRESS+1];
	//empty index of the memory
	public static int emptyIndex = 1;
	public static StringBuffer content = new StringBuffer("");
	
	
	
//	//show memory
//	public static void showMemory(){
//		System.out.println("This is instruction memory!");
//		//instruction memory
//		for(int i = 2; i < 50; i++){
//			//show memory until the empty one
//			if(memory[i]!=0){
//				System.out.println("Address: "+Integer.toString(i) + "    " + "Content: "+Integer.toBinaryString(memory[i]));
//			}else{
//				System.out.println("\n");
//				break;
//			}
//			
//		}
//		//data memory,after address 50
//		System.out.println("This is data memory!");
//		for (int j = 50; j < MAX_ADDRESS; j++){
//			if(memory[j]!=0){
//				System.out.println("Address: "+Integer.toString(j) + "    " +"Content: "+Integer.toString(memory[j]));
//			
//			}
//		}
//		System.out.println("\n");
//	}
//	
//	//show memory by Index
//	public static void showMemoryByIndex(int index){
//		System.out.println("Address: " + Integer.toString(index));
//		System.out.println("Content: " + Integer.toBinaryString(memory[index])+"\n");
//	}
	
	

	//set instruction to memory
	public static void setInstr2Memory(int instruction){
		//emptyIndex point to the empty position of the memory
		if(instructionMemory[emptyIndex] == 0){
			instructionMemory[emptyIndex] = instruction;
			emptyIndex += 1;
		}else{
			System.out.println("Full memory!");
		}
	}
	//get instruction from memory
	public static int getInstrFromMemory(int address){
		return instructionMemory[address];
	}
	
	//set data into memory
	public static void setData2Memory(int data, int address){
		memory[address] = data;
	}
	
	//get data from memory
	public static int getDataFromMemory(int address){
		return memory[address];
	}
	
	
	public static String setContent(){
		for(int i = 0;i < 10000; i++){
			if(Memory.getDataFromMemory(i)!=0){
				System.out.println("setContent ok!");
				content.append("Memory");
				content.append(Integer.toString(i));
				content.append(":");
				content.append(Integer.toString(Memory.getDataFromMemory(i)));
				content.append('\n');
			}
		}
		return Memory.content.toString();
		
	}
}
