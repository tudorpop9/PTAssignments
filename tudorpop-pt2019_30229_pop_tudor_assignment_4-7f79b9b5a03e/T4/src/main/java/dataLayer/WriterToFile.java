package dataLayer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class WriterToFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4648113661613642230L;
	private FileWriter fw;
	private String file;
	
	public WriterToFile(String file) {
		super();
		try {
			this.fw = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.file = file;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public void writeBill(String dataWr) throws IOException {
		BufferedWriter out = new BufferedWriter(fw);
        out.write(dataWr);
        out.close();
	}
	
}
