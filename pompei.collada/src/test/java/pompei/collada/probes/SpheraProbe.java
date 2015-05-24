package pompei.collada.probes;

import java.io.File;
import java.io.PrintStream;

import pompei.collada.data_model.Collada;
import pompei.collada.data_model.PoligonalMesh;
import pompei.collada.data_model.PoligonalMeshNode;
import pompei.collada.factories.Sphera;

public class SpheraProbe {
  public static void main(String[] args) throws Exception {
    String name = "Sphera";
    
    Collada collada = new Collada();
    
    PoligonalMesh cylinder = collada.newPoligonalMesh(name);
    
    Sphera c = new Sphera();
    c.N_ekvat = 100;
    c.N_merid = 100;
    c.R = 1;
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
