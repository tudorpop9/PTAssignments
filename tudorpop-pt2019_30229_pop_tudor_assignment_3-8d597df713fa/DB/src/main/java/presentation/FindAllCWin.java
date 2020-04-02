package presentation;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.ClientDAO;
import model.Client;



@SuppressWarnings("serial")
public class FindAllCWin extends JFrame {

	
	private JTable logArea;
	private JScrollPane logScroll;
	
	public FindAllCWin() {
		super("FindAllClients");
		this.setVisible(true);
		this.setSize(500,500);
		
		ClientDAO cd = new ClientDAO();
		List<String> head = cd.createTableHead();
		List<Client> data = cd.createTableData();
		Object[][] stringData = new Object[data.size()][head.size()];
		
		for(int i=0; i< data.size() ; i++) {
			Object[] arr = data.get(i).getDataInArray();
			for(int j=0;j<head.size();j++) {
				stringData[i][j] =  arr[j];
				//System.out.println(head.get(j));
			}
		}
		
		
		
		this.logArea = new JTable(stringData,head.toArray());
		this.logScroll = new JScrollPane(logArea);
		this.add(logScroll);
		
	}
	
	public void showRezult() {
		//add db rez to the TextArea
		
		
	}
	
}
	

