import java.io.*;

class Main{

  public static void main(String[] args){

    BufferedReader bufReader = null;

    if(args.length == 0)
      System.out.println("No files read");

    for(String filename: args){
      try{
        bufReader = new BufferedReader(new FileReader(filename));
        int numLines = 0;
        while(bufReader.readLine() != null)
          numLines++;
        System.out.println("The number of lines in file " + filename + " is " + numLines);
        bufReader.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
