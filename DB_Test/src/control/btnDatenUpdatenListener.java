package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btnDatenUpdatenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		model.Datenbank.Updatefunktion();
	}

}
