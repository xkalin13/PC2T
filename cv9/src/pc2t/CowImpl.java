package pc2t;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CowImpl implements  Animal{
    byte age;
    @Override
    public void sound() {
        System.out.println("mooo");
    }
    @Override
    public void toFile() {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(this.getClass().getName()+".txt"));
            out.write("mooo");
            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "CowImpl{" +
                "age=" + age +
                '}';
    }
}
