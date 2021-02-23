package pc2t;

public class BPIS implements Predmet{
    private boolean granted;
    private String name = this.getClass().getName();

    public void grantCredits(boolean granted){
        this.granted = granted;
    }

    @Override
    public String getLessonName() {
        return this.name;
    }

    @Override
    public float getPoints() {
        if (granted){
            return minPoints;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean getGranted() {
        return this.granted;
    }
}
