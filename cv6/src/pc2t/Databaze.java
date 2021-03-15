package pc2t;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Databaze {

    private Scanner sc;
    private Student [] prvkyDatabaze;
    private int posledniStudent;

    public Databaze(int pocetPrvku, Scanner sc)
    {
        prvkyDatabaze = new Student[pocetPrvku];
        this.sc = sc;
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

        prvkyDatabaze[posledniStudent++] = new Student(jmeno,rok);
    }

    public Student getStudent(int idx)	{
        return prvkyDatabaze[idx];
    }

    public void setPrumer(int idx, float prumer) throws MyException	{
        prvkyDatabaze[idx].setStudijniPrumer(prumer);
    }
    public void writeDataToConsole()throws MyException{
        //System.out.println(Jmeno: Karel, rok narozeni: 1986, studijni prumer: 1,2 );
        System.out.println("Vypis vsech");
        try {
            for (int i = 0; i < prvkyDatabaze.length; i++){
                System.out.println("Jmeno: " +prvkyDatabaze[i].getJmeno()+", rok narozeni: " + prvkyDatabaze[i].getRocnik() + ", studijni prumer: "+prvkyDatabaze[i].getStudijniPrumer());
            }
        }catch (NullPointerException np){
            System.out.println("V databazi nejsou data "+np);
        }


    }
    public void writeDataToText(String path) throws Exception{
        File fileOut = new File(path+".txt");
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        for (int i = 0; i < prvkyDatabaze.length; i++){
            bw.write(prvkyDatabaze[i].getJmeno()+" " + prvkyDatabaze[i].getRocnik() + " "+prvkyDatabaze[i].getStudijniPrumer());
            bw.newLine();
        }

        bw.close();
    }
    public void getDataFromText(String path) throws Exception{
        BufferedReader readFile;
        int counter = 0;
        String[] temp;
        readFile = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\"+path+".txt"));//cesta
        String line = readFile.readLine();//vzdycky radek
        while (line != null && counter < prvkyDatabaze.length) { //osetreni prazdneho radku

            System.out.println(line);
            temp = line.split(" ");
            prvkyDatabaze[counter] = new Student(temp[0],Integer.parseInt(temp[1]));
            prvkyDatabaze[counter].setStudijniPrumer(Float.parseFloat(temp[2]));
            counter++;
        }
        readFile.close();//ukonci cteni
}

}