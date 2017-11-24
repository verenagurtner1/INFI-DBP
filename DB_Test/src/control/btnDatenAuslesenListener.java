package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import view.Oberflaeche;

public class btnDatenAuslesenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String[]> DBDaten = model.Datenbank.Datenherholen();
		//DefaultTableModel m =(DefaultTableModel) Oberflaeche.
	}

}
