import java.util.*;

public class q1{

  public static void main(String[] args){

    Complex num1, num2;
    double real, im;
    Scanner reader = new Scanner(System.in);
    System.out.println("First number");

    real = numIO.prompt("Enter the real part:", "Please enter a valid number!");
    im = numIO.prompt ("Enter the imaginary part:", "Please enter a valid numer!");
    num1 = new Complex(real,im);

    real = numIO.prompt("Enter the real part:", "Please enter a valid number!");
    im = numIO.prompt ("Enter the imaginary part:", "Please enter a valid numer!");
    num2 = new Complex(real,im);

    Complex addition = num1.add(num2);
    Complex subtraction = num1.subtract(num2);
    Complex multiplcation = num1.multiply(num2);
    Complex division = num1.divide(num2);

    System.out.println(String.format("The addition of " + num1 + " and " + num2 + " is " + addition));
    System.out.println(String.format("The subtraction of " + num1 + " and " + num2 + " is " + subtraction));
    System.out.println(String.format("The multiplcation of " + num1 + " and " + num2 + " is " + multiplcation));
    System.out.println(String.format("The division of " + num1 + " and " + num2 + " is " + division));
  }

  public static class numIO{

    public static Scanner reader = new Scanner(System.in);
    
    public static double prompt(String msg, String errmsg){

      while(true){
        System.out.println(msg);
        try{
          return reader.nextDouble();
        }
        catch (Exception e){
          System.out.println(errmsg); 
          reader.next();
        }
      }
    }

  }

  public static class Complex{
    private double real;
    private double im;

    public Complex(){
      this.real = 0.0;
      this.im = 0.0;
    }

    public Complex(double real, double im){
      this.real = real;
      this.im = im;
    }

    public Complex add(Complex num){
      return new Complex(this.real + num.real, this.im + num.im);
    }

    public Complex subtract(Complex num){
      return new Complex(this.real - num.real, this.im - num.im);
    }

    public Complex multiply(Complex num){
      return new Complex(this.real*num.real - this.im*num.im, this.real*num.im + this.im*num.real);
    }

    public Complex divide(Complex num){
      double factor = num.real*num.real + num.im*num.im;
      return new Complex((this.real*num.real + this.im*num.im)/factor, (this.im*num.real - this.real*num.im)/factor);
    }

    public String toString(){
      if (this.im<0){
        return String.format("%.2f - %.2fi", this.real, -this.im);
      }
      else if (this.im ==0){
        return String.format("%.2f",this.real);
      }else if (this.real ==0){
        return String.format("%.2fi", this.im);
      }
      return String.format("%.2f + %.2fi", this.real, this.im);
    }
  }


}
