import java.awt.*;

import static java.lang.Math.*;

public abstract class SwimmingAnimal extends Animal {
    private float amplitudeMin;
    private float depthMin;
    private float depthMax;
    private float amplitudeMax;

    public void setAmplitudeMin(float amplitude){
        this.amplitudeMin = amplitude;
    }


    public void setAmplitudeMax(float amplitude){
        this.amplitudeMax = amplitude;
    }


    public void setDepthMin(float depth){
        this.depthMin = depth;
    }


    public void setDepthMax(float depth){
        this.depthMax = depth;
    }


    public void doMovement(float time) {

        if (getElementY(startY) > depthMin && getElementY(startY) <= depthMax && abs((getElementX(startX) - startX)) >= amplitudeMin && abs((getElementX(startX) - startX)) < amplitudeMax) {
// увеличение счетчика времени
            timeValue += time;
// расчет максимального расстояния движения
// для линейного движения - гипотенуза прямоугольного треугольника
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
            currentX = startX + timeValue / tmax * (x.get(0) - startX);
            currentY = (float) (startY + (sin(2 * PI * pow(timeValue / tmax, 2))) * (y.get(0) - startY));

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
