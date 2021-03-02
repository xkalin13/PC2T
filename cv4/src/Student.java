
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
			throw new MyException("Prumer je nula",studijniPrumer);
		}

		return studijniPrumer;
	}

	public void setStudijniPrumer(float studijniPrumer) throws MyException {
		//pokud se zadava prumer ktery neni v rozsahu, vyhodi vlastni vyjimku
		if (studijniPrumer > 5 || studijniPrumer < 1){
			throw new MyException("Prumer neni v rozsahu 1-5");
		}
		this.studijniPrumer = studijniPrumer;

	}

}