import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JScrollPane;


public class Entry extends JFrame {

	//Jpanels
	private JPanel contentPane;
	private JPanel panel_filepath;
	private JPanel panel_reg;
	private JPanel panel_result ;
	private JPanel panel_memory;
	private JPanel panel_showreg;
	private JPanel panel_showmemoy;
	//JButtons
	private JButton run;
	private JButton set_reg;
	private JButton set_memory;
	//filepath is present working directory plus Instruction.txt
	public static String filePath = System.getProperty("user.dir") + "/Instruction.txt";
	public static String showresult = "";
	private JLabel address;
	private JLabel content;
	private JLabel showresultlabel;
	private JLabel indexlabel;
	private JLabel regcontentlabel;
	private JLabel memorylabel;
	private JLabel memorycontentlabel;
	//JTextField
	private JTextField filepath;
	private JTextField addressTextField;
	private JTextField contentTextField;
	private JTextField indexTextField;
	
	private JTextField showregcontentTextField;
	private JButton show_reg;
	
	
	private JTextField showmemoryaddressTextField;
	private JButton show_memory;
	
	private JTextField showmemorycontentTextField;
	private JPanel panel_showpcx0;
	private JButton show_X0Reg;
	private JTextField showX0RegTextField;
	private JButton showPC;
	private JTextField showPCTextField;
	private JLabel registerindexlabel;
	private JTextField setRegIndexTextField;
	private JLabel setregcontentlabel;
	private JTextField setRegContentTextField;
	private JPanel keyboardPanel1;
	private JLabel keyboardLabel;
	private JButton keyboardButton;
	private JPanel showcachepanel;
	private JLabel cacheaddresslabel;
	private JTextField cacheIndexTextField;
	private JButton showCacheButton;
	private JLabel cachecontentlabel;
	private JTextField cacheContentTextField;
	private JButton NO1Button;
	private JButton NO2Button;
	private JButton NO3Button;
	private JButton NO0Button;
	private JButton NO4Button;
	private JPanel keyboardPanel2;
	private JButton NO5Button;
	private JButton NO6Button;
	private JButton NO7Button;
	private JButton NO8Button;
	private JButton NO9Button;
	private JButton a_Button;
	private JButton b_Button;
	private JPanel keyboardPanel3;
	private JButton c_Button;
	private JButton d_Button;
	private JButton e_Button;
	private JButton f_Button;
	private JButton g_Button;
	private JButton h_Button;
	private JButton i_Button;
	private JPanel KeyboardPanel4;
	private JButton j_Button;
	private JButton k_Button;
	private JButton l_Button;
	private JButton m_Button;
	private JButton n_Button;
	private JButton o_Button;
	private JButton p_Button;
	private JPanel KeyboardPanel5;
	private JButton q_Button;
	private JButton r_Button;
	private JButton s_Button;
	private JButton t_Button;
	private JButton u_Button;
	private JButton v_Button;
	private JButton w_Button;
	private JPanel KeyboardPanel6;
	private JButton x_Button;
	private JButton y_Button;
	private JButton z_Button;
	private JScrollPane scrollPane;
	private JTextArea printer;
	private JScrollPane scrollPane_1;
	private JTextArea memoryTextArea;
	private JButton showallmemoryButton;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entry frame = new Entry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Entry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_filepath = new JPanel();
		contentPane.add(panel_filepath);
		panel_filepath.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("filepath");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_filepath.add(lblNewLabel);
		
		filepath = new JTextField(filePath);
		panel_filepath.add(filepath);
		filepath.setColumns(33);
		
		run = new JButton("run");
		panel_filepath.add(run);
		//run listen to the run of the  program
		run.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ReadFile.readfile(filepath.getText().toString());
				System.out.println("ok,run!");
				
				Simulator.run();
				
				//console printer
				if(Printer.active == 1){
					
					printer.setText(Printer.content.toString());
					
				}
						
			}
					
		});
		
		panel_reg = new JPanel();
		contentPane.add(panel_reg);
		panel_reg.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		registerindexlabel = new JLabel("register index");
		panel_reg.add(registerindexlabel);
		
		setRegIndexTextField = new JTextField();
		panel_reg.add(setRegIndexTextField);
		setRegIndexTextField.setColumns(10);
		
		setregcontentlabel = new JLabel("content");
		panel_reg.add(setregcontentlabel);
		
		setRegContentTextField = new JTextField();
		panel_reg.add(setRegContentTextField);
		setRegContentTextField.setColumns(10);
		
		set_reg = new JButton("set reg");
		panel_reg.add(set_reg);
		//set_reg listen to the change of the reg
		set_reg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(setRegIndexTextField.getText());
				int content = Integer.parseInt(setRegContentTextField.getText());
				GPRegister.setReg(content, index);
				
				}	
				});
		
		panel_memory = new JPanel();
		contentPane.add(panel_memory);
		panel_memory.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		address = new JLabel("address");
		panel_memory.add(address);
		
		addressTextField = new JTextField();
		panel_memory.add(addressTextField);
		addressTextField.setColumns(10);
		
		content = new JLabel("content");
		panel_memory.add(content);
		
		contentTextField = new JTextField();
		panel_memory.add(contentTextField);
		contentTextField.setColumns(10);
		
		set_memory = new JButton("set memory");
		panel_memory.add(set_memory);
		//set_memory listen to the change of the memory
		set_memory.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			if((addressTextField.getText()!=null) &&(contentTextField.getText()!=null)){
				Memory.setData2Memory(Integer.parseInt(contentTextField.getText().toString()), Integer.parseInt(addressTextField.getText().toString()));
			}
						
			}
					
		});
		
		panel_result = new JPanel();
		contentPane.add(panel_result);
		
		showresultlabel = new JLabel("show result");
		showresultlabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		panel_result.add(showresultlabel);
		
		panel_showreg = new JPanel();
		contentPane.add(panel_showreg);
		
		indexlabel = new JLabel("register index");
		panel_showreg.add(indexlabel);
		
		indexTextField = new JTextField();
		panel_showreg.add(indexTextField);
		indexTextField.setColumns(10);
		
		show_reg = new JButton("show register");
		panel_showreg.add(show_reg);
		//listen
		show_reg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int regindex = Integer.parseInt(indexTextField.getText().toString());
				showregcontentTextField.setText(Integer.toString(GPRegister.getReg(regindex)));
			}
			
		});
		
		regcontentlabel = new JLabel("content");
		panel_showreg.add(regcontentlabel);
		
		showregcontentTextField = new JTextField();
		panel_showreg.add(showregcontentTextField);
		showregcontentTextField.setColumns(10);
		
		panel_showmemoy = new JPanel();
		contentPane.add(panel_showmemoy);
		
		memorylabel = new JLabel("memory address");
		panel_showmemoy.add(memorylabel);
		
		showmemoryaddressTextField = new JTextField();
		panel_showmemoy.add(showmemoryaddressTextField);
		showmemoryaddressTextField.setColumns(10);
		
		show_memory = new JButton("show memory");
		panel_showmemoy.add(show_memory);
		//listen
		show_memory.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("show ok");
				int memoryaddress = Integer.parseInt(showmemoryaddressTextField.getText().toString());
				showmemorycontentTextField.setText(Integer.toBinaryString(Memory.getDataFromMemory(memoryaddress)));
				System.out.println("show ok");
//				memoryTextArea.setText(Memory.setContent());
				
			}
			
		});
		
		memorycontentlabel = new JLabel("content");
		panel_showmemoy.add(memorycontentlabel);
		
		showmemorycontentTextField = new JTextField();
		panel_showmemoy.add(showmemorycontentTextField);
		showmemorycontentTextField.setColumns(11);
		
		panel_showpcx0 = new JPanel();
		contentPane.add(panel_showpcx0);
		
		show_X0Reg = new JButton("show X0");
		show_X0Reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showX0RegTextField.setText(Integer.toString(X0Reg.getX0()));
			}
		});
		panel_showpcx0.add(show_X0Reg);
		
		showX0RegTextField = new JTextField();
		panel_showpcx0.add(showX0RegTextField);
		showX0RegTextField.setColumns(10);
		
		showPC = new JButton("show pc");
		showPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPCTextField.setText(Integer.toString(ProgramCounter.getPC()));
			}
		});
		panel_showpcx0.add(showPC);
		
		showPCTextField = new JTextField();
		panel_showpcx0.add(showPCTextField);
		showPCTextField.setColumns(10);
		
		keyboardPanel1 = new JPanel();
		contentPane.add(keyboardPanel1);
		
		keyboardLabel = new JLabel("keyboard");
		keyboardPanel1.add(keyboardLabel);
		
		keyboardButton = new JButton("Enter");
		keyboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Keyboard.append2Buffer(10, Keyboard.index);
				Keyboard.runKeyboard();
				//inactivate the keyboard
				Keyboard.inactivateKeyboard();
				//end the interrupt
				Keyboard.interrupt=1;
				//rerun the buffer
				System.out.println("buffer2number:"+Integer.toString(Keyboard.buffer2Number()));
				if(Keyboard.hasChar()==1){
					//input has char
					
				}else{
					//input is a number stored in memory(50)
					Memory.setData2Memory(Keyboard.buffer2Number(), 50);
				}
				Keyboard.clearBuffer();
				Keyboard.resetIndex();
				Simulator.run();
				
				//console printer
				if(Printer.active == 1){
					System.out.println("printer active!");
					printer.setText(Printer.content.toString());
				}
				
			}
		});
		
		NO0Button = new JButton("0");
		NO0Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Keyboard.active == 1){
					//append 48 into buffer 
					Keyboard.append2Buffer(48, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					//increase the index
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
					
				}
			}
		});
		keyboardPanel1.add(NO0Button);
		
		NO1Button = new JButton("1");
		NO1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Keyboard.active == 1){
					//append 49 into buffer
					Keyboard.append2Buffer(49, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					//increase the index
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel1.add(NO1Button);
		
		NO2Button = new JButton("2");
		NO2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					//append 50 into buffer
					Keyboard.append2Buffer(50, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					//increase the index
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
				
			}
		});
		keyboardPanel1.add(NO2Button);
		
		NO3Button = new JButton("3");
		NO3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					//append 51 into buffer
					Keyboard.append2Buffer(51, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					///increase the index
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel1.add(NO3Button);
		
		NO4Button = new JButton("4");
		NO4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					//apend 4 into buffer
					Keyboard.append2Buffer(52, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					//increase the index
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel1.add(NO4Button);
		keyboardPanel1.add(keyboardButton);
		
		keyboardPanel2 = new JPanel();
		contentPane.add(keyboardPanel2);
		
		NO5Button = new JButton("5");
		NO5Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(53, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel2.add(NO5Button);
		
		NO6Button = new JButton("6");
		NO6Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(54, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel2.add(NO6Button);
		
		NO7Button = new JButton("7");
		NO7Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(55, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel2.add(NO7Button);
		
		NO8Button = new JButton("8");
		NO8Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(56, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel2.add(NO8Button);
		
		NO9Button = new JButton("9");
		NO9Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(57, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel2.add(NO9Button);
		
		a_Button = new JButton("a");
		a_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(97, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel2.add(a_Button);
		
		b_Button = new JButton("b");
		b_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(98, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel2.add(b_Button);
		
		keyboardPanel3 = new JPanel();
		contentPane.add(keyboardPanel3);
		
		c_Button = new JButton("c");
		c_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(99, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel3.add(c_Button);
		
		d_Button = new JButton("d");
		d_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(100, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel3.add(d_Button);
		
		e_Button = new JButton("e");
		e_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(101, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel3.add(e_Button);
		
		f_Button = new JButton("f");
		f_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(102, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel3.add(f_Button);
		
		g_Button = new JButton("g");
		g_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(103, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel3.add(g_Button);
		
		h_Button = new JButton("h");
		h_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(104, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel3.add(h_Button);
		
		i_Button = new JButton("i");
		i_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(105, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		keyboardPanel3.add(i_Button);
		
		KeyboardPanel4 = new JPanel();
		contentPane.add(KeyboardPanel4);
		
		j_Button = new JButton("j");
		j_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(106, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel4.add(j_Button);
		
		k_Button = new JButton("k");
		k_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(107, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel4.add(k_Button);
		
		l_Button = new JButton("l");
		l_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(108, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel4.add(l_Button);
		
		m_Button = new JButton("m");
		m_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(109, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel4.add(m_Button);
		
		n_Button = new JButton("n");
		n_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(110, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel4.add(n_Button);
		
		o_Button = new JButton("o");
		o_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(111, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel4.add(o_Button);
		
		p_Button = new JButton("p");
		p_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(112, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel4.add(p_Button);
		
		KeyboardPanel5 = new JPanel();
		contentPane.add(KeyboardPanel5);
		
		q_Button = new JButton("q");
		q_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(113, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel5.add(q_Button);
		
		r_Button = new JButton("r");
		r_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(114, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel5.add(r_Button);
		
		s_Button = new JButton("s");
		s_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(115, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel5.add(s_Button);
		
		t_Button = new JButton("t");
		t_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(116, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel5.add(t_Button);
		
		u_Button = new JButton("u");
		u_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(117, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel5.add(u_Button);
		
		v_Button = new JButton("v");
		v_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(118, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel5.add(v_Button);
		
		w_Button = new JButton("w");
		w_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(119, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel5.add(w_Button);
		
		KeyboardPanel6 = new JPanel();
		contentPane.add(KeyboardPanel6);
		
		x_Button = new JButton("x");
		x_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(120, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel6.add(x_Button);
		
		y_Button = new JButton("y");
		y_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(121, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel6.add(y_Button);
		
		z_Button = new JButton("z");
		z_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.append2Buffer(122, Keyboard.index);
					//run the keyboard
					Keyboard.runKeyboard();
					Keyboard.increIndex();
					//inactivate the keyboard
					Keyboard.inactivateKeyboard();
					//end the interrupt
					Keyboard.interrupt=1;
					Simulator.run();
					
					//console printer
					if(Printer.active == 1){
						System.out.println("printer active!");
						printer.setText(Printer.content.toString());
					}
				}
			}
		});
		KeyboardPanel6.add(z_Button);
		
		showallmemoryButton = new JButton("show data memory");
		showallmemoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memoryTextArea.setText(Memory.setContent());
			}
		});
		KeyboardPanel6.add(showallmemoryButton);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		printer = new JTextArea();
		printer.setEditable(false);
		printer.setRows(5);
		printer.setColumns(30);
		scrollPane.setViewportView(printer);
		
		scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1);
		
		memoryTextArea = new JTextArea();
		memoryTextArea.setRows(5);
		memoryTextArea.setColumns(13);
		scrollPane_1.setViewportView(memoryTextArea);
		
		showcachepanel = new JPanel();
		contentPane.add(showcachepanel);
		
		cacheaddresslabel = new JLabel("cache index");
		showcachepanel.add(cacheaddresslabel);
		
		cacheIndexTextField = new JTextField();
		showcachepanel.add(cacheIndexTextField);
		cacheIndexTextField.setColumns(10);
		
		showCacheButton = new JButton("show cache");
		showCacheButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(cacheIndexTextField.getText().toString());
				cacheContentTextField.setText(Integer.toString(Cache.cache_content[index]));
			}
		});
		showcachepanel.add(showCacheButton);
		
		cachecontentlabel = new JLabel("cache content");
		showcachepanel.add(cachecontentlabel);
		
		cacheContentTextField = new JTextField();
		showcachepanel.add(cacheContentTextField);
		cacheContentTextField.setColumns(10);
		
		
		
		
		
	}
}
