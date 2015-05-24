package pompei.collada.factories;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import pompei.collada.data_model.PoligonalMesh;

public class Sphera {
  /**
   * Радиус
   */
  public float R;
  
  /**
   * Количество сегментов разбиения экватора
   */
  public int N_ekvat;
  
  /**
   * Количество сегментов разбиеня половины любого меридиана
   * <p>
   * Если чётное - то по экватору идёт многоугольник, принадлежащий полученному
   * многорганнику; иначе - экватор будет вылезать за полученный многогранник
   * </p>
   */
  public int N_merid;
  
  /**
   * Использовать квадраны (true) или только треугольники (false)
   */
  public boolean quard;
  
  private static final double PI_2 = PI / 2;
  
  public PoligonalMesh appendToMesh(PoligonalMesh m) {
    if (N_ekvat < 3) {
      throw new IllegalArgumentException("N_ekvat = " + N_ekvat
          + "; but must be more or equal to 3");
    }
    if (N_merid < 2) {
      throw new IllegalArgumentException("N_merid = " + N_merid
          + "; but must be more or equal to 2");
    }
    
    int pc = m.pointCount();
    int nc = m.normalCount();
    
    final double PI_N_merid = PI / N_merid;
    final double _2_PI_N_ekvat = 2 * PI / N_ekvat;
    
    {
      
      final double PI_N_merid2 = PI_N_merid / 2;
      final double _2_PI_N_ekvat2 = _2_PI_N_ekvat / 2;
      
      for (int j = 0, Cj = N_merid; j < Cj; j++) {
        double fi = -PI_2 + j * PI_N_merid + PI_N_merid2;
        for (int i = 0, Ci = N_ekvat; i < Ci; i++) {
          double lambda = _2_PI_N_ekvat * i + _2_PI_N_ekvat2;
          
          double nx = cos(fi) * cos(lambda);
          double ny = cos(fi) * sin(lambda);
          double nz = sin(fi);
          
          m.addNormal((float)nx, (float)ny, (float)nz);
        }
      }
    }
    
    {
      m.addPoint(0, 0, -R);
      m.addPoint(0, 0, +R);
      for (int j = 0, C = N_merid - 1; j < C; j++) {
        double fi = -PI_2 + (j + 1) * PI_N_merid;
        for (int i = 0; i < N_ekvat; i++) {
          double lambda = _2_PI_N_ekvat * i;
          
          double x = R * cos(fi) * cos(lambda);
          double y = R * cos(fi) * sin(lambda);
          double z = R * sin(fi);
          
          m.addPoint((float)x, (float)y, (float)z);
        }
      }
    }
    
    {
      
      for (int i1 = 0, Ci = N_ekvat; i1 < Ci; i1++) {
        int i2 = i1 + 1;
        if (i2 >= Ci) i2 = 0;
        
        //@formatter:off
        m.addPoligon(
          pc + 0,      nc + i1,
          pc + i2 + 2, nc + i1,
          pc + i1 + 2, nc + i1
        );
        //@formatter:on
        
        for (int j1 = 0, Cj = N_merid - 2; j1 < Cj; j1++) {
          int j2 = j1 + 1;
          
          int _11 = pc + i1 + j1 * Ci + 2;
          int _12 = pc + i2 + j1 * Ci + 2;
          int _21 = pc + i1 + j2 * Ci + 2;
          int _22 = pc + i2 + j2 * Ci + 2;
          int n = nc + i1 + j1 * Ci + Ci;
          
          if (quard) {
            m.addPoligon(_11, n, _12, n, _22, n, _21, n);
          } else {
            m.addPoligon(_11, n, _12, n, _22, n);
            m.addPoligon(_11, n, _22, n, _21, n);
          }
        }
        
        //@formatter:off
        m.addPoligon(
          pc + i1 + 2 + N_ekvat*(N_merid - 2), nc + i1  + N_ekvat*(N_merid - 1),
          pc + i2 + 2 + N_ekvat*(N_merid - 2), nc + i1  + N_ekvat*(N_merid - 1),
          pc + 1                             , nc + i1  + N_ekvat*(N_merid - 1)
        );
        //@formatter:on
        
      }
      
    }
    
    return m;
  }
}
