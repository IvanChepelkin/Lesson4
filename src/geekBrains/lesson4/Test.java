package geekBrains.lesson4;

public class Test {

    static char matrix [][] = new char[5][5];

    static int a [] = {-1,-1,-1,0,1,1, 1, 0};
    static int b [] = {-1, 0, 1,1,1,0,-1,-1};

    public static void main(String[] args) {
         createMatrix();

         int x = 0;
         int y = 0;
         search(x,y);
        setX();


    }
    public static void createMatrix(){
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                matrix[i][j] = '0';
            }
        }
    }
    public static void setX(){


           for (int i = 0; i <matrix.length ; i++) {
               for (int j = 0; j <matrix[i].length ; j++) {
                   matrix[2][2] = '1';
                   System.out.print(matrix[i][j]);
               }
               System.out.println();
           }
   }
    public static void search(int x, int y){

        for (int i = 0; i <a.length; i++) {
            matrix[x+a[i]][y+b[i]]='x';
        }

    }
}

