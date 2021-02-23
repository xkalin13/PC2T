package pc2t;

public class BPC2 implements Predmet{

    private float points;
    private String name = this.getClass().getName();

    public void addPointsProject(float points){    //max30
        this.points += points;
    }
    public void addPointsHalfSemesterTest(float points){    //max20
        this.points += points;
    }
    public void addPointsFinalTest(float points){    //max50
        this.points += points;
    }
    @Override
    public String getLessonName() {
        return null;
    }

    @Override
    public float getPoints() {
        return this.points;
    }

    @Override
    public boolean getGranted() {
        if(this.points >= minPoints){
            return true;
        }
        else{
            return false;
        }

    }
}
