package GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Listeners.AdminOnlyFrameListener;
import UI_Setter.DesignUI;

public class AdminOnlyFrame {
	
	private static JFrame adminFrame;
	private AdminOnlyFrameListener listen;
	private JPanel contentPanel;
	private JPanel panel;

	public AdminOnlyFrame() {
		adminFrame = new JFrame();
		DesignUI.setFrameUI(adminFrame,"UM-CpE Laboratory Inventory Management System",1200,680);
		adminFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(new AdminBtnPanel()).setBounds(0, 0, 300, 680);
		panel.add(new AdminHeaderPanel()).setBounds(300, 0, 900, 60);
		panel.add(new AdminCardPanel()).setBounds(300, 60, 900, 620);
		
		listen = new AdminOnlyFrameListener();
		adminFrame.addWindowListener(listen);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout(0,0));
		contentPanel.setBackground(Color.DARK_GRAY);
		adminFrame.setContentPane(contentPanel);
		contentPanel.add(panel, BorderLayout.CENTER);
		adminFrame.validate();
	}// end of constructor

	public static JFrame getMainFrame() {
		return adminFrame;
	}
	
}// end of class
