
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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

public class AddStudent {

	private JFrame frmAddAStudent;
	private JTextField textFieldName;
	private JTextField textFieldRoll;
	private JButton btnClose;
	private JLabel lblName;
	private JLabel lblRollNo;
	private JButton btnSubmit;

	/**
	 * Launch the application.
	 */
	public static void runMyProject() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent window = new AddStudent();
					window.frmAddAStudent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddAStudent = new JFrame();
		frmAddAStudent.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Data.writeArrayList();
			}
		});
		frmAddAStudent.setIconImage(
				Toolkit.getDefaultToolkit().getImage(AddStudent.class.getResource("/resorces/add-16.png")));
		frmAddAStudent.setTitle("Add a student");
		frmAddAStudent.getContentPane().setFont(new Font("Montserrat", Font.PLAIN, 20));
		frmAddAStudent.setBounds(100, 100, 467, 299);
		frmAddAStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lblName = new JLabel("Student Name");
		lblName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblName.setText("Hello");

			}
		});
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRollNo = new JLabel("Roll No");
		lblRollNo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textFieldName = new JTextField();
		textFieldName.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		textFieldName.setMargin(new Insets(2, 10, 0, 2));
		textFieldName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textFieldName.setColumns(10);
		textFieldRoll = new JTextField();
		textFieldRoll.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		textFieldRoll.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textFieldRoll.setColumns(10);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(SystemColor.controlHighlight);
		btnSubmit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int roll;
				String name;
				try {
					roll = Integer.parseInt(textFieldRoll.getText());
					name = textFieldName.getText().toUpperCase();
					if (name.length() == 0) {
						JOptionPane.showMessageDialog(btnSubmit, "Name Can't Be Empty", "Error",
								JOptionPane.ERROR_MESSAGE);
						throw new Exception();
					}
					if (roll <= 0) {
						JOptionPane.showMessageDialog(btnSubmit, "RollNo should be greater than 0", "Error",
								JOptionPane.ERROR_MESSAGE);
						throw new Exception();
					}

					// search whether rollNo already exists or not
					if (Data.searchByRoll(roll) == -1) {
						DataSource d = new DataSource();
						d.addRow(textFieldRoll.getText(), textFieldName.getText());
						JOptionPane.showMessageDialog(btnSubmit, "Records Saved Successfully");
						textFieldRoll.setText(null);
						textFieldName.setText(null);
					} else {
						JOptionPane.showMessageDialog(btnSubmit, "RollNo " + roll + " Already Exists", "Error",
								JOptionPane.ERROR_MESSAGE);
						textFieldRoll.setText(null);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please Enter Numeric Values", "Roll No",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception exep) {
				}
			}
		});
		btnSubmit.setToolTipText("Click to submit the records");
		btnClose = new JButton("Close");
		btnClose.setBackground(SystemColor.controlHighlight);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddAStudent.dispose();
			}
		});
		btnClose.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		// darkTheme();

		GroupLayout groupLayout = new GroupLayout(frmAddAStudent.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblRollNo, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(textFieldRoll))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 111,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnClose,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 196,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(65, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(46)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRollNo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldRoll, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(26).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnSubmit)
						.addComponent(btnClose))
				.addGap(67)));
		frmAddAStudent.getContentPane().setLayout(groupLayout);
	}

}
