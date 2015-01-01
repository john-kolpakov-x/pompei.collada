package pompei.collada.probes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pompei.collada.data_model.Vec3;

public class Setka3DCreator {
  public class Point {
    int index;
    final String id;
    final Vec3 value;
    
    public Point(String id, float x, float y, float z) {
      this.id = id;
      value = new Vec3(x, y, z);
    }
  }
  
  public void addPoint(String id, float x, float y, float z) {
    if (pointMap.containsKey(id)) {
      throw new IllegalArgumentException("Point with id = " + id + " already exists");
    }
    pointMap.put(id, new Point(id, x, y, z));
  }
  
  public final Map<String, Point> pointMap = new HashMap<>();
  
  public class Face {
    int index;
    final String id;
    final Point[] points;
    final Object type;
    Cell from, to;
    
    public Face(String id, String pointIds[], Object type) {
      this.id = id;
      this.type = type;
      points = new Point[pointIds.length];
      for (int i = 0, C = pointIds.length; i < C; i++) {
        Point point = pointMap.get(pointIds[i]);
        if (point == null) throw new IllegalArgumentException("No point with id = " + pointIds[i]);
        points[i] = point;
      }
    }
    
    public boolean isRightGimlet(Vec3 from) {
      Vec3 p1 = points[0].value;
      Vec3 p2 = points[1].value;
      Vec3 p3 = points[2].value;
      
      Vec3 a = p1.minus(from);
      Vec3 b = p2.minus(from);
      Vec3 c = p3.minus(from);
      
      return a.mul(b, c) >= 0;
    }
    
    public int fromIndex() {
      return from.index;
    }
    
    public int toIndex() {
      return to.index;
    }
    
    public int[] pointIndexes() {
      final int[] ret = new int[points.length];
      for (int i = 0, C = points.length; i < C; i++) {
        ret[i] = points[i].index;
      }
      return ret;
    }
  }
  
  public final Map<String, Face> faceMap = new HashMap<>();
  
  public void addFace(String id, String pointIds[], Object type) {
    if (faceMap.containsKey(id)) {
      throw new IllegalArgumentException("Face with id = " + id + " already exists");
    }
    faceMap.put(id, new Face(id, pointIds, type));
  }
  
  public class Cell {
    int index;
    final String id;
    final Face faces[];
    
    public Cell(String id, String faceIds[]) {
      this.id = id;
      faces = new Face[faceIds.length];
      for (int i = 0, C = faceIds.length; i < C; i++) {
        Face face = faceMap.get(faceIds[i]);
        if (face == null) throw new IllegalArgumentException("No face with id = " + faceIds[i]);
        faces[i] = face;
      }
    }
    
    public Vec3 center() {
      final Vec3 ret = new Vec3(0, 0, 0);
      int N = 0;
      for (Face face : faces) {
        for (Point point : face.points) {
          N++;
          ret.add(point.value);
        }
      }
      return ret.divMe(N);
    }
    
    public int[] faceIndexes() {
      final int[] ret = new int[faces.length];
      for (int i = 0, C = faces.length; i < C; i++) {
        ret[i] = faces[i].index;
      }
      return ret;
    }
  }
  
  public final Map<String, Cell> cellMap = new HashMap<>();
  
  public void addCell(String id, String faceIds[]) {
    if (cellMap.containsKey(id)) {
      throw new IllegalArgumentException("Cell with id = " + id + " already exists");
    }
    cellMap.put(id, new Cell(id, faceIds));
  }
  
  public void create(Setka3D dest) {
    
    setFaceFromTo();
    
    final List<Point> pointList = new ArrayList<>();
    {
      pointList.addAll(pointMap.values());
      int pointFromIndex = dest.pointList.size();
      for (int i = 0, C = pointList.size(); i < C; i++) {
        pointList.get(i).index = pointFromIndex + i;
      }
    }
    
    for (Point point : pointList) {
      dest.pointList.add(point.value);
    }
    
    final List<Face> faceList = new ArrayList<>();
    {
      faceList.addAll(faceMap.values());
      int faceFromIndex = dest.faceList.size();
      for (int i = 0, C = faceList.size(); i < C; i++) {
        faceList.get(i).index = faceFromIndex + i;
      }
    }
    
    final List<Cell> cellList = new ArrayList<>();
    {
      cellList.addAll(cellMap.values());
      int cellFromIndex = dest.cellList.size();
      for (int i = 0, C = cellList.size(); i < C; i++) {
        cellList.get(i).index = cellFromIndex + i;
      }
    }
    
    for (Face face : faceList) {
      dest.addFace(face.fromIndex(), face.toIndex(), face.pointIndexes(), face.type);
    }
    
    for (Cell cell : cellList) {
      dest.addCell(cell.faceIndexes());
    }
    
  }
  
  private void setFaceFromTo() {
    for (Cell cell : cellMap.values()) {
      Vec3 center = cell.center();
      for (Face face : cell.faces) {
        if (face.isRightGimlet(center)) {
          face.from = cell;
        } else {
          face.to = cell;
        }
      }
    }
    
    for (Face face : faceMap.values()) {
      if (face.from == null) throw new RuntimeException("Face " + face.id + " has from == null");
      if (face.to == null) throw new RuntimeException("Face " + face.id + " has to == null");
    }
  }
}
