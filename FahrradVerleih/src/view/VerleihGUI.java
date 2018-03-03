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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.DBManager;
import model.Fahrrad;
import model.Verleih;
import net.miginfocom.swing.MigLayout;
import view.FahrradGUI.btnDatenHineinschreibenListener;
import view.FahrradGUI.btnDatenLschenListener;
import view.FahrradGUI.btnDatenUpdatenListener;
import view.FahrradGUI.btnTabelleAktualisierenListener;

public class VerleihGUI extends JFrame {

	static private JPanel contentPane;
	static private JTextField textField_DatumZ;
	static private JTable table;
	static private JTextField textField_BenutzerID;
	private Spieler splr;
	private JTextField textField_Radid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerleihGUI frame = new VerleihGUI();
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
	public VerleihGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 555);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][][][][][][][][][][][][grow][][grow][][][][][][][][][][][][][][][]"));

		JLabel lblDatenbankverwaltung = new JLabel("Verleih");
		lblDatenbankverwaltung.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDatenbankverwaltung, "cell 1 0 2 1");

		JLabel lblId = new JLabel("BenutzerID:");
		contentPane.add(lblId, "cell 0 1,alignx trailing");

		textField_BenutzerID = new JTextField();
		contentPane.add(textField_BenutzerID, "cell 1 1,growx");
		textField_BenutzerID.setColumns(10);

		JLabel lblNachname = new JLabel("RadID:");
		contentPane.add(lblNachname, "cell 0 3,alignx trailing");

		textField_Radid = new JTextField();
		contentPane.add(textField_Radid, "cell 1 3,growx");
		textField_Radid.setColumns(10);

		JLabel lblDatumzurck = new JLabel("DatumZur\u00FCck:");
		contentPane.add(lblDatumzurck, "cell 0 4,alignx trailing");

		textField_DatumZ = new JTextField();
		contentPane.add(textField_DatumZ, "cell 1 4,growx");
		textField_DatumZ.setColumns(10);

		JButton btnDatenHineinschreiben = new JButton("Benutzer \u00FCbernehmen");
		contentPane.add(btnDatenHineinschreiben, "cell 2 8,growx");
		btnDatenHineinschreiben.addActionListener(new btnDatenHineinschreibenListener());

		JButton btnTabelleAktualisieren = new JButton("Tabelle aktualisieren");
		contentPane.add(btnTabelleAktualisieren, "cell 0 16");
		btnTabelleAktualisieren.addActionListener(new btnTabelleAktualisierenListener());

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 18 4 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object [][] {}, new String[] {"BenutzerID","RadID","DatumAusleihen","DatumZurück"}));

	}

	public static JTextField getTextField_DatumZ() {
		return textField_DatumZ;
	}

	public static void setTextField_DatumZ(JTextField textField_DatumZ) {
		VerleihGUI.textField_DatumZ = textField_DatumZ;
	}

	public static JTextField getTextField_BenutzerID() {
		return textField_BenutzerID;
	}

	public static void setTextField_BenutzerID(JTextField textField_BenutzerID) {
		VerleihGUI.textField_BenutzerID = textField_BenutzerID;
	}

	public JTextField getTextField_Radid() {
		return textField_Radid;
	}

	public void setTextField_Radid(JTextField textField_Radid) {
		this.textField_Radid = textField_Radid;
	}

	public class btnDatenHineinschreibenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			Verleih v = new Verleih();

			if(textField_Radid.getText()!="")
			{
				v.setRadid(Integer.parseInt(textField_Radid.getText()));
			}
			if(textField_BenutzerID.getText()!="")
			{
				v.setBenutzerid(Integer.parseInt(textField_BenutzerID.getText()));
			}
			v.setDatumzurueck(textField_DatumZ.getText());

			try {
				DBManager db = new DBManager();
				Connection conn = db.getConnection();
				db.InsertVerleih(conn, v);

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
		List<Verleih> v = null;
		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				DBManager db = new DBManager();
				Connection conn = db.getConnection();
				v = db.readVerleih(conn);


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
			for(int i=0;i<v.size();i++)
			{	
				//"BenutzerID","RadID","DatumAusleihen","DatumZurück"
				m.addRow(new String[] {""+(v.get(i).getBenutzerid()),""+v.get(i).getRadid(),""+v.get(i).getDatumaus(),v.get(i).getDatumzurueck(),});	
			}

		}
	}


}
