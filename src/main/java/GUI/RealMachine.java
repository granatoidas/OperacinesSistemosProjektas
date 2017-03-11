package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;

public class RealMachine extends JFrame {

	private JPanel contentPane;
	private JTextField PTRtextField;
	private JTextField ICtextField;
	private JTextField SPtextField;
	private JTextField CDRtextField;
	private JTextField ARtextField;
	private JTextField MDRtextField;
	private JTextField TItextField;
	private JTextField PItextField;
	private JTextField SItextField;
	private JTable table;
	private JLabel lblRegisters;
	private JLabel lblRam;

	/**
	 * Create the frame.
	 */
	public RealMachine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow]", "[][][][][][][][][][][][][][grow]"));
		
		lblRegisters = new JLabel("Registers");
		contentPane.add(lblRegisters, "cell 0 0 2 1");
		
		lblRam = new JLabel("RAM");
		contentPane.add(lblRam, "cell 2 0");
		
		JLabel lblPtr = new JLabel("PTR");
		contentPane.add(lblPtr, "cell 0 1,alignx trailing");
		
		PTRtextField = new JTextField();
		PTRtextField.setEditable(false);
		contentPane.add(PTRtextField, "cell 1 1,alignx left");
		PTRtextField.setColumns(10);
		
		table = new JTable();
		contentPane.add(table, "cell 2 1 1 13,grow");
		
		JLabel lblIc = new JLabel("IC");
		contentPane.add(lblIc, "cell 0 2,alignx trailing");
		
		ICtextField = new JTextField();
		ICtextField.setEditable(false);
		ICtextField.setColumns(10);
		contentPane.add(ICtextField, "cell 1 2,alignx left");
		
		JLabel lblSp = new JLabel("SP");
		contentPane.add(lblSp, "cell 0 3,alignx trailing");
		
		SPtextField = new JTextField();
		SPtextField.setEditable(false);
		contentPane.add(SPtextField, "cell 1 3,alignx left");
		SPtextField.setColumns(10);
		
		JLabel lblCdr = new JLabel("CDR");
		contentPane.add(lblCdr, "cell 0 5,alignx trailing");
		
		CDRtextField = new JTextField();
		CDRtextField.setEditable(false);
		contentPane.add(CDRtextField, "cell 1 5,alignx left");
		CDRtextField.setColumns(10);
		
		JLabel lblAr = new JLabel("AR");
		contentPane.add(lblAr, "cell 0 6,alignx trailing");
		
		ARtextField = new JTextField();
		ARtextField.setEditable(false);
		contentPane.add(ARtextField, "cell 1 6,alignx left");
		ARtextField.setColumns(10);
		
		JLabel lblMdr = new JLabel("MDR");
		contentPane.add(lblMdr, "cell 0 8,alignx trailing");
		
		MDRtextField = new JTextField();
		MDRtextField.setEditable(false);
		contentPane.add(MDRtextField, "cell 1 8,alignx left");
		MDRtextField.setColumns(10);
		
		JLabel lblTi = new JLabel("TI");
		contentPane.add(lblTi, "cell 0 10,alignx trailing");
		
		TItextField = new JTextField();
		TItextField.setEditable(false);
		contentPane.add(TItextField, "cell 1 10,alignx left");
		TItextField.setColumns(10);
		
		JLabel lblPi = new JLabel("PI");
		contentPane.add(lblPi, "cell 0 11,alignx trailing");
		
		PItextField = new JTextField();
		PItextField.setEditable(false);
		contentPane.add(PItextField, "cell 1 11,alignx left");
		PItextField.setColumns(10);
		
		JLabel lblSi = new JLabel("SI");
		contentPane.add(lblSi, "cell 0 12,alignx trailing");
		
		SItextField = new JTextField();
		SItextField.setEditable(false);
		contentPane.add(SItextField, "cell 1 12,alignx left");
		SItextField.setColumns(10);
	}

}
