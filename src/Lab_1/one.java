package Lab_1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class one {

	private JFrame frame;
	private JTextField textField;
	//JList activeList = null;
	JList activeList = null;
	JList nonActiveList = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					one window = new one();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public one() {
		initialize();
	}

	private boolean CheckList() {
		if(activeList==null) {
			JOptionPane.showMessageDialog(null, "Не выбран список для работы с ним");
			return false;
			
		}
		else return true;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultListModel<String> dList1 = new DefaultListModel<>();
		DefaultListModel<String> dList2 = new DefaultListModel<>();
		
		
		JList list_1 = new JList(dList1);
		
		
		JScrollPane scrollPane1 = new JScrollPane(list_1);
		scrollPane1.setBounds(377, 23, 105, 161);
		frame.getContentPane().add(scrollPane1);
		
		
		JList list_2 = new JList(dList2);
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				activeList = list_2;
				nonActiveList = list_1;
			}
		});
		JScrollPane scrollPane2 = new JScrollPane(list_2);
		scrollPane2.setBounds(10, 23, 105, 161);
		frame.getContentPane().add(scrollPane2);
		
		
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				activeList = list_1;
				nonActiveList = list_2;
			}
		});
		
		
		JButton btnNewButton = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u044D\u043B.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CheckList()) {
					DefaultListModel model = (DefaultListModel) activeList.getModel();
					int rand1 = (int) (Math.random() * model.size());
					
					System.out.println(rand1);
					for(int i=0; i <  rand1; i++ ) {
						model.remove((int) (Math.random() * model.size()));
					}
					for(int i=0; i< 20*Math.random(); i++) {
						model.addElement((int)(Math.random()*1000)+"");
					}
				}
			}
		});
		btnNewButton.setBounds(138, 20, 229, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u0432\u044B\u0431\u0440\u0430\u043D\u043D\u044B\u0439 \u044D\u043B.");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CheckList()) {
					
					int[] selectInd;
										
					selectInd = activeList.getSelectedIndices();
					for(int i = selectInd.length-1; i>= 0; i--) {
						//(activeList.getModel()).remove(selectInd[i]);
						DefaultListModel model = (DefaultListModel) activeList.getModel();
						 model.remove(selectInd[i]);
					}
					
				}
			}
		});
		button.setBounds(138, 54, 229, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u0412\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u044D\u043B\u0435\u043C\u0435\u043D\u0442");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CheckList()) {
					if((textField.getText().trim().length() > 0))
					((DefaultListModel<String>) activeList.getModel()).addElement(textField.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "Вы не ввели элемент для добавления в список");
 
				}
			}
		});
		button_1.setBounds(138, 126, 229, 23);
		frame.getContentPane().add(button_1);
		
		textField = new JTextField();
		textField.setBounds(135, 161, 232, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button_2 = new JButton("\u041F\u0435\u0440\u0435\u043C\u0435\u0441\u0442\u0438\u0442\u044C \u044D\u043B. \u0432 \u0434\u0440\u0443\u0433\u043E\u0439 \u043B\u0438\u0441\u0442");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CheckList()) {
					int[] copyIndex = activeList.getSelectedIndices();
					for(int i=0; i < copyIndex.length; i++) {
						((DefaultListModel<String>) nonActiveList.getModel()).addElement(((DefaultListModel<String>)activeList.getModel()).getElementAt(copyIndex[i]));
						((DefaultListModel<String>) activeList.getModel()).remove(copyIndex[i]);
					}
				}
			}
		});
		button_2.setBounds(138, 88, 229, 23);
		frame.getContentPane().add(button_2);
		
		JScrollBar scrollBar = new JScrollBar();
		list_1.add(scrollBar);
	}
}
