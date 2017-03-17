package backend;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import GUI.RealMachine;

/**This class connects GUI and backend. Is controlled by EntryPoint
 * @author grant
 *
 */
public class MissingLink {
	private CPU cpu;
	private RAM ram;
	
	public MissingLink(){
		RealMachine frame = new RealMachine();
		frame.setVisible(true);
		cpu = new CPU();
		ram = new RAM();
		//ram.memory[0][0] = new Byte((byte)20);
		List<Integer> range = IntStream.rangeClosed(0, 255)
			    .boxed().collect(Collectors.toList());
		frame.RAMtable.setModel(new DefaultTableModel(ram.memory, range.toArray()));
		DefaultTableModel model = (DefaultTableModel)frame.RAMtable.getModel();
		this.resizeColumnWidth(frame.RAMtable);
		
		
		ram.memory[0][0] = new Byte((byte)0xFF);
		//model.setDataVector(ram.memory, range.toArray());
	}
	
	
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        columnModel.getColumn(column).setMinWidth(25);
	        columnModel.getColumn(column).setMaxWidth(25);
	        columnModel.getColumn(column).setWidth(25);
	    }
	}
}
