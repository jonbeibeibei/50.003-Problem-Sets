class MultiThreadSearch{

  public static boolean found = false;
  public static int index;

  public static void main (String[] args){

    int num = 3;
    int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};

    SearchThread thread1 = new SearchThread(1,num, array, 0, array.length/2);
    SearchThread thread2 = new SearchThread(2,num, array, array.length/2, array.length);

    System.out.println("Starting threads");
    thread1.start();
    thread2.start();

    System.out.println("Checking found");
    while(true){
      if(found){
        System.out.format("Number found at index %d\n", index);
        thread1.interrupt();
        thread2.interrupt();
        break;
      }
    }
  }

  public static class SearchThread extends Thread{

    private int id;
    private int num;
    private int[] array;
    private int lower;
    private int upper;

    public SearchThread(int id,int num, int[] array, int lower, int upper){
      this.id = id;
      this.num = num;
      this.array = array;
      this.lower = lower;
      this.upper = upper;
    }

    public void run(){
      for(int i = lower; i < upper; i++){
        System.out.println("Running thread "+id+" at index " +i);
        if(isInterrupted()){
          System.out.println("Thread "+id+" interrupted");
          return;
        }
        if(array[i] == num){
          found = true;
          index = i;
        }
      }
    }
  }
}
