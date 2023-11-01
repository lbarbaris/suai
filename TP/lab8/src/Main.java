public class Main {
    public static void main(String[] args) {
        UsualMatrix a = new UsualMatrix(2,1);
        UsualMatrix b = new UsualMatrix(1,2);
        a.random(a.getColumns() * a.getRows(), 1, 9);
        b.random(b.getColumns() * b.getRows(), 1, 9);
        System.out.println(a.toString());
        System.out.println(b.toString());
        ParallelMatrixProduct PMP = new ParallelMatrixProduct(1);
        UsualMatrix c;
        UsualMatrix d;
        long time = System.nanoTime();
        c = PMP.product(a,b);

        long res = System.nanoTime();
        System.out.println(c.toString());
        time=res-time;
        System.out.println("time parralel is " + time + "ms" + '\n');
        long time1 = System.nanoTime();
        d = a.product(b);
        long res1 = System.nanoTime();
        time1=res1-time1;
        System.out.println(d.toString());
        System.out.println("time non-parralel is " + time1 + "ms" + '\n');
    }
}