package pompei.collada.model;

public class Input implements XmlAppender {
  public Semantic semantic;
  public SourceRef source;
  public Integer offset;
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<input");
    U.appendEnum(sb, "semantic", semantic);
    U.appendSource(sb, source);
    U.appendAny(sb, "offset", offset);
    sb.append("/>");
  }
}
