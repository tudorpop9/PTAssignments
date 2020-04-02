package core;

import java.util.Comparator;

public class ComparatorMonom implements Comparator<Monom> {
	public int compare(Monom a, Monom b) {// efectueaza o sortare descrescatoare
		if(a.getExp() > b.getExp()) {
			return -1;
		}else if (a.getExp() < b.getExp()) {
			return 1;
		}else {
			return 0;
		}
	}
}
