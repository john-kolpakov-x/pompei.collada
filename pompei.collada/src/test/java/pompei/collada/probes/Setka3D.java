package pompei.collada.probes;

import java.util.ArrayList;
import java.util.List;

import pompei.collada.data_model.Vec3;

public class Setka3D {
  public final List<Vec3> pointList = new ArrayList<>();
  
  public class Face {
    final int from, to;
    final int pointIndexes[];
    final Object type;
    
    public Face(int from, int to, int pointIndexes[], Object type) {
      this.from = from;
      this.to = to;
      this.pointIndexes = pointIndexes;
      this.type = type;
    }
  }
  
  public final List<Face> faceList = new ArrayList<>();
  
  public class Cell {
    final int faceIndexes[];
    
    public Cell(int faceIndexes[]) {
      this.faceIndexes = faceIndexes;
    }
  }
  
  public final List<Cell> cellList = new ArrayList<>();
  
  public void addFace(int from, int to, int pointIndexes[], Object type) {
    faceList.add(new Face(from, to, pointIndexes, type));
  }
  
  public void addCell(int faceIndexes[]) {
    cellList.add(new Cell(faceIndexes));
  }
  
}
