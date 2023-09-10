import java.util.LinkedList;
import java.util.ListIterator;

public class SparseMatrix implements IMatrix {
    private static class Element{
        private int row;
        private int col;
        private int val;

        public Element(int row,int column,int value){
            this.row=row;
            this.col=column;
            this.val=value;
        }
    }
    protected int colsize;
    protected int rowsize;
    protected LinkedList<Element> mtx = new LinkedList<>();
    protected ListIterator<Element> itr;

    public SparseMatrix(int row, int col){
        rowsize=row;
        colsize=col;
        this.itr = this.mtx.listIterator();
        for (int i=0; i<this.rowsize; i++){
            for (int j=0; j<this.colsize; j++){
                this.mtx.add(new Element(i, j, 0));
            }
        }
    }

    public final int getElement(int row, int col){
        this.itr = this.mtx.listIterator();
        if(row < 0 || col < 0 || row > rowsize || col > colsize){
            throw new MatrixException("Array error: going outside of array");
        }
        while(this.itr.hasNext()){
            Element cur = (Element)this.itr.next();
            if(cur.row == row & cur.col == col)
                return cur.val;
        }
        return 0;
    }

    public void setElement(int row, int col, int val){
        this.itr = this.mtx.listIterator();
        if(row < 0 || col < 0 || row > rowsize || col > colsize){
            throw new MatrixException("Array error: going outside of array");
        }
        while(this.itr.hasNext()){
            Element cur = this.itr.next();
            if(cur.row == row & cur.col == col) {
                this.itr.previous().val = val;
                return;
            }
        }
        this.mtx.add(new Element(row, col, val));


    }

    public IMatrix product(IMatrix b){
        if(b.getRows() < 0 || b.getColumns() < 0 || b.getRows() > this.colsize || b.getColumns() > this.rowsize){
            throw new MatrixException("Array error: going outside of array");
        }
        int multi = 0;
        SparseMatrix c = new SparseMatrix(this.rowsize, b.getColumns());
        for (int i = 0; i < this.rowsize; i++) {
            for (int j = 0; j < b.getColumns(); j++) {
                for (int k = 0; k < this.colsize; k++) {
                    multi+=this.getElement(i,k)*b.getElement(k,j);
                }
                c.setElement(i,j,multi);
                multi=0;
            }
        }
        return c;
    }

    public IMatrix sum(IMatrix b){
        if ((this.colsize!=b.getColumns())||(this.rowsize!=b.getRows())){
            throw new MatrixException("Size error: sizes don't match");
        }
        SparseMatrix c = new SparseMatrix(rowsize, colsize);
        for (int i=0; i<this.rowsize; i++){
            for (int j=0; j<this.colsize; j++){
                int val1=this.getElement(i,j);
                int val2=b.getElement(i,j);
                c.setElement(i,j,val1+val2);
            }
        }
        return c;
    }

    public int getRows(){
        return this.rowsize;
    }

    public int getColumns(){
        return this.colsize;
    }

    public final String toString(){
        StringBuilder c = new StringBuilder();
        for (int i=0; i<this.rowsize; i++){
            for (int j=0; j<this.colsize; j++){
                c.append(this.getElement(i,j));
                c.append(" ");
            }
            c.append("\n");
        }
        return c.toString();
    }

    public final boolean equals (IMatrix b){
        int exit=0;
        boolean c = false;
        if (this.colsize==b.getColumns()){
            if(this.rowsize==b.getRows()){
                for (int i=0; i<this.rowsize; i++) {
                    for (int j = 0; j < this.colsize; j++){
                        if (this.getElement(i,j)!=b.getElement(i,j)){
                            exit++;
                        }
                    }
                }
            }
            else {
                exit++;
            }
        }
        else {
            exit++;
        }
        if (exit==0){
            c=true;
        }

        return c;
    }
    public final void random(int amount, int range, int range2){
        for (int i=0; i<amount; i++){
            int val = (int) ((Math.random() * (range2) + range));
            int col = (int) ((Math.random() * (colsize)));
            int row = (int) ((Math.random() * (rowsize)));
            this.setElement(row,col,val);
        }
    }

}
