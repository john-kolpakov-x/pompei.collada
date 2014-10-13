package pompei.collada.data_model;

import java.util.ArrayList;
import java.util.List;

public class PoligonalMesh {
  
  private String id;
  
  public PoligonalMesh(String id) {
    this.id = id;
  }
  
  private final List<Float> points = new ArrayList<>();
  
  public void addPoint(float x, float y, float z) {
    points.add(x);
    points.add(y);
    points.add(z);
  }
  
  private final List<Float> normals = new ArrayList<>();
  
  public void addNormal(float x, float y, float z) {
    normals.add(x);
    normals.add(y);
    normals.add(z);
  }
  
  private final List<int[]> indexes = new ArrayList<>();
  
  public void addPoligon(int... poligon) {
    indexes.add(poligon);
  }
  
  public void appendGeometry(StringBuilder sb) {
    sb.append("<geometry id=\"").append(id).append("-mesh\" name=\"").append(id).append("\">");
    sb.append("<mesh>");
    
    appendSourcePoints(sb);
    appendSourceNormals(sb);
    appendVertices(sb);
    appendPolylist(sb);
    
    sb.append("</mesh>");
    sb.append("</geometry>");
  }
  
  private void appendPolylist(StringBuilder sb) {
    sb.append("<polylist material=\"").append(id).append("-material\" count=\"");
    sb.append(indexes.size()).append("\">");
    sb.append("<input semantic=\"VERTEX\" source=\"#").append(id).append("-mesh-vertices\"");
    if (normals.size() > 0) sb.append(" offset=\"0\"");
    sb.append(" />");
    
    if (normals.size() > 0) {
      sb.append("<input semantic=\"NORMAL\" source=\"#").append(id)
          .append("-mesh-normals\" offset=\"1\" />");
    }
    
    sb.append("<vcount>");
    for (int[] x : indexes) {
      sb.append(x.length).append(' ');
    }
    sb.append("</vcount>");
    
    sb.append("<p>");
    for (int[] xx : indexes) {
      for (int x : xx) {
        sb.append(x).append(' ');
      }
    }
    sb.append("</p>");
    
    sb.append("</polylist>");
  }
  
  private void appendVertices(StringBuilder sb) {
    sb.append("<vertices id=\"").append(id).append("-mesh-vertices\">");
    sb.append("<input semantic=\"POSITION\" source=\"#").append(id).append("-mesh-positions\" />");
    sb.append("</vertices>");
  }
  
  private void appendSourceNormals(StringBuilder sb) {
    if (normals.size() == 0) return;
    
    sb.append("<source id=\"").append(id).append("-mesh-normals\">");
    sb.append("<float_array id=\"").append(id);
    sb.append("-mesh-normals-array\" count=\"").append(normals.size()).append("\">");
    for (Float x : normals) {
      sb.append(x).append(' ');
    }
    sb.append("<technique_common>");
    sb.append("<accessor source=\"#" + id + "-mesh-normals-array\" count=\"")
        .append(normals.size() / 3).append("\" stride=\"3\">");
    sb.append("<param name=\"X\" type=\"float\" />");
    sb.append("<param name=\"Y\" type=\"float\" />");
    sb.append("<param name=\"Z\" type=\"float\" />");
    sb.append("</accessor>");
    sb.append("</technique_common>");
    sb.append("</source>");
  }
  
  private void appendSourcePoints(StringBuilder sb) {
    sb.append("<source id=\"").append(id).append("-mesh-positions\">");
    
    sb.append("<float_array id=\"").append(id);
    sb.append("-mesh-positions-array\" count=\"").append(points.size()).append("\">");
    for (Float x : points) {
      sb.append(x).append(' ');
    }
    sb.append("</float_array>");
    
    sb.append("<technique_common>");
    sb.append("<accessor source=\"#").append(id).append("-mesh-positions-array\" count=\"")
        .append(points.size() / 3).append("\" stride=\"3\">");
    sb.append("<param name=\"X\" type=\"float\" />");
    sb.append("<param name=\"Y\" type=\"float\" />");
    sb.append("<param name=\"Z\" type=\"float\" />");
    sb.append("</accessor>");
    sb.append("</technique_common>");
    
    sb.append("</source>");
  }
}
