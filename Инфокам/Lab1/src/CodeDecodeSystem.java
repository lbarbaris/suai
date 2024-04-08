import java.util.ArrayList;
import java.util.Collections;

public class CodeDecodeSystem {
    private ArrayList<Byte> l; //information
    private ArrayList<Byte> e; //error
    private ArrayList<Byte> gx; //g(x)
    private ArrayList<Byte> cx; //c(x)
    private ArrayList<Byte> ax; //a(x)
    private ArrayList<Byte> b; // b(x)
    private ArrayList<Byte> sx; //s(x)

    private ArrayList<Byte> m; //message
    int r;
    int k;

    public CodeDecodeSystem(){
        gx = new ArrayList<Byte>();
        e = new ArrayList<Byte>();
        l = new ArrayList<Byte>();
    }




    public static ArrayList<Byte> calculateRemainder(ArrayList<Byte> dividend, ArrayList<Byte> divisor) {
        // Создаем копию делимого (дивидента)
        ArrayList<Byte> remainder = new ArrayList<>(dividend);

        // Для вычисления остатка от деления в GF(2) используем операцию XOR
        for (int i = 0; i <= dividend.size() - divisor.size(); i++) {
            if (remainder.get(i) == 1) {
                for (int j = 0; j < divisor.size(); j++) {
                    Byte value = (byte) ((remainder.get(i + j) ^ divisor.get(j)) & 1); // XOR и побитовое И
                    remainder.set(i + j, value);
                }
            }
        }

        // Удаление ведущих нулевых коэффициентов (нулевые старшие разряды)
        while (remainder.size() > 1 && remainder.get(0) == 0) {
            remainder.remove(0);
        }

        return remainder;
    }



    private void printPolynom(ArrayList<Byte> arrayList){
        for (int i = 0; i < arrayList.size(); i++){
            if (arrayList.get(i) != 0 && i != arrayList.size() - 1){
                System.out.print("x^" + (arrayList.size() - i - 1) + "+");
            }
            else if (i == arrayList.size() - 1){
                System.out.print(arrayList.get(i));
            }
        }
        System.out.println();
    }


    private void printVector(ArrayList<Byte> arrayList){
        for (int i = 0; i < arrayList.size(); i++){
            System.out.print(arrayList.get(i));
        }
        System.out.println();
    }


    private ArrayList<Byte> sumVector(ArrayList<Byte> firstList, ArrayList<Byte> secondList){
        ArrayList<Byte> res = new ArrayList<Byte>();
        res.ensureCapacity(firstList.size());
            for (int i = 0; i < firstList.size() - secondList.size(); i++){
                res.add(firstList.get(i));
            }
            for (int i = firstList.size() - secondList.size(); i < firstList.size(); i++){
                res.add((byte) (firstList.get(i) ^ secondList.get(i - (firstList.size() - secondList.size()))));
            }
        return res;
    }


    private boolean ErrorTest (ArrayList<Byte> s){
        for (int i = 0; i < s.size(); i++){
            if (s.get(i) == 1){
                return true;
            }
        }
        return false;
    }



    private ArrayList<Byte> addOnes(ArrayList<Byte> arrayList, int diff){
        ArrayList<Byte> newList = new ArrayList<Byte>(Collections.nCopies(diff, (byte)1));
        newList.addAll(arrayList);
        return newList;
    }


    private ArrayList<Byte> addZeros(ArrayList<Byte> arrayList, int diff){
        ArrayList<Byte> newList = new ArrayList<Byte>(arrayList);
        newList.addAll(Collections.nCopies(diff, (byte) 0));
        return newList;
    }


    public static int binaryToDecimal(ArrayList<Byte> binary) {
        int decimal = 0;
        for (int i = 0; i < binary.size(); i++) {
            decimal = decimal * 2 + binary.get(i);
        }
        return decimal;
    }


    public static ArrayList<Byte> decimalToBinary(int decimal) {
        ArrayList<Byte> binary = new ArrayList<>();
        while (decimal > 0) {
            binary.add((byte) (decimal % 2));
            decimal /= 2;
        }
        Collections.reverse(binary);
        return binary;
    }



    private void enterVector(ArrayList<Byte> arrayList){
        int x = 0;
        while (x == 0 || x == 1){
            x = new java.util.Scanner(System.in).nextInt();
            if (x == 0 || x == 1){
                arrayList.add((byte) x);
            }
        }
    }

    private void coder(boolean mode){
        System.out.print("g(x) = ");
        printPolynom(gx);
        System.out.println("deg(g(x)) = r = " + r);
        System.out.println("k = " + k);
        System.out.print("l = ");
        printVector(l);


        System.out.print("m = ");
        printVector(m);
        System.out.print("m(x) = ");
        printPolynom(m);


        m = addZeros(m, r); // m(x) * x ^ r

        System.out.print("m(x) * x^r = ");
        printPolynom(m);

        cx = calculateRemainder(m, gx);
        printPolynom(cx);

        ax = sumVector(m, cx); // ax = mx * xr + cx
        System.out.print("a(x) = ");
        printPolynom(ax);
        System.out.print("a = ");
        printVector(ax);
    }

    private void decoder(){
        System.out.print("e = ");
        printVector(e);
        if (ax.size() > e.size()) {
            b = sumVector(ax, e);
        } else {
            b = sumVector(e, ax);
        }
        System.out.print("b = ");
        printVector(b);
        System.out.print("b(x) = ");
        printPolynom(b);
        System.out.print("s(x) = ");
        sx = calculateRemainder(b, gx);
        printPolynom(sx);
        System.out.print("Error: " + ErrorTest(sx));
    }

    public void run(int mode) {
        if (mode == 1) {
            System.out.println("enter g(x). enter 2 to stop.");
            enterVector(gx);
            r = gx.size() - 1;


            System.out.println("enter k");
            k = new java.util.Scanner(System.in).nextInt();
            System.out.println("enter l");
            enterVector(l);

            System.out.print("enter e");
            enterVector(e);

            if (k <= l.size()) {
                m = new ArrayList<Byte>(l.subList(0, k));
            }
            if (k > l.size()) {
                int symbol = -1;
                System.out.println("k > l size. how add symbols? enter 1 for 1 or 0 for 0");
                symbol = new java.util.Scanner(System.in).nextInt();
                if (symbol == 0) {
                    m = addZeros(l, k - l.size());

                } else if (symbol == 1) {
                    m = addOnes(l, k - l.size());
                }
            }

            //вызов кодера
            coder(false);
            //


            //вызов декодера
            decoder();
            //
        }
        else if (mode == 4){
            for (int i = 0; i < 4; i++){
                if (i != 3){
                    gx.add((byte) 1);
                }
                else gx.add((byte) 0);
            }
            r = gx.size() - 1;
            for (int i = 0; i < 128; i++){
                m = decimalToBinary(i);
                coder(true);
            }
        }

    }

}
