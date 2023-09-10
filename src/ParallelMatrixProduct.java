public class ParallelMatrixProduct {
    private MatrixThread[] threads;
    private IMatrix a;
    private IMatrix b;
    private UsualMatrix c;
    int rowval=0;
    public class MatrixThread extends Thread{
        int row = 0;
        public MatrixThread(int val){
            row = val;
        }
        @Override
        public void run() {
            int multi = 0;

            for (int i = 0; i < a.getColumns(); i++) {
                for (int j = 0; j < b.getColumns(); j++) {
                    multi += a.getElement(row, j) * b.getElement(j, i);
                }
                c.setElement(row, i, multi);
                multi = 0;
            }
        }
    }
    public ParallelMatrixProduct(int val){
        rowval=val;
        threads = new MatrixThread[val];
    }

    /*public void setThreads(int val){
        rowval=val;
        threads = new MatrixThread[val];
    }*/

    public UsualMatrix ParallelProduct(IMatrix first, IMatrix second){
        c = new UsualMatrix(first.getRows(), second.getColumns());
        a = first;
        b = second;
        int j = 0;
        for(int i = 0; i < first.getRows(); i++) {
            threads[j] = new MatrixThread(i);
            threads[j].start();
            j++;
            if (j >= rowval)
                j = j - rowval;

        }

        for(int i = 0; i < threads.length; ++i) {
            if(threads[i] != null) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }




        return c;
    }
}
