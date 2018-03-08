package view;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.Benutzer;
import model.DBManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;

public class Spieler extends JFrame {

	static private JPanel contentPane;
	static private JTextField textField_Nachname;
	static private JTextField textField_EMail;
	static private JTextField textField_Benutzername;
	static private JTable table;
	static private JTextField textField_Vorname;
	private JTextField textField_PLZ;
	private JTextField textField_Geburtstag;
	private JTextField textField_Passwort;
	private JTextField textField_UpdateSpalteinhalt;
	private JTextField textField_UpdateID;
	private Spieler splr;
	private JTextField txtIdloesch;
	private JTextField textField_Spalte;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Spieler frame = new Spieler();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Spieler() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 555);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][][][][][][][][][][][grow][][grow][][][][][][][][][][][][]"));

		JLabel lblDatenbankverwaltung = new JLabel("Benutzer");
		lblDatenbankverwaltung.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDatenbankverwaltung, "cell 1 0 2 1");

		JLabel lblId = new JLabel("Vorname:");
		contentPane.add(lblId, "cell 0 1,alignx trailing");

		textField_Vorname = new JTextField();
		contentPane.add(textField_Vorname, "cell 1 1,growx");
		textField_Vorname.setColumns(10);

		JLabel lblEmail = new JLabel("E-Mail:");
		contentPane.add(lblEmail, "cell 2 1,alignx trailing");

		textField_EMail = new JTextField();
		contentPane.add(textField_EMail, "cell 3 1,growx");
		textField_EMail.setColumns(10);

		JLabel lblNachname = new JLabel("Nachname:");
		contentPane.add(lblNachname, "cell 0 3,alignx trailing");

		textField_Nachname = new JTextField();
		contentPane.add(textField_Nachname, "cell 1 3,growx");
		textField_Nachname.setColumns(10);

		JLabel lblGeburtstag = new JLabel("Geburtstag:");
		contentPane.add(lblGeburtstag, "cell 2 3,alignx trailing");

		textField_Geburtstag = new JTextField();
		contentPane.add(textField_Geburtstag, "cell 3 3,growx");
		textField_Geburtstag.setColumns(10);

		JLabel lblVorname = new JLabel("PLZ:");
		contentPane.add(lblVorname, "cell 0 4,alignx trailing");

		textField_PLZ = new JTextField();
		contentPane.add(textField_PLZ, "cell 1 4,growx");
		textField_PLZ.setColumns(10);

		JLabel lblPasswort = new JLabel("Passwort:");
		contentPane.add(lblPasswort, "cell 2 4,alignx trailing");

		textField_Passwort = new JTextField();
		contentPane.add(textField_Passwort, "cell 3 4,growx");
		textField_Passwort.setColumns(10);

		JLabel lblAlter = new JLabel("Benutzername:");
		contentPane.add(lblAlter, "cell 0 5,alignx trailing");

		textField_Benutzername = new JTextField();
		contentPane.add(textField_Benutzername, "flowy,cell 1 5,growx");
		textField_Benutzername.setColumns(10);

		JRadioButton rdbtnFahrradfhrerschein = new JRadioButton("Fahrradf\u00FChrerschein");
		contentPane.add(rdbtnFahrradfhrerschein, "cell 3 5");

		JButton btnDatenHineinschreiben = new JButton("Benutzer \u00FCbernehmen");
		contentPane.add(btnDatenHineinschreiben, "cell 3 7,growx");
		btnDatenHineinschreiben.addActionListener(new btnDatenHineinschreibenListener());

		JButton btnTabelleAktualisieren = new JButton("Tabelle aktualisieren");
		contentPane.add(btnTabelleAktualisieren, "cell 0 15");
		btnTabelleAktualisieren.addActionListener(new btnTabelleAktualisierenListener());

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 19 4 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object [][] {}, new String[] {"ID","Vorname","Nachname","Benutzername","E-Mail","Geburtstag","PLZ","Fahrradführerschein"}));

		JLabel lblIdDerZu = new JLabel("ID der zu Ver\u00E4ndernden Daten:");
		contentPane.add(lblIdDerZu, "cell 0 21,alignx trailing");

		textField_UpdateID = new JTextField();
		contentPane.add(textField_UpdateID, "cell 1 21,growx");
		textField_UpdateID.setColumns(10);

		JLabel lblNeuerEintrag = new JLabel("neuer Eintrag:");
		contentPane.add(lblNeuerEintrag, "cell 0 23,alignx trailing");

		textField_UpdateSpalteinhalt = new JTextField();
		contentPane.add(textField_UpdateSpalteinhalt, "cell 1 23,growx");
		textField_UpdateSpalteinhalt.setColumns(10);

		JButton btnDatenUpdaten = new JButton("Daten updaten");
		contentPane.add(btnDatenUpdaten, "cell 3 23,growx");
		btnDatenUpdaten.addActionListener(new btnDatenUpdatenListener());

		JLabel lblSpalteWelcheGendert = new JLabel("Spalte welche ge\u00E4ndert werden soll:");
		contentPane.add(lblSpalteWelcheGendert, "cell 0 24,alignx trailing");

		textField_Spalte = new JTextField();
		contentPane.add(textField_Spalte, "cell 1 24,growx");
		textField_Spalte.setColumns(10);

		JLabel lblId_1 = new JLabel("ID:");
		contentPane.add(lblId_1, "cell 0 25,alignx trailing");

		txtIdloesch = new JTextField();
		contentPane.add(txtIdloesch, "cell 1 25,growx");
		txtIdloesch.setColumns(10);

		JButton btnDatenLschen = new JButton("Daten l\u00F6schen");
		contentPane.add(btnDatenLschen, "cell 3 25,growx");
		btnDatenLschen.addActionListener(new btnDatenLschenListener());

	}



	public static JTextField getTextField_Nachname() {
		return textField_Nachname;
	}

	public static void setTextField_Nachname(JTextField textField_Nachname) {
		Spieler.textField_Nachname = textField_Nachname;
	}

	public static JTextField getTextField_EMail() {
		return textField_EMail;
	}

	public static void setTextField_EMail(JTextField textField_EMail) {
		Spieler.textField_EMail = textField_EMail;
	}

	public static JTextField getTextField_Benutzername() {
		return textField_Benutzername;
	}

	public static void setTextField_Benutzername(JTextField textField_Benutzername) {
		Spieler.textField_Benutzername = textField_Benutzername;
	}

	public static JTextField getTextField_Vorname() {
		return textField_Vorname;
	}

	public static void setTextField_Vorname(JTextField textField_Vorname) {
		Spieler.textField_Vorname = textField_Vorname;
	}

	public JTextField getTextField_PLZ() {
		return textField_PLZ;
	}

	public void setTextField_PLZ(JTextField textField_PLZ) {
		this.textField_PLZ = textField_PLZ;
	}

	public JTextField getTextField_Geburtstag() {
		return textField_Geburtstag;
	}

	public void setTextField_Geburtstag(JTextField textField_Geburtstag) {
		this.textField_Geburtstag = textField_Geburtstag;
	}

	public JTextField getTextField_Passwort() {
		return textField_Passwort;
	}

	public void setTextField_Passwort(JTextField textField_Passwort) {
		this.textField_Passwort = textField_Passwort;
	}

	public JTextField getTextField_UpdateNeueSpaltInhalt() {
		return textField_UpdateSpalteinhalt;
	}

	public void setTextField_UpdateNeueSpaltInhalt(JTextField textField_UpdateNeueSpaltInhalt) {
		this.textField_UpdateSpalteinhalt = textField_UpdateNeueSpaltInhalt;
	}

	public JTextField getTextField_UpdateSpaltenname() {
		return textField_UpdateID;
	}

	public void setTextField_UpdateSpaltenname(JTextField textField_UpdateSpaltenname) {
		this.textField_UpdateID = textField_UpdateSpaltenname;
	}

	public JTextField getTxtIdloesch() {
		return txtIdloesch;
	}

	public void setTxtIdloesch(JTextField txtIdloesch) {
		this.txtIdloesch = txtIdloesch;
	}

	public JTextField getTextField_Spalte() {
		return textField_Spalte;
	}

	public void setTextField_Spalte(JTextField textField_Spalte) {
		this.textField_Spalte = textField_Spalte;
	}


	public class btnDatenHineinschreibenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			Benutzer b = new Benutzer();
			b.setVorname(textField_Vorname.getText());
			b.setNachname(textField_Nachname.getText());
			b.setBenutzername(textField_Benutzername.getText());
			b.setEmail(textField_EMail.getText());
			if(textField_PLZ.getText()!="")
			{
				b.setPLZ(Integer.parseInt(textField_PLZ.getText()));
			}
			b.setGeburtstagsdatum(textField_Geburtstag.getText());
			b.setPassword(textField_Passwort.getText());
			b.setFahrradführerschein(1);
			//	b.setFahrradführerschein(i);

			try {
				DBManager db = new DBManager();
				Connection conn = db.getConnection();
				db.InsertBenutzer(conn, b);

			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				// Unique Constraint Violation --> Error ist OK
				assertTrue(true);
			}
			catch (SQLException e) {
				e.printStackTrace();
				fail();
			}
		}

	}

	public class btnTabelleAktualisierenListener implements ActionListener {
		ArrayList<Benutzer> b = null;
		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				DBManager db = new DBManager();
				Connection conn = db.getConnection();
				b = db.readBenutzer(conn);


			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				// Unique Constraint Violation --> Error ist OK
				assertTrue(true);
			}
			catch (SQLException e) {
				e.printStackTrace();
				fail();
			}


			DefaultTableModel m = (DefaultTableModel) table.getModel();
			if (table.getRowCount() > 0) {
				m.setRowCount(0);
			}
			for(int i=0;i<b.size();i++)
			{	
				m.addRow(new String[] {""+(b.get(i).getBenutzerid()),b.get(i).getVorname(),b.get(i).getNachname(),b.get(i).getBenutzername(),b.get(i).getEmail(),b.get(i).getGeburtstagsdatum(),""+b.get(i).getPLZ(),""+b.get(i).isFahrradführerschein() });	
			}

		}
	}

	public class btnDatenLschenListener implements ActionListener {

		int id = 0;
		@Override
		public void actionPerformed(ActionEvent e) {

			if(txtIdloesch.getText()!="")
			{
				id = Integer.parseInt(txtIdloesch.getText());
			}
			System.out.println("Daten löschen bei ID: "+id);

			try {
				DBManager db = new DBManager();
				Connection conn = db.getConnection();
				db.deleteVerleihben(conn,id);
				db.deleteBenutzer(conn,id);

			} catch (NumberFormatException n){
				System.out.println("Die Eingabe war keine Zahl");
			}catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public class btnDatenUpdatenListener implements ActionListener {

		int id = 0;

		@Override
		public void actionPerformed(ActionEvent e) {

			if(textField_UpdateID.getText()!="")
			{
				id = Integer.parseInt(textField_UpdateID.getText());
			}
			System.out.println("Daten updaten bei ID: "+id);
			String spalte = textField_Spalte.getText();
			String spalteninhalt = textField_UpdateSpalteinhalt.getText();
			try {
				DBManager db = new DBManager();
				Connection conn = db.getConnection();
				db.UpdateBenutzer(conn,spalte,spalteninhalt,id);

			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}