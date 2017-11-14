package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btnDatenHineinschreibenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String[] Daten = new String[4];
		Daten[0] = view.Oberflaeche.getTextField_ID().getText();
		Daten[1] = view.Oberflaeche.getTextField_Vorname().getText();
		Daten[2] = view.Oberflaeche.getTextField_Nachname().getText();
		Daten[3] = view.Oberflaeche.getTextField_Alter().getText();
		
		//Zur Kontrolle
		System.out.println(Daten[0]);
		System.out.println(Daten[1]);
		System.out.println(Daten[2]);
		System.out.println(Daten[3]);
		
		model.Datenbank.Datenhineinschreiben(Daten);
		

	}


}
