package core;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Monom{
	private float coef;
	private float exp;
	
	
	public Monom(String str) throws NumberFormatException{

	if(str.equals("x")) {// un caz.. netratat || String.split
		str = "+x";
	}
	
	if(str.equals("")) {// un caz.. netratat.. bug-uri la integrare aparea x in loc de nimic
		str = "0";
	}
	
	ArrayList<String> a = new ArrayList<String>();
	for(String i: str.split("x")) {
		a.add(i);
	}
	
	if(str.contains("x") && a.size() == 1) { // un caz.. netratat || String.split
		a.add("");
	}
	
	if(a.size() > 2 || str.equals("+") || str.equals("-")){// daca apar dupa split mai multi termeni, sau niciunul formatul e invalid
		throw new NumberFormatException("Format invalid"); // sau daca se trimite doar + sau -
	}else {
		if(a.size() == 1) {//Daca in list e doar o valoare,
			this.coef = this.coefDetect(a.get(0));
			this.exp = 0;
		}
		else{ // altfel monomul are fie coeficient, fie exponent 
			this.coef = this.coefDetect(a.get(0));
			this.exp = expDetect(a.get(1)); 	// daca un format invalid ajunge totusi aici
		}										// se va arunca oricum NumberFormatException
	}
}


	public Monom(float coef, float exp) {//Constructor pentru campuri+ gettere si settere
		super();
		if(exp >= 0) {
			this.exp = exp;
			this.coef = coef;
		}
		else {
			System.out.println("Exponentul trebuie sa fie pozitiv");
			System.out.println("Monomul creat are implicit coeficientul si exponentul egal cu 0");
			this.exp = 0;
			this.coef = 0;
		}
	}
	public Monom(Monom c) {

		this.coef = c.getCoef();
		this.exp = c.getExp();
	}
	private float coefDetect(String str) {//returneaza returneaza un coeficient dupa caz 
		if(str.equals("+") || str.equals("")) { // 1 pt +
			return 1;
		}else if (str.equals("-")) { // -1 pt -
			return -1;
		}else {
			return Float.parseFloat(str); // valoare int a stringului
		}
	}
	private float expDetect(String str) throws NumberFormatException{//returneaza returneaza un exponent dupa caz 
		if(str.startsWith("^")) {
			float x =  Float.parseFloat(str.substring(1));
			if(x>0) {
				return x;
			}
			else {
				throw new NumberFormatException("!!! Format invalid !!!");
			}
		}else {
			if(str == "") {// daca nu apare nimic la coef, se returneaza 1		
				return 1;
			}
			throw new NumberFormatException("!!! Format invalid !!!");
		}
	}	
	public float getExp() {
		return exp;
	}
	public void setExp(float exp) {
		this.exp = exp;
	}
	public float getCoef() {
		return coef;
	}
	public void setCoef(float coef) {
		this.coef = coef;
	}
	
	public void add(Monom b) {//Aduna, daca se poate,  b-ul la a					
		if(this.getExp() == b.getExp()) {	
			this.coef = this.getCoef() + b.getCoef();
		}								
	}	
	public  void sub(Monom b) {//Scade, daca se poate,  b-ul din a
		if(this.getExp() == b.getExp()) {	
			this.coef = this.getCoef() - b.getCoef();
		}	
	}	
	public static Monom mul(Monom a, Monom b) {//Inmultirea monoamelor, va returna un monom cu urmatoarele campuri: suma exponentilor (daca e negativa se returneaza null)
		Monom c= null;					//produsul coeficientiilor (daca e 0 se returneaza null)
		if(a.getCoef() != 0 || b.getCoef() != 0) {
			if(a.getExp() + b.getExp() >= 0)
			c = new Monom(a.getCoef() * b.getCoef(), a.getExp() + b.getExp());
		}								
		return c;	
	}
	
	public static Monom div(Monom a, Monom b) throws ArithmeticException{//Impartirea monoamelor, va returna un monom cu urmatoarele campuri: diferenta exponentilor (daca e negativa se returneaza null)
		Monom c= null;					//catul coeficientiilor (daca e 0 se returneaza null)
		if(b.getCoef() == 0) {				// daca coeficientul celui de-al doilea polinom e 0, se va arunca o exceptie aritmetica
			throw new ArithmeticException("Impartire la zero!!!!");
		}else {
			if(a.getExp() - b.getExp() >= 0) {
				c = new Monom(a.getCoef() / b.getCoef(), a.getExp() - b.getExp());
			}
		}								
		return c;	
	}
	
	public static Monom derivare(Monom a) { //Derivarea monomului va returna un nou monom care reprezinta monomul initial derivat
		Monom rez= null;			 //daca initial exponentul lui a e 0, atuncti se va returna null	
		if(a.getExp() != 0) {
			rez = new Monom(a.getCoef() * a.getExp(), a.getExp() - 1);
		}else {
			rez = new Monom(0, 0);
		}
		return rez;	
	}
	
	public static Monom integrare(Monom a) { //Integrarea monomului va returna un nou monom care reprezinta monomul initial integrat
		Monom rez = new Monom(a.getCoef() * 1/(a.getExp() + 1), a.getExp() + 1);//Daca noul coeficient e subunitar, se considera 0
		return rez;	
	}
	
	@Override
	public String toString() {//Metoda toString va afisa corespunzator monomul
		DecimalFormat form = new DecimalFormat("#.###");//format pt floaturi
		String rez;
		
		float coef = this.coef;
		float powe = this.exp;
		if(coef == 0f) {
			return "";
		}else if(coef == 1f) {
			rez = "x";
		}else if(coef == -1f) {
			 rez = "-x";
		}else {
			rez = form.format(coef) + "x"; 
		}
	
		if(powe == 0f) {
			return form.format(coef);
		}else if (powe == 1f){
			return rez;
		}else {
			rez += "^" + form.format(powe);
		}
		return rez;	
	}
	@Override
	public boolean equals(Object o) {
		Monom m = (Monom)o;
		return (this.coef == m.getCoef() && this.exp == m.getExp());
	}
}
