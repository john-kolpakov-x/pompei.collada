package pompei.collada.model;

import java.util.ArrayList;
import java.util.List;

public class Accessor implements XmlAppender {
  public SourceRef source;
  public int count;
  
  public final List<Param> paramList = new ArrayList<>();
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<accessor");
    U.appendSource(sb, source);
    U.appendInt(sb, "count", count);
    U.appendInt(sb, "stride", paramList.size());
    sb.append('>');
    for (Param p : paramList) {
      p.append(sb);
    }
    sb.append("</accessor>");
  }
}
