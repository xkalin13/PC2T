package pc2t;

public class Employee {
    private EmployeeType employeeType;
    private String nickname, email;
    private char[]  password;

    public Employee(String nickname,String email, char[] password, EmployeeType employeeType){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
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
    public char[] getPassword() {
        return password;
    }
}
