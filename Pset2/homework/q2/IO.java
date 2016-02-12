import java.io.*;
import java.util.Scanner;

class IO{

  private static IO instance;
  private InputStream inStream;
  private OutputStream outStream;
  private Scanner inScanner;
  private BufferedWriter outBufWriter;

  //Default constructor
  private IO(){
    this.inStream = System.in;
    this.outStream = System.out;
    this.inScanner = new Scanner(inStream);
    this.outBufWriter = new BufferedWriter(new OutputStreamWriter(outStream));
  }

  private IO(InputStream inStream, OutputStream outStream){
    this.inStream = inStream;
    this.outStream = outStream;
    this.inScanner = new Scanner(inStream);
    this.outBufWriter = new BufferedWriter(new OutputStreamWriter(outStream));
  }

  public static synchronized IO getInstance(){
    if(instance == null)
      instance = new IO();
    return instance;
  }

  public static synchronized IO getInstance(
      InputStream inStream, OutputStream outStream
      ){
    if(instance == null)
      instance = new IO(inStream, outStream);
    return instance;
  }

  //Close streams
  public void close(){
    try{
      inStream.close();
      outStream.close();
      inScanner.close();
      outBufWriter.close();
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  //Prompts
  public int getRow(){
    while(true){
      try{
        return -1 + Integer.parseInt(getInput("Please enter a row: "));
      }
      catch(NumberFormatException ne){
        printOutput("Please enter a valid number!",true);
      }
    }
  }

  public int getCol(){
    while(true){
      try{
        return -1 + Integer.parseInt(getInput("Please enter a column: "));
      }
      catch(NumberFormatException ne){
        printOutput("Please enter a valid number!",true);
      }
    }
  }

  public String getConfirmation(){
    return getInput("Play again? (Y/N):");
  }

  private String getInput(String msg){
    printOutput(msg);
    return inScanner.nextLine().trim();
  }

  //Output
  public void printOutput(String output){
    printOutput(output, false);
  }

  public void printOutput(String output, boolean newline){
    try{
      outBufWriter.write(output);
      if(newline)
        outBufWriter.newLine();
      outBufWriter.flush();
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }


}
