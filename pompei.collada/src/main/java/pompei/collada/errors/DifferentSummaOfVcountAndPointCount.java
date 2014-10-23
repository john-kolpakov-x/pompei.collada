package pompei.collada.errors;

public class DifferentSummaOfVcountAndPointCount extends RuntimeException {
  
  public final int summaOfVcount, pointCount;
  
  public DifferentSummaOfVcountAndPointCount(int summaOfVcount, int pointCount) {
    super("Different summa of vcount and point count: summaOfVcount = " + summaOfVcount
        + ", pointCount = " + pointCount);
    this.summaOfVcount = summaOfVcount;
    this.pointCount = pointCount;
  }
}
