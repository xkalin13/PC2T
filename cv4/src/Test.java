import java.util.InputMismatchException;
import java.util.Scanner;


public class Test {

	static Databaze mojeDatabaze = new Databaze(1);
	static Scanner sc = new Scanner(System.in);
<<<<<<< HEAD
=======
	private static int idx;
	private static float prumer;
	private static int volba;
	private static boolean run=true;
>>>>>>> 2641610 (added cv4)

	public static int pouzeCelaCisla(Scanner sc)	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
<<<<<<< HEAD
			sc.nextLine();
=======
>>>>>>> 2641610 (added cv4)
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
<<<<<<< HEAD
			sc.nextLine();
=======
>>>>>>> 2641610 (added cv4)
		}
		catch(Exception e)
		{
			System.out.println("Nelze zadat jinak nez cislo s desetinou carkou"+e);
			sc.nextLine();
			cislo = pouzeFloat(sc);
		}

		return cislo;
	}
	public static void setStudentCount() {
		try {
			System.out.println("Zadejte pocet studentu");
			//osetreni celeho cisla, constructor ocekava int
			mojeDatabaze = new Databaze(pouzeCelaCisla(sc));
		}
		//jelikoz se vybira velikost pole, nemuze byt zaporne cislo
		catch (NegativeArraySizeException n) {
			System.out.println("Nelze zadat zaporne cislo-chyba: " + n);
			System.out.println("-----------------------------------------------");
			setStudentCount();
		}
	}
	public static void insertNewStudent(){
		try{
			mojeDatabaze.setStudent();
		}
		//pokud se zada vic nez pozadovany pocet studentu-zde nemuzeme opakovat zadavani
		catch (ArrayIndexOutOfBoundsException a){
			System.out.println("Nelze zadat id zaka mimo index pole/ zadneho pole: " + a);
			System.out.println("-----------------------------------------------");

		}
		//vlastni vyjimka zaporneho cisla
		catch (MyException m){
			System.out.println("------------");
			insertNewStudent();
		}
		//osetreni cisla
		catch (InputMismatchException i){
			System.out.println("Nastala vyjimka typu "+i.toString());
			System.out.println("Musite zadat cele cislo");
			System.out.println("------------");
			insertNewStudent();
		}
	}
	public static void setGrade() throws MyException{
		try {
			System.out.println("Zadejte index + ENTER a prumer studenta");
			//osetreni celeho cisla a floatu
			//nutnost osetrit mimo index a null
			mojeDatabaze.setPrumer(pouzeCelaCisla(sc), pouzeFloat(sc));
		}
		//zadane id muze by mimo index
		catch (ArrayIndexOutOfBoundsException a){
			System.out.println("Nelze zadat id zaka mimo index pole: " + a);
			System.out.println("-----------------------------------------------");
			setGrade();
		}
<<<<<<< HEAD
		//null pointer z vysokeho cisla nasledneho procesu
		catch (NullPointerException np){
			System.out.println("Nelze zadat id - prvky databaze jsou prazdne na tomto indexu:  " +np);
			System.out.println("-----------------------------------------------");
			setGrade();
		}
=======
>>>>>>> 2641610 (added cv4)

	}
	public static void getInfo() throws MyException{
		try{
			System.out.println("Zadejte index studenta");
			//osetreni cele cisla
			Student info=mojeDatabaze.getStudent(pouzeCelaCisla(sc));
			System.out.println("Jmeno: " + info.getJmeno() + " rok narozeni: " + info.getRocnik() + " prumer: " + info.getStudijniPrumer());
		}
		catch (NullPointerException np){
			System.out.println("Nelze hledat v prazdnem poli " + np);
			System.out.println("-----------------------------------------------");
		}
		//zadane id muze by mimo index
		catch (ArrayIndexOutOfBoundsException a){
			System.out.println("Nelze zadat id zaka mimo index pole: " + a);
			System.out.println("-----------------------------------------------");
		}
	}

	public static void main (String[] args) throws Exception{
<<<<<<< HEAD
		boolean run=true;
=======
>>>>>>> 2641610 (added cv4)
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vytvoreni nove databaze");
			System.out.println("2 .. vlozeni noveho studenta");
			System.out.println("3 .. nastaveni prumeru studenta");
			System.out.println("4 .. vypis informace o studentovi");
			System.out.println("5 .. ukonceni aplikace");

			//osetrujem pouze vstup na cele cislo
<<<<<<< HEAD
			switch(pouzeCelaCisla(sc))
=======
			volba=pouzeCelaCisla(sc);


			switch(volba)
>>>>>>> 2641610 (added cv4)
			{
				case 1:
					setStudentCount();
					break;
				case 2:
					insertNewStudent();
					break;
				case 3:
					setGrade();
					break;
				case 4:
					getInfo();
					break;
				case 5:
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
