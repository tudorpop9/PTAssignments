package businessLayer;

import javax.swing.table.AbstractTableModel;

public class TheTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
    
	@Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
}