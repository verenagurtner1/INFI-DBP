package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.btnDatenAuslesenListener;
import control.btnDatenHineinschreibenListener;
import control.btnDatenLschenListener;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Oberflaeche extends JFrame {

	static private JPanel contentPane;
	static private JTextField textField_Vorname;
	static private JTextField textField_Nachname;
	static private JTextField textField_Alter;
	static private JTable table;
	static private JTextField textField_ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Oberflaeche frame = new Oberflaeche();
					frame.setVisible(true);
					model.Datenbank.VerbindungAufbauen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Oberflaeche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][grow]", "[][][][][][][][grow][][grow]"));
		
		JLabel lblDatenbankverwaltung = new JLabel("Datenbankverwaltung");
		lblDatenbankverwaltung.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDatenbankverwaltung, "cell 1 0 2 1");
		
		JLabel lblId = new JLabel("ID:");
		contentPane.add(lblId, "cell 1 1,alignx trailing");
		
		textField_ID = new JTextField();
		contentPane.add(textField_ID, "cell 2 1,growx");
		textField_ID.setColumns(10);
		
		JLabel lblVorname = new JLabel("Vorname:");
		contentPane.add(lblVorname, "cell 1 3,alignx trailing");
		
		textField_Vorname = new JTextField();
		contentPane.add(textField_Vorname, "cell 2 3,growx");
		textField_Vorname.setColumns(10);
		
		JLabel lblNachname = new JLabel("Nachname:");
		contentPane.add(lblNachname, "cell 1 4,alignx trailing");
		
		textField_Nachname = new JTextField();
		contentPane.add(textField_Nachname, "cell 2 4,growx");
		textField_Nachname.setColumns(10);
		
		JLabel lblAlter = new JLabel("Alter:");
		contentPane.add(lblAlter, "cell 1 5,alignx trailing");
		
		textField_Alter = new JTextField();
		contentPane.add(textField_Alter, "cell 2 5,growx");
		textField_Alter.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 7 4 1,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object [][] {}, new String[] {"ID","Vorname","Nachname","Alter"}));
		
		JButton btnDatenLschen = new JButton("Daten l\u00F6schen");
		contentPane.add(btnDatenLschen, "cell 0 9,growx");
		btnDatenLschen.addActionListener(new btnDatenLschenListener());
		
		JButton btnDatenUpdaten = new JButton("Daten updaten");
		contentPane.add(btnDatenUpdaten, "cell 1 9,growx");
		
		JButton btnDatenHineinschreiben = new JButton("Daten hineinschreiben");
		contentPane.add(btnDatenHineinschreiben, "cell 2 9,growx");
		btnDatenHineinschreiben.addActionListener(new btnDatenHineinschreibenListener());
		
		JButton btnDatenAuslesen = new JButton("Daten auslesen");
		contentPane.add(btnDatenAuslesen, "cell 3 9,growx");
		btnDatenAuslesen.addActionListener(new btnDatenAuslesenListener());
		
	}

	public static JTextField getTextField_Vorname() {
		return textField_Vorname;
	}

	public static void setTextField_Vorname(JTextField textField_Vorname) {
		Oberflaeche.textField_Vorname = textField_Vorname;
	}

	public static JTextField getTextField_Nachname() {
		return textField_Nachname;
	}

	public static void setTextField_Nachname(JTextField textField_Nachname) {
		Oberflaeche.textField_Nachname = textField_Nachname;
	}

	public static JTextField getTextField_Alter() {
		return textField_Alter;
	}

	public static void setTextField_Alter(JTextField textField_Alter) {
		Oberflaeche.textField_Alter = textField_Alter;
	}

	public static JTextField getTextField_ID() {
		return textField_ID;
	}

	public static void setTextField_ID(JTextField textField_ID) {
		Oberflaeche.textField_ID = textField_ID;
	}

}
