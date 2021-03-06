package pompei.collada.model;

import java.util.ArrayList;
import java.util.List;

public class FloatArray implements XmlAppender, HasId {
  public String id;
  public final List<Float> data = new ArrayList<>();
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<float_array");
    U.appendAny(sb, "id", id);
    U.appendInt(sb, "count", data.size());
    sb.append('>');
    for (Float value : data) {
      if (value != null) sb.append(value).append(' ');
    }
    sb.append("</float_array>");
  }
  
  @Override
  public String getId() {
    return id;
  }
}
