
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Settings {
	private static String fileName;
	private static String tempFileName;
	private JFrame frmSettings;
	private ArrayList<String> file;
	private JComboBox<String> comboBoxFile;
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnClose;
	private JPanel panelFiles;
	private JLabel lblNewLabel;
	private JTextField textFieldExt;
	private JPanel panelDeadlines;
	private JTextField textFieldLetter;
	private JLabel lblSynop;
	private JTextField textFieldSynop;
	private JLabel lblLetter;
	private JLabel lblProgress1;
	private JTextField textFieldProg1;
	private JLabel lblProgress2;
	private JTextField textFieldProg2;
	private JLabel lblProgress3;
	private JTextField textFieldProg3;
	private JSeparator separator;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void initSettings() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings window = new Settings();
					window.frmSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		file = Data.getFile();

		frmSettings = new JFrame();
		frmSettings.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {

				// This is used to close the frame as well as update the ArrayList so that it
				// points to the chosen file
				// If we don't change the file, just close frame and don't update the ArrayList
				if (tempFileName != comboBoxFile.getSelectedItem().toString()) {
					Data.setFileName(comboBoxFile.getSelectedItem().toString());
					Data.setFile(file);
					Data.setArrayList();
					Data.prefs.put("DEFAULT_FILE", Data.getFileName());
					Index.lblFileName.setText("File : " + Data.getFileName());

				}
			}
		});
		frmSettings.setTitle("Settings");
		frmSettings.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Settings.class.getResource("/resorces/settings2-16.png")));
		frmSettings.setBounds(100, 100, 500, 366);
		frmSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panelFiles = new JPanel();
		panelFiles.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "File",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panelDeadlines = new JPanel();
		panelDeadlines.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Deadline",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));

		GroupLayout groupLayout = new GroupLayout(frmSettings.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelDeadlines, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelFiles, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 466,
										Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panelFiles, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelDeadlines, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
						.addContainerGap()));
		JLabel lblExt = new JLabel("External");
		lblExt.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		textFieldExt = new JTextField();
		textFieldExt.setEditable(false);
		textFieldExt.setBackground(SystemColor.controlHighlight);
		textFieldExt.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldExt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldExt.setColumns(10);
		textFieldExt.setText(Student.extdeadline.toString());

		textFieldLetter = new JTextField();
		textFieldLetter.setEditable(false);
		textFieldLetter.setBackground(SystemColor.controlHighlight);
		textFieldLetter.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLetter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldLetter.setText("2020-01-23");
		textFieldLetter.setColumns(10);

		lblSynop = new JLabel("Synopsis");
		lblSynop.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		textFieldSynop = new JTextField();
		textFieldSynop.setEditable(false);
		textFieldSynop.setBackground(SystemColor.controlHighlight);
		textFieldSynop.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSynop.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldSynop.setText("2020-01-23");
		textFieldSynop.setColumns(10);

		lblLetter = new JLabel("Letter");
		lblLetter.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		lblProgress1 = new JLabel("Progress 1");
		lblProgress1.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		textFieldProg1 = new JTextField();
		textFieldProg1.setEditable(false);
		textFieldProg1.setBackground(SystemColor.controlHighlight);
		textFieldProg1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldProg1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldProg1.setText("2020-01-23");
		textFieldProg1.setColumns(10);

		lblProgress2 = new JLabel("Progress 2");
		lblProgress2.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		textFieldProg2 = new JTextField();
		textFieldProg2.setEditable(false);
		textFieldProg2.setBackground(SystemColor.controlHighlight);
		textFieldProg2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldProg2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldProg2.setText("2020-01-23");
		textFieldProg2.setColumns(10);

		lblProgress3 = new JLabel("Progress 3");
		lblProgress3.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		textFieldProg3 = new JTextField();
		textFieldProg3.setEditable(false);
		textFieldProg3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldProg3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldProg3.setText("2020-01-23");
		textFieldProg3.setColumns(10);

		separator = new JSeparator();

		JButton btnChangeDeadlines = new JButton("Change Deadline");
		btnChangeDeadlines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldProg3.setEditable(true);
				textFieldProg3.setBackground(Color.WHITE);
				textFieldProg2.setEditable(true);
				textFieldProg2.setBackground(Color.WHITE);
				textFieldProg1.setEditable(true);
				textFieldProg1.setBackground(Color.WHITE);
				textFieldExt.setEditable(true);
				textFieldExt.setBackground(Color.WHITE);
				textFieldSynop.setEditable(true);
				textFieldSynop.setBackground(Color.WHITE);
				textFieldLetter.setEditable(true);
				textFieldLetter.setBackground(Color.WHITE);
				btnChangeDeadlines.setEnabled(false);
				btnSave.setVisible(true);
			}
		});
		btnChangeDeadlines.setBackground(SystemColor.controlHighlight);
		btnChangeDeadlines.setFont(new Font("Segoe UI", Font.BOLD, 12));

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Student.prog1deadline = LocalDate.parse(textFieldProg3.getText());
					Student.prog2deadline = LocalDate.parse(textFieldProg2.getText());
					Student.prog3deadline = LocalDate.parse(textFieldProg1.getText());
					Student.extdeadline = LocalDate.parse(textFieldExt.getText());
					Student.synopdeadline = LocalDate.parse(textFieldSynop.getText());
					Student.letterdeadline = LocalDate.parse(textFieldLetter.getText());
					Data.prefs.put("prog3Deadline", textFieldProg3.getText());
					Data.prefs.put("prog2Deadline", textFieldProg2.getText());
					Data.prefs.put("prog1Deadline", textFieldProg1.getText());
					Data.prefs.put("letterDeadline", textFieldLetter.getText());
					Data.prefs.put("extDeadline", textFieldExt.getText());
					Data.prefs.put("synopDeadline", textFieldSynop.getText());
					JOptionPane.showMessageDialog(null, "Deadlines updated successfully");
					btnSave.setVisible(false);
					btnChangeDeadlines.setEnabled(true);
					textFieldProg3.setEditable(false);
					textFieldProg2.setEditable(false);
					textFieldProg1.setEditable(false);
					textFieldExt.setEditable(false);
					textFieldSynop.setEditable(false);
					textFieldLetter.setEditable(false);
					textFieldProg3.setBackground(SystemColor.controlHighlight);
					textFieldProg2.setBackground(SystemColor.controlHighlight);
					textFieldProg1.setBackground(SystemColor.controlHighlight);
					textFieldExt.setBackground(SystemColor.controlHighlight);
					textFieldSynop.setBackground(SystemColor.controlHighlight);
					textFieldLetter.setBackground(SystemColor.controlHighlight);

				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Enter Date In Proper Format(yyyy-mm-dd)", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSave.setBackground(SystemColor.controlHighlight);
		btnSave.setVisible(false);
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 12));
		GroupLayout gl_panelDeadlines = new GroupLayout(panelDeadlines);
		gl_panelDeadlines.setHorizontalGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDeadlines.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDeadlines.createSequentialGroup()
										.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
												.addComponent(lblSynop, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
												.addComponent(lblExt, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
												.addComponent(lblLetter))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldLetter, GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE)
												.addComponent(textFieldSynop, GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE)
												.addComponent(textFieldExt, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
										.addGap(39)
										.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
														.addComponent(lblProgress1, GroupLayout.PREFERRED_SIZE, 64,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblProgress2, GroupLayout.PREFERRED_SIZE, 64,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(lblProgress3, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldProg2, GroupLayout.DEFAULT_SIZE, 114,
														Short.MAX_VALUE)
												.addComponent(textFieldProg1, Alignment.TRAILING, 114, 114, 114)
												.addComponent(textFieldProg3, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
										.addGap(34))
								.addGroup(gl_panelDeadlines.createSequentialGroup().addComponent(btnChangeDeadlines)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSave)
										.addContainerGap(252, Short.MAX_VALUE))
								.addGroup(gl_panelDeadlines.createSequentialGroup()
										.addComponent(separator, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
										.addGap(34)))));
		gl_panelDeadlines.setVerticalGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDeadlines.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.BASELINE).addComponent(lblProgress1)
								.addComponent(textFieldProg1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldExt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblExt))
						.addGap(21)
						.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldProg2, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblProgress2))
								.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldSynop, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSynop)))
						.addGap(18)
						.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldLetter, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLetter))
								.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldProg3, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblProgress3)))
						.addGap(14).addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelDeadlines.createParallelGroup(Alignment.LEADING).addComponent(btnSave)
								.addComponent(btnChangeDeadlines))
						.addContainerGap(14, Short.MAX_VALUE)));
		panelDeadlines.setLayout(gl_panelDeadlines);

		textFieldProg3.setText(Student.prog3deadline.toString());
		textFieldProg3.setBackground(SystemColor.controlHighlight);
		textFieldProg2.setText(Student.prog2deadline.toString());
		textFieldProg1.setText(Student.prog1deadline.toString());
		textFieldExt.setText(Student.extdeadline.toString());
		textFieldSynop.setText(Student.synopdeadline.toString());
		textFieldLetter.setText(Student.letterdeadline.toString());
		lblNewLabel = new JLabel("Change File");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		comboBoxFile = new JComboBox<String>();
		comboBoxFile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxFile.removeAllItems();

		btnCreate = new JButton("Create New File");
		btnCreate.setBackground(SystemColor.controlHighlight);
		btnCreate.setFont(new Font("Segoe UI", Font.BOLD, 12));

		btnClose = new JButton("Close");
		btnClose.setBackground(SystemColor.controlHighlight);
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));

		btnDelete = new JButton("Delete File");
		// btn is disabled to that we can't delete last remaining file
		if (file.size() == 1) {
			btnDelete.setEnabled(false);
		}
		btnDelete.setBackground(SystemColor.controlHighlight);
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = comboBoxFile.getSelectedItem().toString();
				if (JOptionPane.showConfirmDialog(null, "Delete File \"" + name + ".db\"", "Are You Sure ?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					File f = new File(name + ".db");
					file.remove(name);
					f.delete();
					Data.setFileName(file.get(0));
					updateComboboxFile();
				}

				if (file.size() == 1) {
					btnDelete.setEnabled(false);
				}
			}
		});

		JButton btnRename = new JButton("Rename");
		btnRename.setBackground(SystemColor.controlHighlight);
		btnRename.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newName = JOptionPane.showInputDialog(null, "Enter New File name", comboBoxFile.getItemAt(0));
				if (newName != null && !newName.isEmpty()) {
					File des = new File(newName + ".db");
					File source = new File(comboBoxFile.getItemAt(0) + ".db");
					source.renameTo(des);
					file.remove(0);
					file.add(0, newName);
					updateComboboxFile();
				}
			}
		});
		GroupLayout gl_panelFiles = new GroupLayout(panelFiles);
		gl_panelFiles.setHorizontalGroup(gl_panelFiles.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFiles.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelFiles.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelFiles.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(
												comboBoxFile, GroupLayout.PREFERRED_SIZE, 107,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelFiles.createSequentialGroup()
										.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 122,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnRename)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnClose,
												GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
						.addGap(49)));
		gl_panelFiles.setVerticalGroup(gl_panelFiles.createParallelGroup(Alignment.LEADING).addGroup(gl_panelFiles
				.createSequentialGroup().addGap(5)
				.addGroup(gl_panelFiles.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						comboBoxFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panelFiles.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRename).addComponent(btnClose))
				.addGap(15)));
		panelFiles.setLayout(gl_panelFiles);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSettings.dispose();
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// This is used to create a new file
				fileName = JOptionPane.showInputDialog(null, "Enter File Name", "New File") + ".db";
				// if a user press cancel JOptionPane will return null
				if (fileName != null) {
					// This is used to check whether file being created already exists or not, -1
					// will be returned if file not found in ArrayList
					if (file.indexOf(fileName) == -1) {
						file.add(fileName);

						try {
							DataSource d = new DataSource();
							if (d.createNewDBFile(fileName) == true) {
								JOptionPane.showMessageDialog(null, "File \"" + fileName + "\" created successafully",
										"File Creaated", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Error Creating New File", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception exception) {
						}
						updateComboboxFile();

					} else {
						JOptionPane.showMessageDialog(null, "File \"" + fileName + ".db\" already exists", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				if (file.size() > 1) {
					btnDelete.setEnabled(true);
				}
			}
		});

		frmSettings.getContentPane().setLayout(groupLayout);
		updateComboboxFile();
		tempFileName = comboBoxFile.getSelectedItem().toString();
		// JOptionPane.showMessageDialog(null,file.size()+file.toString());
	}

	void updateComboboxFile() {
		comboBoxFile.removeAllItems();
		// this makes sure that comboBox starts with default file
		if (file.contains(Data.prefs.get("DEFAULT_FILE", ""))) {
			file.remove(Data.prefs.get("DEFAULT_FILE", ""));
			file.add(0, Data.prefs.get("DEFAULT_FILE", ""));
		}
		for (int i = 0; i < file.size(); i++)
			comboBoxFile.addItem(file.get(i));

	}
}
