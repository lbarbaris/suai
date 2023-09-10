public interface IMatrix {
    int getElement(int row, int col);
    void setElement(int row, int col, int val);
    IMatrix product(IMatrix b);
    IMatrix sum(IMatrix b);
    int getRows();
    int getColumns();
    String toString();
    boolean equals(IMatrix b);
    void random(int amount, int range1, int range2);
}
