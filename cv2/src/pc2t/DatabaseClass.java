package pc2t;

public class DatabaseClass {
    private String userName;
    private int birthYear;
    private float actualWorkTime;//vyse uvazku
    static float  maxWorkTime = 1;

    public DatabaseClass(String userName,int birthYear){
        this.userName = userName;
        this.birthYear = birthYear;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public static float getMaxWorkTime() {
        return maxWorkTime;
    }

    public float getActualWorkTime() {
        return actualWorkTime;
    }

    public String getUserName() {
        return userName;
    }

    public static void  setMaxWorkTime(float newMaxWorkTime){
        maxWorkTime = newMaxWorkTime;
    }

    public boolean returnActualWorkTime(float newActualWorktime){
        if ((newActualWorktime + actualWorkTime) > maxWorkTime){
            return false;
        }
        else{
            this.actualWorkTime += newActualWorktime;
            return true;
        }
    }
}
