package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.MissingLink;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Arrays;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
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
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[][][][][][][][][][][][][][][grow]"));

		JLabel lblRegisters = new JLabel("Registers (nr bytes)");
		contentPane.add(lblRegisters, "cell 0 0 2 1");

		JLabel lblRam = new JLabel("RAM");
		contentPane.add(lblRam, "cell 2 0");

		JLabel lblPtr = new JLabel("PTR (1)");
		contentPane.add(lblPtr, "cell 0 1,alignx trailing");

		PTRtextField = new JTextField();
		contentPane.add(PTRtextField, "cell 1 1,alignx left");
		PTRtextField.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, "cell 2 1 1 14,grow");

		RAMtable = new JTable();
		RAMtable.setAlignmentY(Component.TOP_ALIGNMENT);
		RAMtable.setAlignmentX(Component.LEFT_ALIGNMENT);
		RAMtable.setRowSelectionAllowed(false);
		RAMtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(RAMtable);

		JTable rowTable = new RowNumberTable(RAMtable);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());

		JLabel lblIc = new JLabel("IC (2)");
		contentPane.add(lblIc, "cell 0 2,alignx trailing");

		ICtextField = new JTextField();
		ICtextField.setColumns(10);
		contentPane.add(ICtextField, "cell 1 2,alignx left");

		JLabel lblSp = new JLabel("SP (2)");
		contentPane.add(lblSp, "cell 0 3,alignx trailing");

		SPtextField = new JTextField();
		contentPane.add(SPtextField, "cell 1 3,alignx left");
		SPtextField.setColumns(10);

		JLabel lblCdr = new JLabel("CDR (5)");
		contentPane.add(lblCdr, "cell 0 5,alignx trailing");

		CDRtextField = new JTextField();
		contentPane.add(CDRtextField, "cell 1 5,alignx left");
		CDRtextField.setColumns(10);

		JLabel lblAr = new JLabel("AR (4)");
		contentPane.add(lblAr, "cell 0 6,alignx trailing");

		ARtextField = new JTextField();
		contentPane.add(ARtextField, "cell 1 6,alignx left");
		ARtextField.setColumns(10);

		JLabel lblMdr = new JLabel("MDR (1)");
		contentPane.add(lblMdr, "cell 0 8,alignx trailing");

		MDRtextField = new JTextField();
		contentPane.add(MDRtextField, "cell 1 8,alignx left");
		MDRtextField.setColumns(10);

		JLabel lblTi = new JLabel("TI (1)");
		contentPane.add(lblTi, "cell 0 10,alignx trailing");

		TItextField = new JTextField();
		contentPane.add(TItextField, "cell 1 10,alignx left");
		TItextField.setColumns(10);

		JLabel lblPi = new JLabel("PI (1)");
		contentPane.add(lblPi, "cell 0 11,alignx trailing");

		PItextField = new JTextField();
		contentPane.add(PItextField, "cell 1 11,alignx left");
		PItextField.setColumns(10);

		JLabel lblSi = new JLabel("SI (1)");
		contentPane.add(lblSi, "cell 0 12,alignx trailing");

		SItextField = new JTextField();
		contentPane.add(SItextField, "cell 1 12,alignx left");
		SItextField.setColumns(10);

		JButton btnRunSingleCycle = new JButton("Run Single Cycle");
		btnRunSingleCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MissingLink.hardwareMethods.mainMachineCycle();
			}
		});
		contentPane.add(btnRunSingleCycle, "cell 1 13");
		
		initTextFields();
		initActionListeners();
	}
	
	private void initTextFields(){
		PTRtextField.setText("00");
		ICtextField.setText("0000");
		SPtextField.setText("0000");
		CDRtextField.setText("0000000000");
		ARtextField.setText("00000000");
		MDRtextField.setText("00");
		TItextField.setText("00");
		PItextField.setText("00");
		SItextField.setText("00");
	}

	private void initActionListeners() {
		PTRtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(PTRtextField.getText());
				if (bytes.length == 1) {
					MissingLink.cpu.PTR = bytes[0];
				} else {
					MissingLink.cpu.PTR = 00;
					PTRtextField.setText("00");
				}
			}
		});
		ICtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(ICtextField.getText());
				if (bytes.length == 2) {
					MissingLink.cpu.IC = bytes;
				} else {
					MissingLink.cpu.IC = new Byte[] { 0, 0 };
					ICtextField.setText("0000");
				}
			}
		});
		SPtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(SPtextField.getText());
				if (bytes.length == 2) {
					MissingLink.cpu.SP = bytes;
				} else {
					MissingLink.cpu.SP = new Byte[] { 0, 0 };
					SPtextField.setText("0000");
				}
			}
		});
		CDRtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(CDRtextField.getText());
				if (bytes.length == 5) {
					MissingLink.cpu.CDR = bytes;
				} else {
					MissingLink.cpu.CDR = new Byte[] { 0, 0, 0, 0, 0 };
					CDRtextField.setText("0000000000");
				}
			}
		});
		ARtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(CDRtextField.getText());
				if (bytes.length == 4) {
					MissingLink.cpu.CDR = bytes;
				} else {
					MissingLink.cpu.CDR = new Byte[] { 0, 0, 0, 0 };
					ARtextField.setText("00000000");
				}
			}
		});
		MDRtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(MDRtextField.getText());
				if (bytes.length == 1) {
					MissingLink.cpu.MDR = bytes[0];
				} else {
					MissingLink.cpu.MDR = 00;
					MDRtextField.setText("00");
				}
			}
		});
		TItextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(TItextField.getText());
				if (bytes.length == 1) {
					MissingLink.cpu.TI = bytes[0];
				} else {
					MissingLink.cpu.TI = 00;
					TItextField.setText("00");
				}
			}
		});
		PItextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(PItextField.getText());
				if (bytes.length == 1) {
					MissingLink.cpu.PI = bytes[0];
				} else {
					MissingLink.cpu.PI = 00;
					PItextField.setText("00");
				}
			}
		});
		SItextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Byte[] bytes = convertToBytes(SItextField.getText());
				if (bytes.length == 1) {
					MissingLink.cpu.SI = bytes[0];
				} else {
					MissingLink.cpu.SI = 00;
					SItextField.setText("00");
				}
			}
		});
	}

	private Byte[] convertToBytes(String s) {
		String[] s_bytes = s.split("(?<=\\G.{2})");
		Byte[] bytes = new Byte[s_bytes.length];
		try {
			int i = 0;
			for (String b : s_bytes)
				bytes[i++] = (byte) Integer.parseInt(b, 16);
			//System.out.println(Arrays.toString(bytes));
		} catch (Exception e) {
			bytes = new Byte[]{};
		}
		return bytes;
	}
}
