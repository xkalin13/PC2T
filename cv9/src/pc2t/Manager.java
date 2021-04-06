package pc2t;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Manager <T>{
    private EmployeeType employeeType;
    private String nickname, email;
    private char[]  password;
    private List<Employee> listOfEmployees = new ArrayList<Employee>();
    private List<T> listOfRelationships = new ArrayList<T>();

    public Manager(){

    }

    public void addEmployee(Employee employee){
        this.listOfEmployees.add(employee);
    }
    public void addSecretarian(T secretarian){
        this.listOfRelationships.add(secretarian);
    }
    public List<Employee> getEmployees(){
        return listOfEmployees;
    }
    public List<T> getSecretarian(){
        return listOfRelationships;
    }
    public void sortEmployees(){
        Collections.sort(listOfEmployees, new Comparator<Employee>() {
            @Override
            public int compare(final Employee object1, final Employee object2) {
                return object1.getEmail().compareTo(object2.getEmail());
            }
        });
    }


}

