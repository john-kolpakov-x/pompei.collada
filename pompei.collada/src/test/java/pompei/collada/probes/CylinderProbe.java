package pompei.collada.probes;

import java.io.File;
import java.io.PrintStream;

import pompei.collada.data_model.Collada;
import pompei.collada.data_model.PoligonalMesh;
import pompei.collada.factories.Cylinder;

public class CylinderProbe {
  public static void main(String[] args) throws Exception {
    String name = "Cylinder";
    
    Collada collada = new Collada();
    
    PoligonalMesh cylinder = collada.newPoligonalMesh(name);
    
    Cylinder c = new Cylinder();
    c.N = 40;
    c.R = 1;
    c.z1 = -1;
    c.z2 = +1;
    c.closed = true;
    c.quard = true;
    
    c.appendMesh(cylinder);
    
    collada.newPoligonalMeshNode(name + "_node", cylinder);
    
    StringBuilder sb = new StringBuilder();
    collada.append(sb);
    
    //    String dir = "/home/pompei/discs/linux-data/blender/projects/";
    String dir = "build/";
    
    {
      File outFile = new File(dir + name + ".dae.xml");
      PrintStream out = new PrintStream(outFile, "UTF-8");
      out.print(sb);
      out.close();
    }
    {
      File outFile = new File(dir + name + ".dae");
      PrintStream out = new PrintStream(outFile, "UTF-8");
      out.print(sb);
      out.close();
    }
    
    System.out.println("OK " + name);
  }
}
