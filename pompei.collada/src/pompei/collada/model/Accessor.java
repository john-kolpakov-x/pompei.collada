package pompei.collada.model;

import java.util.ArrayList;
import java.util.List;

public class Accessor implements XmlAppender {
  public String source;
  public int count;
  
  public final List<Param> paramList = new ArrayList<>();
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<accessor");
    if (source != null) sb.append(" source=\"").append(source).append('"');
    sb.append(" count=\"").append(count).append('"');
    sb.append(" stride=\"").append(paramList.size()).append('"');
    sb.append('>');
    for (Param p : paramList) {
      p.append(sb);
    }
    sb.append("</accessor>");
  }
}
