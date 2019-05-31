package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {

  public static int getWeight(long x){
    int ret = 0;
    while(x > 0){
      if( (x & 1) == 1){
        ret += 1;
      }
      x = x>>1;
    }
    return ret;
  }

  /**Brute Force*/
 @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    // TODO - you fill in here.
    int wx = getWeight(x);
    int diff = 1;
    while(true){
      if(x-diff == 0 && x+diff == Long.MAX_VALUE ) return -1;
      if(x-diff > 0 && getWeight(x-diff) == wx) return x-diff;
      if(x+diff <Long.MAX_VALUE && getWeight(x+diff) == wx) return x+diff;
      diff += 1;
    }
  }

	/**optimal solution - pg. 32*/
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount1(long x) {
    // TODO - you fill in here.
    long y = x;
    int li = 0;
    long lastBit = x&1L;
   x = x >> 1;

     while(x >=0){
       long nextBit = x&1L;
       if(nextBit != lastBit){
        return SwapBits.swapBits(y,li,li+1);
       }
       x = x>>1;
       lastBit = nextBit;
       li+=1;
   }
   return 0;
  }





  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
