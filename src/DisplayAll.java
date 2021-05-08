
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DisplayAll {
	public static ArrayList<String> sortEntries;
	private JFrame frmStudentRecords;
	private JTable tableshowall;
	private JButton btnClose;
	private JComboBox<String> comboBoxSort;
	private ArrayList<Student> mylist;
	private DefaultTableModel model;
	private JPopupMenu popupMenu;
	private JMenuItem editItem;
	private JMenuItem deleteItem;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayAll window = new DisplayAll();
					window.frmStudentRecords.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DisplayAll() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		mylist = Data.returnlist();
		frmStudentRecords = new JFrame();

		frmStudentRecords.setIconImage(
				Toolkit.getDefaultToolkit().getImage(DisplayAll.class.getResource("/resorces/table-16.png")));
		frmStudentRecords.setTitle("Student Records");
		frmStudentRecords.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmStudentRecords.setFont(new Font("Lato", Font.BOLD, 14));
		frmStudentRecords.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmStudentRecords.setBounds(100, 100, 807, 524);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnPrint = new JButton("Print Table");
		btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnPrint.setBackground(SystemColor.controlHighlight);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean complete = tableshowall.print(PrintMode.FIT_WIDTH,
							new MessageFormat("MCA-III Final Project Assessment Marks\n\n"),
							new MessageFormat("Page - {0}"), true, null, true);
					if (!complete)
						JOptionPane.showMessageDialog(null, "Print was cancelled", "Error", JOptionPane.ERROR_MESSAGE);

				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnClose.setBackground(SystemColor.controlHighlight);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmStudentRecords.dispose();
				sortEntries.remove(comboBoxSort.getSelectedItem().toString());
				sortEntries.add(0, comboBoxSort.getSelectedItem().toString());
			}
		});

		JLabel lblSort = new JLabel("Sort By :");
		lblSort.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxSort = new JComboBox<String>();
		comboBoxSort.setFont(new Font("Segoe UI", Font.BOLD, 12));
		for (int i = 0; i < sortEntries.size(); i++)
			comboBoxSort.addItem(sortEntries.get(i));
		comboBoxSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sortStudents(comboBoxSort.getSelectedItem().toString());
			}
		});

		btnNewButton = new JButton("AESC");
		btnNewButton.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton.getText() == "AESC") {
					btnNewButton.setText("DESC");
				} else {
					btnNewButton.setText("AESC");
				}
				sortStudents(comboBoxSort.getItemAt(0).toString());
			}
		});

		GroupLayout groupLayout = new GroupLayout(frmStudentRecords.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addGap(10)
								.addComponent(lblSort, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBoxSort, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
								.addComponent(btnPrint).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnClose)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnClose, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPrint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblSort).addComponent(comboBoxSort, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnNewButton))
				.addGap(11)));

		tableshowall = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "Roll No", "Name", "External",
				"Synopsis", "Letter", "Progress 1", "Progress 2", "Progress 3", "Total" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableshowall.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tableshowall.getSelectedRow() == -1) {
					tableshowall.setComponentPopupMenu(null);

				} else {

					tableshowall.setComponentPopupMenu(popupMenu);
				}
			}
		});

		tableshowall.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableshowall.setBackground(Color.WHITE);
		tableshowall.setGridColor(Color.GRAY);
		tableshowall.setRowMargin(0);
		tableshowall.setRowHeight(25);
		tableshowall.setSelectionForeground(Color.BLACK);
		tableshowall.setSelectionBackground(Color.LIGHT_GRAY);
		tableshowall.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableshowall);
		model = (DefaultTableModel) tableshowall.getModel();

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		for (int x = 0; x < tableshowall.getColumnCount(); x++) {
			if (x != 1)
				tableshowall.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}

		tableshowall.setForeground(Color.BLACK);
		tableshowall.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frmStudentRecords.getContentPane().setLayout(groupLayout);
		tableshowall.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableshowall.getColumnModel().getColumn(1).setMinWidth(150);
		tableshowall.getColumnModel().getColumn(0).setMinWidth(25);
		frmStudentRecords.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

			}
		});
		frmStudentRecords.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				if (mylist.size() <= 1) {
					lblSort.setEnabled(false);
					comboBoxSort.setEnabled(false);
				}
				sortStudents(sortEntries.get(0));

			}

			public void windowLostFocus(WindowEvent e) {
			}
		});

		popupMenu = new JPopupMenu();
		editItem = new JMenuItem("Edit");
		editItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		popupMenu.add(editItem);
		editItem.setIcon(new ImageIcon(DisplayAll.class.getResource("/resorces/edit_16.png")));
		deleteItem = new JMenuItem("Delete");
		deleteItem.setIcon(new ImageIcon(DisplayAll.class.getResource("/resorces/delete_16.png")));
		popupMenu.add(deleteItem);
		deleteItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(tableshowall, "Delete Selected Record", "Confirm",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					Data.delete(tableshowall.getSelectedRow());
					JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
				}
			}
		});
		editItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentFound.initfound(tableshowall.getSelectedRow());
			}
		});

	}

	private void sortStudents(String s) {
		DataSource d = new DataSource();
		ORDER order;
		if (btnNewButton.getText() == "AESC")
			order = ORDER.ASC;
		else
			order = ORDER.DESC;
		if (s == "Roll No") {
			mylist = d.sortData(SORT.BYROLL, order);
		} else if (s == "Name") {
			mylist = d.sortData(SORT.BYNAME, order);
		} else if (s == "Total") {

		}
		for (int i = 0; i < model.getRowCount();) {
			model.removeRow(i);
		}
		for (int i = 0; i < mylist.size(); i++) {
			Student emp = mylist.get(i);
			model.addRow(new Object[] { emp.getRoll() + " ", " " + emp.getName(), emp.getExt() + " ",
					emp.getSynop() + " ", emp.getLetter() + " ", emp.getProg1() + " ", emp.getProg2() + " ",
					emp.getProg3() + " ", emp.getTotal() + " " });
		}
	}
}
