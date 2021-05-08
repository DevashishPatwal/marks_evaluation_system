import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Delay {
	private JScrollPane scrollPane;
	private JFrame frmDelayInSubmission;
	private JTable table;
	private JButton btnClose;
	private JButton btnPrint;

	/**
	 * Launch the application.
	 */
	public static void initdelay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delay window = new Delay();
					window.frmDelayInSubmission.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Delay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDelayInSubmission = new JFrame();
		frmDelayInSubmission
				.setIconImage(Toolkit.getDefaultToolkit().getImage(Delay.class.getResource("/resorces/table2-16.png")));
		frmDelayInSubmission.setTitle("Delay in Submission");
		frmDelayInSubmission.setBounds(100, 100, 807, 524);
		frmDelayInSubmission.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		scrollPane = new JScrollPane();

		btnPrint = new JButton("Print Table");
		btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnPrint.setBackground(SystemColor.controlHighlight);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.print();
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
				frmDelayInSubmission.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmDelayInSubmission.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(btnPrint)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnClose))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addGroup(groupLayout
								.createParallelGroup(Alignment.BASELINE).addComponent(btnClose).addComponent(btnPrint))
						.addGap(7)));

		table = new JTable();
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setRowHeight(25);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "RollNo", "Name", "Assessments" }) {
			boolean[] columnEditables = new boolean[] { true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setMinWidth(25);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		table.getColumnModel().getColumn(1).setMaxWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		scrollPane.setViewportView(table);
		frmDelayInSubmission.getContentPane().setLayout(groupLayout);
		print();
	}

	private void print() {
		ArrayList<Student> list = Data.returnlist();
		ArrayList<LocalDate> deadlines = new ArrayList<LocalDate>();
		deadlines.add(Student.extdeadline);
		deadlines.add(Student.synopdeadline);
		deadlines.add(Student.letterdeadline);
		deadlines.add(Student.prog1deadline);
		deadlines.add(Student.prog2deadline);
		deadlines.add(Student.prog3deadline);

		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) table.getModel();

		for (int j = 0; j < list.size(); j++) {
			int roll = 0;
			String name = "";
			String subjects = "";
			int already = 0;

			for (int i = 0; i < deadlines.size(); i++) {
				if (deadlines.get(i).isBefore(LocalDate.now())) {
					if (list.get(j).getMarks(i) == 0) {

						if (already == 0) {
							roll = list.get(j).getRoll();
							name = list.get(j).getName();
							subjects = list.get(j).field(i);
							already = 1;
						} else {
							subjects += ", " + list.get(j).field(i);
						}
					}
				}
			}
			if (name.length() != 0) {
				model = new DefaultTableModel();
				model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] { roll + " ", " " + name, " " + subjects });
			}

		}

	}
}
