package pc2t;

public class MyException  extends Exception{

    public MyException(String s){
        System.out.println(s);
    }
    public MyException(String message,float prumer){
        System.out.println(message + " - " + prumer);

    }
}
