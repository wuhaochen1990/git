
public class IO {
	public static int r;
	public static int devid;
	public static int operation;//in 0, out 1, chk 2
	
	public static int getR() {
		return r;
	}
	public static void setR(int r) {
		IO.r = r;
	}
	public static int getDevid() {
		return devid;
	}
	public static void setDevid(int devid) {
		IO.devid = devid;
	}
	public static int getOperation() {
		return operation;
	}
	public static void setOperation(int operation) {
		IO.operation = operation;
	}
	
	
	public static void runIO(){
		switch(operation){
		case(0):{
			//IN 
			switch(devid){
			case(0):{
				//keyboard
				Keyboard.setInterrupt(0);//set the interrupt
				Keyboard.setR(r);
				Keyboard.activateKeyboard();
				break;
			}
			case(2):{
				//card reader
				break;
			}
			}
			break;
		}
		case(1):{
			//OUT
			if(devid == 1){
				//OUT only works on console printer whose devid is 1
				Printer.activatePrinter();//activate the printer
				//content to print
				int asc = GPRegister.getReg(r);
				System.out.println(asc);
//				if(asc<=57 & asc>=48){
//					Printer.setContent(Integer.to);
//				}
				switch(asc){
				
				case(10):{
					Printer.setContent('\n');
					break;
				}
				case(48):{
					Printer.setContent('0');
					break;
				}
				case(49):{
					Printer.setContent('1');
					break;
				}
				case(50):{
					Printer.setContent('2');
					break;
				}
				case(51):{
					Printer.setContent('3');
					break;
				}
				case(52):{
					Printer.setContent('4');
					break;
				}
				case(53):{
					Printer.setContent('5');
					break;
				}
				case(54):{
					Printer.setContent('6');
					break;
				}
				case(55):{
					Printer.setContent('7');
					break;
				}
				case(56):{
					Printer.setContent('8');
					break;
				}
				case(57):{
					Printer.setContent('9');
					break;
				}
				case(97):{
					Printer.setContent('a');
					break;
				}
				case(98):{
					Printer.setContent('b');
					break;
				}
				case(99):{
					Printer.setContent('c');
					break;
				}
				case(100):{
					Printer.setContent('d');
					break;
				}
				case(101):{
					Printer.setContent('e');
					break;
				}
				case(102):{
					Printer.setContent('f');
					break;
				}
				case(103):{
					Printer.setContent('g');
					break;
				}
				case(104):{
					Printer.setContent('h');
					break;
				}
				case(105):{
					Printer.setContent('i');
					break;
				}
				case(106):{
					Printer.setContent('j');
					break;
				}
				case(107):{
					Printer.setContent('k');
					break;
				}
				case(108):{
					Printer.setContent('l');
					break;
				}
				case(109):{
					Printer.setContent('m');
					break;
				}
				case(110):{
					Printer.setContent('n');
					break;
				}
				case(111):{
					Printer.setContent('o');
					break;
				}
				case(112):{
					Printer.setContent('p');
					break;
				}
				case(113):{
					Printer.setContent('q');
					break;
				}
				case(114):{
					Printer.setContent('r');
					break;
				}
				case(115):{
					Printer.setContent('s');
					break;
				}
				case(116):{
					Printer.setContent('t');
					break;
				}
				case(117):{
					Printer.setContent('u');
					break;
				}
				case(118):{
					Printer.setContent('v');
					break;
				}
				case(119):{
					Printer.setContent('w');
					break;
				}
				case(120):{
					Printer.setContent('x');
					break;
				}
				case(121):{
					Printer.setContent('y');
					break;
				}
				case(122):{
					Printer.setContent('z');
					break;
				}
				}
			}
			break;
		}
		case(2):{
			//CHK
			switch(devid){
			case(0):{
				//keyboard
				int status = Keyboard.active;
				System.out.println("keyboard active:"+Integer.toString(status));
				GPRegister.setReg(status, r);
				break;
			}
			case(1):{
				//printer
				int status = Printer.active;
				GPRegister.setReg(status, r);
				break;
			}
			case(2):{
				//card reader
				
				break;
			}
			}
			break;
		}
		}
	}
}
