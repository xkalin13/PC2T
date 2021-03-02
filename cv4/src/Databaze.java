import java.util.Scanner;

public class Databaze {
	public Databaze(int pocetPrvku)
	{
		prvkyDatabaze = new Student[pocetPrvku];
		sc = new Scanner(System.in);
	}
	
	public void setStudent() throws MyException {
		System.out.println("Zadejte jmeno studenta");
		String jmeno = sc.nextLine();

		System.out.println("Zadejte rok");
		int rok = sc.nextInt();
		sc.nextLine();

		if (rok < 0){
			throw new MyException("Nemuze byt zaporne cislo");
		}

<<<<<<< HEAD
=======

>>>>>>> 2641610 (added cv4)
		prvkyDatabaze[posledniStudent++] = new Student(jmeno,rok);
	}
	
	public Student getStudent(int idx)
	{
		return prvkyDatabaze[idx];
	}
	
	public void setPrumer(int idx, float prumer) throws MyException
	{
		prvkyDatabaze[idx].setStudijniPrumer(prumer);
	}
	
	private Scanner sc;
	private Student [] prvkyDatabaze;
	private int posledniStudent;
}