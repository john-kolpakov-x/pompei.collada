package pompei.collada.model;

public class Vertices implements HasId, XmlAppender {
  public String id;
  public Input positionInput;
  
  @Override
  public String getId() {
    return id;
  }
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<vertices");
    U.appendHasId(sb, "id", this);
    sb.append('>');
    U.append(sb, positionInput);
    sb.append("</vertices>");
  }
}
