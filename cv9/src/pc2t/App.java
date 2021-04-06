package pc2t;

import java.util.List;

/**
 * Abstraktni trida- muze obsahovat tela metod - avsak musi mit jednu a vice abstraktnich-bez telnych metod (dedi se metody ktere mohou byt vykonany potomky stejne)
 * Interface- je ciste abstraktni - nemuze mit zadnou metodu s telem (ciste jako sablona)
 *
 * Enum-zrychluje cas kompilace, casto se i lepe cte a nemuze dojit k chybam TYPOs
 *
 * @author Jan Kalinic xkalin13
 */
public class App {
    public static void main(String[] args) {
        //Employee 5x
        Employee e1 = new Employee("nick1","Fmail",new char[]{'2','x','Q','6'}, EmployeeType.ACTIVE);
        Employee e2 = new Employee("nick2","Dmail",new char[]{'2','x','Q','6'}, EmployeeType.ACTIVE);
        Employee e3 = new Employee("nick3","Bmail",new char[]{'2','x','Q','6'}, EmployeeType.DELETED);
        Employee e4 = new Employee("nick4","Cmail",new char[]{'2','x','Q','6'}, EmployeeType.ACTIVE);
        Employee e5 = new Employee("nick5","Amail",new char[]{'2','x','Q','6'}, EmployeeType.INACTIVE);

        //Manager
        Manager m = new Manager();

        //Secretarian
        Secretarian s1 = new Secretarian("nickS1","mailS1", new char[]{'2','x','Q','6'},35,EmployeeType.ACTIVE);
        Secretarian s2 = new Secretarian("nickS2","mailS2", new char[]{'2','x','Q','6'},30,EmployeeType.ACTIVE);

        m.addSecretarian(s1);
        m.addEmployee(e1);
        m.addEmployee(e2);
        m.addEmployee(e3);
        m.addEmployee(e4);
        m.addEmployee(e5);

        List<Employee> employeesList = m.getEmployees();

        System.out.println("EMPLOYEES");

        for (var value:employeesList) {
            System.out.println(value.getNickname()+", "+value.getEmail()+", "+value.getEmployeeType());
        }

        System.out.println("EMPLOYEES-EMAIL SORTED");
        m.sortEmployees();

        for (var value:employeesList) {
            System.out.println(value.getNickname()+", "+value.getEmail()+", "+value.getEmployeeType());
        }

        List<Secretarian> secretarianList = m.getSecretarian();

        System.out.println("SECRETARIAN");

        for (var value:secretarianList) {
            System.out.println(value.getNickname()+", "+value.getEmail()+", "+value.getEmployeeType()+", "+value.getAge());
        }

        //zvirata
        Cat cat = new Cat();
        Dog dog = new Dog();
        Pig pig = new Pig();
        Cow cow = new Cow();
        Goat goat = new Goat();

        cat.sound();
        dog.sound();
        pig.sound();
        cow.sound();
        goat.sound();

        CatImpl catImpl = new CatImpl();
        DogImpl dogImpl = new DogImpl();
        PigImpl pigImpl = new PigImpl();
        CowImpl cowImpl = new CowImpl();
        GoatImpl goatImpl = new GoatImpl();

        catImpl.sound();
        dogImpl.sound();
        pigImpl.sound();
        cowImpl.sound();
        goatImpl.sound();

        //ukazka toFile, jsou implementovany ve vsech animal tridach implementujici interface
        catImpl.toFile();
        catImpl.toString();

    }
}
