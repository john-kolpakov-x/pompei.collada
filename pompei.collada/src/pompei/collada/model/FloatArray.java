package pompei.collada.model;

import java.util.ArrayList;
import java.util.List;

public class FloatArray implements XmlAppender {
  public String id;
  public final List<Float> data = new ArrayList<>();
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<float_array");
    if (id != null) sb.append(" id=\"").append(id).append('"');
    sb.append("count=\"").append(data.size()).append('"');
    sb.append('>');
    for (Float value : data) {
      if (value != null) sb.append(value).append(' ');
    }
    sb.append("</float_array>");
  }
}
