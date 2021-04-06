package pc2t;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GoatImpl implements Animal{
    byte age;

    @Override
    public void sound() {
        System.out.println("meeeeeh");
    }
    @Override
    public void toFile() {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(this.getClass().getName()+".txt"));
            out.write("meeeeeh");
            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "GoatImpl{" +
                "age=" + age +
                '}';
    }
}
