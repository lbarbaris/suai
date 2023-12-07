import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

public class GUI {
    private static JFrame jFrame; // само окно
    static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();// чтобы вывести прогу по центру экрана
    public GUI() throws Exception { // конструктор класса. нужен чтобы создать окно
        jFrame = new JFrame(); //создаём окно
        //выставляем размеры окна и его положение на экране компьютера
        jFrame.setBounds(dimension.width / 2 - 320, dimension.height / 2 - 240, 1280, 720);
        //
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выставляем операцию для закрытия окна по нажатию на крестик
        jFrame.setTitle("Lab 4"); // ставим описание сверху окна - лаба 3
        jFrame.setVisible(true); // отображаем окно
    }


    public void run() throws Exception { // основная функция
                GraphicPanel  gp = new GraphicPanel();
                jFrame.add(gp);
                jFrame.setVisible(true); //отображаем то, что получилось
    }






}
