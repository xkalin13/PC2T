package pc2t;


public class Hranol extends Ctverec{

	float vyska;
	float hrana;
	boolean zeDreva;
	static int pocetHranolu = 0; //ZDE BYLA CHYBA- ze staticke metody volat nestatickou promennou

	// konstruktor se zadanim delky hrany, vysky a materialu
	public Hranol(float hrana,float vyska, boolean drevena){
		super(hrana);//ZDE BYLA CHYBA
		this.vyska = vyska;
		this.hrana = hrana;
		zeDreva = drevena;
		pocetHranolu++;
	}

	// vypocet objemu hranolu
	float vypoctiObjem() {
		return this.vyska * this.vypoctiObsah();//Obsah podstavy * vyska
	}
	
	// nastaveni materialu
	void setDreveny(boolean dreveny){
		zeDreva = dreveny;
	}
	
	// zjisteni materialu
	boolean jeDreveny(){
		return this.zeDreva;
	}
	
	// zjisteni celkoveho poctu existujicich kostek
	static int getPocetHranolu(){
		return pocetHranolu;
	}
	
	// zjisteni vysky hranolu
	@Override
	float getHrana(){					
		return this.hrana;
	}
	// nastaveni vysky hranolu
	@Override
	void setHrana(float delka){			
		this.hrana = delka;
	}
	
	
}
