package pompei.collada.model;

public class FloatSource implements XmlAppender, HasId {
  public String id;
  public FloatArray array;
  public TechniqueAccessor techniqueAccessor;
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<source");
    U.appendAny(sb, "id", id);
    sb.append('>');
    U.append(sb, array);
    U.append(sb, techniqueAccessor);
    sb.append("</source>");
  }
  
  @Override
  public String getId() {
    return id;
  }
}
