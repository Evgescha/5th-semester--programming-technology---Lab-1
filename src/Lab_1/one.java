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
	// переменная, содержащая само окно приложения
	private JFrame frame;
	// текстовое поле, в которое вводим какой элемент добавляем
	private JTextField textField;
	// создаем переменную, хранящую активный лист
	JList activeList = null;
	// создаем перемнную, хранящую не активный лист
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
	// метод проверки списка
	private boolean CheckList() {
		// если не щелкнули ни на один список
		if(activeList==null) {
			// выводим сообщение об ошибке
			
			JOptionPane.showMessageDialog(null, "Не выбран список для работы с ним");
			//  и говорим что неудачная попытка действия
			return false;
			
		}
		else return true;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// приваиваем переменной окна новое окно (или просто создаем новое окно)
		frame = new JFrame();
		// указываем его размеры
		frame.setBounds(100, 100, 500, 300);
		// говорим, ри нажати на крестик полностью закрывать программу
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// говорим, что будем использовать абсолютное позициониование(явно указывать координаты кнопок и тд)
		frame.getContentPane().setLayout(null);
		
		// список элементов первого листа
		DefaultListModel<String> dList1 = new DefaultListModel<>();
		// список элементов второго листа
		DefaultListModel<String> dList2 = new DefaultListModel<>();
		
		// первый список
		JList list_1 = new JList(dList1);
		
		// присваиваем первому списку скролл (знак прокрутки)
		JScrollPane scrollPane1 = new JScrollPane(list_1);
		// указываем размеры и координаты списка
		scrollPane1.setBounds(377, 23, 105, 161);
		// добавляем на экран
		frame.getContentPane().add(scrollPane1);
		
		// второй список
		JList list_2 = new JList(dList2);
		// действие при нажатии на список
		list_2.addMouseListener(new MouseAdapter() {
			// переопределение метода
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// при нажатии кнопкой мыши
				// активным списком делаем лист 2 (нажали на второй лист)
				activeList = list_2;
				// не активным делаем список 1
				nonActiveList = list_1;
			}
		});
		// добавляем второму листу скролл
		JScrollPane scrollPane2 = new JScrollPane(list_2);
		// устанавливаем координаты и разммеры
		scrollPane2.setBounds(10, 23, 105, 161);
		// добавляем на окно
		frame.getContentPane().add(scrollPane2);
		
		// при нажатии на первый список
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// активным списком делаем первым
				activeList = list_1;
				// не активным списком делаем второй
				nonActiveList = list_2;
			}
		});
		
		// создаем кнопку "Изменить количество элементов"
		JButton btnNewButton = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u044D\u043B.");
		// обработчик нажатия
		btnNewButton.addActionListener(new ActionListener() {
			// если нажата кнопка
			public void actionPerformed(ActionEvent arg0) {
				// проверяем, вернет ли метод ChechList() true
				if(CheckList()) {
					// если вернет, создаем новую модель списка (присваиваем переменной список элементов, находящихся в активном списке)
					DefaultListModel model = (DefaultListModel) activeList.getModel();
					int rand1 = (int) (Math.random() * model.size());
					// выводим в коноль, какое рандомное число полуилось
					System.out.println(rand1);
					// удаляем это число элементов
					for(int i=0; i <  rand1; i++ ) {
						model.remove((int) (Math.random() * model.size()));
					}
					// добавляем рандомное число элементов(до 20)
					for(int i=0; i< 20*Math.random(); i++) {
						model.addElement((int)(Math.random()*1000)+"");
					}
				}
			}
		});
		btnNewButton.setBounds(138, 20, 229, 23);
		frame.getContentPane().add(btnNewButton);
		
		// кнопка удаления
		JButton button = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u0432\u044B\u0431\u0440\u0430\u043D\u043D\u044B\u0439 \u044D\u043B.");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// проверяем на то, выбран ли лист
				if(CheckList()) {
					// создаем массив выбранных элементов (ид выбранных элементов)
					int[] selectInd;
					// получаем ид выбранных элементов					
					selectInd = activeList.getSelectedIndices();
					// удаляем элементы с выбранными ид 
					for(int i = selectInd.length-1; i>= 0; i--) {						
						DefaultListModel model = (DefaultListModel) activeList.getModel();
						 model.remove(selectInd[i]);
					}
					
				}
			}
		});
		button.setBounds(138, 54, 229, 23);
		frame.getContentPane().add(button);
		// кнопка добавления
		JButton button_1 = new JButton("\u0412\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u044D\u043B\u0435\u043C\u0435\u043D\u0442");
		// добавляем обработчик события
		button_1.addActionListener(new ActionListener() {
			// обработчик нажатия
			public void actionPerformed(ActionEvent e) {
				// при выбранном листе
				if(CheckList()) {
					// если текстовое поле не пустое
					if((textField.getText().trim().length() > 0))
						// берем список элементов активного листа и добавляем к нему новое значение
					((DefaultListModel<String>) activeList.getModel()).addElement(textField.getText());
				}	// иначе выводим ошибку
				else {
					JOptionPane.showMessageDialog(null, "Вы не ввели элемент для добавления в список");
 
				}
			}
		});
		button_1.setBounds(138, 126, 229, 23);
		frame.getContentPane().add(button_1);
		// создание текстового поля
		textField = new JTextField();
		textField.setBounds(135, 161, 232, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		// кнопка переместить
		JButton button_2 = new JButton("\u041F\u0435\u0440\u0435\u043C\u0435\u0441\u0442\u0438\u0442\u044C \u044D\u043B. \u0432 \u0434\u0440\u0443\u0433\u043E\u0439 \u043B\u0438\u0441\u0442");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CheckList()) {
					// получаем ид выбранного элемента
					int[] copyIndex = activeList.getSelectedIndices();
					// через цикл
					for(int i=0; i < copyIndex.length; i++) {
						// в неактивный лист добавляем выбранный
						((DefaultListModel<String>) nonActiveList.getModel()).addElement(((DefaultListModel<String>)activeList.getModel()).getElementAt(copyIndex[i]));
						// из активного листа удаляем выбранный
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
