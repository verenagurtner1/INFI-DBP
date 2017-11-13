package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btnDatenLschenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		model.Datenbank.Datenlöschen();

	}

}
