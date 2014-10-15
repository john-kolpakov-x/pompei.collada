package pompei.collada.model;

import java.util.ArrayList;
import java.util.List;

import pompei.collada.errors.DifferentSummaOfVcountAndPointCount;

public class Polylist implements XmlAppender {
  public Material material;
  public final List<Input> inputList = new ArrayList<>();
  public final List<Integer> vcountList = new ArrayList<>();
  public final List<Integer> pointList = new ArrayList<>();
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<polylist");
    U.appendHasId(sb, "material", material);
    U.appendInt(sb, "count", vcountList.size());
    sb.append('>');
    for (Input i : inputList) {
      i.append(sb);
    }
    {
      int summa = 0;
      sb.append("<vcount>");
      for (Integer i : vcountList) {
        sb.append(summa += i.intValue()).append(' ');
      }
      if (pointList.size() != summa) {
        throw new DifferentSummaOfVcountAndPointCount(summa, pointList.size());
      }
      sb.append("</vcount>");
    }
    {
      sb.append("<p>");
      for (Integer i : pointList) {
        sb.append(i.intValue()).append(' ');
      }
      sb.append("</p>");
    }
    sb.append("</polylist>");
  }
}
