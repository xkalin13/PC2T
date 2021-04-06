package pc2t;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PigImpl implements  Animal{
    byte age;
    @Override
    public void sound() {
        System.out.println("hrrrroooh");
    }
    @Override
    public void toFile() {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(this.getClass().getName()+".txt"));
            out.write("hrrrroooh");
            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "PigImpl{" +
                "age=" + age +
                '}';
    }
}
