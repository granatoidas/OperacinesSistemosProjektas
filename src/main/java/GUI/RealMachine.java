package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;

public class RealMachine extends JFrame {

	public JPanel contentPane;
	public JTextField PTRtextField;
	public JTextField ICtextField;
	public JTextField SPtextField;
	public JTextField CDRtextField;
	public JTextField ARtextField;
	public JTextField MDRtextField;
	public JTextField TItextField;
	public JTextField PItextField;
	public JTextField SItextField;
	public JScrollPane scrollPane;
	public JTable RAMtable;

	/**
	 * Create the frame.
	 */
	public RealMachine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow]", "[][grow][][][][][][][][][][][][grow]"));
		
		JLabel lblRegisters = new JLabel("Registers");
		contentPane.add(lblRegisters, "cell 0 0 2 1");
		
		JLabel lblRam = new JLabel("RAM");
		contentPane.add(lblRam, "cell 2 0");
		
		JLabel lblPtr = new JLabel("PTR");
		contentPane.add(lblPtr, "cell 0 1,alignx trailing");
		
		PTRtextField = new JTextField();
		PTRtextField.setEditable(false);
		contentPane.add(PTRtextField, "cell 1 1,alignx left");
		PTRtextField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, "cell 2 1 1 13,grow");
		
		RAMtable = new JTable();
		RAMtable.setAlignmentY(Component.TOP_ALIGNMENT);
		RAMtable.setAlignmentX(Component.LEFT_ALIGNMENT);
		RAMtable.setRowSelectionAllowed(false);
		RAMtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(RAMtable);	
		
		JTable rowTable = new RowNumberTable(RAMtable);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
		rowTable.getTableHeader());
		
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
