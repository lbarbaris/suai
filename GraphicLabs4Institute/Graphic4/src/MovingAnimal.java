import java.awt.*;

import static java.lang.Math.*;

public abstract class MovingAnimal extends Animal {

    public void doMovement(float time) {
        if (getElementX(startX) != startX && abs(getElementX(startX) - startX) >= 1 && abs(getElementX(startX) - startX) <= 5) {


            timeValue += time;

            float speedX = (float) Math.abs(speed * Math.cos(Math.atan2(getElementY(startY) - startY, x.get(0) - startX)));

// расчет максимального времени движения
            float tmax = Math.abs(x.get(0) - startX) / speedX;
// если движение закончилось
            if (timeValue >= tmax) {
                currentX = x.get(0);
                currentY = y.get(0);
                startX = x.get(0);
                startY = y.get(0);
                timeValue = 0;

                return;
            }
// расчет текущих координат


            currentY = (float) (startY + (sin(PI * sqrt(1 - (timeValue / tmax)))) * (1 - startY));
            currentX = startX + timeValue / tmax * (x.get(0) - startX);


        }
        else{
            try {
                x.remove(0);
                y.remove(0);
            }
            catch (Exception e){

            }
        }
    }





}
