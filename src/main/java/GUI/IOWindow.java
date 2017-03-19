package GUI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class IOWindow extends JPanel {

	private static final long serialVersionUID = 1L;

	public IOWindow(boolean isInput) {
		setLayout(new MigLayout("", "[grow][]", "[][grow]"));
		
		JLabel lbl_IO = new JLabel(isInput? "Input screen" : "Output screen");
		add(lbl_IO, "cell 0 0");
		
		JButton btnNewButton = new JButton("Clear");
		add(btnNewButton, "cell 1 0");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, "cell 0 1 2 1,grow");
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(isInput);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

	}

}
