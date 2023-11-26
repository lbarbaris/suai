import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.abs;

public class GUI {
        private static JFrame jFrame; // само окно, куда мы будем выводить графику
        private double x = 0; // храним значение x
        private double n = 0; // храним значение n
        private double reference = 0; // храним значение эталонное
        private double mc = 0; // храним значение по маклорену

        public GUI(){ // конструктор, который мы вызываем, чтобы создать окно и всё к нему прилегающее
                jFrame = new JFrame(); // создаём окно
                jFrame.setResizable(false); // запрещаем окну растягиваться
                jFrame.setLayout(null);

                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // чтобы вывести прогу по центру экрана

                //выставляем размеры окна и его положение на экране компьютера
                jFrame.setBounds(dimension.width / 2 - 320, dimension.height / 2 - 240, 640, 480);
                //
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // выставляем операцию для закрытия окна по нажатию на крестик
                jFrame.setTitle("Lab 2"); // ставим описание сверху окна - лаба 2



                JButton exit = new JButton("exit"); // создаём кнопку выхода
                exit.setBounds(0,412,70,30); // задаём размеры и позицию
                jFrame.add(exit); // добавляем её на окно



                JButton reset = new JButton("reset"); // создаём кнопку сброса
                reset.setBounds(0,350,70,30); // задаём размеры и позицию
                jFrame.add(reset); // добавляем её на окно



                JButton init = new JButton("init"); // создаём кнопку инициализации вычислений
                init.setBounds(100,350,70,30); // задаём размеры и позицию
                jFrame.add(init); // добавляем её на окно



                JScrollBar ScrollValue = new JScrollBar(); // создаём полосу прокрутки
                ScrollValue.setBounds(0,190,25,100); // задаём размеры и позицию
                jFrame.add(ScrollValue);// добавляем её на окно



                JTextField accuracy = new JTextField(); // создаём текстовое поля для ввода точности
                accuracy.setBounds(120,150,50,50); // задаём размеры и положение в окне
                jFrame.add(accuracy);// добавляем на окно



                JLabel sin = new JLabel(new ImageIcon("sin.png")); // загружаем картинку с синусом
                sin.setBounds(0,0,627,100);// задаём размеры и положение в окне
                jFrame.add(sin); // добавляем на окно



                JLabel xValue = new JLabel("x = " + String.valueOf(x)); // создаём поле для вывода значений
                xValue.setBounds(0,150,50,50); // задаём размеры и положение в окне
                jFrame.add(xValue); // добавляем на окно



                JLabel nValue = new JLabel("n = "); // создаём поле для вывода значений
                nValue.setBounds(100,150,50,50); // задаём размеры и положение в окне
                jFrame.add(nValue); // добавляем на окно



                JLabel mcValue = new JLabel("MacLaurin = " + String.valueOf(mc)); // создаём поле для вывода результата по маклорену
                mcValue.setBounds(200,150,250,50); // задаём размеры и положение в окне
                jFrame.add(mcValue); // добавляем на окно



                JLabel refValue = new JLabel("Reference = " + String.valueOf(reference)); // создаём поле для вывода результата по эталону
                refValue.setBounds(200,130,250,50); // задаём размеры и положение в окне
                jFrame.add(refValue); // добавляем на окно

                //////////////////////////////////////
                // ДАЛЕЕ ДЕЛАЕМ АЛЬТЕРНАТИВНЫЙ ВВОД //
                //////////////////////////////////////
                JTextField minText = new JTextField(); // создаём текстовое поля для ввода нижней границы
                minText.setBounds(420,150,50,50); // задаём размеры и положение в окне
                jFrame.add(minText);// добавляем на окно



                JLabel min = new JLabel("min"); // создаём текст минимума
                min.setBounds(435,100,250,50); // задаём размеры и положение в окне
                jFrame.add(min); // добавляем на окно



                JTextField maxText = new JTextField(); // создаём текстовое поля для ввода верхней границы
                maxText.setBounds(480,150,50,50); // задаём размеры и положение в окне
                jFrame.add(maxText);// добавляем на окно



                JLabel max = new JLabel("max"); // создаём текст максимума
                max.setBounds(490,100,250,50); // задаём размеры и положение в окне
                jFrame.add(max); // добавляем на окно



                JTextField valText = new JTextField(); // создаём текстовое поля для ввода шага
                valText.setBounds(540,150,50,50); // задаём размеры и положение в окне
                jFrame.add(valText);// добавляем на окно



                JButton init2 = new JButton("init"); // создаём кнопку инициализации вычислений
                init2.setBounds(450,350,70,30); // задаём размеры и позицию
                jFrame.add(init2); // добавляем её на окно



                exit.addMouseListener(new MouseListener() { // добавляем к кнопке считыватель мышки
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) { // если мышь нажала на кнопку
                                System.exit(0); // выходим из программы
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



                // добавляем к прокрутке считыватель значений
                ScrollValue.addAdjustmentListener(new AdjustmentListener() {
                        @Override
                        public void adjustmentValueChanged(AdjustmentEvent e) { // если значение изменилось
                                x = (double) (e.getValue() / 9) / 10; //меняем x
                                xValue.setText("x = " + String.valueOf(x)); //выводим на экран
                        }
                });




                reset.addMouseListener(new MouseListener() { // добавляем к кнопке сброса считыватель мышки
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) { // если нажали на кнопку
                                x = 0; // сбрасываем значение x
                                n = 0; // сбрасываем значение n
                                xValue.setText("x = 0"); // сбрасываем вывод x
                                maxText.setText(""); // сбрасываем альтернативные значения
                                minText.setText(""); // сбрасываем альтернативные значения
                                valText.setText(""); // сбрасываем альтернативные значения
                                accuracy.setText("");// сбрасываем n
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



                init.addMouseListener(new MouseListener() { //добавляем к кнопке действие по нажатию
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) { // в случае нажатия, просчитываем функции

                                try{ // пытаемся считать
                                        n = Double.parseDouble(accuracy.getText()); // считываем чему равно n
                                }
                                catch (Exception f){
                                        JOptionPane.showMessageDialog(null, "некорректный ввод, попробуйте снова");
                                }
                                reference = Math.sin(x); //считаем значение эталона
                                mc = Maclaurin(x, n); // считаем значение маклорена
                                if(Double.isNaN(mc)){ // смотрим, не вышли ли мы за пределы значений
                                        JOptionPane.showMessageDialog(null, "выход за пределы значений, попробуйте снова");
                                }
                                else if(Double.isInfinite(mc)){
                                        JOptionPane.showMessageDialog(null, "выход за пределы значений, попробуйте снова");
                                }
                                mcValue.setText("MacLaurin = " + String.valueOf(mc)); // выводим маклорена
                                refValue.setText("Reference = " + String.valueOf(reference)); // выводим эталон






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


                //ДАЛЬШЕ АЛЬТЕРАТИВНЫЙ ВВОД
                init2.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                                double altMin = 0.0;
                                double altMax = 0.0;
                                double altVal = 0.0;
                                try{ // пытаемся считать
                                        n = Double.parseDouble(accuracy.getText()); // считываем чему равно n
                                        altMin = Double.parseDouble(minText.getText()); // считываем чему равно min
                                        altMax = Double.parseDouble(maxText.getText()); // считываем чему равно max
                                        altVal = Double.parseDouble(valText.getText()); // считываем чему равно val

                                }
                                catch (Exception f){
                                        JOptionPane.showMessageDialog(null, "некорректный ввод, попробуйте снова"); // выводим если что-то не так
                                }
                                JTable table = new JTable((int)((altMax + altVal) / altVal),2); // создаём табличку под значения
                                try{
                                        if (altMax > 0 && altVal < altMax){ // если максимум больше нуля, то пробуем посчитать
                                                int row = 0; // задаём строчки под табличку
                                                double i = altMin;
                                                        while (i < altMax + altVal){ // перебираем все считанные значения
                                                        if(Double.isNaN(Maclaurin(i,n))){ // смотрим, не вышли ли мы за пределы значений
                                                                JOptionPane.showMessageDialog(null, "выход за пределы значений, попробуйте снова"); // выводим ошибку
                                                                break; // выходим из цикла
                                                        }
                                                        else if(Double.isInfinite(Maclaurin(i,n))){// смотрим, не вышли ли мы за пределы значений
                                                                JOptionPane.showMessageDialog(null, "выход за пределы значений, попробуйте снова");// выводим ошибку
                                                                break;// выходим из цикла
                                                        }
                                                        table.getModel().setValueAt(Maclaurin(i,n),row,0); //вставляем в табличку значения если всё хорошо
                                                        table.getModel().setValueAt(Math.sin(i),row,1);//вставляем в табличку значения если всё хорошо
                                                        row++; // увеличиваем счётчики
                                                        i+=altVal;
                                                }
                                        }
                                        else {
                                                JOptionPane.showMessageDialog(null, "некорректный ввод, попробуйте снова");
                                        }
                                }
                                catch (Exception f){
                                        //System.out.println(f.getMessage());
                                }
                                table.setBounds(300,300,300,300); // задаём табличке размер
                                jFrame.add(table); //добавляем её
                                table.setVisible(true); //выводим её
                                jFrame.setVisible(true); //обновляем окно
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

                jFrame.setVisible(true); // отображаем окно
        }


        private double Maclaurin(double a, double b){ // функция, которая считает синус по ряду маклорена как в формуле


                int i = 1; // счётчик для степени
                double val = ((Math.pow(-1,1) * (Math.pow(a, (2) + 1)/calculateFactorial((2) + 1)))); // считаем шаг где x^3
                while (abs(val) > b){ // если мы попали в точность, то можем считать
                        a = (a + val); //прибавляем к синусу значение
                        i++; // увеличиваем счётчик
                        val = Math.pow(-1,1) * (Math.pow(a, (2 * i) + 1)/calculateFactorial((2 * i) + 1)); // пересчитываем очередной шаг
                }
                return a;

        }


        private int calculateFactorial(int a){ // функция факториала для функции маклорена
                int result = 1;
                for (int i = 1; i <= a; i ++){
                        result = result*i;
                }
                return result;
        }
}
