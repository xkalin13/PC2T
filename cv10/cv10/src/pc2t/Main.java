package pc2t;

public class Main {
    public static void main(String[] args) {

        Pole pole = new Pole(3);

        pole.zadejHranol();
        pole.zadejHranol();
        pole.zadejHranol();

        pole.vypoctiObjem();
        pole.vypoctiObsahPodstavy();
        System.out.println("Nejmensi objem ma objekt cislo: "+pole.najdiNejmensiObjem());
        System.out.println("Pocet drevenych: "+pole.zjistiPocetDrevenych());

    }
}
