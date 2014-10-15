package pompei.collada.model;

public class SourceRef {
  public final HasId ref;
  
  public SourceRef(HasId ref) {
    this.ref = ref;
  }
  
  public void appendAttr(StringBuilder sb) {
    if (ref == null) return;
    String id = ref.getId();
    if (id == null) return;
    U.appendAny(sb, "source", "#" + id);
  }
}
