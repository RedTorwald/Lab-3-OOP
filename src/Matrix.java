
import java.util.Scanner;
public class Matrix{

   // public final int rows; // сделать public, т.к. имеют final
   // public final int columns;// сделать public, т.к. имеют final
   /* private  int rows;
    private  int columns;*/
    public  int rows;
    public  int columns;
    public Matrix(){                      //пустой конструктор
    };
    private int[][] matrix;

    public Matrix(int n) {
        rows = columns = n;
        init();
    }
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        init();
    }
    public Matrix(int[][] matrix) {
        this.matrix = this.copyMatrix(matrix);
        rows = matrix.length;
        columns = matrix[0].length;
        if (rows!=columns)
        {
            if (rows<columns)
            {
                for(int i=0; i<rows; i++)
                {
                    for(int j=0; j<rows; i++)
                    {
                        this.matrix[i][j]=matrix[i][j];
                    }
                }
            }
            else
            {
                for(int i=0; i<columns; i++)
                {
                    for(int j=0; j<columns; i++)
                    {
                        this.matrix[i][j]=matrix[i][j];
                    }
                }
            }

        }
    }
    public void init() {
        matrix = new int[rows][columns];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
    }
    public void output() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("|"+matrix[i][j]+ "|"+ " ");
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
        int[][] temp=new int[rows][columns];

        for (int i = 0, ii=0; i < rows; i++){
            for (int j = 0, jj = 0; j < columns; j++) {
                temp[ii][jj++] = matrix[j][i];
            };
            ii++;
        } matrix=temp;
    }

    public double getDeterminant() {
        double det = 0;
        if (rows != columns) return 0;
        int size = rows;

        switch (size) {
            case 1: det = matrix[0][0]; break;
            case 2: det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]; break;
            default: {
                for (int row = 0; row < size; row++) {

                    int[][] minor = new int[rows-1][rows-1];
                    for (int i=0, ii=0; i<rows; i++) {
                        if (i == row) continue;
                        for (int j=0, jj=0; j<rows; j++) {
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
        int size = rows;
        int[][] minors = new int[rows][columns];

        for (int r=0; r<rows; r++) {
            for (int c=0; c<columns; c++) {
                int[][] M = new int[rows-1][rows-1];

                for (int i=0, ii=0; i<rows; i++) {
                    if (i == r) continue;
                    for (int j=0, jj=0; j<rows; j++) {
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
        for(int r=0; r<rows; r++){
            for(int c=0; c<columns; c++){
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
        if (rows == 0 || columns == 0) {
            throw new Exception("Отсутствует матрица");
        }
    }


}