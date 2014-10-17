package pompei.collada.data_model;

import java.io.File;
import java.io.PrintStream;

public class ColladaProbe {
  public static void main(String[] args) throws Exception {
    Collada collada = new Collada();
    
    //единичный куб, одна вершина в центре (её индекс 3 (начиная с нуля)),
    //а ребра уходят по осям в положительном направлении для каждой оси.
    PoligonalMesh cube = new PoligonalMesh("Cube");
    cube.addPoint(1, 0, 0);//0
    cube.addPoint(1, 1, 0);//1
    cube.addPoint(0, 1, 0);//2
    cube.addPoint(0, 0, 0);//3
    
    cube.addPoint(1, 0, 1);//4
    cube.addPoint(1, 1, 1);//5
    cube.addPoint(0, 1, 1);//6
    cube.addPoint(0, 0, 1);//7
    
    cube.addNormal(+1, 0, 0);//0
    cube.addNormal(-1, 0, 0);//1
    cube.addNormal(0, +1, 0);//2
    cube.addNormal(0, -1, 0);//3
    cube.addNormal(0, 0, +1);//4
    cube.addNormal(0, 0, -1);//5
    
    //Ось x идёт ко мне
    //Ось y уходит вправо
    //Ось z уходит вверх
    
    //вращение правого буравчика
    
    //нижняя   сторона: вершины (3 2 1 0) нормаль 5
    cube.addPoligon(0, 5, 3, 5, 1, 5);
    cube.addPoligon(1, 5, 3, 5, 2, 5);
    
    //верхняя  сторона: вершины (4 5 6 7) нормаль 4
    cube.addPoligon(4, 4, 5, 4, 7, 4);
    cube.addPoligon(7, 4, 5, 4, 6, 4);
    
    //передняя сторона: вершины (0 1 5 4) нормаль 0
    cube.addPoligon(0, 0, 5, 0, 4, 0);
    cube.addPoligon(0, 0, 1, 0, 5, 0);
    
    //задняя   сторона: вершины (2 3 7 6) нормаль 1
    cube.addPoligon(2, 1, 7, 1, 6, 1);
    cube.addPoligon(2, 1, 3, 1, 7, 1);
    
    //левая    сторона: вершины (0 4 7 3) нормаль 3
    cube.addPoligon(3, 3, 0, 3, 4, 3);
    cube.addPoligon(3, 3, 4, 3, 7, 3);
    
    //правая   сторона: вершины (1 2 6 5) нормаль 2
    cube.addPoligon(1, 2, 2, 2, 5, 2);
    cube.addPoligon(5, 2, 2, 2, 6, 2);
    
    collada.meshList.add(cube);
    
    Node cubeNode1 = collada.newPoligonalMeshNode("Cube_node1", cube);
    Node cubeNode2 = collada.newPoligonalMeshNode("Cube_node2", cube);
    
    {
      LinearAnimation locX = new LinearAnimation("Cube1_location_X", "X", cubeNode1, "location.X");
      for (float t = 0; t <= 4 + 0.001; t += 1f / 24) {
        locX.addKey(t, 4 * t - t * t);
      }
      collada.animationList.add(locX);
    }
    {
      LinearAnimation locX = new LinearAnimation("Cube2_location_X", "X", cubeNode2, "location.X");
      for (float t = 0; t <= 4 + 0.001; t += 1f / 24) {
        locX.addKey(t, -4 * t + t * t);
      }
      collada.animationList.add(locX);
    }
    
    StringBuilder sb = new StringBuilder();
    collada.append(sb);
    
    //    String dir = "/home/pompei/discs/linux-data/blender/projects/";
    String dir = "build/";
    
    {
      File outFile = new File(dir + "probe.dae.xml");
      PrintStream out = new PrintStream(outFile, "UTF-8");
      out.print(sb);
      out.close();
    }
    {
      File outFile = new File(dir + "probe.dae");
      PrintStream out = new PrintStream(outFile, "UTF-8");
      out.print(sb);
      out.close();
    }
    
    System.out.println("OK OK");
  }
}
