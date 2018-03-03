package view;

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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.Benutzer;
import model.DBManager;
import model.Fahrrad;
import net.miginfocom.swing.MigLayout;
import view.Spieler.btnDatenHineinschreibenListener;
import view.Spieler.btnDatenLschenListener;
import view.Spieler.btnDatenUpdatenListener;
import view.Spieler.btnTabelleAktualisierenListener;

public class FahrradGUI extends JFrame {

	static private JPanel contentPane;
	static private JTextField textField_Art;
	static private JTextField textField_Zoll;
	static private JTable table;
	static private JTextField textField_Marke;
	private JTextField textField_Farbe;
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
					FahrradGUI frame = new FahrradGUI();
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
	public FahrradGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 555);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][][][][][][][][][][][grow][][grow][][][][][][][][][][][][]"));

		JLabel lblDatenbankverwaltung = new JLabel("Fahrrad");
		lblDatenbankverwaltung.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDatenbankverwaltung, "cell 1 0 2 1");

		JLabel lblId = new JLabel("Marke:");
		contentPane.add(lblId, "cell 0 1,alignx trailing");

		textField_Marke = new JTextField();
		contentPane.add(textField_Marke, "cell 1 1,growx");
		textField_Marke.setColumns(10);

		JLabel lblNachname = new JLabel("Art:");
		contentPane.add(lblNachname, "cell 0 3,alignx trailing");

		textField_Art = new JTextField();
		contentPane.add(textField_Art, "cell 1 3,growx");
		textField_Art.setColumns(10);

		JLabel lblVorname = new JLabel("Farbe:");
		contentPane.add(lblVorname, "cell 0 4,alignx trailing");

		textField_Farbe = new JTextField();
		contentPane.add(textField_Farbe, "cell 1 4,growx");
		textField_Farbe.setColumns(10);

		JLabel lblAlter = new JLabel("Zoll:");
		contentPane.add(lblAlter, "cell 0 5,alignx trailing");

		textField_Zoll = new JTextField();
		contentPane.add(textField_Zoll, "flowy,cell 1 5,growx");
		textField_Zoll.setColumns(10);

		JButton btnDatenHineinschreiben = new JButton("Benutzer \u00FCbernehmen");
		contentPane.add(btnDatenHineinschreiben, "cell 2 7,growx");
		btnDatenHineinschreiben.addActionListener(new btnDatenHineinschreibenListener());

		JButton btnTabelleAktualisieren = new JButton("Tabelle aktualisieren");
		contentPane.add(btnTabelleAktualisieren, "cell 0 15");
		btnTabelleAktualisieren.addActionListener(new btnTabelleAktualisierenListener());

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 19 4 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object [][] {}, new String[] {"ID","Marke","Art","Farbe","Zoll"}));

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
		contentPane.add(btnDatenUpdaten, "cell 2 23,growx");
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
		contentPane.add(btnDatenLschen, "cell 2 25,growx");
		btnDatenLschen.addActionListener(new btnDatenLschenListener());

	}

	public static JTextField getTextField_Art() {
		return textField_Art;
	}

	public static void setTextField_Art(JTextField textField_Art) {
		FahrradGUI.textField_Art = textField_Art;
	}

	public static JTextField getTextField_Zoll() {
		return textField_Zoll;
	}

	public static void setTextField_Zoll(JTextField textField_Zoll) {
		FahrradGUI.textField_Zoll = textField_Zoll;
	}

	public static JTextField getTextField_Marke() {
		return textField_Marke;
	}

	public static void setTextField_Marke(JTextField textField_Marke) {
		FahrradGUI.textField_Marke = textField_Marke;
	}

	public JTextField getTextField_Farbe() {
		return textField_Farbe;
	}

	public void setTextField_Farbe(JTextField textField_Farbe) {
		this.textField_Farbe = textField_Farbe;
	}

	public JTextField getTextField_UpdateSpalteinhalt() {
		return textField_UpdateSpalteinhalt;
	}

	public void setTextField_UpdateSpalteinhalt(JTextField textField_UpdateSpalteinhalt) {
		this.textField_UpdateSpalteinhalt = textField_UpdateSpalteinhalt;
	}

	public JTextField getTextField_UpdateID() {
		return textField_UpdateID;
	}

	public void setTextField_UpdateID(JTextField textField_UpdateID) {
		this.textField_UpdateID = textField_UpdateID;
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

			Fahrrad f = new Fahrrad();
			f.setArt(textField_Art.getText());
			f.setFarbe(textField_Farbe.getText());
			f.setMarke(textField_Marke.getText());
			
			if(textField_Farbe.getText()!="")
			{
				f.setZoll(Integer.parseInt(textField_Zoll.getText()));
			}

			try {
				DBManager db = new DBManager();
				Connection conn = db.getConnection();
				db.InsertFahrrad(conn, f);

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
		List<Fahrrad> f = null;
		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				DBManager db = new DBManager();
				Connection conn = db.getConnection();
				f = db.readFahrrad(conn);


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
			for(int i=0;i<f.size();i++)
			{	
				//"ID","Marke","Art","Farbe","Zoll
				m.addRow(new String[] {""+(f.get(i).getRadid()),f.get(i).getMarke(),f.get(i).getArt(),f.get(i).getFarbe(),""+f.get(i).getZoll()});	
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
				db.deleteFahrrad(id,conn);

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
				db.UpdateFahrrad(conn,spalte,spalteninhalt,id);

			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}


}
