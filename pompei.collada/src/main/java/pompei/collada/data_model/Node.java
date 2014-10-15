package pompei.collada.data_model;

import pompei.collada.model.HasId;

public interface Node extends HasId {
  void append(StringBuilder sb);
}
