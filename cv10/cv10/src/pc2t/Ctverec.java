package pc2t;



class Ctverec {

	private float hrana;

	// konstruktor se zadanim delky hrany ctverce
	public Ctverec(float hrana){
		this.hrana = hrana;//ZDE BYLA CHYBA
	}

	// vypocet obsahu ctverce
	float vypoctiObsah(){
		return (hrana * hrana);//ZDE MOHLA BYT chyba float do int ?
	}
	// zjisteni delky hrany ctverce
	float getHrana(){					
		return hrana;
	}
	// nastaveni delky hrany ctverce
	void setHrana(float delka){			
		this.hrana = delka;
	}

}
