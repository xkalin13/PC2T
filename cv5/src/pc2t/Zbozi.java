package pc2t;

public abstract class Zbozi {

    public String productName;
    public Double priceWithoutDPH;
    static int dph = 21;

    //nazev
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    //cena
    public Double getPrice() {
        return priceWithoutDPH*(1 +  (dph)/(float)100);
    }
    public void setPriceWithoutDPH(Double priceWithoutDPH) {
        this.priceWithoutDPH = priceWithoutDPH;
    }

    //dph
    public static int getDph() {
        return dph;
    }
    public static void setDph(int dph) {
        Zbozi.dph = dph;
    }

    public Zbozi(String product,double price){
        this.productName = product;
        this.priceWithoutDPH = price;
    }

    abstract String getJednotka();
    abstract public int getParameter();

}
