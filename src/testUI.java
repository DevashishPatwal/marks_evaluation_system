import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import java.awt.event.*;
import java.awt.*;

public class testUI extends JFrame {
	private JTextField textField;

	public testUI() {

		super("Underline In Swing");
		setSize(400, 300);
		getContentPane().setLayout(null);

		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("task-manager-api");
		database.createCollection("lit", null);
		System.out.print(database);
		textField = new JTextField();

		textField.setBounds(141, 57, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(wndCloser);
		setVisible(true);
	}

	public static void main(String args[]) {
		new testUI();
	}
}