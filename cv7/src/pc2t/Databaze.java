package pc2t;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;

public class Databaze {

	private Scanner sc;
	//jmeno, data
	private HashMap<String, Student> prvkyDatabaze;


	public Databaze(Scanner sc)
	{
		this.sc	= sc;
		prvkyDatabaze = new HashMap<>();
	}
	
	public boolean setStudent() throws MyException {
		System.out.println("Zadejte jmeno studenta");
		String jmeno = sc.nextLine();

		System.out.println("Zadejte rok");
		int rok = sc.nextInt();
		sc.nextLine();

		if (rok < 0){
			throw new MyException("Nemuze byt zaporne cislo");
		}
		if (prvkyDatabaze.containsKey(jmeno)){//nutne osetreni proti prepisu
			prvkyDatabaze.put(jmeno,new Student(jmeno,rok));
			return true;
		}
		else{
			return false;
		}


	}
	public Student getStudent(String name)	{
		return prvkyDatabaze.get(name);
	}

	public boolean setPrumer(String jmeno, float prumer){
		if (prvkyDatabaze.containsKey(jmeno)){//existuje?
			return prvkyDatabaze.get(jmeno).setStudijniPrumer(prumer);
		}
		else {
			return false;
		}
	}
	public boolean deleteStudent(String jmeno){
		if (prvkyDatabaze.containsKey(jmeno)){//existuje?
			prvkyDatabaze.remove(jmeno);
			return true;
		}
		else {
			return false;
		}
	}
	public void vypisDatabaze(){
		Set<String> jmena = prvkyDatabaze.keySet();
		if (jmena.isEmpty()){//osetreni prazdneho
			System.out.println("ZADNY STUDENT TU NENI");

		}else{
			System.out.println("VSICHNI STUDENTI:");

		}

		for(String jmeno:jmena){//foreach
			System.out.println(jmeno);
		}
	}

}