//USED AS A PANEL IN EmployeeTblPanel CLASS
package GUI;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import DAO.StudentDAO;
import DB.StudentDB;
import Listeners.EmployeeTblPanelListener;
import Objects.Students;

public class EmpTblStudentsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblList;
	private static JTable tblStudent;
	private static DefaultTableModel table;
	private static JScrollPane scroll;
	private static String col[] = {"ID #","First Name","Last Name","Course"};
	private EmployeeTblPanelListener listen;
	
	public EmpTblStudentsPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(0,2,0,0,Color.BLACK));
		
		lblList = new JLabel("List of Students");
		
		tblStudent = new JTable();
		tblStudent.setShowGrid(false);
		tblStudent.setFocusable(false);
		
		scroll = new JScrollPane();
		scroll.getViewport().setBackground(Color.WHITE);
		
		fillTable();
		
		listen = new EmployeeTblPanelListener();
		tblStudent.addMouseListener(listen);
		
		add(lblList).setBounds(10,3,100,30);
		add(scroll).setBounds(10,30,375,260);
	}
	
	public static JTable getTblStudent() {
		return tblStudent;
	}
	
	public static DefaultTableModel getTable() {
		return table;
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
			
			table.setColumnIdentifiers(col);
			
			StudentDB studentDB = new StudentDB();
			StudentDAO studentDAO = new StudentDAO();
			
			studentDB.DBSetConnection();
			studentDAO.clearList();
			studentDB.storeData();
			
			for(Students s : studentDAO.getAll()){
				table.addRow(new Object[]{
						s.getId(),s.getfName(),s.getlName(),s.getCourse()
				});
			}
			
			tblStudent.setModel(table);
			scroll.setViewportView(tblStudent);
			scroll.setVisible(true);
			studentDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
}//end of class
