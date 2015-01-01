package pompei.collada.probes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pompei.collada.data_model.Collada;
import pompei.collada.data_model.PoligonalMesh;
import pompei.collada.data_model.Vec3;
import pompei.collada.probes.Setka3D.Cell;
import pompei.collada.probes.Setka3D.Face;

public class Exporter {
  public static void move(Setka3D from, Collada to) {
    int i = 0;
    for (Cell cell : from.cellList) {
      appendNode(from, cell, to, "cell" + i);
    }
  }
  
  private static void appendNode(Setka3D owner, Cell cell, Collada to, String name) {
    
    int newIndex = 0;
    for (int faceIndex : cell.faceIndexes) {
      final Map<Integer, Integer> pointAssoc = new HashMap<>();
      Face face = owner.faceList.get(faceIndex);
      int p1 = -1, p2 = -1;
      int pp1 = -1, pp2 = -1;
      PoligonalMesh mesh = to.newPoligonalMesh(name);
      to.newPoligonalMeshNode(name + "_node", mesh);
      int normalIndex = 0;
      For1: for (int pointIndex : face.pointIndexes) {
        if (!pointAssoc.containsKey(pointIndex)) {
          pointAssoc.put(pointIndex, newIndex++);
        }
        int p = pointAssoc.get(pointIndex);
        if (p1 < 0) {
          p1 = p;
          pp1 = pointIndex;
          continue For1;
        }
        if (p2 < 0) {
          p2 = p;
          pp2 = pointIndex;
          continue For1;
        }
        Vec3 P1 = owner.pointList.get(pp1);
        Vec3 P2 = owner.pointList.get(pp2);
        Vec3 P3 = owner.pointList.get(pointIndex);
        Vec3 n = calcNormal(P1, P2, P3);
        mesh.addNormal(n.x, n.y, n.z);
        int nIndex = normalIndex++;
        mesh.addPoligon(p1, p2, p, nIndex, nIndex, nIndex);
        
        p2 = p;
        pp2 = pointIndex;
      }
      
      final Map<Integer, Integer> pointRevAssoc = new HashMap<>();
      for (Entry<Integer, Integer> e : pointAssoc.entrySet()) {
        pointRevAssoc.put(e.getValue(), e.getKey());
      }
      for (int idx = 0, C = pointRevAssoc.size(); idx < C; idx++) {
        Vec3 p = owner.pointList.get(pointRevAssoc.get(idx));
        mesh.addPoint(p.x, p.y, p.z);
      }
    }
  }
  
  private static Vec3 calcNormal(Vec3 p1, Vec3 p2, Vec3 p3) {
    Vec3 a = p2.minus(p1);
    Vec3 b = p3.minus(p1);
    Vec3 n = a.vmul(b);
    n.divMe(n.len());
    return n;
  }
}
