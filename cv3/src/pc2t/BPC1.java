package pc2t;

public class BPC1 implements Predmet {

    private float points;
    private String name = this.getClass().getName();

    public void addPointsExercise(float points){    //max20
        this.points += points;
    }
    public void addPointsFinalTest(float points){    //max80
        this.points += points;
    }

    @Override
    public String getLessonName() {
        return this.name;
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
