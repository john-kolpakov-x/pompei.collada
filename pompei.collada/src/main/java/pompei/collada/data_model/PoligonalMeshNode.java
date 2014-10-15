package pompei.collada.data_model;

public class PoligonalMeshNode implements Node {
  private final PoligonalMesh poligonalMesh;
  private final String id;
  
  PoligonalMeshNode(String id, PoligonalMesh poligonalMesh) {
    this.id = id;
    this.poligonalMesh = poligonalMesh;
  }
  
  @Override
  public void append(StringBuilder sb) {
    
    sb.append("<node id=\"").append(id).append("\" name=\"");
    sb.append(id).append("\" type=\"NODE\">");
    
    sb.append("<translate sid=\"location\">0 0 0</translate>");
    sb.append("<rotate sid=\"rotationZ\">0 0 1 0</rotate>");
    sb.append("<rotate sid=\"rotationY\">0 1 0 0</rotate>");
    sb.append("<rotate sid=\"rotationX\">1 0 0 0</rotate>");
    sb.append("<scale sid=\"scale\">1 1 1</scale>");
    
    sb.append("<instance_geometry url=\"#").append(poligonalMesh.id).append("-mesh\">");
    sb.append("</instance_geometry>");
    sb.append("</node>");
    
  }
  
  @Override
  public String getId() {
    return id;
  }
}
