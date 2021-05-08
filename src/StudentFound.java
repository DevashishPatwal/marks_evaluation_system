
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class StudentFound {
	private int current;
	private JLabel lblNewLabel;
	private JLabel lblsynop;
	private JLabel lblprog1;
	private JLabel lblprog2;
	private JLabel lblprog3;
	private JLabel lblletter;
	private JLabel lblext;
	private JFrame frmStudentFound;
	private ArrayList<Student> mylist;
	private JTextField txtext;
	private JTextField txtsynop;
	private JTextField txtletter;
	private JTextField txtprog1;
	private JTextField txtprog2;
	private JTextField txtprog3;
	private JButton btnEdit;
	private JButton btnSave;
	private JLabel lblErrLetter;
	private JLabel lblErrProg1;
	private JLabel lblErrProg2;
	private JLabel lblErrProg3;
	private JLabel lblErrSynop;
	private JLabel lblErrExt;

	/**
	 * Launch the application.
	 */
	public static void initfound(int i) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFound window = new StudentFound(i);
					window.frmStudentFound.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentFound(int i) {
		initialize(i);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int i) {
		current = i;
		mylist = Data.returnlist();
		frmStudentFound = new JFrame();
		frmStudentFound.setIconImage(
				Toolkit.getDefaultToolkit().getImage(StudentFound.class.getResource("/resorces/student-16.png")));
		frmStudentFound.setTitle("Student Details");
		frmStudentFound.setBounds(100, 100, 643, 486);
		frmStudentFound.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));

		lblext = new JLabel("");
		lblext.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblsynop = new JLabel("");
		lblsynop.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		lblletter = new JLabel("");
		lblletter.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		lblprog1 = new JLabel("");
		lblprog1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		lblprog2 = new JLabel("");
		lblprog2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		lblprog3 = new JLabel("");
		lblprog3.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JButton btnPrevious = new JButton("< Previous");
		btnPrevious.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnPrevious.setBackground(SystemColor.controlHighlight);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current >= 1) {
					current--;
					updateLabels();
				} else {
					JOptionPane.showMessageDialog(null, "Reached at the begining of Records", "Begining",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton btnNext = new JButton("Next >");
		btnNext.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnNext.setBackground(SystemColor.controlHighlight);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current < mylist.size() - 1) {
					current++;
					updateLabels();
				} else {
					JOptionPane.showMessageDialog(null, "Reached at the end of Records", "End",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnClose.setBackground(SystemColor.controlHighlight);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.writeArrayList();
				frmStudentFound.dispose();
			}
		});

		JSeparator separator = new JSeparator();

		txtext = new JTextField();
		txtext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					if (Integer.parseInt(txtext.getText()) > Student.MAX_MARKS) {
						lblErrExt.setText("Marks can't be greater than " + Student.MAX_MARKS);
						btnSave.setEnabled(false);
					} else if (Integer.parseInt(txtext.getText()) < 0) {
						lblErrExt.setText("Marks values can't be negative");
						btnSave.setEnabled(false);
					} else {
						lblErrExt.setText(null);
						btnSave.setEnabled(true);
					}
				} catch (Exception ex) {
					lblErrExt.setText("Please Enter A Numeric Number");
					btnSave.setEnabled(false);
				}
			}
		});
		txtext.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtext.setHorizontalAlignment(SwingConstants.RIGHT);
		txtext.setEditable(false);
		txtext.setColumns(10);

		txtsynop = new JTextField();
		txtsynop.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					if (Integer.parseInt(txtsynop.getText()) > Student.MAX_MARKS) {
						lblErrSynop.setText("Marks can't be greater than " + Student.MAX_MARKS);
						btnSave.setEnabled(false);
					} else if (Integer.parseInt(txtsynop.getText()) < 0) {
						lblErrSynop.setText("Marks values can't be negative");
						btnSave.setEnabled(false);
					} else {
						lblErrSynop.setText(null);
						btnSave.setEnabled(true);
					}
				} catch (Exception ex) {
					lblErrSynop.setText("Please Enter A Numeric Number");
					btnSave.setEnabled(false);
				}
			}
		});
		txtsynop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtsynop.setHorizontalAlignment(SwingConstants.RIGHT);
		txtsynop.setEditable(false);
		txtsynop.setColumns(10);

		txtletter = new JTextField();
		txtletter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if (Integer.parseInt(txtletter.getText()) > Student.MAX_MARKS) {
						lblErrLetter.setText("Marks can't be greater than " + Student.MAX_MARKS);
						btnSave.setEnabled(false);
					} else if (Integer.parseInt(txtletter.getText()) < 0) {
						lblErrLetter.setText("Marks values can't be negative");
						btnSave.setEnabled(false);
					} else {
						lblErrLetter.setText(null);
						btnSave.setEnabled(true);
					}
				} catch (Exception ex) {
					lblErrLetter.setText("Please Enter A Numeric Number");
					btnSave.setEnabled(false);

				}
			}
		});

		txtletter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtletter.setHorizontalAlignment(SwingConstants.RIGHT);
		txtletter.setEditable(false);
		txtletter.setColumns(10);

		txtprog1 = new JTextField();
		txtprog1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					if (Integer.parseInt(txtprog1.getText()) > Student.MAX_MARKS) {
						lblErrProg1.setText("Marks can't be greater than " + Student.MAX_MARKS);
						btnSave.setEnabled(false);
					} else if (Integer.parseInt(txtprog1.getText()) < 0) {
						lblErrProg1.setText("Marks values can't be negative");
						btnSave.setEnabled(false);
					} else {
						lblErrProg1.setText(null);
						btnSave.setEnabled(true);
					}
				} catch (Exception ex) {
					lblErrProg1.setText("Please Enter A Numeric Number");
					btnSave.setEnabled(false);
				}
			}
		});
		txtprog1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtprog1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtprog1.setEditable(false);
		txtprog1.setColumns(10);

		txtprog2 = new JTextField();
		txtprog2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					if (Integer.parseInt(txtprog2.getText()) > Student.MAX_MARKS) {
						lblErrProg2.setText("Marks can't be greater than " + Student.MAX_MARKS);
						btnSave.setEnabled(false);
					} else if (Integer.parseInt(txtprog2.getText()) < 0) {
						lblErrProg2.setText("Marks values can't be negative");
						btnSave.setEnabled(false);
					} else {
						lblErrProg2.setText(null);
						btnSave.setEnabled(true);
					}
				} catch (Exception ex) {
					lblErrProg2.setText("Please Enter A Numeric Number");
					btnSave.setEnabled(false);
				}
			}
		});
		txtprog2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtprog2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtprog2.setEditable(false);
		txtprog2.setColumns(10);

		txtprog3 = new JTextField();
		txtprog3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					if (Integer.parseInt(txtprog3.getText()) > Student.MAX_MARKS) {
						lblErrProg3.setText("Marks can't be greater than " + Student.MAX_MARKS);
						btnSave.setEnabled(false);
					} else if (Integer.parseInt(txtprog3.getText()) < 0) {
						lblErrProg3.setText("Marks values can't be negative");
						btnSave.setEnabled(false);
					} else {
						lblErrProg3.setText(null);
						btnSave.setEnabled(true);
					}
				} catch (Exception ex) {
					lblErrProg3.setText("Please Enter A Numeric Number");
					btnSave.setEnabled(false);
				}
			}
		});
		txtprog3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtprog3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtprog3.setEditable(false);
		txtprog3.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLabels();
				lblErrExt.setText(null);
				lblErrLetter.setText(null);
				lblErrSynop.setText(null);
				lblErrProg1.setText(null);
				lblErrProg2.setText(null);
				lblErrProg3.setText(null);
				btnNext.setEnabled(true);
				btnPrevious.setEnabled(true);
				btnClose.setEnabled(true);
				txtext.setEditable(false);
				txtsynop.setEditable(false);
				txtletter.setEditable(false);
				txtprog1.setEditable(false);
				txtprog2.setEditable(false);
				txtprog3.setEditable(false);
				btnSave.setVisible(false);
				btnEdit.setEnabled(true);
				btnCancel.setVisible(false);

			}
		});
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnCancel.setVisible(false);
		btnEdit = new JButton("Edit Marks");
		btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEdit.setBackground(SystemColor.controlHighlight);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNext.setEnabled(false);
				btnPrevious.setEnabled(false);
				btnClose.setEnabled(false);
				txtext.setEditable(true);
				txtsynop.setEditable(true);
				txtletter.setEditable(true);
				txtprog1.setEditable(true);
				txtprog2.setEditable(true);
				txtprog3.setEditable(true);
				btnSave.setVisible(true);
				btnEdit.setEnabled(false);
				btnCancel.setVisible(true);
			}
		});

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSave.setBackground(SystemColor.controlHighlight);
		btnSave.setVisible(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student stu = new Student();
				try {
					stu.setExt(Integer.parseInt(txtext.getText()));
					stu.setName(mylist.get(current).getName());
					stu.setRoll(mylist.get(current).getRoll());
					stu.setSnop(Integer.parseInt(txtsynop.getText()));
					stu.setLetter(Integer.parseInt(txtletter.getText()));
					stu.setProg1(Integer.parseInt(txtprog1.getText()));
					stu.setProg2(Integer.parseInt(txtprog2.getText()));
					stu.setProg3(Integer.parseInt(txtprog3.getText()));
					Data.replace(current, stu);
					DataSource d = new DataSource();
					d.editMarks(stu);
					mylist = Data.returnlist();
					JOptionPane.showMessageDialog(null, "Marks updated Successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					btnEdit.setEnabled(true);
					btnSave.setVisible(false);
					txtext.setEditable(false);
					txtsynop.setEditable(false);
					txtletter.setEditable(false);
					txtprog1.setEditable(false);
					txtprog2.setEditable(false);
					txtprog3.setEditable(false);
					btnNext.setEnabled(true);
					btnPrevious.setEnabled(true);
					btnClose.setEnabled(true);
					btnCancel.setVisible(false);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Exception Occurred");
				}

			}
		});

		btnCancel.setBackground(SystemColor.controlHighlight);

		lblErrLetter = new JLabel("");
		lblErrLetter.setForeground(Color.RED);
		lblErrLetter.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		lblErrProg1 = new JLabel("");
		lblErrProg1.setForeground(Color.RED);
		lblErrProg1.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		lblErrProg2 = new JLabel("");
		lblErrProg2.setForeground(Color.RED);
		lblErrProg2.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		lblErrProg3 = new JLabel("");
		lblErrProg3.setForeground(Color.RED);
		lblErrProg3.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		lblErrSynop = new JLabel("");
		lblErrSynop.setForeground(Color.RED);
		lblErrSynop.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		lblErrExt = new JLabel("");
		lblErrExt.setForeground(Color.RED);
		lblErrExt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(frmStudentFound.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(58)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnPrevious).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNext).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnEdit)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnClose)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSave)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCancel))
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblext, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(144))
										.addComponent(
												lblsynop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblletter, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblprog1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblprog2, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblprog3, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
										.addGap(35)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(txtprog3, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtprog2, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(txtsynop, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtprog1, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtext, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtletter, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblErrProg1, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblErrSynop, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblErrExt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														284, Short.MAX_VALUE)
												.addComponent(lblErrLetter, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addComponent(lblErrProg3, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
										.addComponent(lblErrProg2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 284,
												Short.MAX_VALUE))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(35).addComponent(lblNewLabel).addGap(18)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE).addGap(39)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblErrExt, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblext).addComponent(
								txtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblErrSynop, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblsynop)
								.addComponent(txtsynop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblErrLetter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblletter)
								.addComponent(txtletter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblErrProg1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblprog1)
								.addComponent(txtprog1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblprog2)
								.addComponent(txtprog2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(lblErrProg2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblprog3)
								.addComponent(txtprog3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(lblErrProg3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnPrevious)
						.addComponent(btnNext).addComponent(btnEdit).addComponent(btnClose).addComponent(btnSave)
						.addComponent(btnCancel))
				.addGap(19)));

		updateLabels();
		frmStudentFound.getContentPane().setLayout(groupLayout);

	}

	private void updateLabels() {
		lblNewLabel.setText(mylist.get(current).getName() + " - " + mylist.get(current).getRoll());
		lblsynop.setText("Synposis");
		lblprog1.setText("Progress 1");
		lblprog2.setText("Progress 2");
		lblprog3.setText("Progress 3 ");
		lblletter.setText("Letter");
		lblext.setText("External");
		txtext.setText(Integer.toString(mylist.get(current).getExt()));
		txtsynop.setText(Integer.toString(mylist.get(current).getSynop()));
		txtletter.setText(Integer.toString(mylist.get(current).getLetter()));
		txtprog1.setText(Integer.toString(mylist.get(current).getProg1()));
		txtprog2.setText(Integer.toString(mylist.get(current).getProg2()));
		txtprog3.setText(Integer.toString(mylist.get(current).getProg3()));
	}
}