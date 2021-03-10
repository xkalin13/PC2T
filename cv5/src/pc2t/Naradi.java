package pc2t;

public class Naradi extends Zbozi{
    @Override
    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    public int parameter;

    public Naradi(String productName,double price,int parameter){
        super(productName,price);
        this.parameter = parameter;
    }

    @Override
    String getJednotka() {
        return "mesicu";
    }
}
