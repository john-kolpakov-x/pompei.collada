package pompei.collada.model;

public class TechniqueAccessor implements XmlAppender {
  public final Accessor accessor;
  
  public TechniqueAccessor(Accessor accessor) {
    this.accessor = accessor;
  }
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<technique_common>");
    accessor.append(sb);
    sb.append("</technique_common>");
  }
}
