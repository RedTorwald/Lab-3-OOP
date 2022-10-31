
import java.util.Scanner;
public class Matrix{

   // public final int rows; // сделать public, т.к. имеют final
   // public final int columns;// сделать public, т.к. имеют final
   /* private  int rows;
    private  int columns;*/
    private int rows;
    private int columns;
    private int[][] matrix;
    public Matrix(){                      //пустой конструктор
    };


    public Matrix(int n) {
        rows = columns = n;
        init();
    }
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        init();
    }
    private Matrix(int[][] matrix) {
        this.matrix = this.copyMatrix(matrix);
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        if (this.getRows() != this.getColumns()) {
            int i;
            byte j;
            if (this.getRows() < this.getColumns()) {
                for(i = 0; i < this.getRows(); ++i) {
                    for(j = 0; j < this.getRows(); ++i) {
                        this.matrix[i][j] = matrix[i][j];
                    }
                }
            } else {
                for(i = 0; i < this.getColumns(); ++i) {
                    for(j = 0; j < this.getColumns(); ++i) {
                        this.matrix[i][j] = matrix[i][j];
                    }
                }
            }
        }

    }
    private void init() {
        this.matrix = new int[this.getRows()][this.getColumns()];
        Scanner in = new Scanner(System.in);
        System.out.println("Введите элементы матрицы");

        for(int i = 0; i < this.getRows(); ++i) {
            for(int j = 0; j < this.getColumns(); ++j) {
                this.matrix[i][j] = in.nextInt();
            }
        }
    }
    public void output() {
        for(int i = 0; i < this.getRows(); ++i) {
            for(int j = 0; j < this.getColumns(); ++j) {
                System.out.print("|" + this.matrix[i][j] + "| ");
            }
            System.out.println();
        }
    }
    private int[][] copyMatrix(int[][] matrix) {
        int[][] copiedMatrix = new int[matrix.length][matrix.length];
        for (int i = 0, ii=0; i < matrix.length; i++){
            for (int j = 0, jj = 0; j < matrix.length; j++) {
                copiedMatrix[ii][jj++] = matrix[i][j];
            }
            ii++;
        }
        return copiedMatrix;
    }
    public void transpose() {
        int[][] temp = new int[this.getRows()][this.getColumns()];
        int i = 0;
        for(int ii = 0; i < this.getRows(); ++i) {
            int j = 0;
            for(int jj = 0; j < this.getColumns(); ++j) {
                temp[ii][jj++] = this.matrix[j][i];
            }
            ++ii;
        }
        this.matrix = temp;
    }

    public double getDeterminant() {
        double det = 0;
        if (this.getRows() != this.getColumns()) return 0;
        int size = this.getRows();

        switch (size) {
            case 1: det = matrix[0][0]; break;
            case 2: det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]; break;
            default: {
                for (int row = 0; row < size; row++) {

                    int[][] minor = new int[this.getRows() - 1][this.getRows() - 1];
                    for (int i=0, ii=0; i<this.getRows(); i++) {
                        if (i == row) continue;
                        for (int j=0, jj=0; j<this.getRows(); j++) {
                            if (j == 0) continue;
                            minor[ii][jj++] = matrix[i][j];
                        } ii++;
                    }

                    det += ((row) % 2 == 0 ? -1 : 1) * matrix[row][0] * (new Matrix(minor)).getDeterminant();
                }
            }
        }
        return det;
    }
    public int[][] getMinors() {
        int size =this.getRows();
        int[][] minors = new int[this.getRows()][this.getColumns()];

        for (int r=0; r<this.getRows(); r++) {
            for (int c=0; c<this.getColumns(); c++) {
                int[][] M = new int[this.getRows()-1][this.getRows()-1];

                for (int i=0, ii=0; i<this.getRows(); i++) {
                    if (i == r) continue;
                    for (int j=0, jj=0; j<this.getRows(); j++) {
                        if (j == c) continue;
                        M[ii][jj++] = matrix[i][j];
                    } ii++;
                }
                new Matrix(M).getDeterminant();
                if (size%2==1)
                {
                    minors[r][c]=(int)new Matrix(M).getDeterminant();
                }
                else if (size%2==0)
                {
                    minors[r][c]=(-1)*(int)new Matrix(M).getDeterminant();
                }
                System.out.print("|"+minors[r][c]+"|"+" ");
            }System.out.println();
        }
        return minors;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int r=0; r<this.getRows(); r++){
            for(int c=0; c<this.getColumns(); c++){
                str.append(matrix[r][c]);
                str.append(" ");
            }
            str.setCharAt(str.length()-1, '\n');
        }
    return str.toString();
    }
    /*

*/
    public void emptyCheck() throws Exception {
        if (this.getRows() == 0 || this.getColumns() == 0) {
            throw new Exception("Отсутствует матрица");
        }
    }
    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

}