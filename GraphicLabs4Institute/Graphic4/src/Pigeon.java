public class Pigeon extends FlyingAnimal{
    public Pigeon(float speed, float x, float y){
        this.setSpeed(speed);
        this.currentX = x;
        this.currentY = y;
        this.startX = x;
        this.startY = y;
    }
}
