
public class Printer {
	public static int active;//status of the printer
	public static StringBuffer content=new StringBuffer("");//source register of the printer
	public static int getActive() {
		return active;
	}
	public static void setActive(int active) {
		Printer.active = active;
	}
	

	
	public static StringBuffer getContent() {
		return content;
	}
	public static void setContent(char s) {
		Printer.content.append(s);
	}
	//function to activate the printer
	public static void activatePrinter(){
		active = 1;
	}
	//function to inactivate the printer
	public static void inactivatePrinter(){
		active = 0;
	}
	
	public static void runPrinter(){
		
	}
}
