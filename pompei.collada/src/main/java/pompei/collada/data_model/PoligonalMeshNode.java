package pompei.collada.data_model;

import pompei.collada.model.U;

public class PoligonalMeshNode implements Node {
  private final PoligonalMesh poligonalMesh;
  private final String id;
  
  PoligonalMeshNode(String id, PoligonalMesh poligonalMesh) {
    this.id = id;
    this.poligonalMesh = poligonalMesh;
  }
  
  public final Vec3 location = new Vec3();
  public final Vec3 rotation = new Vec3();
  public final Vec3 scale = new Vec3(1, 1, 1);
  
  @Override
  public void append(StringBuilder sb) {
    
    sb.append("<node id=\"").append(id).append("\" name=\"");
    sb.append(id).append("\" type=\"NODE\">");
    
    sb.append("<translate sid=\"location\">" + U.toStr(location) + "</translate>");
    sb.append("<rotate sid=\"rotationZ\">0 0 1 " + rotation.z + "</rotate>");
    sb.append("<rotate sid=\"rotationY\">0 1 0 " + rotation.y + "</rotate>");
    sb.append("<rotate sid=\"rotationX\">1 0 0 " + rotation.x + "</rotate>");
    sb.append("<scale sid=\"scale\">" + U.toStr(scale) + "</scale>");
    
    sb.append("<instance_geometry url=\"#").append(poligonalMesh.id).append("-mesh\">");
    sb.append("</instance_geometry>");
    sb.append("</node>");
    
  }
  
  @Override
  public String getId() {
    return id;
  }
}
