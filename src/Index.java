import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;
import java.awt.event.MouseMotionAdapter;

public class Index {
	public static JLabel lblFileName;
	private JFrame frmMarksEvaluation;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frmMarksEvaluation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Index() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DisplayAll.sortEntries = new ArrayList<String>();
		DisplayAll.sortEntries.add("Roll No");
		DisplayAll.sortEntries.add("Name");
		DisplayAll.sortEntries.add("Total");
		JLabel lblDate = new JLabel("");
		lblDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Date now = new Date();
				lblDate.setToolTipText(now.toString());
			}
		});
		Data.setArrayList();
		frmMarksEvaluation = new JFrame();

		frmMarksEvaluation
				.setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/resorces/tick_16.png")));
		frmMarksEvaluation.setTitle("Marks Evaluation");
		frmMarksEvaluation.setBounds(100, 100, 673, 458);
		frmMarksEvaluation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Date now = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E dd-MM-yyyy");
		lblDate.setText(ft.format(now));
		JButton btnAddStudent = new JButton("Add Student      ");
		btnAddStudent.setBounds(58, 85, 226, 95);
		btnAddStudent.setIcon(new ImageIcon(Index.class.getResource("/resorces/user-add-icon-64.png")));
		btnAddStudent.setFocusable(false);
		btnAddStudent.setMargin(new Insets(2, 2, 2, 2));
		btnAddStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddStudent.setSelectedIcon(null);
		btnAddStudent.setForeground(Color.BLACK);
		btnAddStudent.setBackground(SystemColor.controlHighlight);
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent.runMyProject();

			}
		});
		btnAddStudent.setFont(new Font("Lato", Font.PLAIN, 18));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 659, 22);
		menuBar.setBorderPainted(false);

		menuBar.setMargin(new Insets(5, 0, 5, 0));
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JMenu mnMainMMenu = new JMenu("Menu");
		mnMainMMenu.setSelectedIcon(null);
		mnMainMMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnMainMMenu);

		JMenu mnDeleteStudent = new JMenu("Delete a Student");
		mnDeleteStudent.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnDeleteStudent.setPreferredSize(new Dimension(135, 20));
		mnDeleteStudent.setIcon(null);
		mnMainMMenu.add(mnDeleteStudent);

		JMenuItem mntmSearchByRoll = new JMenuItem("Rollno");
		mntmSearchByRoll.setIcon(null);
		mntmSearchByRoll.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmSearchByRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int roll = 0;
				boolean correct = true;
				String rol;
				// loop required so that we can take correct input(Numeric Value only)
				while (correct) {
					try {
						do {
							rol = JOptionPane.showInputDialog(null, "Enter the Rollno", "Delete",
									JOptionPane.PLAIN_MESSAGE);
						} while (rol.length() == 0);

						roll = Integer.parseInt(rol);
						if (roll < 1)
							JOptionPane.showMessageDialog(null, "RollNo should be greater than 0");
						else {
							correct = false;
							int index = Data.searchByRoll(roll);
							if (index != -1) {
								Data.delete(index);
								JOptionPane.showMessageDialog(null, "Record Deleted Sucessfully");
							} else
								JOptionPane.showMessageDialog(null, "RollNo Not Found !!!", "Error",
										JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please Enter A Numeric Number", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						correct = false;
					}
				}

			}
		});

		JMenuItem mntmSearchByName = new JMenuItem("Name");
		mntmSearchByName.setIcon(null);
		mntmSearchByName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmSearchByName.setPreferredSize(new Dimension(74, 20));
		mntmSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n;
				try {
					do {
						n = JOptionPane.showInputDialog(null, "Enter Name", "Delete", JOptionPane.PLAIN_MESSAGE);
					} while (n.length() == 0);
					int index = Data.searchByName(n);
					if (index != -1) {
						Data.delete(index);
						JOptionPane.showMessageDialog(null, "Record Deleted Sucessfully", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "Name Not Found!!!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception NullPoint) {

				}
			}
		});

		mnDeleteStudent.add(mntmSearchByName);
		mnDeleteStudent.add(mntmSearchByRoll);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(null);
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.setSelectedIcon(null);
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.initSettings();
			}
		});
		mntmSettings.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnMainMMenu.add(mntmSettings);

		mnMainMMenu.add(mntmExit);

		JButton btnSearch = new JButton("Search Student");
		btnSearch.setBounds(356, 85, 226, 95);
		btnSearch.setIcon(new ImageIcon(Index.class.getResource("/resorces/search-64.png")));
		btnSearch.setFocusable(false);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "";
				try {
					do {
						name = JOptionPane.showInputDialog(null, "Enter Name or Rollno", "Search Student",
								JOptionPane.QUESTION_MESSAGE);
					} while (name.length() == 0);
					int index = Data.searchByRoll(Integer.parseInt(name));
					if (index != -1)
						StudentFound.initfound(index);
					else
						JOptionPane.showMessageDialog(null, "Student Not Found!!!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException n) {
					int index = Data.searchByName(name);
					if (index != -1)
						StudentFound.initfound(index);
					else
						JOptionPane.showMessageDialog(null, "Student Not Found!!!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e1) {

				}
			}
		});
		btnSearch.setBackground(SystemColor.controlHighlight);
		btnSearch.setFont(new Font("Lato", Font.PLAIN, 18));

		JButton btnDelay = new JButton("Show Delays ");
		btnDelay.setBounds(58, 253, 226, 96);
		btnDelay.setIcon(new ImageIcon(Index.class.getResource("/resorces/deadline-64.png")));
		btnDelay.setFocusable(false);
		btnDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delay.initdelay();
			}
		});
		btnDelay.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnDelay.setHideActionText(true);
		btnDelay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelay.setBackground(SystemColor.controlHighlight);
		btnDelay.setFont(new Font("Lato", Font.PLAIN, 18));

		JButton btnDisplay = new JButton("View Students");
		btnDisplay.setBounds(356, 253, 226, 96);
		btnDisplay.setIcon(new ImageIcon(Index.class.getResource("/resorces/table-64.png")));
		btnDisplay.setFocusable(false);
		btnDisplay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayAll.init();
			}
		});
		btnDisplay.setBackground(SystemColor.controlHighlight);
		btnDisplay.setPreferredSize(new Dimension(101, 21));
		btnDisplay.setMinimumSize(new Dimension(101, 21));
		btnDisplay.setMaximumSize(new Dimension(101, 21));
		btnDisplay.setFont(new Font("Lato", Font.PLAIN, 18));

		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 394, 659, 27);
		panelBottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		textField = new JTextField();
		textField.setMargin(new Insets(4, 4, 4, 4));
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField.setBounds(356, 32, 226, 27);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModel);
		list.setName("searchResult");
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String value = list.getSelectedValue().toString();
				if (value != "No Record Found !") {
					int index = Data.searchByRoll(Integer.parseInt(value.substring(0, value.indexOf('-')).trim()));
					if (index != -1)
						StudentFound.initfound(index);
					textField.setText(null);
					list.setVisible(false);
					btnSearch.setVisible(true);
				}
			}
		});
		list.setSelectionBackground(new Color(176, 224, 230));

		list.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		list.setBorder(UIManager.getBorder("MenuItem.border"));
		list.setVisible(false);
		frmMarksEvaluation.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(null);
				btnSearch.setVisible(true);
				list.setVisible(false);
			}
		});
		frmMarksEvaluation.getContentPane().add(list);

		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// This is done so that database is not accessed if searchTextBox is empty
				// .trim() deletes all preceding and trailing spaces
				if (textField.getText().trim().isEmpty()) {
					list.setVisible(false);
					btnSearch.setVisible(true);
				} else {
					list.setForeground(Color.BLACK);
					int loop, height;
					btnSearch.setVisible(false);
					list.setVisible(true);
					DataSource d = new DataSource();
					ArrayList<Student> searchData = new ArrayList<>();
					try {
						searchData = d.searchData(textField.getText().trim());
						if(searchData==null) {
							System.out.print("yaar");	
						}
						listModel.clear();
						System.out.print(searchData.size());
						if (searchData.size() == 0) {
							list.setForeground(Color.RED);
							listModel.addElement("No Record Found !");
							list.setBounds(356, 62, 226, 25);
						} else {
							if (searchData.size() < 5) {
								loop = searchData.size();
								height = 25 * loop;
								list.setBounds(356, 62, 226, height);
							} else {
								loop = 5;
								list.setBounds(356, 62, 226, 120);
							}
							for (int i = 0; i < loop; i++) {
								listModel.addElement(String.format(" %2d -> %s", searchData.get(i).getRoll(),
										searchData.get(i).getName().toUpperCase()));
							}
						}
					} catch (NullPointerException ex) {
						list.setForeground(Color.RED);
						listModel.addElement("No Record Found !");
						list.setBounds(356, 62, 226, 25);
					}
				}
			}
		});
		lblFileName = new JLabel("");
		lblFileName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFileName.setToolTipText("File Name = " + Data.getFileName() + ".db");
			}
		});

		lblFileName.setText("File : " + Data.getFileName());
		lblFileName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		;
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup().addContainerGap()
						.addComponent(lblFileName, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)));
		gl_panelBottom.setVerticalGroup(gl_panelBottom.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFileName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)));
		panelBottom.setLayout(gl_panelBottom);
		frmMarksEvaluation.getContentPane().setLayout(null);
		frmMarksEvaluation.getContentPane().add(btnDelay);
		frmMarksEvaluation.getContentPane().add(btnDisplay);
		frmMarksEvaluation.getContentPane().add(menuBar);
		frmMarksEvaluation.getContentPane().add(panelBottom);
		frmMarksEvaluation.getContentPane().add(btnAddStudent);
		frmMarksEvaluation.getContentPane().add(textField);
		frmMarksEvaluation.getContentPane().add(btnSearch);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
