package pc2t;

import java.sql.SQLOutput;
import java.util.Scanner;

public class DatabaseOperation {
    private static DatabaseClass [] dbClass = new  DatabaseClass[3];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String userNameInput;
        int birthYearInput;
        int selectUser=-1;
        float newWorktime=-1;

        //naplneni pole objektu
        for (int i = 0;i<3;i++) {
            //jmeno
            do {
                System.out.println("Zadej uzivatelske jmeno o minimalne 3 znacich");
                userNameInput = sc.nextLine();
            } while (userNameInput.isEmpty() || userNameInput.length() < 3);
            //rok narozeni
            do {
                System.out.println("Zadej rok narozeni o rozsahu 1930-2021");
                //osetreni pro int
                while (!sc.hasNextInt()){ sc.next(); }
                birthYearInput = sc.nextInt();

                //jelikoz int cte jen cisla, nezaznamena enter, proto je nutne precist enter do prazdna
                sc.nextLine();
            } while (birthYearInput < 1930 || birthYearInput > 2021);

            dbClass[i] = new DatabaseClass(userNameInput, birthYearInput);
            System.out.println("Pocitadlo" + i + " jmeno: " + dbClass[i].getUserName() + " rok:" + dbClass[i].getBirthYear());
        }

        //maximalni uvazek
        do {
            System.out.println("Zadej maximalni povolenou vysi uvazku (kladne cislo):");
            while (!sc.hasNextInt()){ sc.next();}
            DatabaseClass.setMaxWorkTime(sc.nextInt());
            sc.nextLine();
        }while(DatabaseClass.maxWorkTime < 0);

        //nekonecna smycka
        while(true){
            System.out.println("-----------------------------------------------------------------");
                do {
                    System.out.println("Zadej cislo vybraneho uzivatele aktualne 0;1;2:");
                    while (!sc.hasNextInt()){ sc.next();}
                    selectUser=sc.nextInt();
                    sc.nextLine();
                }while(selectUser > 2 || selectUser < 0);

            do {
                System.out.println("Zadej vysi dalsiho uvazku (kladne cislo), pokud nepresahne max vysi, pricte se ke stavajicimu, ktery je nyni:"+dbClass[selectUser].getActualWorkTime());
                while (!sc.hasNextInt()){ sc.next();}
                newWorktime = sc.nextFloat();
                sc.nextLine();
            }while(newWorktime<0);

            System.out.println("Vybran uzivatel s indexem:"+selectUser);
            System.out.println("Jmeno: "+dbClass[selectUser].getUserName());
            System.out.println("Rok narozeni: "+dbClass[selectUser].getBirthYear());

            if (!dbClass[selectUser].returnActualWorkTime(newWorktime)){
                System.out.println("maximalni uvazek:"+dbClass[selectUser].getActualWorkTime()+" presahnut maximalni limit: "+DatabaseClass.getMaxWorkTime()+"-> nelze si vzit vice");
            }
            else{
                System.out.println("maximalni uvazek nepresahnut, aktualni stav uvazku je :"+dbClass[selectUser].getActualWorkTime());
            }
        }
    }


}



