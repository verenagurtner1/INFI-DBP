package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Oberflaeche frame = new Oberflaeche();
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
	public Oberflaeche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[32.00][grow][][][grow]", "[][][][][][grow][][grow]"));
		
		JLabel lblDatenbankverwaltung = new JLabel("Datenbankverwaltung");
		lblDatenbankverwaltung.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDatenbankverwaltung, "cell 2 0");
		
		JLabel lblVorname = new JLabel("Vorname:");
		contentPane.add(lblVorname, "cell 2 1,alignx trailing");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 1,growx");
		textField.setColumns(10);
		
		JLabel lblNachname = new JLabel("Nachname:");
		contentPane.add(lblNachname, "cell 2 2,alignx trailing");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 3 2,growx");
		textField_1.setColumns(10);
		
		JLabel lblAlter = new JLabel("Alter:");
		contentPane.add(lblAlter, "cell 2 3,alignx trailing");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 3 3,growx");
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 5 4 1,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnDatenLschen = new JButton("Daten l\u00F6schen");
		contentPane.add(btnDatenLschen, "cell 1 7,growx");
		
		JButton btnDatenHineinschreiben = new JButton("Daten hineinschreiben");
		contentPane.add(btnDatenHineinschreiben, "cell 2 7,growx");
		
		JButton btnDatenAuslesen = new JButton("Daten auslesen");
		contentPane.add(btnDatenAuslesen, "cell 3 7,growx");
	}

}
