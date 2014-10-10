package pompei.collada.model;

public class FloatSource implements XmlAppender {
  public String id;
  public FloatArray array;
  public TechniqueAccessor techniqueAccessor;
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<source");
    if (id != null) sb.append(" id=\"").append(id).append('"');
    sb.append('>');
    if (array != null) array.append(sb);
    if (techniqueAccessor != null) techniqueAccessor.append(sb);
    sb.append("</source>");
  }
}
