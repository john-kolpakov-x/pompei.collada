package pompei.collada.model;

import pompei.collada.data_model.Vec3;

public class U {
  
  public static void appendAny(StringBuilder sb, String key, Object value) {
    if (value == null) return;
    sb.append(" ").append(key).append("=\"").append(value).append('"');
  }
  
  @SuppressWarnings("rawtypes")
  public static void appendEnum(StringBuilder sb, String key, Enum value) {
    if (value == null) return;
    sb.append(" ").append(key).append("=\"").append(value.name()).append('"');
  }
  
  public static void appendInt(StringBuilder sb, String key, int value) {
    sb.append(" ").append(key).append("=\"").append(value).append('"');
  }
  
  public static void appendSource(StringBuilder sb, SourceRef source) {
    if (source == null) return;
    source.appendAttr(sb);
  }
  
  public static void append(StringBuilder sb, XmlAppender appender) {
    if (appender == null) return;
    appender.append(sb);
  }
  
  public static void appendHasId(StringBuilder sb, String key, HasId hasId) {
    if (hasId == null) return;
    String id = hasId.getId();
    if (id == null) return;
    sb.append(' ').append(key).append("=\"").append(id).append('"');
  }
  
  public static String toStr(Vec3 vec) {
    if (vec == null) return "";
    return vec.x + " " + vec.y + " " + vec.z;
  }
}
