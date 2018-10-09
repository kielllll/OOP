package UI_Setter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DesignUI {
	public static void setButtonUI(JButton btn,String text,char c) {
		btn.setText(text);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(true);
		btn.setBackground(Color.WHITE);
		btn.setForeground(Color.BLACK);
		btn.setFont(new Font("Segoe UI Semilight",Font.PLAIN,13));
		btn.setMnemonic(c);
	}
	
	public static void setFrameUI(JFrame frm,String title,int width,int height) {
		frm.setTitle(title);
		frm.setSize(width,height);
		frm.setResizable(false);
		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setIconImage(Toolkit.getDefaultToolkit().getImage("assets\\images\\umIcon32.png"));
		frm.setVisible(true);
	}
}// end of class
