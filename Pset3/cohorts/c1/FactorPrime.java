import java.math.BigInteger;

class FactorPrime{
  public static void main(String[] args){
    // Fermats no.
    BigInteger semiprime = new BigInteger("4294967297");
    BigInteger[] factors = factorPrime(semiprime);
    for(BigInteger factor: factors){
      System.out.println(factor);
    }

    // Second test
    BigInteger semiprime2 = new BigInteger("1127451830576035879");
    factors = factorPrime(semiprime2);
    for(BigInteger factor: factors){
      System.out.println(factor);
    }
  }

  public static BigInteger[] factorPrime(BigInteger num){
    BigInteger[] factors = new BigInteger[2];
    for(BigInteger i = BigInteger.valueOf(2);
        i.compareTo(num) == -1; i = i.add(BigInteger.ONE)) {
      if(num.mod(i)==BigInteger.ZERO){
        factors[0] = i;
        factors[1] = num.divide(i);
        return factors;
      }
    }

    return factors;
  }
}
