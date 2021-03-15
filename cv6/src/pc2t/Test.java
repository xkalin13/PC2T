package pc2t;
import pc2t.Databaze;
import pc2t.MyException;
import pc2t.Student;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Test {

    static Scanner sc = new Scanner(System.in);
    static Databaze mojeDatabaze = new Databaze(1,sc);


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
    public static void setStudentCount() {
        try {
            System.out.println("Zadejte pocet studentu");
            //osetreni celeho cisla, constructor ocekava int
            mojeDatabaze = new Databaze(pouzeCelaCisla(sc),sc);
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
            System.out.println("Nastala vyjimka typu "+i);
            System.out.println("Musite zadat cele cislo");
            System.out.println("------------");
            sc.nextLine();
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
        //null pointer z vysokeho cisla nasledneho procesu
        catch (NullPointerException np){
            System.out.println("Nelze zadat id - prvky databaze jsou prazdne na tomto indexu:  " +np);
            System.out.println("-----------------------------------------------");
            setGrade();
        }

    }
    public static void writeInfoToText(){
        try{
            System.out.println("Zadej nazev vystupniho souboru bez pripony");
            String path = sc.next();// do prvni mezery
            mojeDatabaze.writeDataToText(path);

        }catch (ArrayIndexOutOfBoundsException ob){
            System.out.println("mimo index databaze, rozsirte nejdrive databazi"+ob);
        }
        catch (FileNotFoundException fnf){
            System.out.println("soubor nenalezen"+fnf);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void getInfoFromText(){
        try{
            System.out.println("Zadej nazev vstupniho souboru bez pripony");
            String path = sc.next();// do prvni mezery
            mojeDatabaze.getDataFromText(path);

        }
        catch (FileNotFoundException fnf){
            System.out.println("Soubor nenalezen"+fnf);
            getInfoFromText();
        }
        catch (NullPointerException np){
            System.out.println("prvek v souboru je prazdny, je nutne aby byl uzivatel spravne zapsany"+np);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void getInfo() throws MyException {
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
        boolean run=true;
        while(run)
        {
            System.out.println("Vyberte pozadovanou cinnost:");
            System.out.println("1 .. vytvoreni nove databaze");
            System.out.println("2 .. vlozeni noveho studenta");
            System.out.println("3 .. nastaveni prumeru studenta");
            System.out.println("4 .. vypis informace o studentovi");
            System.out.println("5 .. vypis informaci o vsech");
            System.out.println("6 .. vypis informaci do souboru");
            System.out.println("7 .. cteni informaci ze souboru");
            System.out.println("8 .. ukonceni aplikace");

            //osetrujem pouze vstup na cele cislo
            switch(pouzeCelaCisla(sc))
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
                    mojeDatabaze.writeDataToConsole();
                    break;
                case 6:
                    writeInfoToText();
                    break;
                case 7:
                    getInfoFromText();
                    break;
                case 8:
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
