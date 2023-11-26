import java.util.ArrayList;

public class Colony { // класс для внутрянки, тут бактерии, размножение и всё в этом духе
 // скорость размножения бактерий
    private final int radius; // радиус круга, в котором бактерии лежат
    private final int size; // размер бактерии
    private static ArrayList<Bacteria> field; // массив для хранения всех наших бактерий

    public Colony(int size, int radius) throws Exception { //конструктор для колонии
        if ((size >= radius)  || (radius <= 0) || (size <= 0)){ //если у нас дурачок пользователь, то выдадим ошибку
            throw new Exception("Error: incorrect data");
        }
        field = new ArrayList<Bacteria>(); //создаём поле с бактериями

        this.radius = radius; //присваиваем радиусу его значение
        this.size = size; //такая же темка с размером бактерии
    }

    public synchronized void run() throws InterruptedException { //функция добавления бактерии к полю
        int x = (int) (Math.random() * (2 * radius) - radius); //генерируем х и y
        int y = (int) (Math.random() * (2 * radius) - radius);
        boolean trigger = true; //триггер, отвечающий за добавление бактерии в поле. если он true, то добавляем
        if (isCircleInside(x,y,size,0,0,radius) == 0){ //если сгенерированные точки лежат внутри круга (внимание! поэтому может не работать геометрическая прогрессия - не все точки обязательно лежат внутри круга, часть отсеивается!!!)
            for (Bacteria bacteria : field) { //идём по массиву бактерий
                if (isCircleInside(x, y, size, bacteria.getX(), bacteria.getY(), size) != 2) { // 2 означает то, что бактерия никак не пересекается с другой. если у нас не 2, то мы не будем добавлять бактерию
                    trigger = false;
                }

            }
            if (trigger){ //если триггер не сработал, то добавляем бактерию
                field.add(new Bacteria(x,y,size));

            }
            else{ //иначе перезапускаемся
                run();
            }
        }

    }


    public synchronized void cut() throws InterruptedException { //функция обрезки
        cutThread ct = new cutThread(); //создаём отдельный поток, чтобы параллельно можно было порезать бактерии
        ct.start(); //запускаем поток
        ct.join(); //ждём пока порежет
    }



    private  static class cutThread extends Thread{ //описываем поток для порезки массива бактерий
        @Override
        public synchronized void run(){ //функция, которую надо описать в потоке, чтобы он работал параллельно программе
            for (int i = 0; i < field.size() / 2 + 1; i++) { //идём по половине массива и +1
                field.remove(i); //удаляем элементы
            }
        }
    }



    public class Bacteria{ // собственно, сама бактерия
        private int x; // координата по иксу
        private int y; // координата по игреку
        private int r; // радиус бактерии, будем размер в радиусе измерять

        public Bacteria(int x, int y, int r) { //конструктор
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public int getX() { //здесь и далее методы, чтобы работать с этими параметрами. подробнее - в методичке к третьей лабе
            return x;
        } //геттеры и сеттеры чтобы получать доступ к данным (типа мы крутые программисты и грамотно всё пишем)

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }
    }


    public String toString(){ //функция если мы вдруг захотим вывести все наши бактерии в консоль
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.size(); i++){
            sb.append("Bacteria #").append(i).append(": ");
            sb.append(field.get(i).getX()).append(", ").append(field.get(i).getY()).append("\n");
        }
        return sb.toString();
    }




    public int isCircleInside(int x1, int y1, int radius1, int x2, int y2, int radius2) { //функция для проверки кругов
        // Вычисляем расстояние между центрами кругов
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (radius1 < radius2){
            if (distance < (radius2 - radius1)){
                return 0; //0 значит что круг ровно внутри другого круга
            }
        }
        if (radius2 < radius1){
            if (distance < (radius1 - radius2)){
                return 0;
            }
        }
        // Проверяем, входит ли центр одного круга внутрь другого круга

         if (distance < radius1 + radius2) {
            return 1; // Частичное вхождение круга 1 в круг 2
        } else {
            return 2; // Круг 1 не внутри круга 2
        }
    }

    public ArrayList<Bacteria> returnField(){ // функция для возврата поля. нужна для вывода в приложение
        return field;
    }
}
