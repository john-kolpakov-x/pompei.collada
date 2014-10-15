package pompei.collada.data_model;

import java.util.ArrayList;
import java.util.List;

public class LinearAnimation {
  private final List<Float> timeList = new ArrayList<>();
  private final List<Float> valueList = new ArrayList<>();
  private final String id;
  private final String target;
  private final String valueName;
  
  public void addKey(float time, float value) {
    timeList.add(time);
    valueList.add(value);
  }
  
  public LinearAnimation(String id, String valueName, String target) {
    this.id = id;
    this.valueName = valueName;
    this.target = target;
  }
  
  public void append(StringBuilder sb) {
    if (timeList.size() == 0) return;
    if (timeList.size() != valueList.size()) throw new IllegalStateException("Different sizes");
    
    sb.append("<animation id=\"").append(id).append("\">");
    appendSourceInput(sb);
    appendSourceOutput(sb);
    appendSourceInterpolation(sb);
    appendSampler(sb);
    appendChannel(sb);
    sb.append("</animation>");
  }
  
  private void appendChannel(StringBuilder sb) {
    sb.append("<channel source=\"#").append(id).append("-sampler\" target=\"").append(target)
        .append("\"/>");
  }
  
  private void appendSampler(StringBuilder sb) {
    sb.append("<sampler id=\"").append(id).append("-sampler\">");
    sb.append("<input semantic=\"INPUT\" source=\"#").append(id).append("-input\"/>");
    sb.append("<input semantic=\"OUTPUT\" source=\"#").append(id).append("-output\"/>");
    sb.append("<input semantic=\"INTERPOLATION\" source=\"#").append(id)
        .append("-interpolation\"/>");
    sb.append("</sampler>");
  }
  
  private void appendSourceInterpolation(StringBuilder sb) {
    int size = timeList.size();
    sb.append("<source id=\"").append(id).append("-interpolation\">");
    sb.append("<Name_array id=\"").append(id).append("-interpolation-array\" count=\"")
        .append(size).append("\">");
    for (int i = 0; i < size; i++) {
      sb.append("LINEAR ");
    }
    sb.append("</Name_array>");
    
    sb.append("<technique_common>");
    sb.append("<accessor source=\"#").append(id).append("-interpolation-array\" count=\"")
        .append(size).append("\" stride=\"1\">");
    sb.append("<param name=\"INTERPOLATION\" type=\"name\"/>");
    sb.append("</technique_common>");
    
    sb.append("</source>");
  }
  
  private void appendSourceOutput(StringBuilder sb) {
    int size = valueList.size();
    
    sb.append("<source id=\"").append(id).append("-output\">");
    sb.append("<float_array id=\"").append(id).append("-output-array\" count=\"").append(size)
        .append("\">");
    appendList(sb, valueList);
    sb.append("</float_array>");
    
    sb.append("<technique_common>");
    sb.append("<accessor source=\"#").append(id).append("-output-array\" count=\"").append(size)
        .append("\" stride=\"1\">");
    sb.append("<param name=\"").append(valueName).append("\" type=\"float\"/>");
    sb.append("</technique_common>");
    
    sb.append("</source>");
  }
  
  private void appendSourceInput(StringBuilder sb) {
    int size = timeList.size();
    
    sb.append("<source id=\"").append(id).append("-input\">");
    sb.append("<float_array id=\"").append(id).append("-input-array\" count=\"").append(size)
        .append("\">");
    appendList(sb, timeList);
    sb.append("</float_array>");
    
    sb.append("<technique_common>");
    sb.append("<accessor source=\"#").append(id).append("-input-array\" count=\"").append(size)
        .append("\" stride=\"1\">");
    sb.append("<param name=\"TIME\" type=\"float\"/>");
    sb.append("</technique_common>");
    
    sb.append("</source>");
  }
  
  private void appendList(StringBuilder sb, List<Float> list) {
    for (Float x : list) {
      sb.append(x).append(' ');
    }
  }
}
