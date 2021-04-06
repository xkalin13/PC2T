package pc2t;

public class Secretarian {
    private EmployeeType employeeType;
    private String nickname, email;
    private char[]  password;
    private int age;
     public Secretarian(String nickname, String email, char[] password, int age,EmployeeType employeeType){
         this.nickname = nickname;
         this.email = email;
         this.password = password;
         this.age = age;
         this.employeeType = employeeType;
     }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
