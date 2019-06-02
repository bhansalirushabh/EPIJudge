/**Problem 5.1. pg. 42*/

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    // TODO - you fill in here.
    Color pivot = A.get(pivotIndex);
    int l=0;
    int r=A.size()-1;
    //int lr =0;

    while(true){
      //find an element bigger than pivot
      while(l<=A.size()-1 && A.get(l).ordinal() <= pivot.ordinal() ){
        l++;
      }
      //find an element not bigger than pivot
      while(r>=0 && A.get(r).ordinal() > pivot.ordinal()){
        r --;
      }

      if(l<r){
        Collections.swap(A,l,r);
      }
      else{
        break;
      }
    }

    l=0;
    int lmax = A.size()-1;
    r=A.size()-1;
    //iterate over bigger than pivot element
    while(A.get(r).ordinal()>pivot.ordinal()){
      r --;
    }
    lmax = r;

    while(true){
      //find an element equal to pivor
      while(l<=lmax &&  A.get(l).ordinal() != pivot.ordinal() ){
        l++;
      }
      //find an element not equal to the pivot (i.e smaller than in the group)
      while( r>=0 && A.get(r).ordinal() == pivot.ordinal() ){
        r --;
      }
      if(l<r){
        Collections.swap(A,l,r);
      }
      else{
        break;
      }
    }

  }
  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
