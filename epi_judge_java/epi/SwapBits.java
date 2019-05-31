package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
/**problem 4.2*/
public class SwapBits {
  public static int getBitAti(long x, int i){
    while(i>0){
      x = x>>1;
      i--;
    }
    return (int)(x&1);
  }
  public static long flipBitAti(long x, int i){
    long MASK = 1L << i;
    return x^MASK;
  }
  /**optimal solution*/
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    // COMPLETE - you fill in here.
    int iBit = getBitAti(x,i);
    int jBit = getBitAti(x,j);
    if(iBit != jBit){
      x = flipBitAti(x,i);
      x = flipBitAti(x,j);
    }

    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
