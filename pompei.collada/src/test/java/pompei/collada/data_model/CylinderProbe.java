package pompei.collada.data_model;

import java.io.File;
import java.io.PrintStream;

import pompei.collada.factories.Cylinder;

public class CylinderProbe {
  public static void main(String[] args) throws Exception {
    String name = "Cylinder";
    
    Collada collada = new Collada();
    
    PoligonalMesh cylinder = collada.newPoligonalMesh(name);
    
    Cylinder.createMesh(cylinder, 4, 1, -1, +1, false);
    
    collada.newPoligonalMeshNode("Cylinder_node", cylinder);
    
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
    
    System.out.println("OK Cylinder");
  }
}
