public class Dolphin extends SwimmingAnimal{

    public Dolphin(float speed, float x, float y){
        this.setSpeed(speed);
        this.setAmplitudeMin(2);
        this.setAmplitudeMax(10);
        this.setDepthMin(-20);
        this.setDepthMax(0);
        this.currentX = x;
        this.currentY = y;
        this.startX = x;
        this.startY = y;
    }


}
