import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;


public class GraphicPanel extends JPanel{
    private JButton addD = new JButton("dolphin");
    private JButton addP = new JButton("pigeon");
    private JButton addC = new JButton("cheetah");


    private ArrayList<Cheetah> cheetahs = new ArrayList<Cheetah>();
    private ArrayList<Pigeon> pigeons = new ArrayList<Pigeon>();
    private ArrayList<Dolphin> dolphins = new ArrayList<Dolphin>();


    // создание таймера
    private Timer timer = new Timer(40, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < dolphins.size(); i++){
                dolphins.get(i).doMovement((float) 0.04);
            }
            for (int i = 0; i < pigeons.size(); i++){
                pigeons.get(i).doMovement((float) 0.04);
            }

            for (int i = 0; i < cheetahs.size(); i++){
                cheetahs.get(i).doMovement((float) 0.04);
            }
            repaint();
        }
    });

    // границы мировой системы координат
    private float worldXMin = 0; private float worldXMax = 100; private float worldYMin = -25; private float worldYMax = 10;



    public GraphicPanel () {
        addD.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dolphins.add(new Dolphin(random(12,18),random(0,90),random(-19,-1)));
            }
        });
        addD.setBounds(0,0,20,10);
        this.add(addD);
        addP.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pigeons.add(new Pigeon(random(15,25),random(0,90),random(1,10)));
            }
        });
        addP.setBounds(0,0,20,10);
        this.add(addP);
        addC.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cheetahs.add(new Cheetah(random(20,30),random(0,90),0));
            }
        });
        addC.setBounds(0,0,20,10);
        this.add(addC);
// Добавление обработчика нажатия кнопки мыши
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {

// установка координат конечной точки движения объекта

                float x = screenXtoWorldX(evt.getX());
                float y = screenYtoWorldY(evt.getY());
                for (int i = 0; i < dolphins.size(); i++){
                    dolphins.get(i).x.add(x);
                    dolphins.get(i).y.add(y);
                }

                for (int i = 0; i < cheetahs.size(); i++){
                    cheetahs.get(i).x.add(x);
                    cheetahs.get(i).y.add((float) 0);
                }
                for (int i = 0; i < pigeons.size(); i++){
                    pigeons.get(i).x.add(x);
                    pigeons.get(i).y.add(y);
                }
            }

        });
// создание экземпляра класса движущегося объекта
        dolphins.add( new Dolphin(12,0, -5));
        pigeons.add(new Pigeon(15, 0, 10));
        cheetahs.add( new Cheetah(20,0, 0));
// запуск таймера
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        // вызов метода суперкласса
        super.paintComponent(g);
        for (int i = 0; i < dolphins.size(); i++){
            int subjX = worldXtoScreenX(dolphins.get(i).getCurrentX());
            int subjY = worldYtoScreenY(dolphins.get(i).getCurrentY());
            dolphins.get(i).drawAt(g, subjX, subjY, Color.BLUE);
        }

// прорисовка движущегося объекта
        for (int i = 0; i < pigeons.size(); i++){
            int subjX = worldXtoScreenX(pigeons.get(i).getCurrentX());
            int subjY = worldYtoScreenY(pigeons.get(i).getCurrentY());
            pigeons.get(i).drawAt(g, subjX, subjY, Color.GRAY);
        }
        for (int i = 0; i < cheetahs.size(); i++){
            int subjX = worldXtoScreenX(cheetahs.get(i).getCurrentX());
            int subjY = worldYtoScreenY(cheetahs.get(i).getCurrentY());
            cheetahs.get(i).drawAt(g, subjX, subjY, Color.ORANGE);
        }
        g.setColor(Color.BLUE);
        g.drawLine(worldXtoScreenX(worldXMin), worldYtoScreenY(0), worldXtoScreenX(worldXMax), worldYtoScreenY(0));
// прорисовка текстовой строки с координатами объекта
        g.drawString("x: " + dolphins.get(0).getCurrentX() + " y: " + dolphins.get(0).getCurrentY(), 10, 20);
        g.setColor(Color.GRAY);
        g.drawString("x: " + pigeons.get(0).getCurrentX() + " y: " + pigeons.get(0).getCurrentY(), 10, 40);
        g.setColor(Color.ORANGE);
        g.drawString("x: " + cheetahs.get(0).getCurrentX() + " y: " + cheetahs.get(0).getCurrentY(), 10, 60);
    }


    // процедуры преобразования мировых координат в экранные
    private int worldXtoScreenX(float wx) {
        return Math.round(this.getWidth() * (wx - this.worldXMin) / (this.worldXMax - this.worldXMin));
    }
    private int worldYtoScreenY(float wy) {
        return Math.round(this.getHeight() * (1 - (wy - this.worldYMin) / (this.worldYMax -
                this.worldYMin)));
    }


    // процедуры преобразования экранных координат в мировые
    private float screenXtoWorldX(int sx) {
        return (float) sx / this.getWidth() * (this.worldXMax - this.worldXMin) + this.worldXMin;
    }
    private float screenYtoWorldY(int sy) {
        return (1 - (float) sy / this.getHeight()) * (this.worldYMax - this.worldYMin) + this.worldYMin;
    }

    private float random(float a, float b){
        Random r = new Random();
        return r.nextFloat(b - a + 1) + a;
    }





}

