package pompei.collada.model;

public enum ParamType {
  _float, name;
  
  @Override
  public String toString() {
    String ret = name();
    if (ret.startsWith("_")) return ret.substring(1);
    return ret;
  }
}
