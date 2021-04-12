package pc2t;

import java.util.Scanner;


public class Pole {

	private final Scanner sc;
	private Hranol[] mojeHranoly;

	// Konstruktor s velikosti pole
	public Pole(int velikost)
	{
		mojeHranoly = new Hranol[velikost];
		sc = new Scanner(System.in);
	}
	
	// vlozeni hranolu do pole na prvni volnou pozici
	void zadejHranol(){

		boolean drevena;
		float delka = 0;
		float vyska = 0;

		System.out.println("Zadejte delku podstavy hranolu");
		while(!sc.hasNextFloat())
		{
			sc.next();
		}
		delka = sc.nextFloat();

		System.out.println("Zadejte vysku hranolu");

		while(!sc.hasNextFloat())
		{
			sc.next();
		}
		vyska = sc.nextFloat();

		System.out.println("Je drevena?");

		while(!sc.hasNextBoolean())//ZDE BYLA CHYBA -datovy typ float
		{
			sc.next();
		}
		drevena = sc.nextBoolean();

		if (Hranol.getPocetHranolu() > mojeHranoly.length)
		{
			System.out.println("Pole uz je zaplneno");
			return;
		}

		mojeHranoly[Hranol.getPocetHranolu()] = new Hranol(delka,vyska,drevena);//ZDE BYLA CHYBA
		//neni nutne nastavovat
		/*
			mojeHranoly[].setHrana(delka);
			mojeHranoly[Hranol.getPocetHranolu()].setHrana(vyska);
			mojeHranoly[Hranol.getPocetHranolu()].setDreveny(drevena);

		 */
	}
	
	// vypis objemu vsech hranolu
	void vypoctiObjem()
	{
		for (int i = 0;i < Hranol.getPocetHranolu(); i++){
			System.out.println("Objem " + (i + 1)+ " hranolu je " + mojeHranoly[i].vypoctiObjem());//ZDE BYLA CHYBA
		}

	}
	
	// vypis obsahu podstavy vsech hranolu
	void vypoctiObsahPodstavy()
	{
		for (int i = 0; i < Hranol.getPocetHranolu(); i++){
			System.out.println("Obsah " + (i + 1) + " hranolu je " + mojeHranoly[i].vypoctiObsah());//ZDE BYLA CHYBA vypoctiObjem
		}

	}
	
	// nalezeni indexu nejmensiho hranolu
	int najdiNejmensiObjem()
	{
		float min = mojeHranoly[0].vypoctiObjem();//ZDE BYLA CHYBA- nemuze porovnavat objem s 0
		int idx = 0;

		for (int i = 0; i < Hranol.getPocetHranolu(); i++)
		{
			if (mojeHranoly[i].vypoctiObjem() < min){
				min = mojeHranoly[i].vypoctiObjem();
				idx = i + 1;
			}
		}
		return idx;
	}
	
	// zjisteni celkoveho poctu drevenych kostek
	int zjistiPocetDrevenych(){

		int pocetDrevenych = 0;

		for (int i = 0 ; i < Hranol.getPocetHranolu(); i++)//ZDE BYLA CHYBA - iterace pole out of bounds
		{
			if (mojeHranoly[i].jeDreveny())//ZDE BYLA CHYBA jedno rovna se je vzdy true && boolean nepotrebuje porovnani
			{
				pocetDrevenych++;
			}
		}
		return pocetDrevenych;
	}

}
