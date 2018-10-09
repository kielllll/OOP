//MAIN
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import UI_Setter.DesignUI;

public class LoginFrame {
	
	private static JFrame loginFrame;
	private JPanel contentPane;
	
	public LoginFrame(){
		
		loginFrame = new JFrame();
		DesignUI.setFrameUI(loginFrame, "User Log in", 440, 330);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,0));
		contentPane.setBackground(Color.DARK_GRAY);
		loginFrame.setContentPane(contentPane);
		contentPane.add(new LoginPanel(), BorderLayout.CENTER);
		
		loginFrame.validate();
	}// end of constructor
	
	public static JFrame getLoginFrame() {
		return loginFrame;
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}//end of main
}// end of class
