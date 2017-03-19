package GUI;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CustomDocumentListener implements DocumentListener {

	private JTextField txtField;
	
	public CustomDocumentListener(JTextField txtField){
		this.txtField = txtField;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {}

	@Override
	public void insertUpdate(DocumentEvent e) {
		txtField.setBackground(Color.RED);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		txtField.setBackground(Color.RED);
	}
	
}
