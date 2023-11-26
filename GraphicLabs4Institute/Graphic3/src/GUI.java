import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

public class GUI {
    private static JFrame jFrame; // само окно
    private static Colony c; // колония, внутрянка
    private static JButton cut = new JButton("cut"); // создаём окно
    private static JLabel BacValue; //для вывода сколько бактерий
    private static JLabel TimeValue; // для вывода сколько времени

    private static int radius; // радиус поля
    private static int speed; // скорость геометрической прогрессии
    static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();// чтобы вывести прогу по центру экрана
    public GUI(int size, int radius, int speed) throws Exception { // конструктор класса. нужен чтобы создать окно

        this.radius = radius; // передаём размер поля
        this.speed = speed; // передаём скорость прогрессии

        c = new Colony(size, radius); // создаём колонию с размером бактерий и размером поля
        BacValue = new JLabel("bacteries: " + c.returnField().size());
        jFrame = new JFrame(); //создаём окно
        jFrame.setResizable(false); //запрещаем ему растягиваться



        //выставляем размеры окна и его положение на экране компьютера
        jFrame.setBounds(dimension.width / 2 - 320, dimension.height / 2 - 240, 640, 480);
        //
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выставляем операцию для закрытия окна по нажатию на крестик
        jFrame.setTitle("Lab 3"); // ставим описание сверху окна - лаба 3


        // создаём кнопку обрезки половины
         // задаём размеры и позицию
       //  // добавляем её на окно

        jFrame.setVisible(true); // отображаем окно

        cut.addMouseListener(new MouseListener() { // добавляем к кнопке считыватель мышки
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) { //если нажали на кнопку
                try {
                    c.cut(); // пытаемся порезать количество бактерий
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }




    public void run() throws Exception { // основная функция
        long startTime = System.currentTimeMillis();
        int b = 1; // это нужно для количества действий
        while (true){ // бесконечный цикл, нужно чтобы программа работала пока на крестик не нажмём
            for (int i = 0; i < b; i ++){ // выполняем столько, сколько нужно по прогрессии
                c.run(); // функция добавления в колонию бактерии
            }
            long endTime = System.currentTimeMillis();
            long durationInSeconds = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);

                drawBacteria dB = new drawBacteria(); //отрисовываем поле
                dB.add(cut); // добавляем кнопку к полю
            TimeValue = new JLabel("time: " + durationInSeconds + " seconds");
            BacValue = new JLabel("bacteries: " + c.returnField().size());
             // создаём поле для вывода значений

                dB.add(BacValue); // добавляем на окно
            dB.add(TimeValue);


                jFrame.add(dB); //добавляем поле с кнопкой на экран
                jFrame.setVisible(true); //отображаем то, что получилось
            b = b * speed; //увеличиваем нашу прогрессию
            Thread.sleep(2000); //делаем задержку в 2 секунды чтобы не офигеть от скорости
        }
    }

    private static class drawBacteria extends JPanel{ //класс рисования поля
        protected void paintComponent(Graphics g){ //функция рисования поля
            super.paintComponent(g); //вызываем конструктор от описанной где-то в языке функции
            //Отрисовываем поле
            g.drawOval(  getWidth() / 2 - (radius * 10),  getHeight() / 2 - (radius * 10),   radius * 20,   radius * 20);
            //
            g.setColor(Color.RED);//ставим цвет красный
            for (int i = 0; i < c.returnField().size(); i++) { //идём по всем бактериям и их тоже отрисовываем
                g.fillOval(  getWidth() / 2 + c.returnField().get(i).getX() * 10 - c.returnField().get(i).getR() * 10 ,  getHeight() / 2 + c.returnField().get(i).getY()  * 10 - c.returnField().get(i).getR() * 10,   c.returnField().get(i).getR() * 20,   c.returnField().get(i).getR() * 20);
            }

        }
    }




}
