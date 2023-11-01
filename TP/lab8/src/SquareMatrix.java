
class SquareMatrix extends UsualMatrix implements IMatrix{
    public SquareMatrix(int row){
        super(row,row);
        if(row < 0){
            throw new MatrixException("Creation error: Matrix sizes can't be < 0");
        }
        mtx = new int[row][row];
        for (int i=0; i<mtx.length; i++){
            for (int j=0; j<mtx[i].length; j++){
                if (i==j){
                    mtx[i][j]=1;
                }
                else{
                    mtx[i][j]=0;
                }
            }
        }
    }


    public IMatrix sum(SquareMatrix b){
        if ((mtx.length!=b.getColumns())||(mtx[0].length!=b.getRows())){
            throw new MatrixException("Size error: sizes don't match");
        }
        int change=0;
        SquareMatrix c = new SquareMatrix(mtx.length);
        for (int i=0; i<mtx.length; i++){
            for (int j=0; j<mtx[i].length; j++){
                change=mtx[i][j]+b.getElement(i,j);
                c.setElement(i,j,change);
            }
        }
        return(c);
    }
}