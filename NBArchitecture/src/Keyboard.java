
public class Keyboard {
	public static int active;//status of the keyboard, 0 is inactive and 1 is active
	public static int r;//destination register index
	public static int[] contentBuffer = new int[50];//keyboard buffer
	public static int index = 0;//index in the buffer
	public static int interrupt=1;//interrupt
	
	
	public static int getActive() {
		return active;
	}
	public static void setActive(int active) {
		Keyboard.active = active;
	}
	public static int getR() {
		return r;
	}
	public static void setR(int r) {
		Keyboard.r = r;
	}
	
	public static int[] getContentBuffer() {
		return contentBuffer;
	}
	public static void setContentBuffer(int[] contentBuffer) {
		Keyboard.contentBuffer = contentBuffer;
	}
	//function to activate the keyboard
	public static void activateKeyboard(){
		active = 1;
	}
	//function to inactivate the keyboard
	public static void inactivateKeyboard(){
		active = 0;
	}
	
	public static int getInterrupt() {
		return interrupt;
	}
	public static void setInterrupt(int interrupt) {
		Keyboard.interrupt = interrupt;
	}
	
	public static int getIndex() {
		return index;
	}
	public static void setIndex(int index) {
		Keyboard.index = index;
	}
	//append character into buffer
	public static void append2Buffer(int character, int myindex){
		contentBuffer[myindex] = character;
	}
	//increment the index
	public static void increIndex(){
		index++;
	}
	//decrement the index
	public static void decreIndex(){
		index--;
	}
	//change the buffer into a number
	public static int buffer2Number(){
		double sum = 0;
		int indexcopy = index;
		for(int i=0;i<index;i++){
			sum = sum + (contentBuffer[i]-48) * Math.pow(10, indexcopy-1);
			indexcopy = indexcopy - 1;
		}
		return (int)sum;
		
	}
	//clear the buffer
	public static void clearBuffer(){
		for(int i=0;i<index;i++){
			contentBuffer[i]=0;
		}
	}
	//reset the index
	public static void resetIndex(){
		index = 0;
	}
	public static int hasChar(){
		for(int i=0;i<index;i++){
			if(contentBuffer[i]<48 | contentBuffer[i]>57){
				return 1;
			}
		}
		return 0;
	};//whether the buffer has char
	//run the keyboard when pressed the 'Enter'
	public static void runKeyboard(){
		GPRegister.setReg(contentBuffer[index], r);
	}
}
