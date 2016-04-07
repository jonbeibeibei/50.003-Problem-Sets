class MultiplyMatrices{

  public static void main (String[] args) throws InterruptedException{
    int numRows = 3;
    int[][] A = new int[numRows][numRows];
    int[][] B = new int[numRows][numRows];

    for(int i = 0; i < numRows; i++){
      for(int j = 0; j < numRows; j++){
        A[i][j] = i+1;
        B[i][j] = j+1;
      }
    }
    int[][] C = new int[numRows][numRows];

    //multiplyMatrices(A,B,C,0, rows);

    int numThreads = 3;
    Thread[] threads = new Thread[numThreads];

    long startTime = System.currentTimeMillis();
    for(int i =0; i < numThreads; i++){
      threads[i] = new Thread(
        new MultiplyRunnable(A,B,C,i*numRows/numThreads,(i+1)*numRows/numThreads));
      threads[i].start();
    }

    for(int i = 0; i< numThreads; i++){
      threads[i].join();
    }
    long endTime = System.currentTimeMillis();

    System.out.format("Final result computed in %d ms\n", endTime-startTime);
    for(int i = 0; i < numRows; i++){
      for(int j = 0; j < numRows; j++){
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
