package core;

import java.util.Comparator;

public class DateComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		if(o1.split(" ")[0].compareTo(o2.split(" ")[0]) < 0 ) {
			return -1;
		}else if(o1.split(" ")[0].compareTo(o2.split(" ")[0]) > 0) {
			return 1;
		}
		return 0;
	}
	
}
