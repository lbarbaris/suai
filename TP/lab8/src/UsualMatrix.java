public class UsualMatrix implements IMatrix{
        protected int[][] mtx;
        public UsualMatrix(int row, int column){
            if(row < 0 || column < 0){
                throw new MatrixException("Creation error: Matrix sizes can't be < 0");
            }
            mtx = new int[row][column];
            for (int i=0; i<mtx.length; i++){
                for (int j=0; j<mtx[i].length; j++){
                    mtx[i][j]=0;
                }
            }
        }

        public final int getElement(int row, int column){
            if(row < 0 || column < 0 || row > this.getRows() || column > this.getColumns()){
                throw new MatrixException("Array error: going outside of array");
            }
            return mtx[row][column];
        }


        public final int getColumns(){
            return mtx[0].length;
        }


        public final int getRows(){
            return mtx.length;
        }


        public IMatrix sum(IMatrix b){
            if ((mtx.length!=b.getColumns())||(mtx[0].length!=b.getRows())){
                throw new MatrixException("Size error: sizes don't match");
            }
            UsualMatrix c = new UsualMatrix(mtx.length, mtx[0].length);
            for (int i=0; i<mtx.length; i++){
                for (int j=0; j<mtx[i].length; j++){
                    c.setElement(i,j,mtx[i][j]+b.getElement(i,j));
                }
            }

            return c;
        }



        public final String toString(){
            StringBuilder c = new StringBuilder();
            for (int i=0; i<mtx.length; i++){
                for (int j=0; j<mtx[i].length; j++){
                    c.append(mtx[i][j]);
                    c.append(" ");
                }
                c.append("\n");
            }
            return c.toString();
        }


        public final UsualMatrix product(IMatrix b) throws MatrixException{
            if ((this.getRows()!=b.getColumns())||(this.getColumns()!=b.getRows())){
                throw new MatrixException("Size error: 1 size != 2 size");
            }
            UsualMatrix c = new UsualMatrix(this.getRows(), b.getColumns());
            int multi=0;
            for (int i=0; i<this.getRows(); i++){
                for (int j=0; j<b.getColumns(); j++){
                    for (int k=0; k<this.getColumns(); k++){
                        multi+=this.getElement(i,k)*b.getElement(k,j);
                    }
                    c.setElement(i,j,multi);
                    multi=0;
                }
            }


            return c;
        }

        public final boolean equals (IMatrix b){
            int exit=0;
            boolean c = false;
            if (this.getRows()==b.getRows()){
                if(this.getColumns()==b.getColumns()){
                    for (int i=0; i<this.getRows(); i++){
                        for (int j=0; j<this.getColumns(); j++){
                            if (mtx[i][j]!=b.getElement(i,j)){
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

        public final void setElement(int row, int column, int value){
            if(row < 0 || column < 0 || row > this.getRows() || column > this.getColumns()){
                throw new MatrixException("Array error: going outside of array");
            }
            mtx[row][column]=value;
        }

        public final void random(int amount, int range, int range2){
            for (int i=0; i<amount; i++){
                int val = (int) ((Math.random() * (range2) + range));
                int col = (int) ((Math.random() * (mtx[0].length)));
                int row = (int) ((Math.random() * (mtx.length)));
                this.setElement(row,col,val);
            }
        }

}
