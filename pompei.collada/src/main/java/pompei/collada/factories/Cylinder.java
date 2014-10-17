package pompei.collada.factories;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import pompei.collada.data_model.PoligonalMesh;

public class Cylinder {
  public static PoligonalMesh createMesh(PoligonalMesh cyl, int N, float R, float z1, float z2,
      boolean closed, boolean quard) {
    int pc = cyl.pointCount();
    int nc = cyl.normalCount();
    
    double dFI = 2 * Math.PI / N;
    double dFI2 = dFI / 2;
    for (int i = 0; i < N; i++) {
      double fi = dFI * i;
      float x = (float) (R * cos(fi));
      float y = (float) (R * sin(fi));
      cyl.addPoint(x, y, z1);
      cyl.addPoint(x, y, z2);
      
      float nx = (float) cos(fi + dFI2);
      float ny = (float) sin(fi + dFI2);
      cyl.addNormal(nx, ny, 0);
      
      int j = i + 1;
      if (j >= N) j = 0;
      
      if (closed) {
        //Нижний полигон
        cyl.addPoligon(//
            pc + 2 * N, nc + N, pc + 2 * j, nc + N, pc + 2 * i, nc + N);
        //Верхний полигон
        cyl.addPoligon(//
            pc + 2 * N + 1, nc + N + 1, pc + 2 * i + 1, nc + N + 1, pc + 2 * j + 1, nc + N + 1);
      }
      
      if (quard) {
        cyl.addPoligon(pc + 2 * i, nc + i, pc + 2 * j, nc + i, //
            pc + 2 * j + 1, nc + i, pc + 2 * i + 1, nc + i);
      } else {
        cyl.addPoligon(pc + 2 * i, nc + i, pc + 2 * j + 1, nc + i, pc + 2 * i + 1, nc + i);
        cyl.addPoligon(pc + 2 * i, nc + i, pc + 2 * j, nc + i, pc + 2 * j + 1, nc + i);
      }
    }
    
    if (closed) {
      cyl.addPoint(0, 0, z1);
      cyl.addPoint(0, 0, z2);
      
      cyl.addNormal(0, 0, -1);
      cyl.addNormal(0, 0, +1);
    }
    
    return cyl;
  }
}
