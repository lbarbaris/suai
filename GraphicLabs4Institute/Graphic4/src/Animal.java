import java.awt.*;
import java.util.ArrayList;

public abstract class Animal {
    // начальные координаты
    protected float startX = 0;
    protected float startY = 0;
    // текущие координаты
    protected float currentX = 0;
    protected float currentY = 0;
    // скорость движения
    protected float speed;
    // таймер движения
    protected float timeValue = 0;
    protected ArrayList<Float> x = new ArrayList<Float>();
    protected ArrayList<Float> y = new ArrayList<Float>();

    public Animal() {
        this.currentX = 0;
        this.currentY = 0;
    }









    // установка скорости движения объекта
    public void setSpeed(float speed) {
        if (speed >= 1) {
            this.speed = speed;
        } else {
            this.speed = 1;
        } }

    //public abstract void doMovement(float time);  // здесь поставлено protected так как иначе метод не вызывается в других классах


    public float getElementX(float el2){
        try {
            return x.get(0);
        }
        catch (Exception e){

        }
        return el2;
    }

    public float getElementY(float el2){
        try {
            return y.get(0);
        }
        catch (Exception e){

        }
        return el2;
    }





    // прорисовка объекта по указанным координатам x, y
    public void drawAt(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillOval(x - 5, y - 5, 10, 10);
    }

    public float getCurrentX() {
        return currentX;
    }

    public float getCurrentY() {
        return currentY;
    }
}

