

public abstract class FlyingAnimal extends Animal {
    public void doMovement(float time) {
        if (getElementY(startY) != startY && getElementY(startY) > 0){
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
            currentY = (float) (startY + (1 - Math.pow((timeValue / tmax) - 1, 2)) * (y.get(0) - startY));
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
