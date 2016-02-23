class MultiplyMatrices{

  public static void main (String[] args) throws InterruptedException{
    int[][] A = {{1,3},{2,3}};
    int[][] B = {{1,2}, {4,3}};
    int rows = A.length;
    int cols = B[0].length;
    int[][] C = new int[rows][cols];

    //multiplyMatrices(A,B,C,0, rows);

    Thread thread1 = new Thread(
        new MultiplyRunnable(A,B,C,0,rows/2));
/bin/bash: j: command not found
        new MultiplyRunnable(A,B,C,rows/2,rows));
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    System.out.println("Final result");
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        System.out.print(C[i][j] + " ");
      }
      System.out.println("");
    }

  }

  public static void multiplyMatrices(int[][] A, int[][] B,
      int[][] C, int lower, int upper){

    //row of C, row of A
    for(int i = lower ; i < upper; i++){
      //col of C, col of B
      for(int j = 0; j< C[0].length; j++){
        C[i][j] = 0;
        for(int k = 0; k < A[0].length; k++){
          C[i][j] += A[i][k] * B[k][j];
        }
      }
    }
  }

  public static class MultiplyRunnable implements Runnable{

    private int lower;
    private int upper;
    private int[][] A;
    private int[][] B;
    private int[][] C;

    public MultiplyRunnable(int[][] A, int[][] B,
        int[][] C, int lower, int upper){
      this.A = A;
      this.B = B;
      this.C = C;
      this.lower = lower;
      this.upper = upper;
    }

    public void run(){
      System.out.println("Running in thread");
      multiplyMatrices(A,B,C,lower,upper);
    }
  }
}
