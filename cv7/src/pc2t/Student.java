package pc2t;

public class Student {

	private String jmeno;
	private int rocnik;
	private float studijniPrumer;

	public Student(String jmeno, int rocnik)
	{
		this.jmeno = jmeno;
		this.rocnik = rocnik;
	}
	
	public String getJmeno() {
		return jmeno;
	}
	
	public int getRocnik() {
		return rocnik;
	}
	
	public float getStudijniPrumer()throws MyException {
		//pokud je nulovy prumer-neni zatim zadan, vyhodi vyjimku
		if (studijniPrumer == 0){
			throw new MyException("nula");
		}
		return studijniPrumer;
	}

	public  boolean setStudijniPrumer(float studijniPrumer) {
		//pokud se zadava prumer ktery neni v rozsahu, vyhodi vlastni vyjimku
		if (studijniPrumer > 5 || studijniPrumer < 1){
			return false;
		}
		this.studijniPrumer = studijniPrumer;
		return true;

	}

}