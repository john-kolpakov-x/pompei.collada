package pompei.collada.model;

public class VerticesMesh implements XmlAppender {
  
  public FloatSource positionSource, normalSource;
  public Vertices vertices;
  public Polylist polylist;
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<mesh>");
    U.append(sb, positionSource);
    U.append(sb, normalSource);
    U.append(sb, vertices);
    U.append(sb, polylist);
    sb.append("</mesh>");
  }
}
