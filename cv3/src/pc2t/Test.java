package pc2t;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // write your code here
        BPC1 bpc1 = new BPC1();
        BPC2 bpc2 = new BPC2();
        BPIS bpis = new BPIS();

        System.out.println("Zadani bodu BPC1 cviceni-max 20, test-max 80" );
        bpc1.addPointsExercise(19.5F);
        bpc1.addPointsFinalTest(70);
        System.out.println("Body z BPC1:  "+bpc1.getPoints());
        System.out.println("Je splnen predmet BPC1?: "+bpc1.getGranted());

        System.out.println("Zadani bodu BPC2 projekt-max 30, pulsemestralni zkouska-max 20, zaverecna zkouska-max 50");
        bpc2.addPointsProject(19.5F);
        bpc2.addPointsHalfSemesterTest(15.5F);
        bpc2.addPointsFinalTest(45);
        System.out.println("Body z BPC2:  "+bpc2.getPoints());
        System.out.println("Je splnen predmet BPC2: "+bpc2.getGranted());

        System.out.println("Zadani zda je splnen zapocet predmetu BPIS");
        bpis.grantCredits(true);
        System.out.println("Body z BPIS jsou pouze stanovene minimum pri splneni predmetu:  "+bpis.getPoints());
        System.out.println("Je splnen predmet BPIS: "+bpis.getGranted());



    }
}
