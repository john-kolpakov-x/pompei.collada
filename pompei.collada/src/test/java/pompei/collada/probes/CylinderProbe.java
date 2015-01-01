package pompei.collada.probes;

import java.io.File;
import java.io.PrintStream;

import pompei.collada.data_model.Collada;
import pompei.collada.data_model.PoligonalMesh;
import pompei.collada.data_model.PoligonalMeshNode;
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
    
    c.appendToMesh(cylinder);
    
    {
      PoligonalMeshNode node = collada.newPoligonalMeshNode(name + "_node1", cylinder);
      node.location.set(1, 1, 1);
    }
    {
      PoligonalMeshNode node = collada.newPoligonalMeshNode(name + "_node2", cylinder);
      node.location.set(-1, -1, 1);
    }
    {
      PoligonalMeshNode node = collada.newPoligonalMeshNode(name + "_node3", cylinder);
      node.location.set(+1, -1, 1);
    }
    {
      PoligonalMeshNode node = collada.newPoligonalMeshNode(name + "_node4", cylinder);
      node.location.set(-1, +1, 1);
    }
    
    StringBuilder sb = new StringBuilder();
    collada.append(sb);
    
    {
      File outFile = new File("build/" + name + ".dae.xml");
      PrintStream out = new PrintStream(outFile, "UTF-8");
      out.print(sb);
      out.close();
    }
    {
      File outFile = new File("/home/pompei/tmp/" + name + ".dae");
      PrintStream out = new PrintStream(outFile, "UTF-8");
      out.print(sb);
      out.close();
    }
    
    System.out.println("OK " + name);
  }
}
