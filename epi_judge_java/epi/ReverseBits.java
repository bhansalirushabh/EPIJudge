package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // TODO - you fill in here.
    long ret = 0L;
    int n = Long.SIZE;
    while(n>0){
      int tempBit = (int) (x &(1L));
      x = x>>1;
      ret=ret<<1;
      ret=ret|tempBit;
      n -= 1;
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
