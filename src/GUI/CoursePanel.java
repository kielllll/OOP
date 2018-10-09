//USED AS A PANEL IN AddStudentPanel AND EditStudentPanel CLASS
package GUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import DAO.CourseDAO;
import DB.CourseDB;
import Listeners.CourseListener;
import UI_Setter.DesignUI;

public class CoursePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel addPanel;
	private JPanel editPanel;
	private static JPanel cardPanel;
	private static CardLayout card;
	private JLabel lblAddCourse;
	private JLabel lblEditCourse;
	private JLabel lblSearch;
	private JLabel lblOption;
	private static JTextField txtAddCourse;
	private static JTextField txtEditCourse;
	private static JTextField txtSearch;
	private static JTable tblCourse;
	private static DefaultTableModel table;
	private static JScrollPane scroll;
	private static JComboBox<String> jcbView;
	private static JButton btnAdd;
	private static JButton btnEdit;
	private static String option[] = {"Add","Edit"};
	private CourseListener listen;
	
	public CoursePanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(0,2,0,0,Color.BLACK));
		
		addPanel = new JPanel();
		addPanel.setLayout(null);
		addPanel.setBackground(Color.WHITE);
		
		lblAddCourse = new JLabel("Course");
		txtAddCourse = new JTextField(20);
		
		btnAdd = new JButton();
		DesignUI.setButtonUI(btnAdd, "Add", '\u0000');
		
		addPanel.add(lblAddCourse).setBounds(10,0,50,30);
		addPanel.add(txtAddCourse).setBounds(70,0,90,30);
		addPanel.add(btnAdd).setBounds(70,50,90,30);
		
		editPanel = new JPanel();
		editPanel.setLayout(null);
		editPanel.setBackground(Color.WHITE);
		
		lblSearch = new JLabel("Search");
		txtSearch = new JTextField(20);
		
		lblEditCourse = new JLabel("Course");
		txtEditCourse = new JTextField(20);
		
		btnEdit = new JButton();
		DesignUI.setButtonUI(btnEdit, "Edit", '\u0000');
		
		editPanel.add(lblSearch).setBounds(10,0,50,30);
		editPanel.add(txtSearch).setBounds(70,0,90,30);
		editPanel.add(lblEditCourse).setBounds(10,50,50,30);
		editPanel.add(txtEditCourse).setBounds(70,50,90,30);
		editPanel.add(btnEdit).setBounds(70,100,90,30);
		
		lblOption = new JLabel("Select");
		
		jcbView = new JComboBox<String>(option);
		jcbView.setBackground(Color.WHITE);
		jcbView.setFocusable(false);
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		cardPanel.setBackground(Color.WHITE);
		
		cardPanel.add(addPanel,"1");
		cardPanel.add(editPanel,"2");
		
		tblCourse = new JTable();
		tblCourse.setShowGrid(false);
		tblCourse.setFocusable(false);
		
		scroll = new JScrollPane();
		scroll.getViewport().setBackground(Color.WHITE);
		
		fillTable();
		
		listen = new CourseListener();
		tblCourse.addMouseListener(listen);
		jcbView.addActionListener(listen);
		txtSearch.addKeyListener(listen);
		btnAdd.addActionListener(listen);
		btnEdit.addActionListener(listen);
		
		add(lblOption).setBounds(20,10,50,30);
		add(jcbView).setBounds(80,10,90,30);
		add(cardPanel).setBounds(10,60,170,150);
		add(scroll).setBounds(220,10,150,200);
	}
	
	public static JComboBox<String> getJcbView() {
		return jcbView;
	}
	
	public static JButton getBtnAdd() {
		return btnAdd;
	}
	
	public static JButton getBtnEdit() {
		return btnEdit;
	}
	
	public static JTextField getTxtAddCourse() {
		return txtAddCourse;
	}
	
	public static JTextField getTxtEditCourse() {
		return txtEditCourse;
	}
	
	public static JTextField getTxtSearch() {
		return txtSearch;
	}
	
	public static JTable getTblCourse() {
		return tblCourse;
	}
	
	public static DefaultTableModel getTable() {
		return table;
	}
	
	public static CardLayout getCard() {
		return card;
	}
	
	public static JPanel getCardPanel() {
		return cardPanel;
	}
	
	public static void fillTable(){
		try{
			table = new DefaultTableModel(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int x, int y) {
					return false;
				}
			};
			
			table.setColumnIdentifiers(new Object[]{
					"Course Name"
			});
			
			CourseDB courseDB = new CourseDB();
			CourseDAO courseDAO = new CourseDAO();
			
			courseDB.DBSetConnection();
			courseDAO.clearList();
			courseDB.storeData();
			
			for(String course : courseDAO.getAll()){
				table.addRow(new Object[]{
						course
				});
			}
			
			tblCourse.setModel(table);
			scroll.setViewportView(tblCourse);
			scroll.setVisible(true);
			courseDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
}//end of class
