package pc2t;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Test {

	static Scanner sc = new Scanner(System.in);
	static Databaze mojeDatabaze = new Databaze(sc);


	public static int pouzeCelaCisla(Scanner sc)	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
			sc.nextLine();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	public static float pouzeFloat(Scanner sc)	{
		float cislo = 0;
		try
		{
			cislo = sc.nextFloat();
			sc.nextLine();
		}
		catch(Exception e)
		{
			System.out.println("Nelze zadat jinak nez cislo s desetinou carkou"+e);
			sc.nextLine();
			cislo = pouzeFloat(sc);
		}

		return cislo;
	}
	public static void getStudentInfo() throws MyException{
		System.out.println("zadej jmeno studenta");
		Student currentStudent = mojeDatabaze.getStudent(sc.nextLine());//fetch uzivatele
		if (currentStudent != null)
			System.out.println("Jmeno: " + currentStudent.getJmeno() + " rok narozeni: " + currentStudent.getRocnik() + " prumer: " + currentStudent.getStudijniPrumer());
		else{
			System.out.println("student neexistuje");
		}
	}
	public static void deleteStudent(){
		System.out.println("zadejte jmeno studenta");
		if (mojeDatabaze.deleteStudent(sc.nextLine())){
			System.out.println("student smazan");
		}
		else{
			System.out.println("student neexistuje");
		}
	}
	public static void insertNewStudent(){
		try{
			if (!mojeDatabaze.setStudent()){
				System.out.println("Zadan studen ktery je v databazi");
			}
		}
		//vlastni vyjimka zaporneho cisla
		catch (MyException m){
			System.out.println("------------");
			insertNewStudent();
		}
		//osetreni cisla
		catch (InputMismatchException i){
			System.out.println("Nastala vyjimka typu "+i);
			System.out.println("Musite zadat cele cislo");
			System.out.println("------------");
			sc.nextLine();
			insertNewStudent();
		}
	}
	public static void setGrade() throws MyException{
		System.out.println("Zadejte jmeno + enter a prumer studenta");

		if (!mojeDatabaze.setPrumer(sc.nextLine(), pouzeFloat(sc))){
			System.out.println("Prumer neexistuje");
		}

	}


	public static void main (String[] args) throws Exception{
		boolean run=true;
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			//System.out.println("1 .. vytvoreni nove databaze"); depricated -uz neni potreba zadavat velikost
			System.out.println("1 .. vlozeni noveho studenta");
			System.out.println("2 .. nastaveni prumeru studenta");
			System.out.println("3 .. vypis informace o studentovi");
			System.out.println("4 .. odebrani studenta");
			System.out.println("5 .. vypis informaci");
			System.out.println("6 .. ukonceni aplikace");

			//osetrujem pouze vstup na cele cislo
			switch(pouzeCelaCisla(sc))
			{
				case 1:
					insertNewStudent();
					break;
				case 2:
					setGrade();
					break;
				case 3:
					getStudentInfo();
					break;
				case 4:
					deleteStudent();
					break;
				case 5:
					mojeDatabaze.vypisDatabaze();
					break;
				case 6:
					run=false;
					break;
					//osetreni 1-5 vstupu
				default:
					System.out.println("ZADANE CISLO NENI 1-5");
					break;
			}
		}
	}
}
