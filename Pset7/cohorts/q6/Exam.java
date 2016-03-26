import java.util.concurrent.*;
import java.util.*;

public class Exam{

  private static final int NUM_STUDENTS = 50;

  public static void main (String[] args)
  {
    Student[] students = new Student[NUM_STUDENTS];
    Phaser phaser = new Phaser();
    phaser.register();
    phaser.bulkRegister(NUM_STUDENTS);

    for(int i = 0; i < NUM_STUDENTS; i++){
      students[i] = new Student(phaser);
      students[i].start();
    }

    System.out.println("Phase at phase " + phaser.getPhase());
    phaser.arriveAndAwaitAdvance();
    System.out.println("Arrived number " + phaser.getArrivedParties());
    System.out.println("Phase at phase " + phaser.getPhase());
    phaser.arriveAndAwaitAdvance();
    System.out.println("Exam is over");

  }
}

class Student extends Thread{

  private Phaser phaser;

  public Student(Phaser phaser){
    this.phaser = phaser;
  }

  public void run(){
    System.out.println("Arrived at exam hall");
    phaser.arriveAndAwaitAdvance();

    System.out.println("Doing exam");
    try{
      Thread.sleep(500);
    }catch(InterruptedException e){
      System.out.println("Interrupted while sleeping");
    }

    System.out.println("Leaving examination hall");
    phaser.arriveAndDeregister(); //deregisters the number of parties to wait for in future phases

  }
}
