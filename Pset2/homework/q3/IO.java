import java.io.*;
import java.util.Scanner;

class IO{

  private static IO instance;
  private InputStream inStream;
  private OutputStream outStream;
  private Scanner inScanner;
  private BufferedWriter outBufWriter;

  private IO(){
    this.inStream = System.in;
    this.outStream = System.out;
    inScanner = new Scanner(inStream);
    outBufWriter = new BufferedWriter(new OutputStreamWriter(outStream));
  }

  private IO(InputStream inStream, OutputStream outStream){
    this.inStream = inStream;
    this.outStream = outStream;
    inScanner = new Scanner(inStream);
    outBufWriter = new BufferedWriter(new OutputStreamWriter(outStream));
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
    catch(Exception e){
      e.printStackTrace();
    }
  }

  //get inputs
  public String getCandidate(){
    return getInput("Vote for your candidate (A/B): ");
  }

  public String getInput(String msg){
    printOutput(msg);
    return inScanner.nextLine().trim();
  }

  //print outputs
  public void printOutput(String msg)
  {
    printOutput(msg, false);
  }

  public void printOutput(String msg, boolean newline){
    try{
      outBufWriter.write(msg);
      if(newline)
        outBufWriter.newLine();
      outBufWriter.flush();
    }
    catch(Exception e){
      e.printStackTrace();
    }


  }
}
