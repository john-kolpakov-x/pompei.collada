package pompei.collada.model;

public class Param implements XmlAppender {
  public static final Param FLOAT_X = new Param(ParamType._float, "X");
  public static final Param FLOAT_Y = new Param(ParamType._float, "Y");
  public static final Param FLOAT_Z = new Param(ParamType._float, "Z");
  
  public static final Param FLOAT_TIME = new Param(ParamType._float, "TIME");
  
  public static final Param NAME_INTERPOLATION = new Param(ParamType.name, "INTERPOLATION");
  
  public final ParamType type;
  public final String name;
  
  public Param(ParamType type, String name) {
    this.type = type;
    this.name = name;
  }
  
  @Override
  public void append(StringBuilder sb) {
    sb.append("<param");
    U.appendAny(sb, "name", name);
    U.appendAny(sb, "type", type);
    sb.append("/>");
  }
}
