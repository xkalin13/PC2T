package projekt;

import projekt.DatabaseActions.Database;

import java.sql.SQLException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws SQLException {
        boolean runBool = true;
        int selection=0;
        Scanner sc = new Scanner(System.in);
        InputHandler handler = new InputHandler();

        while (runBool){
            System.out.println("-----------ZAKLADNI-------------");
            System.out.println("-1...SMAZAT TABULKY");//DONE
            System.out.println("-2...VYTVORIT TABULKY");//DONE
            System.out.println("-3...NAPLNIT TABULKY");//DONE
            System.out.println("-4...VYPIS VSECH V TABULCE");//DONE

            System.out.println("1...Pridat novou osobu");//DONE
            System.out.println("2...Propustit osobu");//DONE
            System.out.println("3...Vypis vsech informaci jedne osoby");//DONE
            System.out.println("4...Vypis ucitelu podle poctu zaku");
            System.out.println("5...Vypis informaci vsech osob abecedne dle kategorii");
            System.out.println("6...Vypis financi - stipendia a mzdy");

            System.out.println("--------STUDENT-------");
            System.out.println("7...Pridat znamku");
            System.out.println("8...Vypis vsech ucitelu");

            System.out.println("--------UCITEL--------");
            System.out.println("9...Pridat studenta");
            System.out.println("10...Odebrat studenta");
            System.out.println("11...Vypis studentu podle prumeru");

            System.out.println("-----------SQL-------------");
            System.out.println("12...Pripojit k databazi pomoci uzivatele ze souboru");
            System.out.println("13...Nacist databazi ze souboru");
            System.out.println("14...Ulozit databazi do souboru");
            //System.out.println("15...Vymazat osobu z SQL souboru");
           // System.out.println("16...Nacist osobu z SQL souboru");


            do{
                selection = sc.nextInt();sc.nextLine();
            }while (selection < -5 && selection > 18);


            switch (selection){
                case -1:System.out.println("Smazani vsech tabulek");Database.getInstance().deleteAllTables();break;
                case -2:System.out.println("Vytvoreni tabulek");Database.getInstance().createTables();break;
                case -3:System.out.println("Vlozeni defaultnich uzivatelu a znamek");Database.getInstance().insertDefaultValues();break;
                case -4:System.out.println("Vypis vsech ");Database.getInstance().getAllUserRecords();Database.getInstance().getAllClassesRecords();break;

                case 1:System.out.println("Pridani nove osoby");handler.createNewUser(sc);break;
                case 2:System.out.println("Propusteni osoby");handler.deleteUser(sc);break;
                case 3:System.out.println("Vypis vsech informaci jedne osoby");handler.getUserInfo(sc);break;
                case 4:System.out.println("Vypis ucitelu podle poctu zaku");handler.printTeachersSortedByStudentCount();break;
                case 5:System.out.println("5...Vypis informaci vsech osob abecedne dle kategorii");handler.printAlphabeticallySortedUsers();break;
                case 6:System.out.println("6...Vypis financi - stipendia a mzdy");handler.printFinances();break;
                case 7:System.out.println("7...Pridat znamku");handler.addMark(sc);break;
                case 8:System.out.println("8...Vypis vsech ucitelu");handler.printStudentsTeachers(sc);break;

                case 9:System.out.println("9...Pridat studenta");handler.addClass(sc);break;
                case 10:System.out.println("10...Odebrat studenta");handler.deleteClass(sc);break;
                case 11:System.out.println("11...Vypis studentu podle prumeru");handler.printSortedStudents(sc);break;

                case 12:System.out.println("12...Pripojit k databazi pomoci uzivatele ze souboru");handler.connectWithFile();break;
                case 13:System.out.println("13...Nacist databazi ze souboru");handler.importDatabase();break;
                case 14:System.out.println("14...Ulozit databazi do souboru");handler.exportDatabase();break;
                //case 15:System.out.println("15...Vymazat osobu z SQL souboru");break;
                //case 16:System.out.println("16...Nacist osobu z SQL souboru");handler.importRecord();break;

                default:runBool=false;break;
            }

        }
    }
}
