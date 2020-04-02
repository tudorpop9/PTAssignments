package Main;

import guiPackage.*;

public class MainClass {
	public static void main (String[] args) {
		
		/*try{
			System.out.println(new Monom("2x^4"));
			System.out.println(new Monom("2x"));
			System.out.println(new Monom("x^6"));
			System.out.println(new Monom("-x"));
			System.out.println(new Monom("5"));
			System.out.println(new Monom("+5"));
			System.out.println(new Monom("-5"));
			System.out.println(new Monom("-x^3"));
			System.out.println(new Monom("-7x^6"));
			System.out.println(new Monom("x"));
			System.out.println(new Monom("xsa;go42"));
		}catch (NumberFormatException e) {
			System.out.println("ERRRRRRRRORRRRRReeeeEEEEEEeEe");
		}*/
		
		
		/*String[] test = "-x".split("x");
		for(String i: test) {
			//System.out.println(Boolean.valueOf(i));
			//System.out.println(test[1]);
		}
		System.out.println(Integer.parseInt("-10"));
		ArrayList<String> a = new ArrayList<String>(2);
		
		int j=0;
		for(String i: "2x".split("x")) {
			a.add(i);
			System.out.println(a.get(j));
			a.size()
		}*/
		
		//String str = new String("x+ 1 ");
		//String str2 = new String("x^2 + 2x+ 1");
		//str = str.replaceAll("\\s+", "");
		//str = str.toLowerCase();
		//System.out.println(str);
		/*for(String dummy: str.split("(?=[+-])")) {
			System.out.println(dummy);
		}*/
		//Polinom p = new Polinom(str);
		//Polinom p2 = new Polinom(str2);
		//System.out.println(Polinom.addP(p,p));
		//System.out.println(Polinom.supP(p,p2));
		//System.out.println(Polinom.derivP(p));
		//System.out.println(Polinom.integrP(p));
		//System.out.println(p);
		//System.out.println(p);
		//System.out.println(p2);

		//Polinom rez=Polinom.mulP(p, p2);
		//System.out.println(Polinom.integrP(p2));
		//System.out.println(Polinom.integrP(p));
		//System.out.println(rez);
		
		/*ArrayList<Polinom> rezDiv = Polinom.divP(p2, p);
		for(Polinom i: rezDiv) {
			System.out.println(i);
		}*/
		AppWindow a = new AppWindow();
		a.perform();

	}
}
