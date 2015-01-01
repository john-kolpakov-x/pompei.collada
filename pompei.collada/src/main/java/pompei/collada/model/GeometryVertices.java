package pompei.collada.model;

public class GeometryVertices implements XmlAppender, HasId {
  public String id, name;
  public VerticesMesh verticesMesh;
  
  @Override
  public String getId() {
    return id;
  }
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<geometry");
    U.appendAny(sb, "id", id);
    U.appendAny(sb, "name", name);
    sb.append('>');
    U.append(sb, verticesMesh);
    sb.append("</geometry>");
  }
}
