package core;
import java.util.ArrayList;

public class Polinom{

	ArrayList<Monom> monomList;
	public Polinom() {
		super();
		this.monomList = new ArrayList<Monom>();
	}
	public Polinom(String str) {
		
		this.monomList = new ArrayList<Monom>();
		str = str.toLowerCase(); // se poate accepta x, dar si X 
		str = str.replaceAll("\\s+", ""); // elimina toate white space-urile \n, \t, spatiu etc
		//try {
			for(String dummy: str.split("(?=[+-])")) { // desparte stringul dupa + si - [+-]
				monomList.add(new Monom(dummy));       // si pune delimitatorul in fata fragmentului ?=
			}
		//}catch(NumberFormatException e) {
		//	System.out.println("Format invalid !!!!"); // Daca se intercepteaza o exceptie de format, ea va fi prinsa
		//}
		
		this.monomList.sort(new ComparatorMonom()); // Aranjare in oridine descrescatoare a exponentilor
	}
	
	public float getGrad() { // returneaza gradul polinomului
		for(Monom i: this.monomList) {
			if(i.getCoef() != 0) {
				return i.getExp();
			}
		}
		return 0f;
	}
	
	public static Polinom addP(Polinom p1, Polinom p2) {
		int flag = 0;
		Polinom rez = new Polinom();// creez noul polinom
		for(Monom c: p1.monomList) {
			rez.monomList.add(new Monom(c));
		}
		
		for(Monom m1: p2.monomList) {
			for(Monom m2: rez.monomList) {
				if(m1.getExp() == m2.getExp()) { // al doilea polinom la rezultat
					m2.add(m1);
					flag = 1;
				}
			}
			if (flag == 0) { // daca nu s-a adunat elementul curent cu nimic, se va adauga el insusi la rezultat
				rez.monomList.add(m1);
			}
			flag = 0;
		}
		rez.monomList.sort(new ComparatorMonom()); // Aranjare in oridine descrescatoare a exponentilor
		return rez;
	}
	
	public static Polinom supP(Polinom p1, Polinom p2) {
		int flag = 0;
		Polinom rez = new Polinom();// creez noul polinom
		for(Monom c: p1.monomList) {
			rez.monomList.add(new Monom(c));
		}
		
		for(Monom m1: p2.monomList) {
			for(Monom m2: rez.monomList) {
				if(m1.getExp() == m2.getExp()) { // al doilea polinom la rezultat
					m2.sub(m1);
					flag = 1;
				}
			}
			if (flag == 0) { // daca nu s-a scazu elementul curent cu nimic, se va adauga el insusi la rezultat inmultit cu -1
				m1.setCoef(m1.getCoef()*-1);
				rez.monomList.add(m1);
			}
			flag = 0;
		}
		rez.monomList.sort(new ComparatorMonom());// Aranjare in oridine descrescatoare a exponentilor
		return rez;
	}
	
	public static Polinom derivP(Polinom p) {
		Polinom rez = new Polinom();// creez noul polinom
		for(Monom c: p.monomList) {
			rez.monomList.add(Monom.derivare(new Monom(c))); // derivez fiecare monom in parte
		}
		return rez;
	}
	
	public static Polinom integrP(Polinom p) {
		Polinom rez = new Polinom();// creez noul polinom
		for(Monom c: p.monomList) {
			rez.monomList.add(Monom.integrare(new Monom(c)));
		}
		return rez;
	}
	
	public static Polinom mulP(Polinom p1, Polinom p2) {
		Polinom rez = new Polinom();// creez noul polinom
		/*for(Monom c: p1.monomList) {
			rez.monomList.add(new Monom(c));
		}*/
		ArrayList<Monom> auxList = new ArrayList<Monom>();
		
		for(Monom i: p1.monomList) { // inmulteste termenii
			for(Monom j: p2.monomList) {
				auxList.add(Monom.mul(i, j));
			}
		}
		
		for(Monom i: auxList) {// elimina termenii asemenea
			for(Monom j: auxList) {
				if(i.getExp() == j.getExp() && i!=j) {
					i.add(j);
					j.setCoef(0);
				}
			}
			if(i.getCoef() != 0) {
				rez.monomList.add(i);
			}
		}
		
		rez.monomList.sort(new ComparatorMonom()); //elemente arajate 
		return rez;
	}
	
	public boolean isZero() {// detecteaza daca polinomul e gol
		for(Monom i: this.monomList) {
			if(i.getCoef() != 0) {
				return false;
			}
		}
		return true;
	}
	public Monom msTerm() {//returneaza cel mai semnificativ terment 
		for(Monom i: this.monomList) {
			if(i.getCoef()!= 0) {
				return i;
			}
		}
		return new Monom(0f, 0f);
	}
	
	public static ArrayList<Polinom> divP(Polinom p1, Polinom p2){//https://en.wikipedia.org/wiki/Polynomial_long_division
		ArrayList<Polinom> rez = new ArrayList<Polinom>();
		Polinom q = new Polinom(); //q va fi null
		Polinom r = new Polinom();
		Monom term = new Monom(0f, 0f);
		
		for(Monom i: p1.monomList) {// restul va avea initial o copie a polinomului p1
			r.monomList.add(new Monom(i));
		}
		while(!r.isZero() && (r.getGrad() >= p2.getGrad())) {// daca r nu e null si are gradul mai mare decat p2
			
			term = Monom.div(r.msTerm(), p2.msTerm());
			q.monomList.add(new Monom(term));
			r = Polinom.supP(r, Polinom.mulP(new Polinom(term.toString()), p2));
			//r.monomList.sort(new ComparatorMonom());
		}
		
		rez.add(q);
		rez.add(r);
		return rez;
		
	}
	
	@Override
	public String toString() {
		
		String sol="";
		for(Monom m: monomList) {
			if(monomList.indexOf(m) != 0 && m.getCoef() > 0) {
				sol = sol + "+" + m.toString(); // daca coeficientul e pozitiv
			}else{
					sol += m.toString();
			}
			if(m.getCoef() != 0) {		//pt bugguri la inmultire		
				sol+=" "; // pt lizibilitate
			}
		}
		return sol;
	}
	
	@Override
	public boolean equals(Object o) {
		Polinom p = (Polinom)o;
		return this.monomList.equals(p.monomList);
	}

}
