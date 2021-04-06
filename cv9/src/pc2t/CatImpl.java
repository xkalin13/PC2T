package pc2t;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CatImpl implements Animal{
    byte age;
    @Override
    public void sound() {
        System.out.println("meow");
    }

    @Override
    public void toFile() {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(this.getClass().getName()+".txt"));
            out.write("meow");
            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "CatImpl{" +
                "age=" + age +
                '}';
    }
}
