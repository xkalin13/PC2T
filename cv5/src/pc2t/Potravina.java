package pc2t;

public class Potravina extends Zbozi {
    @Override
    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameterValid) {
        this.parameter = parameterValid;
    }
    //trvanlivost
    public int parameter;

    public Potravina(String productName,double price,int parameter){
        super(productName,price);
        this.parameter = parameter;
    }
    @Override
    String getJednotka() {
        return "dnu";
    }
}
