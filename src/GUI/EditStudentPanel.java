//USED AS A PANEL IN EmployeeCardFtPanel CLASS
package GUI;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import DAO.CourseDAO;
import DB.CourseDB;
import Listeners.EditStudentListener;
import UI_Setter.DesignUI;

public class EditStudentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblIDNum;
	private JLabel lblFName;
	private JLabel lblLName;
	private JLabel lblCourse;
	private JLabel lblSearch;
	private static JTextField txtIDNum;
	private static JTextField txtFName;
	private static JTextField txtLName;
	private static JTextField txtSearch;
	private static JComboBox<String> jcbCourse;
	private static JButton btnEdit;
	private EditStudentListener listen;
	
	public EditStudentPanel(){
		setLayout(null);
		setBackground(Color.WHITE);

		lblIDNum = new JLabel("ID Number", SwingConstants.RIGHT);
		txtIDNum = new JTextField(20);
		
		lblFName = new JLabel("First Name", SwingConstants.RIGHT);
		txtFName = new JTextField(30);
		
		lblLName = new JLabel("Last Name", SwingConstants.RIGHT);
		txtLName = new JTextField(30);
		
		lblCourse = new JLabel("Course", SwingConstants.RIGHT);
		fillComboBox();
		jcbCourse.setBackground(Color.WHITE);
		jcbCourse.setFocusable(false);
		
		lblSearch = new JLabel("Search ID");
		txtSearch = new JTextField(20);
		
		btnEdit = new JButton();
		DesignUI.setButtonUI(btnEdit, "Edit", '\u0000');
		
		DocumentFilter numericfilter = new DocumentFilter(){
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				fb.insertString(offset, string.replaceAll("[^\\d]", ""), attr);
			}
			
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				fb.replace(offset, length, text.replaceAll("[^\\d]", ""), attrs);
			}
		};
		
		((AbstractDocument)txtIDNum.getDocument()).setDocumentFilter(numericfilter);
		((AbstractDocument)txtSearch.getDocument()).setDocumentFilter(numericfilter);
		
		listen = new EditStudentListener();
		txtSearch.addKeyListener(listen);
		txtIDNum.addKeyListener(listen);
		btnEdit.addActionListener(listen);
		
		add(lblIDNum).setBounds(75,10,61,30);
		add(txtIDNum).setBounds(155,10,200,30);
		add(lblFName).setBounds(75,60,61,30);
		add(txtFName).setBounds(155,60,200,30);
		add(lblLName).setBounds(75,110,61,30);
		add(txtLName).setBounds(155,110,200,30);
		add(lblCourse).setBounds(75,160,61,30);
		add(jcbCourse).setBounds(155,160,200,30);
		add(lblSearch).setBounds(380,10,100,30);
		add(txtSearch).setBounds(440,10,140,30);
		add(btnEdit).setBounds(265,210,90,20);
	}
	
	public static JTextField getTxtIDNum() {
		return txtIDNum;
	}
	
	public static JTextField getTxtFName() {
		return txtFName;
	}
	
	public static JTextField getTxtLName() {
		return txtLName;
	}
	
	public static JComboBox<String> getJcbCourse() {
		return jcbCourse;
	}
	
	public static JTextField getTxtSearch() {
		return txtSearch;
	}
	
	public static JButton getBtnEdit() {
		return btnEdit;
	}
	
	public static void fillComboBox(){
		try{
			CourseDB courseDB = new CourseDB();
			CourseDAO courseDAO = new CourseDAO();
			
			courseDB.DBSetConnection();
			courseDAO.clearList();
			courseDB.storeData();
			
			Vector<String> courseList = new Vector<String>();
			
			for(String course : courseDAO.getAll())
				courseList.add(course);
				
			jcbCourse = new JComboBox<String>(courseList);
			
			courseDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public static void clear(){
		txtSearch.setText("");
		txtIDNum.setText("");
		txtFName.setText("");
		txtLName.setText("");
		jcbCourse.setSelectedItem("BSCS");
	}
	
	public static boolean checkFields() {
		return (txtIDNum.getText().equals("")||txtFName.getText().equals("")
				||txtLName.getText().equals(""))?true:false;
	}
}//end of class