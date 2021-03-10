package pc2t;

public class Test {

    public static void main(String[] args) {
	// write your code here
        Zbozi[] zbozi = new  Zbozi[4];
        zbozi[0] = new Potravina("Rohlik", 1.5,  1);
        zbozi[1] = new Naradi("Kleste", 278.0,  24);
        zbozi[2] = new Potravina("Chleba", 20.8,  6);
        zbozi[3] = new Potravina("Jablko", 51.0,  20);
        for(int i =0; i < zbozi.length;i++){
            System.out.println(zbozi[i].productName + ", cena:" + (double) Math.round(zbozi[i].getPrice() * 100) / 100 + ", trvanlivost: " +zbozi[i].getParameter() +" " + zbozi[i].getJednotka());
        }
    }
}
