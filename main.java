package d1008;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class main extends Frame implements ActionListener{
	private TextField tf = new TextField();
	private Button bt[] = new Button[16];
	private String str[] = new String[]{"7","8","9","+",	
					    "4","5","6","-",
					    "1","2","3","*",
					    "0","=","%","/"};
	
	private BorderLayout bl = new BorderLayout();
	private Panel p = new Panel();
	private GridLayout gl = new GridLayout(4,4,5,5);

	private String result = "";
	private String res1 = "";
	private char yun = '\0';
	private int res2 = 0;
	
	public void init(){
		this.setLayout(bl);
		tf.setEditable(false);
		tf.setFont(new Font("", Font.BOLD, 20));
		this.add("North", tf);
		this.add("Center", p);
		p.setLayout(gl);
		for(int i=0; i<16; ++i){
			bt[i] = new Button(str[i]);
			p.add(bt[i]);
		}
	}
	public void start(){
		for(int i=0; i<16; ++i){
			bt[i].addActionListener(this);
		}
		WindowAdapter wa = new WindowAdapter(){
			public void windowClosing(WindowEvent  e){
				System.exit(0);
			}
		};
		this.addWindowListener(wa);
	}
	main(String title){
		super(title);
		this.init();
		this.start();
		super.setSize(300,200);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2 - super.getHeight()/2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt[3]){
			yun = '+';
			yunButton();
			return;
		}else if(e.getSource()==bt[7]){
			yun = '-';
			yunButton();
			return;
		}else if(e.getSource()==bt[11]){
			yun = '*';
			yunButton();
			return;
		}else if(e.getSource()==bt[13]){
			calculation();
		}else if(e.getSource()==bt[14]){
			yun = '%';
			yunButton();
			return;
		}else if(e.getSource()==bt[15]){
			yun = '/';
			yunButton();
			return;
		}else {
			for(int i=0; i<16; ++i){
				if (e.getSource()==bt[i]){
					if (res2 == -1) {
						res2 = 0;
						result = "";
					}
					result += str[i];
				}
			}
		}
		tf.setText(result);
	}
	public void yunButton(){
		if (res1 == ""){
			res1 = result;
			result = "";
		}
	}
	public void calculation(){
		switch(yun){
		case '+' : res2 = Integer.parseInt(res1) + Integer.parseInt(result); break;  
		case '-' : res2 = Integer.parseInt(res1) - Integer.parseInt(result); break;
		case '*' : res2 = Integer.parseInt(res1) * Integer.parseInt(result); break;
		case '/' : res2 = Integer.parseInt(res1) / Integer.parseInt(result); break;
		case '%' : res2 = Integer.parseInt(res1) % Integer.parseInt(result); break;
		}
		result = String.valueOf(res2);
		res1 = "";
		res2 = -1;
	}
}