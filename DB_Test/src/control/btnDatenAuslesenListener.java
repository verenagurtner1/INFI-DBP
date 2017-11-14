package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Datenbank;

public class btnDatenAuslesenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String[]> DBDaten = model.Datenbank.Datenherholen();

	}

}
