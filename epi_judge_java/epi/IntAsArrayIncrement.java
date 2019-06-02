package epi;
/**
 * Problem 5.2 - */
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    int c =1;

    for(int i=A.size()-1; i>=0; i--){
      int n=A.get(i);
      A.set(i,(n+c)%10);
      c = (int)((n+c)/10);
    }
    if(c>0){
      A.add(0,c);
    }

    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
