package pompei.collada.data_model;

import java.util.ArrayList;
import java.util.List;

public class Collada {
  public final List<PoligonalMesh> meshList = new ArrayList<>();
  public final List<LinearAnimation> animationList = new ArrayList<>();
  
  public void append(StringBuilder sb) {
    appendHead(sb);
    appendLibraryGeometries(sb);
    appendLibraryAnimations(sb);
    appendLibraryVisualScenes(sb);
    appendActiveScene(sb);
    sb.append("</COLLADA>");
  }
  
  private void appendActiveScene(StringBuilder sb) {
    sb.append("<scene>");
    sb.append("<instance_visual_scene url=\"#Scene\"/>");
    sb.append("</scene>");
  }
  
  private void appendLibraryVisualScenes(StringBuilder sb) {
    sb.append("<library_visual_scenes>");
    sb.append("<visual_scene id=\"Scene\" name=\"Scene\">");
    for (PoligonalMesh x : meshList) {
      x.appendNode(sb);
    }
    sb.append("</visual_scene>");
    sb.append("</library_visual_scenes>");
  }
  
  private void appendLibraryAnimations(StringBuilder sb) {
    sb.append("<library_animations>");
    for (LinearAnimation x : animationList) {
      x.append(sb);
    }
    sb.append("</library_animations>");
  }
  
  private void appendLibraryGeometries(StringBuilder sb) {
    sb.append("<library_geometries>");
    for (PoligonalMesh x : meshList) {
      x.appendGeometry(sb);
    }
    sb.append("</library_geometries>");
  }
  
  private void appendHead(StringBuilder sb) {
    sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    sb.append("<COLLADA xmlns=\"http://www.collada.org/2005/11/COLLADASchema\" version=\"1.4.1\">");
  }
}
